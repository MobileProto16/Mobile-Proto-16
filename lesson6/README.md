# Lesson 6 - How to Design an App

##  Designing an app

Here is an example process for desigining an app:

#### 1. Observe
When you look at any simple or complex Android app, there's something to learn from both. While using it, ask questions. Some examples of questions could be:
  1. What does the logo/name communicate to the users?
  2. Are all the features easily discoverable?
  3. In order to perform an action, how many times did you tap? Too many popups asking you to choose YES or NO?
  4. How does the color scheme fit the overall feel?
  5. How easy are certain buttons to tap? Are they used often?

Read through the [Android Design Principles](https://developer.android.com/design/get-started/principles.html). It will help you think of more questions to ask.

#### 2. Design
Asking questions while looking at an app can be incredibly helpful while trying to design something. Once you have an idea what your app is going to be about, first make a rough draft. Concentrate on the features you'll be providing and the top-level navigation of the app.

Draw the screens on paper (prototyping software like fluidui work too, or even MSPaint/Photoshop). Test these mockups on your friends. It is recommended that you let your friends first explore the mockups without any instructions and observe what they see, think and do. Later, you can ask more specific questions about the features in your mockup.

Next step is to integrate whatever feedback you received from user testing into your app's design. At this stage, you can also decide on the color scheme of the app, logo, name etc. You could do cycles of user testing, then feedback integration, then testing again until you are satisfied with the app.

Apps like Facebook Messenger or Snapchat go through many cycles of usability testing. It's important to make sure your app remains sleek and intuitive when you're adding new features or editing existing features.

#### 3. Testing after developing:
Once you have a Minimum Viable Product (MVP) of your app, test it again.
  1. Try to break it
      * Rotate the device. Do all elements adjust well?
      * Tap at random places
      * Try devices of different screen sizes.
  2. Get a fresh pair of eyes
  3. Test your code for edge cases and weird events 
  4. Try different hardware

## App Elements
Note: The documentation for all the app elements listed below are very good. We tried to summarize them so that you get an idea of each element. When you use them in your app, the developer's website is the best place to learn more about them.

![Layout](https://material-design.storage.googleapis.com/publish/material_v_9/0Bx4BSt6jniD7T0hfM01sSmRyTG8/layout_structure_regions_mobile.png)

#### Layouts
While writing the XML files for your app, you might have noticed there are numerous ways to layout and display the content. [This](https://developer.android.com/guide/topics/ui/declaring-layout.html) **[HW]** document explains layouts really well and talks about margins, and padding. Go read it!

The two common layouts used in XML are:
  1. [Linear Layout](https://developer.android.com/guide/topics/ui/layout/linear.html)
The elements in a linear layout are stacked one after another, either horizontally or vertically. You get to specify the orientation. 

  2. [Relative Layout](https://developer.android.com/guide/topics/ui/layout/relative.html)
As the name suggests, all the elements are displayed relative to either other children or the parent view. It is more powerful as it eliminates the need for nested Linear Layouts.

#### [App bar](https://developer.android.com/training/appbar/index.html)
![AppBar](https://developer.android.com/images/training/appbar/appbar_sheets.png)

Almost every app you use will have an app bar because it provides a visual structure and interactive elements that are familiar to users. The key functions of the app bar are as follows:
  * A dedicated space for giving your app an identity and indicating the user's location in the app.
  * Access to important actions in a predictable way, such as search.
  * Support for navigation and view switching (with tabs or drop-down lists).

#### [Settings](https://developer.android.com/guide/topics/ui/settings.html)
Remember how one of the design principles said "Let me make it mine"? We want to provide them with good defaults but always provide easy ways to change those defaults. Settings page just does that.

If you want to learn about how to design a Settings page, [read this guide](https://material.google.com/patterns/settings.html#settings-usage).

#### [Pop-up messages](https://developer.android.com/training/snackbar/index.html)
Whenever you need to provide the users with a prominent enough message, use pop-up messages. Most of the time, the user doesn't have to respond to the message. Also, the message disappears after a short while. The pop-up message should not block the users from using the app.

![Pop-up message with undo action](https://developer.android.com/images/training/snackbar/snackbar_undo_action.png "Pop-up message with undo action")

#### [Toasts](https://developer.android.com/guide/topics/ui/notifiers/toasts.html)
A toast provides simple feedback about an operation in a small popup. It only fills the amount of space required for the message and the current activity remains visible and interactive.

![Toasts](https://developer.android.com/images/toast.png)

There are many more UI elements available to your app than the few mentioned above. So, go explore the developer's page and the links listed below under "Resources".

### Resources:
Skim through the resources' content listed below. Whenever you start working on an app, you can come back to these and use the relevant information.

  1. [Best Practices for User Interface](https://developer.android.com/training/best-ui.html)
  2. [User Interface Guide](https://developer.android.com/guide/topics/ui/index.html)
  3. [Material Design](https://material.google.com/)
  4. [Android Design Principles](https://developer.android.com/design/get-started/principles.html)
  5. [Material Design apps, icons, etc](https://material.uplabs.com/)

### Try It Out!
 
**[HW]** 
  1. Download Facebook app on an Android phone (if you don't have access to either a Facebook account or an Android phone, let the teaching team know).
  2. Observe and ask questions about the design intention. The design principles will help you here. Write up the questions and the corresponding findings.
  3. You might have thoughts about a different layout. Prototype on paper. Include your prototype's picture (and its revisions) in your write-up. You could also use one of the programs (like Fluid UI) to make the prototypes.
  4. Recreate your layout(s) in Android Studio by writing XML file(s) for them.
  
Fill out [this survey](https://goo.gl/forms/LLUhDfZzBkkqawtp1) after you finish the HW
