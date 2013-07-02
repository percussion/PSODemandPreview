## Overview
This is the PSODemandPreview Extension for Rhythmyx 7.2.   

THIS VERSION REQUIRES RHYTHMYX 7.2.0 OR LATER 

If you are using an earlier version select the branch that matches your product version when checking out the code:

* rel-71
* rel-67

**NOTE:** Please remove prior versions of the PSO Demand Preview from your Rhythmyx installation by removing prior versions of the jar from 
the `/Rhythmyx/AppServer/server/rx/deploy/rxapp.ear/rxapp.war/WEB-INF/lib` directory.  

## Download  

*  http//cdn.percussion.com/downloads/open/psodemandpreview/pso-demand-preview-SNAPSHOT.zip

## Installation  
To deploy, you must first build the project from source, or download a packaged distribution.
Unzip the distribution into a new directory.

>Install.bat c:\Rhythmyx
>sh install.sh ~/Rhythmyx

Where the argument is the home directory where Rhythmyx is installed. 

## Manual Install
To manually install, you must have the Java 1.6 JDK with a JAVA_HOME environment variable,
and Apache Ant installed with an ANT_HOME environment variable set. 

The RHYTHMYX_HOME environment variable must point at your Rhythmyx installation directory.  

For Example:

To use the patch installer to install on Linux, add these lines to your .profile  

> export RHYTHMYX_HOME=$HOME/Rhythmyx  ##or where ever it is installed   
> export JAVA_HOME=$RHYTHMYX_HOME/JRE/   
> export ANT_HOME=$RHYTHMYX_HOME/Patch/InstallToolkit/   

you can then run Ant: 

> $ANT_HOME/bin/ant -f deploy.xml 


## Building from Source

> git clone https://github.com/percussion/PSODemandPreview.git

### Configure Ivy
The Toolkit uses [Apache Ivy](http://ant.apache.org/ivy/) for dependency management.  In addition to requiring that JAVA_HOME, ANT_HOME, and RHYTHMYX_HOME environment variables are configured, the Ivy dependencies also need configured in your Ant profile.  

Download Apache Ivy with dependencies and copy the Ivy jar from the Ivy distribution AND the jars in the lib folder of the Ivy distribution to:

> $HOME/.ant/lib

### Building
The build script provides several targets.  To build the Toolkit distribution, use the "dist" target from the directory that you cloned the repository to:

> ant ivy-retrieve, dist 
