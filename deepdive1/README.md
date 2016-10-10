# Deep Dive 1 :ocean: :swimming_woman: - Testing
Testing your app is important for making sure your app functions as expected. Today we'll specifically be talking about Unit Testing, which is a form of automated testing. As the name implies, unit testing is where you write tests small units of your code. For example, lets say I want to test that the fibonacci function I wrote actually works. In plain Java it might look like this:

```
public boolean fibTest(){
    int fib = doFibonacci(6);
    if (fib != 8) return false;
    else return true;
}
```
Since the 6th fibonacci number is 8, if `fib` is not 8, then my test has failed. In Android, your unit tests will be a little different. Since some things you want to test will be more complex than checking Fibonacci numbers, you'll probably have to use a testing library/framework specific to Android. Even for `fibTest` you can use a libary/framework (a certain library is commonly used for this). More on that later!

## Why are unit tests important?
Say you wrote 23 units tests in your code. You then add a new feature, and now suddenly 5 of your unit tests aren't passing anymore. Unit tests are a clear indicator to show how solidly built your code is, and are very helpful for debugging. You can quickly catch errors in your code and lets you verify that the logic is correct. They might seem superfluous, but they're easy to implement and prevent you from backtracking/spending a lot of time trying to figure out where things went wrong.

Testing is something you'll most likely encounter when you get a software job, so learning how to do testing now will give you a leg up in the game.

## What are some things I want to test in my code?
Pretty much everything! You can test both backend and frontend components. Generally you'd write a test everytime you write a piece of code that does something. Made a new fragment that should show you 10 pictures? You can write a test for that.

## What are some rules I should follow for writing tests?
You want your tests to be:
* **Targeted** - You test one thing at a time
* **Isolated** - The code you're testing should be isolated from the rest of the app's code and any external dependencies or events
* **Repeatable and predictable** - The result of your test should be predictable and the test should be repeatable
* **Independent** - Your tests can run in any order and shouldn't be dependent on other tests

## What are the different overall types of tests?
* **Unit tests** target small, isolated pieces of code, like functions. A unit test might check that a function with a return statement returns the correct value, or that a function which modifies a list does so correctly.
* **Integration tests** combine isolated units of code to ensure they work together correctly. An integration test might save a record to a database, then read it back and check that it's unchanged.
* **End-to-end tests** (also known as **system tests**) simulate user interactions with the app and check that it's working as it should. An end-to-end test might fill out a login form, click "Sign In", and verify that the correct activity has loaded afterwards.

A common philosophy (check out [this blog post](https://testing.googleblog.com/2015/04/just-say-no-to-more-end-to-end-tests.html) from Google) suggests a "testing pyramid": a large base of unit tests, some integration tests, and fewer end-to-end tests. The goal of the "testing pyramid" is to catch a bug with the smallest test possible. For example, an end-to-end test might fail if a function it depends on isn't returning the right value, but it won't give you much information about where the failure is; a unit test for the function will point you towards the failure right away.

(Thanks OlinJS for that wonderfully written section :heart:)

## [Test-Driven Development (TDD)](https://www.agilealliance.org/glossary/tdd/)
TDD is an Agile practice where you:
* Write a unit test for describing how some aspect of the program works
* Run the test (which should fail since the aspect isn't written yet)
* Write the simplest possible code to make the test pass
* Refactor the code until it follows [simplicity criteria.](https://www.agilealliance.org/glossary/rules-of-simplicity/)

If you're interested in learning more about the details of TDD, check out [this link](https://www.agilealliance.org/glossary/tdd/). Agile and TDD are commonly used in the tech world, so knowing how to do TDD will give you a leg up in the job market (interviewers are interested to hear that you used TDD in your project).

Since we're talking about good development practices, check out [Continuous Integration](https://www.thoughtworks.com/continuous-integration) as well.

Of course, not everyone is gung-ho about TDD. Check out [this article](http://david.heinemeierhansson.com/2014/tdd-is-dead-long-live-testing.html) about a guy who wants to move away from unit testing towards system testing.

## What types of tests do I run for Android?
In Android, there are typically two types of tests:

* **Local tests**: These are unit tests that run on your local machine only. These are good for tests that don't have any dependencies on the Android framework or dependencies that can be filled using mock objects. Basically, if you're writing something that isn't Android specific (like our Fibonacci test above) you'd put them here. These tests should be located under `module-name/src/test/java/`.

* **Instrumented tests**: These are unit tests that run on an Android device. This approach is good for unit tests that use Android dependencies and can't easily be filled with mock objects. These tests should be located under `module-name/src/androidTest/java`. Since instrumented tests are built into an APK separate from your app APK, they need their own `AndroidManifest.xml` file. Gradle will automatically generate this, so it may not initially be visible.

## Testing Frameworks
There are **tons** of testing frameworks. 
* JUnit - Pretty much all the other testing frameworks are built off of this. JUnit is a testing library for Java in general that's widely used.
* [AndroidJUnitRunner](https://developer.android.com/topic/libraries/testing-support-library/index.html#AndroidJUnitRunner) - JUnit compatible test runner for Android. Made by Google.
* [Espresso](https://developer.android.com/topic/libraries/testing-support-library/index.html#Espresso) - UI testing framework. Good for functional UI testing within an app. Only supports API level 8, 10, => 15. Made by Google.
* [UI Automator](https://developer.android.com/topic/libraries/testing-support-library/index.html#UIAutomator) - UI testing framework. Good for cross-app functional UI testing. Only supports API levels => 16. Made by Google.
* [Robolectric](http://robolectric.org/) - Lets you run Android tests directly from inside your IDE instead of in an emulator
* [Robotium](https://github.com/robotiumtech/robotium) - Similar to [Selenium](https://en.wikipedia.org/wiki/Selenium_(software)) (a testing framework for web), it lets you make robust automatic black-box UI tests.
* [Calabash](https://github.com/calabash/calabash-android) - It has an English-like syntax that makes it very easy to understand. The tests are described in [Cucumber](https://cucumber.io/) and then converted to Robotium in run time.
* [Appium](http://appium.io/) - You can test both iOS and Android versions of your app at the same time.
* And probably many more...

## Making your first local test
*Note: This is a condensed version of [this](https://developer.android.com/training/testing/unit-testing/local-unit-tests.html#build).*

Let's say I have a certain class I want to test. For this example, I'll use `MathExamples.java` as my class to test:

```
public class MathExamples {

    public int fibonacci(int n){
        if (n==1 || n==2) return 2;
        else return fibonacci(n-1) + fibonacci(n-2);
    }

    public int addition(int a, int b){
        return a + b;
    }

    public int multiplyByTen(int a){
        return 10 * a;
    }
}
```

There's a little mistake in there ;-)

### 1. Check build.gradle for dependencies
Depending on which framework you use, the steps here will be different. For now, let's include these dependencies in our build.gradle:

```
dependencies {
    // Required -- JUnit 4 framework
    testCompile 'junit:junit:4.12'
    // Optional -- Mockito framework
    testCompile 'org.mockito:mockito-core:1.10.19'
    // Optional -- Hamcrest library
    testCompile 'org.hamcrest:hamcrest-library:1.3'
}
```

[Mockito](http://mockito.org/) is a framework that lets you create mock variables for things that don't always stay the same. For example, your app's `context` might be something you want to mock.

### 2. Creating a test class
1. Click on the method or class you want to test. Press Ctrl+Shift+T (⇧⌘T)
2. Press `Create New Test` in the menu that appears
3. In the menu that appears, change the Class name to be something that's very descriptive. You'll be writing multiple tests within this class. Save it in `/test` as this is a local unit test, not an instrumented unit test.

### 3. Writing the test
Instead of returning True or False, here we will use `assert` (check [these docs](http://junit.sourceforge.net/javadoc/org/junit/Assert.html) for different `assert` methods). So, for our Fibonacci example, our test would look like this:
```
public class MathExamplesTest {
    @Test
    public void fibonnaciTest(){
        MathExamples mathClass = new MathExamples();
        int actual = mathClass.fibonacci(6);
        int expected = 8;
        assertEquals("The sixth fibonacci number is 8", expected, actual);
    }
}
```

You can use [Hamcrest matchers](https://github.com/hamcrest/JavaHamcrest) if you'd like as well. They make your tests more English-like, readable, and make matching easier. For example:

```
// JUnit 4 for equals check
assertEquals(expected, actual);
// Hamcrest for equals check
assertThat(actual, is(equalTo(expected)));

// JUnit 4 for not equals check
assertFalse(expected.equals(actual));
// Hamcrest for not equals check
assertThat(actual, is(not(equalTo(expected))));

// Without Hamcrest
boolean found = false;
for (Kitten kitten : cat.getKittens()){
    if (kitten.equals(someKitten)) found = true;
}
assertTrue(found);
// With Hamcrest
assertThat(cat.getKittens(), hasItem(someKitten))

// You can chain matchers too
assertThat("test", anyOf(is("testing"), containsString("est")));
```

### 5. Run your tests
First check to see if your project is synced with Gradle. You can run a single test by right-clicking a test and then selecting `Run`. To test all methods in a class, right click the class/method and select `Run`. To run all tests in a directory, right-click the directory and select `Run tests`.

The test should fail because I made a mistake in `fibonacci()` (it returns 2 isntead of 1). Fix that, and your test should pass!

## Making an instrumented unit test
If you remember the difference between local and instrumented tests, you'll want to use instrumented tests to test most things about how the app actually works (like testing if clicking a button does something).

Let's start with making a test for Lesson 3's homework. I want to start with making a test for one of the buttons that changes the background color.
```
Button changeColorButton1 = (Button) view.findViewById(R.id.changeColorButton1);
changeColorButton1.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Log.d(TAG, "Clicked change color button");
        View activityView = getActivity().findViewById(R.id.mainLayout);
        activityView.setBackgroundColor(Color.argb(255, 0, 255, 255));
    }
});
```

### 1. Add dependencies
Add these dependencies to your top-level build.gradle. **Note**: These are *not* the same dependencies you added before for your local tests. One is `testCompile` while the other is `androidTestCompile`.
```
dependencies {
    androidTestCompile 'com.android.support:support-annotations:24.0.0'
    androidTestCompile 'com.android.support.test:runner:0.5'
    androidTestCompile 'com.android.support.test:rules:0.5'
    // Optional -- Hamcrest library
    androidTestCompile 'org.hamcrest:hamcrest-library:1.3'
    // Optional -- UI testing with Espresso
    androidTestCompile 'com.android.support.test.espresso:espresso-core:2.2.2'
    // Optional -- UI testing with UI Automator
    androidTestCompile 'com.android.support.test.uiautomator:uiautomator-v18:2.1.2'
}
```

*Caution*: If your build configuration includes a compile dependency for the support-annotations library and an androidTestCompile dependency for the espresso-core library, your build might fail due to a dependency conflict. To resolve, update your dependency for espresso-core as follows:

```
androidTestCompile('com.android.support.test.espresso:espresso-core:2.2.2', {
    exclude group: 'com.android.support', module: 'support-annotations'
})
```

Next, add this to your module-level build.gradle:

```
android {
    defaultConfig {
        testInstrumentationRunner "android.support.test.runner.AndroidJUnitRunner"
    }
}
```

### 2. Add a test class
Create a new test class in `/androidTest`, and make sure `@RunWith(AndroidJUnit4.class)` is before the `public class MyAndroidTest{}`. This is necessary for writing an instrumented test.

### 3. Writing a test


## Quick coding guides and links

### [Annotations](http://junit.sourceforge.net/javadoc/org/junit/package-summary.html)
Anything with a `@` is an annotation (think `@Override`).
* `@Test`: Goes above method to test
    * `@Test(expected=Exception.class)`: Fails if named exception is not thrown
    * `@Test(timeout=100)`: Fails if it takes longer than 100 milliseconds
* `@Before`: Goes above method you want to execute before each test. Usually done to prepare the test environment.
* `@After`: Similar to `@Before`, but used to cleanup test environment. Can be used to save memory by cleaning up afterwards.
* `@BeforeClass` and `@AfterClass`: Unlike `@Before` and `@After`, these go before your class and are executed once.
* `@Ignore("Description why this is ignored")`: Ignores a test method.
* `@SmallTest`, `@MediumTest`, `@BigTest`: See this link [here](https://testing.googleblog.com/2010/12/test-sizes.html)
* `@Mock`: Goes above variable to state that it's a mock variable. If you're going to run with a mock variable, make sure to include `@RunWith(MockitoJUnitRunner.class)` above `public class TestClass{}`.
* Want to group tests together? Try this:
```
@RunWith(Suite.class)
@Suite.SuiteClasses({MyFirstTestClass.class,
        MySecondTestClass.class})
public class UnitTestThatRunsTwoTestClasses {}
```

### [Assert](http://junit.sourceforge.net/javadoc/org/junit/Assert.html)

### [Hamcrest matchers](http://hamcrest.org/JavaHamcrest/javadoc/1.3/org/hamcrest/Matchers.html) (and a [quick intro](http://www.slideshare.net/shaiyallin/hamcrest-matchers) too)

### Extra resources
* [Presentation](https://docs.google.com/presentation/d/18Gb8NIR_e-cB_LK4evxB7SrdPyrmlxG0GKL5-qcprnA/edit?usp=sharing) from in-class.
* Read through [this](https://developer.android.com/training/testing/unit-testing/index.html) if you're interested in learning more about testing. This README.md is heavily based off of this.
* [Monkey](https://developer.android.com/studio/test/monkey.html) and [MonkeyRunner](https://developer.android.com/studio/test/monkeyrunner/index.html) for how to do stress tests (making your tests even more comprehensive).

## Activity
As an in-class activity, **write 3 tests** for the "Intro to Java" lesson. Then, try **writing 3 tests** for any of the Android apps. Dividing tests between assignments is fine. Note that this is a non-graded in-class activity, not homework, so we aren't expecting you to finish and turn it in.

### Check your understanding
We hope that at the end of the lesson, you can answer all these questions:
* Why is writing tests important?
* Do I know how to write a good test?
* Can I write a Java test?
* Can I write a an Android specific test?
* What's the difference between local and instrumented tests?
* How do I use `assert`?
* What is a mock variable?

### Finished early?
First, make sure that you can answer all the questions in the "Check your understanding" section above. Then, try exploring testing frameworks like Robolectric or Robotium and see if you can write a test with them.