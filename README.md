# Review Services

This is how to make a simple service while omitting any response back to the Android component.

What is applied here:

- Service does background service but instead of using AsyncTask, it uses RxJava2's Observable which has instructions to run the task in a background thread, and response in the main thread.

- The task performed is a Fibonacci sequence. The main activity request to enter a value to run against.
