FROM tomcat:8.0-jre8
LABEL maintainer="Kyle Crowley"
ADD target/FrontControllerDemo.war /usr/local/tomcat/webapps
