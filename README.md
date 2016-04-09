# [spring-boot#3627](https://github.com/spring-projects/spring-boot/issues/3627) - Unable to configure UrlRewriteFilter with Spring Boot 1.2.5

This branch contains an example to demonstrate Spring Boot's issue with Tuckey's configuration file.

When starting the Spring Boot application as a jar, Tuckey is able to load `urlrewrite.xml` and everything is fine.

    mvnw clean package && java -jar target/spring-boot-tuckey-0.1.0.jar
    ... snipped maven output ...
    ... snipped startup logs ...
    [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : org.tuckey.web.filters.urlrewrite.UrlRewriteFilter INFO: loaded (conf ok)
    ... snipped more logs ...

When using spring-boot:run, Tuckey's servlet class loader can't find `urlrewrite.xml`:

    mvnw clean spring-boot:run
    ... snipped maven output ...
    ... snipped startup logs ...
    [ost-startStop-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : org.tuckey.web.filters.urlrewrite.UrlRewriteFilter ERROR: unable to find urlrewrite conf file at urlrewrite.xml
    ... snipped more logs ...

# Other stuff
StackOverflow question "[Spring boot cannot find urlrewrite.xml inside jar file](http://stackoverflow.com/questions/31011577/spring-boot-cannot-find-urlrewrite-xml-inside-jar-file) might be related to this problem.

Tuckey loads its configuration at (org.tuckey.web.filters.urlrewrite.UrlRewriteFilter#loadUrlRewriterLocal() (#L264))[https://github.com/paultuckey/urlrewritefilter/blob/urlrewritefilter-4.0.4/src/main/java/org/tuckey/web/filters/urlrewrite/UrlRewriteFilter.java#L264]

An ugly, but working attempt to fix the problem by overriding UrlRewriteFilter#loadUrlRewriter can be seen in
(this commit)[https://github.com/crowdsupport/crowdsupport/commit/abf5ed531f524cbc858f7d93f1aaf3b9e6c4f331].

# Tested environments
Windows 8, 64bit

    java -version
    java version "1.8.0_45"
    Java(TM) SE Runtime Environment (build 1.8.0_45-b15)
    Java HotSpot(TM) 64-Bit Server VM (build 25.45-b02, mixed mode)