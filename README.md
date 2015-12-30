# Jacket
#### Jacket - Sort of like Pocket

The app uses MySQL to run and relies on two schemas 'jacket' and 'jackettest', I'm setting the username as 'jacket' and reading the password from the environment (the variable name is jacket_password), you can see this in, for example, JacketWeb/build.gradle where the configuration files are copied.

You'll also need to create a gradle.properties file for the user (i.e. in USER_HOME/.gradle/gradle.properties) and this file will need to contain:
    mysql_user=[some_user_name_for_my_sql]
    tomcat_home=[TOMCAT_HOME_FOLDER] and
    tomcat_home_config=[TOMCAT_HOME_FOLDER]/conf

To set the environment variable on the Mac see [this stackoverflow answer](http://stackoverflow.com/questions/25385934/setting-environment-variables-via-launchd-conf-no-longer-works-in-os-x-yosemite)

If you want to run this as a standalone app from inside Eclipse then you need to set

* spring.profiles.active=dev

in the IDE


