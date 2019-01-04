@echo off 

setlocal enabledelayedexpansion

set CLASSPATH=%CLASSPATH%;bin

java de.bartmail.moviebutler.main.Main %*
