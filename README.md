# android-driver-multi-device
Framework supporting **multiple connected Android devices**. The framework **allows to switch between devices** during test. When this can be useful? For example, when testing some messaging software - chats. You can type message on first device, send this, and then switch to second device to verify the message is delivered.

Technology stack:
- JBehave to write tests using [Behavior-driven development](https://en.wikipedia.org/wiki/Behavior-driven_development) approach
- Java [Spring](https://en.wikipedia.org/wiki/Spring_Framework) framework to inject dependent beans (driver, locators, etc.)
- [Lombok](https://projectlombok.org/) to auto generate setters/getters/constructors

A simple test, that is delivered with the framework, starts typing in calculator on first device (emulator) and then switches to second device to finish testing.

![alt multi-device](https://raw.githubusercontent.com/maxim-filkov/android-driver-multi-device/master/src/main/resources/demo.gif)

# How to install and run

- Download Brew package manager
```
sudo apt-get install build-essential curl git m4 ruby texinfo libbz2-dev \
    libcurl4-openssl-dev libexpat-dev libncurses-dev zlib1g-dev
```
- Install Brew
```
ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/linuxbrew/go/install)"
```
- Set Path for Brew in /etc/environment
```
export PATH="$HOME/.linuxbrew/bin:$PATH"
export MANPATH="$HOME/.linuxbrew/share/man:$MANPATH"
export INFOPATH="$HOME/.linuxbrew/share/info:$INFOPATH"
```
- Then reload /etc/environment:
```
source /etc/environment
```
- Install nodejs
```
brew update
curl -sL https://deb.nodesource.com/setup_0.12 | sudo -E bash -
sudo apt-get install -y nodejs
```
- Check installed nodejs version, please note npm should of version 3.x not 4.x. Otherwise you'll have a lot of errors when installing appium later on (see details here if needed: [Unable to generate meanjs using yeoman generator](https://github.com/meanjs/generator-meanjs/issues/117))
```
$ node -v
v0.12.7
$ npm info generator-meanjs version
0.4.1
npm info npm version
3.3.4
```
- Install Appium
```
npm install -g appium
npm install wd
```
- Download Selenium server
```
cd /opt/
sudo wget https://selenium-release.storage.googleapis.com/2.47/selenium-server-standalone-2.47.1.jar
sudo chown jenkins:jenkins selenium-server-standalone-2.47.1.jar
```
- Run Selenium server
```
nohup java -jar /opt/selenium-server-standalone-2.47.1.jar >/dev/null 2>&1 &
```
- Run two nodes (for two devices), by this time you should already have the devices running (see: [How to run Android emulators](http://developer.android.com/tools/devices/index.html)).
```
nohup node ~/.npm-packages/lib/node_modules/appium/bin/appium.js -a localhost -p 3482 \
    --udid emulator-5554 --command-timeout 2000000 2>&1 &
nohup node ~/.npm-packages/lib/node_modules/appium/bin/appium.js -a localhost -p 3483 \
    --udid emulator-5555 --command-timeout 2000000 2>&1 &
```
- Run test
```
mvn clean test
```
