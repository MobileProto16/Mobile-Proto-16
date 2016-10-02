# Lab 1 - Restaurant App

Your goal for this lab is to make an app for a restaurant to manage incoming orders from customers.

You will have 1.5 weeks to complete this lab. **Lab 1 is due at 6:30pm on Thursday, October 13th.** This lab will be done in groups of either two or three. There should be two groups of three.

The following are the minimum requirements for this lab. **You must also add at least one additional feature (minimum one, possibly more!) as well.**

## MVP

Your app should have two “users”, cooks and customers.

### Cooks

- There should be a “Menu” with food items such as Hamburgers, Pizza, etc.
- Each food item should have an associated ingredients list. (ie, Pizza -> dough, sauce, cheese)
- Cooks can add/edit/delete food items from the Menu.
- Cooks can add/edit/delete ingredients from each menu item’s ingredients list.
- Cooks can view “orders”, which each have an associated customer and food item(s).
- Cooks can mark orders as completed.

### Customers

- Customers can view the menu and make orders by selecting one or more menu items, and the quantities of each item (i.e., 2 burgers, 4 pizzas, etc.).
- Customers should be able to specify their name when placing an order.

Your app’s data should be persistent on your phone. This means you should store everything in either an S3 or SQL database so that menu items/orders/ingredients are saved when you exit the app.

## Possible Additions (feel free to come up with your own, these are just suggestions)

- Include pictures of menu items (allow a cook to use their phone’s camera to add images of menu items)
- Customers can customize menu items (Customers can see the ingredients list for menu items, and select certain items to include/not include)
- Use an external SQL database (so that it syncs across multiple phones)
- Store your app’s data in [Amazon S3](http://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/s3transferutility.html) or [firebase](https://www.firebase.com/docs/android/quickstart.html). This will make it persistent across phones as well!
- Have a simple login-authentication (prompt cooks/customers for a username, and maybe password. Have a login/add new user feature)
- Have a login-authetication with an API like [facebook](https://developers.facebook.com/docs/facebook-login/android) or [google](https://developers.google.com/api-client-library/javascript/features/authentication).

You can also come up with your own features to add to the app!

## Proposal
Before the end of today’s class (Mon Oct 3) each team will turn in a proposal. Include the following information:

* The names of everyone in your group
* The additional feature(s) you plan on implementing
* The name of the git repo `username/RepoNamo` you will be using
* A quick write up on how you plan to divide up work and integrate features.
* Where you plan to be in a week (Mon Oct 10) We will be doing informal check-ins to monitor your progress.

Put your proposal in your repository as `Proposal.md`

## Deliverables
We require these three things to be turned in:
* Your proposal (Mon Oct 3)
* Wireframes (these can be drawn by hand or done using software) showing what screens/fragments your app will have, and how users will navigate your app. (Thu Oct 7)
* Your app (Thu Oct 13)

Submit your app [here]()!

## Lab Rubric(200 points total):
#### Proposal (10 points)
* 10 - You did the proposal and a teaching team member checked it off
* 0 - You did not do the proposal by the end of class

#### Wireframes (30 points)
* 30 - You created wireframes for all fragments/screens of your app. You created a rough design of what your app will look like. You clearly outlined the workflow of your app (fragment transitions, how users navigate the app, where the user enters/receives information)
* 15 - Information flow of your app is not clear from wireframes and/or you did not make an effort to design the aesthetics of your app.
* 0 - You did not submit your wireframes.

### Final Deliverable (160 points)
#### Functionality: 70 points
##### Completion: 60 points
* 60 - You implemented all of the required features in the assignment. The assignment is complete
* 45 - All major features were implemented. You didn't get to one or two small features.
* 30 - Some of the required features were implemented.
* 15 - An attempt was made to implement some of the of the desired features.
* 0 - Very few or none of the desired features were implemented.

##### Bug free: 10 points
* 10 - The app is bug free
* 5 - Your app has one or two bugs, occasionally causing unexpected behavior
* 0 - Your app has multiple bugs, frequently causing unexpected behavior.


#### Design/Usability: 15 points
* 15 - Fragments are designed well, it is intuitive how to interact with the app.  User-facing strings are descriptive. The app is for the most part aesthetically appealing.
* 10 - A small part of the app was unintuitive to interact with or it was a bit ugly.
* 5 - Fragment transitions and/or information flow were unintuitive. It is difficult for a user to do what they want with your app.
* 0 - Your app was unintuitive to the point of being unusable.

#### Quality: 75 points

##### Git practices: 20 pts

* 20 - Separate branches were used for distinct features, master branch was kept “clean” and functional. Code was peer reviewed with pull requests before being merged.
* 10 - Not all code was reviewed before being merged. Each branch did not have a distinct purpose/feature.
* 0 - You frequently committed to master. You didn’t use/barely used branches.

##### Good coding practices: 20 pts

* 20 - Objects are intelligently designed and used where appropriate, inheritance used when appropriate, code is concise, naming conventions followed, proper access modifiers are used, public getters/setters are used instead of public variables, etc.
* 10 - Some of the above practices were broken, but you mostly followed good practices.
* 0 - Good coding practices were consistently broken.

##### Readability: 20 pts
* 20 - Functions and variables are named well. Code is well commented where appropriate. Confusing lines are commented. Lines are not too long.
* 10 - Code is occasionally confusing. Could use more comments. Some variables are poorly named.
* 0 - Code is difficult to read. Other developers would not be able to use your code.

##### Even work distribution: 15 pts
* 15 - You divided work fairly. Everyone had a significant contribution to your app.
* 10 - One person clearly did more/less than their fair share, but everyone was involved.
* 5 - Some people’s contributions were minimal
* 0 - One person did the whole app
