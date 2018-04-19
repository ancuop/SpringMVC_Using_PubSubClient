# SpringMVC_Using_PubSubClient

1. This web is used to get data from mqtt server.
2. **Example**:   *ESP8266*   connect to   *mqtt server*   connect to  *this server*.

* When user click on "feed" button, it will publish topic: boardMacAddress/feed to mqtt server with retained flag.
* Whenever corresponding board (which has the macAddress) subscribes message (boardMacAddress/) it will receive above message and process.


*TODO:*
+  Use ajax to publish message
