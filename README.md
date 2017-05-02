# L2ACP-api

This is the gameserver API project for L2ACP

## How to get this working?

* First and foremost you need to add Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files on your JRE. Download from here: http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html
* You will need to run l2acpinit.sql against your gameserver database for this to work.
* You need to add `L2ACPServer.getInstance();` at the end of your Gameserver.java

(To be edited)
