# Lesson 7 - APIs

### What's an API anyway?
An API stands for an application program interface. Think of it as a way for one application to easily communicate with another. For example, maybe you have a blog and want it to show your Twitter feed on the side. In that case, you'd need to use Twitter's API to provide a way for your blog to access Twitter's data. This example is specific to web APIs, but you'll be using a lot of Android APIs in order to make an app (e.g., an API for your app to communicate with the camera). The Android platform has a framework API that apps use to interact with the underlying Android system.

### Android APIs
Android has a *ton* of APIs built in; they're useful for using hardware sensors, accessing storage, handling user input, and setting configuration info. However, you'll have to be careful with which version of Android you're using as APIs will differ depending on that.

Check the sidebar in [this link](https://developer.android.com/about/versions/marshmallow/android-6.0.html) out to look at the different APIs for versions 4.1 to 6.0. [This link](https://developer.android.com/reference/android/package-summary.html) provides you with a detailed list of every Android-built API available. There are way too many to list so we won't go through them in detail, but if you've seen your phone do it, you'll find how to do it there.

## HTTP requests
*Note: this portion was constructed borrowing heavily from [these](https://developer.android.com/training/volley/simple.html) Android docs. Feel free to check them out for more information*

Many websites or companies offer RESTful APIs (application program interfaces) for other developers to use. REST (REpresentational State Transfer) APIs are APIs that allow you to access them using the HTTP verbs you may already be familiar with, such as `GET`, and `POST` (`GET` google.com would give you the google webpage, while something like `POST` google.com/login/yourinfo would send data to google). For your future labs and project, you may want to incorporate functionality with Twitter, Spotify, or Instagram, all of which have a REST API available.

### Step 1: Dependencies

The Android framework offers a specific protocol for making http requests. Android provides the HTTP library `Volley`. Add the following to your Module gradle under `dependencies`:

```
dependencies {
    ...
    ...
    compile 'com.android.volley:volley:1.0.0'
}
```

and add `<uses-permission android:name="android.permission.INTERNET" />` to your app's manifest, right above the `application` tag.

### Step 2: Set up a `Singeton` and a `RequestQueue`

You use Volley by creating a `RequestQueue` and passing it `Request` objects. The Queue handles making all of your requests for you, and therefore you should only have one. The Queue will make all your requests and receive their responses.

If you are constantly making requests in your app (for example, you make multiple requests to different APIs when your app loads), you will want to use a single instance of a `RequestQueue` that lasts the lifetime of your app. The way you do this is with a singleton class.

The `RequestQueue` must be instantiated in your Application as opposed to your Activity. This is so the queue does not get recreated when the activity is (such as when the device is rotated).

Here's the code for a singleton class:

```java
public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mCtx;

    private MySingleton(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized MySingleton getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new MySingleton(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
```

### Step 3: Build your search URL

When making REST calls, you make requests to a certain URL. They should look identical to website URLs. Sometimes you must pass additional information in the URL. For example, in the homework you will make a request to a URL that looks like this:

```
http://finance.google.com/finance/info?client=iq&q=aapl
```

This makes a querry to `http://finance.google.com/finance/info`, and passes it the following parameters:

```
{
    client: iq
    q: aapl
}
```

These parameters will allow us to recieve the stock prices for Apple and Microsoft. However, manually building your search URL (`http://finance.google.com/finance/info?client=iq&q=aapl`) is a real pain. Luckily, there is library that simplifies this process called `URI Builder`. Check out [this](http://stackoverflow.com/questions/19167954/use-uri-builder-in-android-or-create-url-with-variables) stackoverflow for some example code.

### Step 4: Make a request

You're now ready to make a request! First, you create a request, and then you pass it to the `RequestQueue`:

```java
String myUrl = "http://finance.google.com/finance/info?client=iq&q=aapl";

// Request a string response from the provided URL.
StringRequest stringRequest = new StringRequest(Request.Method.GET, myUrl,
        new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Handle your response here
            }
        }, new Response.ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
    }
});

// Add a request (in this example, called stringRequest) to your RequestQueue.
MySingleton.getInstance(this.getActivity()).addToRequestQueue(stringRequest);
```

### Step 5: Parse the response and update your `Listener`

You can also request your data come back in JSON form (which is basically just nested dictionaries and lists, if you are familiar with Python). Volley provides two classes: `JsonObjectRequest` and `JsonArrayRequest` to request objects and arrays ([docs](https://developer.android.com/training/volley/request.html)). These objects are also a lot easier to parse than a String.

You can also just parse the `String` into a `JSON` on your own. That's what we will do in this lesson. Let's say your `response` looked something like this:

```
{
    "Cats": 3,
    "Dogs": 4
}
```

and you wanted to get the number of Dogs and put it in a `TextView`. You could make your `Response.Listener` code look like this:

```java
...
new Response.Listener<String>() {
    @Override
    public void onResponse(String response) {
        try {
            JSONObject j = new JSONObject(response);
            String s = j.getInt("Dogs") + "";  // "4"
            textView.setText(s);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
```

#### Threading

**You don't have to worry too much about the contents of the Threading section. However if you want to learn, feel free to read this. Otherwise, go ahead and start the homework.**

A thread is a single sequence of instructions your computer/phone executes. Your Android application contains a "main thread", which is where all of the code you write gets executed. If one of your lines is really slow, ie, `Thread.sleep(5000);` it will cause your app to hang, as the "main thread" is busy waiting for the `sleep` to end.

Volley always delivers responses on the main thread. This allows you to update UI elements directly in the response listener. Ie, you make an HTTP request to Twitter to search for a tweet, and once a response comes in, you can directly update a TextView with the contents of the tweet.

Programs run one line at a time. If you have a single expression that is slow, it can cause your app to lag. This is generally not an issue, most statements (like `int i = 1 + 1`) are *really* fast. However, you don't know how fast/slow an API will respond. If your program waited for APIs to respond this would be problematic.

One more important aspect of Volley: **you don't have to specify for requests to be done [asynchronously](http://stackoverflow.com/questions/3393751/what-does-asynchronous-means-in-ajax)!** Volley handles all requests on a separate thread and deals with multi-threading for you, and returns all responses to the main thread. Rather than waiting for a request to execute code, the response listener gets called whenever the API responds. Additionally, you can put several requests in the queue and they will all be made one after another, without waiting for each to respond before making the next request.

## Assignment

Create an application that allows the user to enter in the stock ticket for a company, and display the current price for the company. How you display them is up to you. We have included a simple UI with some boiler plate code that has an `EditText`, a `TextView`, and a button. We've also included some starter code and scaffolding. You can use this as a starting point, or start from scratch. If you choose to use our starter code, make sure you poke around `MainActivityFragment` and understand all of the functions you will have to implement before you begin.

We recommend using Google's stock API. Here's an example of a url that gives you Apple's current prices:

```
http://finance.google.com/finance/info?client=iq&q=aapl
```

Try pasting that URL into your web browser to see what the response looks like! The stock price is in the key-value pair who's key is `l` (that's a lowercase L).

**Hint:** You're going to want to take off the `"// "` from the beginning of the response using [substring](https://www.tutorialspoint.com/java/java_string_substring.htm). You can then parse the response into a `JSONArray` and then iterate over it like this:

```java
JSONArray json = new JSONArray(mySubstring);
String p = extractPriceFromJSON(json); // You should define this function
```

### Stretch goal

Allow the user to input multiple stocks. You can either to a single api call and input multiple stocks:

```
http://finance.google.com/finance/info?client=iq&q=aapl,msft
```

or you can add multiple calls to your `RequestQueue`.

One way you could do it is with a custom `ListView` and `Adapter`, where each element has a search box on the left and displays the price on the right.
