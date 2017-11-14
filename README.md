# Learning RxJava for Android by example

This repository contains example of using RxJava with Android to solve real-world problems. More exapmples will be adding day by day and you are also welcome to contribute.

# Examples
1. [Form validation](#1-form-validation-using-combinelatest)
2. [Timer demo](#2-timer-demo-interval-and-delay)
3. [Two-way data binding](#3-data-binding-for-textview-using-publishsubject)
4. [Search implementation](#4-search-implementation)
5. [RestFul API call](#5-restful-api-call)

### 1. Form validation (using combinelatest)
`.combinelatest` allows you to motinor the state of multiple observables at a single place. [This example](https://github.com/shuza/RxJava-Android-Samples/tree/master/RxFormValidation) demonstrats the use of combinelatest to validate a basic form. In this example there are 3 input fileds for the form. The form will be valid if all those 3 fields are valid. If any input field is invalid, an error message will be shown against invalid input.
We have 3 imdependent observables that observes the input changes. After an ecent is emited from all 3 inputs, the result is combined and the form is evaluted for validity.

Note that the form will evalute only after each of those 3 inputs receives at least one event notification or text changes.

### 2. Timer demo (interval and delay)
[This example](https://github.com/shuza/RxAndroid-Examples/tree/master/RxTimerDemo) shows how you can use RxJava's `interval` and `delay` operators to a bunch of cases where yo want to run a task at specific time inerval or after a specific time. This a better option than `TimerTask`. In the [exapmle](https://github.com/shuza/RxAndroid-Examples/tree/master/RxTimerDemo) we have used `interval` operator to log a message after every 2s and `delay` operator is used for the same purpose but it starts after 10s.

### 3. Data binding for TextView (using PublishSubject)
Data binding is a preety cool thing. It will auto update the view if the data source change. [This example](https://github.com/shuza/RxAndroid-Examples/tree/master/RxDataBinding) demonstrates how we can use `PublishSubject` to bind data with TextView. Here we have 2 EditText where we can enter two number and 1 TextView to show the sumation of those 2 numbers. The sumation will auto update if we change those numbers. This is a basic use of data binding. You can also use [Presentation ViewModel pattern](https://martinfowler.com/eaaDev/PresentationModel.html).

### 4. Search implementation
Search is a common feature of our daily using apps. It provides the content which we are looking for. We as a developer is responsible to implement in a better way to make it fast. [This example](https://github.com/shuza/RxAndroid-Examples/tree/master/RxSearchDemo) will demonstrtes how we can implemenet search feature with RxJava. It use `Publish Subject` to emit the events when user type to search. `Debounce` operator is used to execute the search task after a sertain interval. `Filter` operator is used to avoid unkowanded search like empty search key and `DistinctUntilChanged` operator to avboid executing search task for the same key. It also use `SwitchMap` operator to avoid execute search testk or network call which are no longer needed to display. Let's assume user is typeing "a", "ab", "abc" then `Publish Subject` will emit event for each key total 3 events. But `Debounce` operator will wait for time invertal then accept events available at that time and will pass it to `Filter` operator to check either empty or not. As key is chenged than before like "ab"->"abc" `DistinctUntilChanged` operator will pass it to `SwitchMap` operator to avoud the previous search tesk or network call and process the latest search result.

### 5. RestFul API call
Now a days, most of the app need to retrive data from remote server through web service or API. In [this example](https://github.com/shuza/RxAndroid-Examples/tree/master/RxDemoAPICall) we will get data from a web service and show it in app. As a network library we will use [Retrofit](https://github.com/square/retrofit). Retrofit will provide the response data as a observable so that we can easily process it.
