# Review Services

Through a local broadcastReceiver, a service can response back to an Android component.

What I really like about this approach is the consumer, the Android component, is losely coupled with the producer, the service. So a rotated device during service processing can still attach itself back to listen to the service response.

[read more...](https://developer.android.com/guide/components/broadcasts.html)