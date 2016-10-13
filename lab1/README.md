# Lab 1 - Restaurant App

Your goal for this lab is to make an app for a restaurant to manage incoming orders from customers.

You will have 1.5 weeks to complete this lab. **Lab 1 is due at 6:30pm on Thursday, October 13th.** This lab will be done in groups of either two or three. There should be two groups of three.

[Here](./RUBRIC.md) is the rubric.

## Proposal
Before the end of today’s class (Mon Oct 3) each team will turn in a proposal. Include the following information:

* The names of everyone in your group
* The additional feature(s) you plan on implementing
* The name of the git repo `username/RepoNamo` you will be using (you should make a new one)
* A quick write up on how you plan to divide up work and integrate features.
* Where you plan to be in a week (Mon Oct 10) We will be doing informal check-ins to monitor your progress.

After you've been approved by a teacher, put your proposal in your repository as `Proposal.md` and fill out [this survey](https://goo.gl/forms/PVisEoErxKZNqjz53).

## Wireframes + Design Explanation

On Thur Oct 6 you will submit wireframes for your app. These can be drawn by hand or using software. Your wireframes should show all screens your app will have, and how your users will navigate/use the app. For each screen, write 2-3 sentences explaining:

- The purpose of the screen
- What the user can do in that screen/what all the buttons/elements on the screen will do.
- How the user can get to/away from the screen.

Put all of your wireframes + your explanations in a single `.pdf` file and upload it to your repo as `Design.pdf`.

The wireframes are worth 30 points.

## MVP

Your app should have two “users”, cooks and customers. The following are the minimum requirements for this lab. **You must also add at least one additional feature (minimum one, possibly more!) as well.**

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

Your app’s data should be persistent on your phone. This means you should store everything in a database so that menu items/orders/ingredients are saved when you exit the app.

## Possible Additions (feel free to come up with your own, these are just suggestions)

- Include pictures of menu items (allow a cook to use their phone’s camera to add images of menu items)
- Customers can customize menu items (Customers can see the ingredients list for menu items, and select certain items to include/not include)
- Use an external SQL database (so that it syncs across multiple phones)
- Store your app’s data in [Amazon S3](http://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/s3transferutility.html) or [Firebase](https://www.firebase.com/docs/android/quickstart.html). This will make it persistent across phones as well!
- Have a simple login-authentication (prompt cooks/customers for a username, and maybe password. Have a login/add new user feature)
- Have a login-authetication with an API like [facebook](https://developers.facebook.com/docs/facebook-login/android) or [google](https://developers.google.com/api-client-library/javascript/features/authentication).

You can also come up with your own features to add to the app!

## Deliverables
We require these three things to be turned in:
* Your proposal (Mon Oct 3)
* Wireframes + Design Explanation (Thu Oct 7)
* Your app (Thu Oct 13)

Submit your app [here](https://goo.gl/forms/PpFxYt0GFXzes5LV2)!
