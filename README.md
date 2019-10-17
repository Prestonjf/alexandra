# Alexandra

![Release](https://img.shields.io/badge/release-v0.2.0-blue.svg)

This README file describes how to configure and deploy the Alexandra Application.
This is an application used to process various workflows


### Prerequisites
- 	Apache Tomcat 8+
- 	Apache Maven 4
-	MySQL 5.6+

### Database Configuration

**|**


### Java Web Application Configuration
**|** Configuration assumes Apache Tomcat has already been installed.  

**1** Properties - Move alexandra.properties to $CATALINA_HOME/properties.

**2** If building the Alexandra application from source, use the following steps, otherwise continue to step 3.

**a** Download Source Code and Build WAR.

```
	git clone https://github.com/Prestonjf/alexandra.git
	
	cd Alexandra
	
	mvn clean install
```
**b** Use WAR file from /target folder.

```
	cd target
	
	alexandra##< latest version >.war
```
	

**3** Move built alexandra##< latest version >.war to $CATALINA_HOME/webapps. WAR deployment should happen automatically.



### Testing / Logging

**|** View catalina.out log for startup information.

**|** View $CATALINA_HOME/logs/Alexadra/Alexadra.log for usage logs.

**|** Run the Health Check API. 200 Response should be returned. Use the healthcheck credentials for basic auth.

```
curl -X GET http://localhost:8080/alexandra/healthcheck -H 'Accept: application/json' -u admin:admin
```

**|** Access Alexandra application in web browser at http://localhost:8080/alexandra

### Built With

* [Maven](https://maven.apache.org/) - Dependency Management

### Versioning

*	Version < latest version >


### Authors

* **Preston Frazier** - [prestonfrazier.net](preston@prestonfrazier.net)

### License
| Name | License | Description |
|---|---|---|
| Alexandra | GNU General Public License v3.0 | Core application used to process various workflows

