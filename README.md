# Liferay MVC Portlet with React JS and Webpack

## Development

* Install blade CLI workspace with Liferay 7.x bundle
* Clone this repo to `/modules`

Rest TODO...

## OSGi Dependencies

This portlet requires Kotlin runtime running in Liferay's OSGi container.

```
blade sh install http://central.maven.org/maven2/org/jetbrains/kotlin/kotlin-osgi-bundle/1.1.50/kotlin-osgi-bundle-1.1.50.jar
```

## Build

Currently server code needs to be built in extra step:

    npm run buildServer

Raw command: 
    
    gradle clean buildWebpack deploy cleanJSDist

Blade: 
        
    blade deploy`