### Failing the test

The fail action will generate an exception in order to terminate the test case with error. The test case will therefore not be successful in the reports.

The user can specify a custom error message for the exception in order to describe the error cause. Here is a very simple example to clarify the syntax:

**XML DSL** 

```xml
<testcase name="failTest">
    <actions>
        <fail message="Test will fail with custom message"/>
    </actions>
</testcase>
```

Test results:

```xml
Execution of test: failTest failed! Nested exception is: 
com.consol.citrus.exceptions.CitrusRuntimeException: 
Test will fail with custom message

[...]

CITRUS TEST RESULTS

failTest          : failed - Exception is: Test will fail with custom message

Found 1 test cases to execute
Skipped 0 test cases (0.0%)
Executed 1 test cases, containing 3 actions
Tests failed:        1 (100.0%)
Tests successfully:  0 (0.0%) 
```

While using the Java DSL tester might want to raise some Java exceptions in the middle of configuring the test case. But this is not possible as we have to separate the design time and the execution time of the test case. The **@CitrusTest** annotated configuration method is called for building up the whole test case. After this method was processed the test gets executed in runtime oth the test. If you specify a throws exception statement in the configuration method this will not be done at runtime but at design time. This is why you have to use the special fail test action which raises a Java exception during the runtime of the test. The next example will not work as expected:

**Java DSL designer and runner** 

```java
@CitrusTest
public void wrongUsageSample() {
    // some test actions
    
    throw new ValidationException("This test should fail now"); // does not work as expected 
}
```

The validation exception above is directly raised before the test is able to start as the **@CitrusTest** annotated method does not represent the test runtime. Instead of this we have to use the fail action as follows:

**Java DSL designer and runner** 

```java
@CitrusTest
public void failTest() {
    // some test actions
    
    fail("This test should fail now"); // fails at test runtime as expected 
}
```

Now the test fails at runtime as the fail action is raised during the test execution as expected.

