# Lesson 4 - Listviews, Adapters, Debugging, and Butterknife

**The assignment(s) in this README are due on at 6:30pm on Monday, Sept 19.**

## ListViews
### Concepts Behind ListViews
In order to display a scrollable list of something in Android, you will need to use a `ListView`.  A `ListView` is what you see when you scroll through a list of clickable things in an app, like your friends list in Snapchat or your list of chats in Messenger.

Unfortunately, this is not as simple as just making a `ListView` and attaching an `ArrayList` to it.  You also need an `Adapter`.  There are three levels of displaying scrollable and clickable data in Android: the data source, the `Adapter`, and the `ListView`.

- **Data Source**: This is the actual variable that stores all your data, usually an `ArrayList`.
- **Adapter**: This interprets the data source and tells the `ListView` what each row of the list should look like.
- **ListView**: This handles displaying the full list of rows and having the logic for any onClick listeners.

In our Snapchat example, the data source would be an `ArrayList` of strings of our Snapchat friends' names.  The `Adapter` would handle displaying the name in a `TextView`, and also displaying the little icon to indicate whether you have an unread snap from them.  The `ListView` would handle displaying all your friends and making it scrollable when there's not enough room on the screen for the whole list.  It would also handle the logic for displaying your snap when you click on a friend with an unread snap, or replying when you double-click on a friend.

Note that an `Adapter` can be used for other things too, not just for a `ListView`. An `Adapter` acts as a bridge between the `[something]View` and data that populates that view by providing access to the data. If you're interested about learning more about Adapters, check out [this Google I/O talk](https://www.youtube.com/watch?v=N6YdwzAvwOA).

For simple lists where you just want to display a list of strings in a `TextView`, you can use Google's built-in `ArrayAdapter`.  If you want to make a more complex display, such as including a picture or a button on each row, then you will need to create your own custom `Adapter`.  Just like you made a layout .xml file when creating the view for your fragment, you can make a layout xml file for your `Adapter` to position where each component goes in the row.  For the built-in `ArrayAdapter` you can pass in Google's built-in `simple_list_item_1` as your layout file, which fills the row with a `TextView`.

### Code

Now that you know the concept behind making a `ListView` work, let's learn how to code it!  The following is largely taken from [this guide](https://github.com/codepath/android_guides/wiki/Using-an-ArrayAdapter-with-ListView), if you want more detail you can go read the full guide.

#### Basics
In Android development, any time we want to show a vertical list of scrollable items we will use a `ListView` which has data populated using an `Adapter`. The simplest adapter to use is called an `ArrayAdapter` because the adapter converts an `ArrayList` of objects into `View` items loaded into the `ListView` container.

<img src="https://i.imgur.com/mk82Jd2.jpg" width="600" />

The `ArrayAdapter` fits in between an `ArrayList` (data source) and the `ListView` (visual representation) and configures two aspects:

 * Which array to use as the data source for the list
 * How to convert any given item in the array into a corresponding View object

Note as shown above that there are other data sources besides an `ArrayAdapter` such as the [CursorAdapter](https://github.com/codepath/android_guides/wiki/Populating-a-ListView-with-a-CursorAdapter) which instead binds directly to a result set from a [Local SQLite Database](https://github.com/codepath/android_guides/wiki/Local-Databases-with-SQLiteOpenHelper).

#### Using a Basic ArrayAdapter

To use a basic `ArrayAdapter`, you just need to initialize the adapter and attach the adapter to the ListView. First, we initialize the adapter:

```java
ArrayAdapter<String> itemsAdapter = 
    new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
```

The `ArrayAdapter` requires a declaration of the type of the item to be converted to a `View` (a `String` in this case) and then accepts three arguments: `context` (activity instance), XML item layout, and the array of data. Note that we've chosen [simple_list_item_1.xml](https://github.com/android/platform_frameworks_base/blob/master/core/res/res/layout/simple_list_item_1.xml) which is a simple `TextView` as the layout for each of the items.

Now, we just need to connect this adapter to a `ListView` to be populated:

```java
ListView listView = (ListView) findViewById(R.id.lvItems);
listView.setAdapter(itemsAdapter);
```

By default, this will now convert each item in the data array into a view by calling `toString` on the item and then assigning the result as the value of a `TextView` ([simple_list_item_1.xml](https://github.com/android/platform_frameworks_base/blob/master/core/res/res/layout/simple_list_item_1.xml)) that is displayed as the row for that data item. If the app requires a more complex translation between item and `View` then we need to create a custom `ArrayAdapter` instead. 

#### Using a Custom ArrayAdapter

When we want to display a series of items into a list using a custom representation of the items, we need to use our own custom XML layout for each item. To do this, we need to create our own custom `ArrayAdapter` class. See [this repo for the source code](https://github.com/codepath/android-custom-array-adapter-demo). First, we often need to define a model to represent the data within each list item.

##### Defining the Model

Given a Java object that has certain fields defined such as a `User` class:

```java
public class User {
    public String name;
    public String hometown;

    public User(String name, String hometown) {
       this.name = name;
       this.hometown = hometown;
    }
}
```

We can create a custom `ListView` of `User` objects by subclassing `ArrayAdapter` to describe how to translate the object into a view within that class and then using it like any other adapter.

##### Creating the View Template

Next, we need to create an XML layout that represents the view template for each item in `res/layout/item_user.xml`:

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
 android:layout_width="match_parent"
 android:layout_height="match_parent" >
    <TextView
      android:id="@+id/tvName"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="Name" />
   <TextView
      android:id="@+id/tvHome"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:text="HomeTown" />
</LinearLayout>
```

##### Defining the Adapter

Next, we need to define the adapter to describe the process of converting the Java object to a View (in the `getView` method). The naive approach to this (without any view caching) looks like the following:

```java
public class UsersAdapter extends ArrayAdapter<User> {
    public UsersAdapter(Context context, ArrayList<User> users) {
       super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // Get the data item for this position
       User user = getItem(position);    
       // Check if an existing view is being reused, otherwise inflate the view
       if (convertView == null) {
          convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user, parent, false);
       }
       // Lookup view for data population
       TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
       TextView tvHome = (TextView) convertView.findViewById(R.id.tvHome);
       // Populate the data into the template view using the data object
       tvName.setText(user.name);
       tvHome.setText(user.hometown);
       // Return the completed view to render on screen
       return convertView;
   }
}
```

That adapter has a constructor and a `getView()` method to describe the **translation between the data item and the View** to display.  `getView()` is the method that returns the actual view used as a row within the `ListView` at a particular position. Another method used is `getItem()` which is already present in the `ArrayAdapter` Class and its task is to simply get the data item associated with the specified `position` in the data set which is associated with that `ArrayAdapter`.

#### Attaching the Adapter to a ListView

Now, we can use that adapter in the Activity to display an array of items into the ListView:

```java
// Construct the data source
ArrayList<User> arrayOfUsers = new ArrayList<User>();
// Create the adapter to convert the array to views
UsersAdapter adapter = new UsersAdapter(this, arrayOfUsers);
// Attach the adapter to a ListView
ListView listView = (ListView) findViewById(R.id.lvItems);
listView.setAdapter(adapter);
```

At this point, the ListView is now successfully bound to the users array data.

#### Populating Data into ListView

Once the adapter is attached, items will automatically be populated into the ListView based on the contents of the array. You can add new items to the adapter at any time with:

```java
// Add item to adapter
User newUser = new User("David", "Lametown");
adapter.add(newUser);
// Or even append an entire new collection
// Fetching some data, data has now returned
// If data was JSON, convert to ArrayList of User objects.
JSONArray jsonArray = ...;
ArrayList<User> newUsers = User.fromJson(jsonArray)
adapter.addAll(newUsers);
```

which will append the new items to the list. You can also clear the entire list at any time with:

```java
adapter.clear();
```

Using the adapter now, you can add, remove and modify users and the items within the ListView will automatically reflect any changes.

## Debugging
Android Studio has a built-in debugger that allows you to set breakpoints in your code and see the values of variables during runtime.  This is extremely useful when debugging your code to see exactly what is going on at every line.

[This official Android guide](https://developer.android.com/studio/debug/index.html) covers all the functionality of the AS debugger.  We'll go into the basics here.

To make breakpoints, just click to the left of any line in your code.  To run your app in debugging mode, click the little bug icon next to the normal "Run" button.  Select your phone, and your app will get installed and run as normal.  If you hit a line with a breakpoint while using your app, the program will freeze and the debug window will pop up.  At each line before your breakpoint, variables and their values will be displayed.  You can mouse over specific variables or boolean expressions to see their values.

Using the buttons on the top of the debug window, you can do a couple useful things:

- Step Over: go to the next line of code without entering a method at the current line 
- Step Into: go to the first line of the inside method called at the current line 
- Step Out: go to the next line outside the current method 
- Resume Program: continue the program normally (or advance to the next breakpoint if you have other breakpoints)

If you want more information about debugging, [this guide](http://blog.strv.com/debugging-in-android-studio-as/) goes into more detail and is easy to read.

## Butterknife

By now you're probably tired of using lines of code like this:

```
editText = (EditText) view.findViewById(R.id.edit_text_id);
```

over and over again. Turns out, there's a library called [Butterknife](http://jakewharton.github.io/butterknife/) that will bind your UI elements to variables for you! Here's how to use it.

### Dependencies

First, add `classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'` to your `dependencies` in your project level `build.gradle` (It should say "build.gradle (Project:MyApplication)" in Android Studio).

Here's an example of a `build.gradle` with the `apt` plugin:

```
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.1.3'
        // HERE IS THE IMPORTANT LINE YOU HAVE TO ADD
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        jcenter()
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

Open your module level gradle (build.gradle (Module: app)) and add `apply plugin: 'android-apt'` to the top. Finally, add the two butterknife dependencies to `dependencies`:

```
dependencies {
  compile 'com.jakewharton:butterknife:8.4.0'
  apt 'com.jakewharton:butterknife-compiler:8.4.0'
}
```

Here's the final module level gradle file:

```
apply plugin: 'com.android.application'
apply plugin: 'android-apt'

android {
    compileSdkVersion 24
    buildToolsVersion "24.0.2"

    defaultConfig {
        applicationId "com.example.david.myapplication"
        minSdkVersion 15
        targetSdkVersion 24
        versionCode 1
        versionName "1.0"
    }
    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android.txt'), 'proguard-rules.pro'
        }
    }
}

dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:24.2.1'
    compile 'com.android.support:design:24.2.1'

    // HERE ARE THE BUTTERKNIFE IMPORTS
    compile 'com.jakewharton:butterknife:8.4.0'
    apt 'com.jakewharton:butterknife-compiler:8.4.0'
}
```

### Usage

Now, instead of using `view.findViewById(...)`, place `Bind` statements at the top of your class (right after the `public class MyFragment extends Fragment`):

```
public class MainActivityFragment extends Fragment {
    @BindView(R.id.text_view_id) TextView textView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
}
```

Finally, you have to explicitly bind ButterKnife to your fragment in your `onCreateView` using `ButterKnife.bind`:

```

public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.bind(this, view);
    return view;
}

```

Here's the final ButterKnife'd fragment:

```
public class MainActivityFragment extends Fragment {
    @BindView(R.id.text_view_id) TextView textView;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
```

The `textView` variable can now be used throughout your class. For example, you can edit its text in the `onCreateView`:

```
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.bind(this, view);
    textView.setText("This is some text");
    return view;
}
```

## Assignment
Before next class, you're going to modify your todo app from Lesson 2 to use what you learned today.  Requirements:
- A way to add new todo items.
- A display of all the current todo items.
- A way to delete items when the user completes them.
- A way to edit an item.
- Use a custom `Adapter`. You could make an icon that changes when the item is completed, or make each item have an inline edit/complete button, but make something custom that goes beyond an `ArrayAdapter`.
- Uses ButterKnife instead of `findViewById` to declare variables.

Stretch goals (optional):
- Have each item have a description that displays when you click on it, maybe in an `AlertDialog` or a different fragment.

The details of how to implement this are up to you.

Fill out [this submission form](https://docs.google.com/forms/d/e/1FAIpQLSf9mVD3P3hRbISzums4vCkl1wcFWjO-Nc86lhvMo_9LYjKbWQ/viewform) when you're done!
