@echo off

SET CURRENT_DIRECTORY=%~dp0
SET CLASSPATH=%CURRENT_DIRECTORY%lib\*

: default jdk
java -classpath %CLASSPATH% org.prolobjectlink.prolog.jpl7.swi7.SwiProlog7Console -g
