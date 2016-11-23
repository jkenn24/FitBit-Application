### Welcome to the team06 repo

To get started, please clone the repo:

## git clone ssh://git@repo.gaul.csd.uwo.ca:7999/cs2212_w2016/team06.git

To build the prototype:

Use build automation with maven:

## mvn package

### To run test mode, please use a MAC based platform:

———————————  READ ME FIRST  ——————————

To see shelly at her current level, run first in either normal mode or in the “test” mode, if any of the other test modes are used first, shelly’s current level will be over-written. This is a limitation of test mode and will not be a problem for the user.

There are four test mode arguments, one for Shelly's current level “test”, one for Shelly at level 25 “test25”, one for Shelly at level 50 “test50”, and one that will reset Shelly's level to 0 “testReset”.  These test cases will not make calls to the API and will be running off of canned data.  Also note that running the test cases permanently changes Shelly's level (even when running in normal mode afterwards), this is because we needed to offer a way for you to test shelly at different levels and with different unlock able components.  Please use the “testReset”
option to return Shelly to level 0. Also note that when you use this option, Shelly will appear on the dashboard without a shell for the first time it opens. Again, this option is only for shelly testing purposes and is never a problem for the user running in normal mode (as the user will never be able to affect Shelly’s level) 

When running in normal mode (no test arguments) the refresh button will be unclickable for 5 minutes after each time it is clicked to prevent hammering the API.

When running in any of the test modes, clicking the refresh button will do nothing. 

When the app is closed Shelly will be serialized. Shelly's experience only pulls once per day to avoid giving XP twice. 

Lastly, in either normal or test mode, customizing shelly in the shelly tab will not appear on the dashboard until the next run. This is to prevent the app from taking a picture of shelly every time the user clicks a new shell, body or hat (in case they are testing multiple options to see what they like best)

If you rename shelly, you must press enter for the name to serialize and save for the next run.


———————————  Different running modes of our Fitbit App  ——————————

This tests Shelly with her current level: (or last level if you ran another test mode first)

## java -jar target/team06-Project-1.0-SNAPSHOT-jar-with-dependencies.jar test




This tests Shelly with some accessories unlocked and others unavailable. The accessories will be serialized.  
When accessories are selected, the changes will not be visible on the dashboard.  This is in response to the possibility 
of the user clicking between the different customizations repeatedly.  To prevent the process of customization being slowed 
down due to the background work, the changes will be made permanant upon closing the app. 

## java -jar target/team06-Project-1.0-SNAPSHOT-jar-with-dependencies.jar test25





This tests Shelly with all the accessories unlocked:
The accessories will be serialized.
When accessories are selected, the changes will not be visible on the dashboard.  This is in response to the possibility 
of the user clicking between the different customizations repeatedly.  To prevent the process of customization being slowed 
down due to the background work, the changes will be made permanant upon closing the app.

## java -jar target/team06-Project-1.0-SNAPSHOT-jar-with-dependencies.jar test50





This resets Shelly to level 0.  Please note that none of the accessories will be available to be clicked. Also note that shelly appears on the dashboard with no shell when this is run. Again this is not an issue for the user because these test modes are only to see shelly at different levels and will not be available for the user to select.

## java -jar target/team06-Project-1.0-SNAPSHOT-jar-with-dependencies.jar testReset





### To run normal mode:

## java -jar target/team06-Project-1.0-SNAPSHOT-jar-with-dependencies.jar