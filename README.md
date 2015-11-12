# Jacket
#### Jacket - Sort of like Pocket

The app uses MySQL to run and relises on two schemmas 'jacekt' and 'jacekttest', I'm setting the username as 'kevin' and reading the password from the environment (the variable name is jacket_password), you can see this in, for example, JacketWeb/build.gradle where the configuration files are copied

To set the environment variable on the Mac see [this stackoverflow answer](http://stackoverflow.com/questions/25385934/setting-environment-variables-via-launchd-conf-no-longer-works-in-os-x-yosemite)

If you want to run this as a standalone app from inside Eclipse then you need to set

* spring.profiles.active=dev

in the IDE


