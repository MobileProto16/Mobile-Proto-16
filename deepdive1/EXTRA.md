# Extra information
[Back to README.md](README.md)

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

## [Annotations](http://junit.sourceforge.net/javadoc/org/junit/package-summary.html)
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
```java
@RunWith(Suite.class)
@Suite.SuiteClasses({MyFirstTestClass.class,
        MySecondTestClass.class})
public class UnitTestThatRunsTwoTestClasses {}
```
* `@Rule`: [How do rules work?](http://stackoverflow.com/questions/13489388/how-does-junit-rule-work) & [Rules in Android.](http://wiebe-elsinga.com/blog/whats-new-in-android-testing/)
