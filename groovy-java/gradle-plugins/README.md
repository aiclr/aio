# [gradle plugins](https://docs.gradle.org/current/userguide/custom_plugins.html#sec:custom_plugins_standalone_project)

## use

> publish local `publishSimplePluginPluginMarkerMavenPublicationToMavenLocal`\
> using plugin in another project
> > 参考 java-app \
> > `settings.gradle`
> > ```groovy
> > pluginManagement{
> >     repositories {
> >         //gradle 默认 plugin 仓库
> >         gradlePluginPortal()
> >         //本地仓库
> >         mavenLocal()
> >     }
> > }
> > 
> > ```
> > 
> > build.gradle
> > ```groovy
> > plugins {
> >     id 'org.bougainvilleas.ilg.plugin' version '0.0.1'
> > }
> > ```