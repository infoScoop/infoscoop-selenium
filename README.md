# infoScoop Selenium
This server is the structure which photos a screen shot for infoScoop.
  
## Run up
### Selenium Server
* for Modern Web Browser

    java -jar selenium-server-standalone-2.31.0.jar -role hub

* for InternetExplorer  
(required: InternetExplorer Driver)

    java -Dwebdriver.ie.driver=IEDriverServer.exe -jar selenium-server-standalone-2.31.0.jar -role webdriver -hub http://localhost:4444/grid/register -port 5556 -browser browserName=iexplorer,version=<versionNo>,platform=WINDOWS,maxInstances=10  

### Client(This Apps)
    mvn
  
## Powered by
* [Selenium](https://github.com/SeleniumHQ/selenium)