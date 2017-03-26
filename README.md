# Review Services

Service connection.

Service connection is not a hard subject, yet the process is very verbose.
What this is really about is for the consumer, the Android component, and the producer, the service, communicate using both handlers which serve as ports.

Each handler is able to get a messenger wrapping a message. This is pretty much what it is. Also each messenger comes with a code, to filter how to handle each message.

Over the course of each demo I am using `dependencies.Codes` in order to store the codes, and fields from each message object. As I said this is very verbose, but it's an elegant way to communicate with contracts in each end.

[read more...](https://developer.android.com/guide/components/bound-services.html#Basics)