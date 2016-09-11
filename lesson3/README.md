# Lesson 3 - Android Basics
### Your first Android application!
Today in class we'll walk you through making your first Android application. Found in the lesson3 folder is a copy of the application that we will create in class.

## Create a Blank App
To begin with, we'll create a default app and walk you through the different components.

1. Create a new project in Android Studio
2. Name it something akin to "Lesson3HW" and save it in your forked repo
3. Click "Basic Activity"
4. Check "Use a Fragment"
5. Click done
6. Click the little green play arrow and run your app

Great, you just created your very first app!

## Explore Around
Android Studio just created a bunch of files and boilerplate code to get you started. Before we write any code, let's explore the different files that were just created.

There are generally three different types of files that you'll need to know about in Android Studio:

1. **Java files** are where your logic goes.
2. **XML Layout files** are where you define the frontend layout of your screens.
3. **Configuration files** are used by Android Studio to configure your app.

You'll be writing most of your code in Java files.
### 1. Java Files
When you interact with an app on your phone, there are different "screens" that you can go to. Snapchat, for example has a "friends list" screen, a "picture-taking" screen, a "settings" screen, and so on.  The settings screen displays different things and needs completely different logic than the picture-taking screen.  Android separates these "screens" into different files called **activities** and **fragments**.

#### [Activities](https://developer.android.com/guide/components/activities.html) (ex. MainActivity.java)

 An activity provides a screen that users can interact with in order to do something, such as dial the phone, take a photo, send an email, or view a map. Each activity is given a window in which to draw its UI. The window typically fills the screen, but may be smaller than the screen and float on top of other windows.
 
 Each activity can then start another activity in order to perform different actions. You generally have the main activity which launches other activities (or, your application could just be a single activity). Each time a new activity starts, the previous activity is stopped, but the system preserves the activity in a stack (the "back stack"). When a new activity starts, it is pushed onto the back stack and takes user focus.
 
 Note that an activity can hold multiple "fragments," which is very useful. The activity handles passing information between fragments and lets you switch between different fragments.

#### [Fragments](https://developer.android.com/guide/components/fragments.html) (ex. MainActivityFragment.java)
Fragments are basically what we described as "screens" in our Snapchat example. Each fragment does a separate task and shows different components to the user. If you were making Snapchat, you might have a fragment for each swipeable screen. You could even [nest fragments within fragments](https://developer.android.com/about/versions/android-4.2.html#NestedFragments) if you'd like! Fragments are useful for:

1. Modularity: dividing complex activity code across fragments for better organization and maintenance.
2. Reusability: placing behavior or UI parts into fragments that can be shared across multiple activities.
3. Adaptability: representing sections of a UI as different fragments and utilizing different layouts depending on screen orientation and size.

![alt text][fragment]

However, with the Snapchat example, you could also have each swipeable screen be another activity. Now, this sounds confusing. Why have single fragments that you switch through in one activity instead of just having multiple activities? Well, the biggest reason is for when you're designing an app to be used on both phones and tablets.
 
![alt text][activityfrag]

Alongside activities and fragments are other java files you can write, like classes, tests, or interfaces.

[fragment]: https://cdn2.raywenderlich.com/wp-content/uploads/2015/10/android_fragments_d001_why_fragments.png "i like fragments"
[activityfrag]: https://camo.githubusercontent.com/b768afff0888fcb8cbe1704b0609b53110276969/687474703a2f2f646576656c6f7065722e616e64726f69642e636f6d2f696d616765732f66756e64616d656e74616c732f667261676d656e74732e706e67 "see how useful fragments are?"
### 2. [Layout XML](https://developer.android.com/guide/topics/ui/declaring-layout.html) files (ex. fragment_main.xml)
XML files are basically the CSS/HTML of the Android world. They define where each component goes in your activities and fragments. Each activity and fragment will have its own XML file that it can communicate with.

#### strings.xml and colors.xml
Android coding conventions dictate that every user-facing string should be stored in strings.xml instead of hardcoded.  This is so every string in your app can be easily translated in one file, and makes changing strings that repeat a lot easier. Similarly, you have colors.xml which defines the colors you use in your other XML files.

### 3. Configuration Files
#### [Manifest](https://developer.android.com/guide/topics/manifest/manifest-intro.html) (AndroidManifest.xml)
The Manifest file defines settings about your app and what permissions it needs.  Whenever you download an app from the Play Store, it will show you what permissions the app needs (like internet, data storage, GPS location) so that users can be knowledgable about what their apps are using. These permissions are taken from the app's manifest file.

#### [Gradle file](https://developer.android.com/studio/build/index.html) (build.grade (Module: app))
Gradle is the thing that builds your app, like a compiler. Each time you launch your app to your phone to test it, gradle will run and compile your code.  This is also where you can import any libraries you may need.

## Android Studio Overview
Android Studio (AS) is an IDE, not a text editor. This means that AS is very smart and you can use it to navigate and write code quickly.  Here's an overview of some of our favorite features:

* **Auto-saving**: No need to ever press the save button, AS saves as soon as you type.
* **Auto-compiling**: AS does some compiling in real-time, which means you'll know immediately if you've misspelled a variable or didn't pass the right parameters to a function.
* **Smart Autocomplete**: AS will only make autocomplete suggestions that are legal and make sense in the context of the code.
* **Git integration**: AS can be set up to show you what lines of code you've changed since your last commit, and how exactly you've changed them.
* **Documentation**: Pressing CTRL+Q will give you info for a class or method that you’ve highlighted, if you see something and don’t know what it is.

### Layout of Android Studio
If you don't see the project view pane on the left, press `Alt+1` to make it appear.  Here you can see the directory structure of the different files in your app.  The java files are in `app > java > [DOMAIN]` and the xml layout files are in `app > res > layout`.  Double-clicking on files opens them, and you can have multiple tabs of files open.

When you have a layout .xml file open, AS gives you two different ways to edit the layout.  At the bottom you should see a "Design" tab and a "Text" tab.  The Design tab gives you a drag-and-drop GUI to easily add components onto the screen. The pane on the left shows you all the possible components that you can add, and you can drag them onto the screen to place them where you want.  If you want more manual fine-tuning you can click on the Text tab, where you can edit the raw xml that produces the layout.

When you want to run your app, press the green play icon in the top toolbar.  A window will appear, where you can select to run an emulator or use a physical phone.  If you plugged in your android phone and enabled usb debugging mode, you should see your phone pop up in this list.  Select it, press OK, and your app should appear on your phone after it installs.

## Let's get to work!

1. Go to your XML for this fragment
2. Drag a button onto the view or type it in and suggest-complete
3. Give it an id
4. Go to the fragment java file
5. Assign that button as a variable
  * Note that the XML file does not make the button - it only describes the design of the button. You declare the button in the fragment/activity by using `findViewById()`, then assign the XML id to your button to describe the look of the button. XML is necessary if you want your button to actually appear on screen. This is true many other elements as well, not just buttons
6. Make an `onClickListener`
7. Have the button log something in android studio

## Extra Resources
* [Android Developer guide](https://developer.android.com/develop/index.html) <-- you should 100% be using this

## Assignment
#### Part 1: Understanding high level design
Draw on the whiteboards a diagram of how a currently existing social media app might use fragments and activities. Get this checked off by a member of the teaching team or a NINJA.

#### Part 2: Building on top of the app we showed you
Your goal is to start making a quasi-to-do list app. We'll refer to it as the Simple To-do List app.

1. This app will have two fragments and one activity. One fragments holds the to-do list, while the other fragment holds the settings page. The activity will have a button that lets you switch between fragments.
2. The to-do list fragment should have five editable `textView`s. Add `onClick` listeners to them so that when you click on a to-do list item, an `alertDialog` will popup and ask for new text. The text the user inputs into the `alertDialog` will then replace the old text in the `textView`. Use a type of `layout` to organize the `textView`s. Don't worry if your app doesn't save the items you changed in your list when you switch fragments.
3. The settings fragment will allow you to change the app's background color. Add three buttons (any type of 'button' will do) that change the app's background color. For example, have one blue, one red, and one green button.

**When you've completed your assignment, [fill out this survey](https://goo.gl/forms/8yPXBdtwBQlXICur2).**
