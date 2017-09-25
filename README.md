# Learning RxJava for Android by example

This repository contains example of using RxJava with Android to solve real-world problems. More exapmples will be adding day by day and you are also welcome to contribute.

# Examples
1. [Form validation (using combineLatest)](#1-form-validation-using-combinelatest)

### 1. Form validation
`.combinelatest` allows you to motinor the state of multiple observables at a single place. [This example](https://github.com/shuza/RxJava-Android-Samples/tree/master/RxFormValidation) demonstrats the use of combinelatest to validate a basic form. In this example there are 3 input fileds for the form. The form will be valid if all those 3 fields are valid. If any input field is invalid, an error message will be shown against invalid input.
We have 3 imdependent observables that observes the input changes. After an ecent is emited from all 3 inputs, the result is combined and the form is evaluted for validity.

Note that the form will evalute only after each of those 3 inputs receives at least one event notification or text changes.
