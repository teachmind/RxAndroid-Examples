# Learning RxJava for Android by example

This repository contains example of using RxJava with Android to solve real-world problems. More exapmples will be adding day by day and you are also welcome to contribute.

# Examples
1. [Form validation](#1-form-validation-using-combinelatest)
2. [Timer demo](#2-timer-demo-interval-and-delay)

### 1. Form validation
`.combinelatest` allows you to motinor the state of multiple observables at a single place. [This example](https://github.com/shuza/RxJava-Android-Samples/tree/master/RxFormValidation) demonstrats the use of combinelatest to validate a basic form. In this example there are 3 input fileds for the form. The form will be valid if all those 3 fields are valid. If any input field is invalid, an error message will be shown against invalid input.
We have 3 imdependent observables that observes the input changes. After an ecent is emited from all 3 inputs, the result is combined and the form is evaluted for validity.

Note that the form will evalute only after each of those 3 inputs receives at least one event notification or text changes.

### 2. Timer demo (interval and delay)
[This example](https://github.com/shuza/RxAndroid-Examples/tree/master/RxTimerDemo) shows how we can use RxJava's `interval` and `delay` operators to a bunch of cases where we want to run a task at specific time inerval or after a specific time. This a better option than `TimerTask`. In the [exapmle](https://github.com/shuza/RxAndroid-Examples/tree/master/RxTimerDemo) we have used `interval` operator to log a message after every 2s and `delay` operator is used for the same purpose but it starts after 10s.
