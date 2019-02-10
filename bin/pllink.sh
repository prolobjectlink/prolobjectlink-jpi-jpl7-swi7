#!/usr/bin/bash
java -classpath "$(dirname "$(pwd)")/lib/*" org.prolobjectlink.prolog.jpl7.swi7.SwiProlog7Console ${1+"$@"}