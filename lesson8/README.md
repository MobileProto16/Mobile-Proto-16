# Lesson 8 - Amazon s3

## [Amazon S3](https://aws.amazon.com/s3/)
Amazon Web Services (AWS) provides something called Amazon Simple Storage Service, AKA Amazon S3. As the name implies, it's a cloud storage service. The cloud is basically just a bunch of servers that are made available to the developers and people. When you're storing something in "the cloud", you're just putting it on a server somewhere else that you have access to. Now, instead of locally storing data on your phone you can use S3 and save it on the cloud! This is great for applications that need to share or store a lot of data.

Follow [these instructions](https://docs.aws.amazon.com/mobile/sdkforandroid/developerguide/s3transferutility.html) to set up your own S3 bucket (a cloud storage container). If you're interested in learning more about S3 past what the link above says, then check out AWS's [documentation](https://docs.aws.amazon.com/sdkfornet1/latest/apidocs/html/N_Amazon_S3_Transfer.htm).

## Libraries
Since we talked about APIs, we'll introduce some key libraries as well.
    * [Volley](https://developer.android.com/training/volley/index.html) - You'll need this to make HTTP requests. We talk about this below! Alternatively, you can use [Retrofit](https://square.github.io/retrofit/)
    * [Gson](https://github.com/google/gson) - For when you need to parse JSON
    * [Picasso](https://square.github.io/picasso/) - Makes displaying images simpler

## Assignment

Previously, you've had your todo app's data persist via an SQL database. Make a duplicate of your todo app from last class. Switch the app's storage method to using Amazon S3. The link provided above in the s3 section should help you get started.
