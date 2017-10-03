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

## Configuration

If HTML5 history API is enabled in portlet config, urlrewrite.xml needs to be modified.
 
```xml
<urlrewrite>
    <rule>
        <from>^/ui/(.*)$</from>
        <to>%{context-path}/web/guest/ui?path=$1</to>
    </rule>
    <!-- ... the rest -->
</urlrewrite>
```   

Every route needs to be added to Liferay navigation as URL redirects to `/ui`.

## Build

Currently server code needs to be built in extra step:

    npm run buildServer

Raw command: 
    
    gradle clean buildWebpack deploy cleanJSDist

Blade: 
        
    blade deploy