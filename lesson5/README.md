# Lesson 5 - Data Storage App Life Cycle

**The homework in this README is due at 6:30pm on Monday, Sept 26**


##  Data Storage
Everytime you close an app and reopen it, a new session of app is created. This means that any data that wasn't saved cannot be retrieved again. This is where persistent storage comes in. Android provides you with 5-6 options to choose from. While choosing an option, you should consider complexity of data being stored, privacy (if it can be read by the app only, the users or other apps on the device), and location (if it is local or on the cloud). We'll be mainly discussing Shared Preferences and SQLite databases. But here are many of the options available to Android:

1. [Shared Preferences](https://developer.android.com/guide/topics/data/data-storage.html#pref):

   Useful for writing small amount of data as key-value pairs.
2. [Firebase](https://www.firebase.com/docs/android/quickstart.html):

   A cool way to store data, run tests and analytics, authenticate, and monetize. The quickstart page linked here gives you a very good idea about how to use Firebase.
3. [Internal Storage](https://developer.android.com/guide/topics/data/data-storage.html#filesInternal):

   This memory chunk is private by default and is only available to the app. This data gets deleted when the app is uninstalled from the Android device. You can use methods like `openFileInput()`, `read()`, and `close()` on a [FileOutputStream](https://developer.android.com/reference/java/io/FileOutputStream.html)
4. [External Storage](https://developer.android.com/guide/topics/data/data-storage.html#filesExternal):

   The data stored here is world-readable but the data itself can't be relied upon as the user can unmount the storage anytime they want. Be sure to handle in the code the case where the data is unavailable.
   ```java
    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    
    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
            Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
   ```
5. [Cache](https://developer.android.com/reference/android/content/Context.html#getCacheDir()):

   If you have data that aren't important enough to be stored safely (i.e. if deleted, the user experience won't get affected), use cache. Remember that cache isn't reliable as it gets deleteled unreliably and you can't store large amounts or data (>1MB) without causing problems. Also, be responsible about how you use the cache memory. Clean the space you occupied after you are done.
6. [SQLite databases]():

   Think of SQL as a giant table of rows and columns where you can name the columns. You can also ask the table for certain values within those columns. You also have the ability to build complex queries that ask for multiple things within multiple columns. You can create multiple SQL databases for your app and all of them will be available to all parts of your app but won't be available to anything outside the app. SQL databases are also stored locally.

### Shared Preferences
Imagine an app which lets the users set the background color and the user sets it to red. The expected behaviour for the app should be to set the background color to red everytime the app opens even if the source code says the default color is white. It would be pretty bad if the user is made to set the color EVERY SINGLE TIME they close and reopen the app. Small things similar to this can be saved in Shared Preferences. Only condition is that the data being stored has to be of primitive type (like booleans, floats, ints, longs, and strings).

The way you store data is using key-value pairs. There are two ways to create/access a SharedPreferences file.

1. [`getSharedPreferences()`](https://developer.android.com/reference/android/content/Context.html#getSharedPreferences(java.lang.String, int)): You can create and access multiple shared preferences file. You can access it by calling the method with the name of the file using the activity.
    ```java
    SharedPreferences sharedPref = getActivity().getSharedPreferences(
            getString(R.string.preference_file_key), Context.MODE_PRIVATE);
    ```

2. [`getPreferences()`](https://developer.android.com/reference/android/app/Activity.html#getPreferences(int)): Here you are pretty much doing the same thing except you don't need to mention the file name.
    ```java
    SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
    ```

So, whenever a session of the app gets created (inside `onCreate()`), you should read the shared preference file and when the app gets closed (inside `onStop()`), changes made (for ex. the backgroud color is changed to blue) should be written to the file.

```java
// To read
SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
// Default value for when the file is unavilable
String defaultValue = getResources().getString(R.string.saved_background_default);
String background = sharedPref.getString(getString(R.string.saved_background), defaultValue);

// To write
SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
SharedPreferences.Editor editor = sharedPref.edit();
editor.putString(getString(R.string.saved_background), currentBackground);
editor.commit();
```
While writing, notice that we gave it the key (`getString(R.string.saved_background)`) and the corresponding value (`currentBackground`).

### SQL

While implementing a SQLite database in Android, you almost always need to make a schema. The schema basically tells the database what columns are needed. This [page](https://developer.android.com/training/basics/data-storage/databases.html) has all the information you might need to add a SQLite database to your app. 

## App Life Cycle

![](https://developer.android.com/images/training/basics/basic-lifecycle.png)

When you create an new project in Android Studio, you will see a lot of boilerplate. If you are new to Android dev, you probably wouldn't have gone beyond using `onCreate()` and `onDestroy()`. But as you advance, you will realize the need for other methods. Other that the create and destroy methods, we'll use `onPause()` and `onResume`.

Read through each of the lessons listed in [this page](https://developer.android.com/training/basics/activity-lifecycle/index.html).

Below are some important excerpts from each lesson.

1. _**Starting An Activity**_:

   **Resumed**: In this state, the activity is in the foreground and the user can interact with it. (Also sometimes referred to as the "running" state.)

   **Paused**: In this state, the activity is partially obscured by another activity—the other activity that's in the foreground is semi-transparent or doesn't cover the entire screen. The paused activity does not receive user input and cannot execute any code.

   **Stopped**: In this state, the activity is completely hidden and not visible to the user; it is considered to be in the background. While stopped, the activity instance and all its state information such as member variables is retained, but it cannot execute any code.

2. _**Pausing and Resuming an Activity**_:
   
   **Pausing**: Generally, you should not use `onPause()` to store user changes (such as personal information entered into a form) to permanent storage. The only time you should persist user changes to permanent storage within `onPause()` is when you're certain users expect the changes to be auto-saved (such as when drafting an email). However, you should avoid performing CPU-intensive work during `onPause()`, such as writing to a database, because it can slow the visible transition to the next activity (you should instead perform heavy-load shutdown operations during `onStop()`).

   **Resuming**: Be aware that the system calls this method every time your activity comes into the foreground, including when it's created for the first time. As such, you should implement `onResume()` to initialize components that you release during `onPause()` and perform any other initializations that must occur each time the activity enters the Resumed state (such as begin animations and initialize components only used while the activity has user focus).
   
3. _**Stopping and Restarting an Activity**_:
   
   **Stopping**: When your activity receives a call to the `onStop()` method, it's no longer visible and should release almost all resources that aren't needed while the user is not using it. Once your activity is stopped, the system might destroy the instance if it needs to recover system memory. In extreme cases, the system might simply kill your app process without calling the activity's final onDestroy() callback, so it's important you use `onStop()` to release resources that might leak memory. Although the `onPause()` method is called before `onStop()`, you should use `onStop()` to perform larger, more CPU intensive shut-down operations, such as writing information to a database.

   **Restarting**: When your activity comes back to the foreground from the stopped state, it receives a call to onRestart(). The system also calls the `onStart()` method, which happens every time your activity becomes visible (whether being restarted or created for the first time). The `onRestart()` method, however, is called only when the activity resumes from the stopped state, so you can use it to perform special restoration work that might be necessary only if the activity was previously stopped, but not destroyed. It's uncommon that an app needs to use `onRestart()` to restore the activity's state, so there aren't any guidelines for this method that apply to the general population of apps. However, because your `onStop()` method should essentially clean up all your activity's resources, you'll need to re-instantiate them when the activity restarts. Yet, you also need to instantiate them when your activity is created for the first time (when there's no existing instance of the activity). For this reason, you should usually use the `onStart()` callback method as the counterpart to the `onStop()` method, because the system calls `onStart()` both when it creates your activity and when it restarts the activity from the stopped state.
   
4. _**Recreating an Activity**_:
   
   By default, the system uses the **Bundle** instance state to save information about each **View** object in your activity layout (such as the text value entered into an `EditText` object). So, if your activity instance is destroyed and recreated, the state of the layout is restored to its previous state with no code required by you. However, your activity might have more state information that you'd like to restore, such as member variables that track the user's progress in the activity.

   To save additional data about the activity state, you must override the `onSaveInstanceState()` callback method. The system calls this method when the user is leaving your activity and passes it the **Bundle** object that will be saved in the event that your activity is destroyed unexpectedly. If the system must recreate the activity instance later, it passes the same **Bundle** object to both the `onRestoreInstanceState()` and `onCreate()` methods.

## Homework

We'll be using two kinds of storage to save the data in our Todo app. All the *Settings* should be saved using SharedPreferences. Use a SQL database to save the todos as well as their status. Use the correct methods of activity life cycle to figure out where to save and retrive those preferences. Test your app by pausing, resuming, quitting and restarting the app.

When you're done, fill in [this](https://goo.gl/forms/8fSPitpJ9BiCzY812) google form!

**The homework in this README is due at 6:30pm on Monday, Sept 26**
