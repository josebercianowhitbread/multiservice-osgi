# Managing multiple instances of the same Adobe Experience Manager OSGi service

Implementation of the helpx guide:   
https://helpx.adobe.com/experience-manager/using/osgi_multiservices.html

This is a project template for AEM-based applications. It is intended as a best-practice set of examples as well as a potential starting point to develop your own functionality.

## Modules

The main parts of the template are:

* core: Java bundle containing all core functionality like OSGi services, listeners or schedulers, as well as component-related Java code such as servlets or request filters.
* ui.apps: contains the /apps (and /etc) parts of the project, ie JS&CSS clientlibs, components, templates, runmode specific configs as well as Hobbes-tests
* ui.content: contains sample content using the components from the ui.apps


## AEM setup

- You need to configure the SMTP server you will use beforehand. Following the instructions of the following URL http://help-forums.adobe.com/content/adobeforums/en/experience-manager-forum/adobe-experience-manager.topic.html/forum__2qnb-hi_guys_i_haveconf.html

## How to build

To build all the modules run in the project root directory the following command with Maven 3:

    mvn clean install

If you have a running AEM instance you can build and package the whole project and deploy into AEM with  

    mvn clean install -PautoInstallPackage -x settings.xml
    
Or to deploy it to a publish instance, run

    mvn clean install -PautoInstallPackagePublish -x settings.xml
    
Or to deploy only the bundle to the author, run

    mvn clean install -PautoInstallBundle -x settings.xml

## Maven settings

The project comes with the auto-public repository configured. To setup the repository in your Maven settings, refer to:

    http://helpx.adobe.com/experience-manager/kb/SetUpTheAdobeMavenRepository.html
