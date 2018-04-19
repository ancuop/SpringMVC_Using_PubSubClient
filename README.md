# SpringMVC_Using_PubSubClient

1. This web is used to get data from mqtt server
...**Example**: *ESP8266 <----> mqtt server <----> this server*
...When user click of "feed", it will publish message (topic: boardMacAddress/feed) to mqtt server with retain flag
...Whenever corresponding board (which has the macAddress) subscribes message (boardMacAddress/) it will receive above message and process
