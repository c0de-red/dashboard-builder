    Copyright 2012 JBoss Inc

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Dashboard Builder Demo
=======================

The following directories contain the needed files to run the Dashboard Builder application in standalone demo mode.
Feel free to run and modify this installation as much as you wish, but remember that's NOT
RECOMMENDED to use this demo in production environments.


Prerequisites
-------------------

The only prerequisite to run the demo application is to have the JDK 1.6 installed in your system.

If you don't have the JDK 1.6 installed in your system please use the following [link]
(http://www.oracle.com/technetwork/java/javase/downloads/index.html) to download the JDK and get
the installation instructions. (go the "Java SE 6 Update XX" section to select the last available update)

Installation steps
------------------

* 1 Check your JDK installation:

  Open a command window a type the following command:

    $ java -version

* If you have the JDK installed in your system you will see a command output like this:

        "java version "1.6.0_35"
        Java(TM) SE Runtime Environment (build 1.6.0_35-b10)
        Java HotSpot(TM) 64-Bit Server VM (build 20.10-b01, mixed mode)"

* Set the JAVA_HOME environment variable pointing to the JDK installation directory.

        $ export JAVA_HOME=/usr/java/jdk1.6.0

* Unzip the dashbuilder-demo-installer.zip file to a given directory (the [target_directory]).
  You should get a directory structure like this:

         [target_directory]/dashbuilder-demo
                              README.md
                              start-demo.sh
                              start-demo.bat
                              realm.properties
                              /db
                              /log

* Open a command window and execute the start-demo.sh script for linux environments or start-demo.bat for windows
   environments.

        $ cd <target_directory>/dashbuilder-demo
        $ ./start-demo.sh

   or

        C:\ cd <target_directory>\dashbuilder-demo
        start-demo.bat

  NOTE: The application uses an auto-deployable embedded H2 database which it's automatically created when you start
  the app for the very first time. The database initialization procedure it takes a few minutes. Furthermore, you should
  take into account that the H2 database downgrades the application performance compared with other databases like
  PostgreSQL, MySQL, normally used in production environments.

* Once the application is started, open a browser and type the URL: <code>http://localhost:8080/dashbuilder</code>.
The following user/password are available by default:

    * <code>root/root</code>: to sign-in as the superuser
    * <code>demo/demo</code>: to sign-in as an end user.

  On start-up, the application installs automatically some ready-to-use sample dashboards, for demo and learning purposes,
  as well as the jBPM Process Dashboard.

* To stop the application close the terminal window or type the "Ctrl + C" command.

jBPM Process Dashboard
------------------------

Once logged, the jBPM Process Dashboard can be accessed in two ways:

1. As root, by selecting the jBPM Dashboard workspace at the top administration toolbar.
2. Typing the following URL: <code>http://localhost:8080/dashbuilder/workspace/jbpm-dashboard</code>

In order to populate the jBPM dashboard with dummy data, go to the installation root directory and run
the **install-jbpm-data.sh** script Don't forget to start the application before executing the script,
otherwise you will get the following error:

    Exception in thread "main" org.h2.jdbc.JdbcSQLException: Table "PROCESSINSTANCELOG" not found;


Application database
----------------------

The demo application database will be generated automatically when you start the application for the first time.
If you want to restore the application to its initial state you can:

1. Stop the application (if running).
2. Delete the database files in the <code>/db</code> directory.
3. Start the application.
