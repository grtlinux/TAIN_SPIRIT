@echo off
@setlocal

::-----------------------------------------------------------------------------------------
:ENV

set JAVA_HOME=N:\PROG\jdk1.7.0_79
if not exist %JAVA_HOME% set JAVA_HOME=..\..\jdk1.7.0_79

set PATH=%PATH%;%JAVA_HOME%\bin

set JAR_NAME=tain-spirit-1.0.jar

::-----------------------------------------------------------------------------------------
:OPTION

set OPTION= -Dclass=SpiritClient

set OPTION=%OPTION% -Dtain.kr.com.spirit.joint.retry=5

set OPTION=%OPTION% -Dtain.kr.com.spirit.joint.host=localhost
set OPTION=%OPTION% -Dtain.kr.com.spirit.joint.port=20025

set OPTION=%OPTION% -Dtain.kr.com.spirit.target.host=localhost
set OPTION=%OPTION% -Dtain.kr.com.spirit.target.port=13389

::-----------------------------------------------------------------------------------------
:RUN_JAR

java %OPTION% -jar %JAR_NAME%

::-----------------------------------------------------------------------------------------
:END

pause
::timeout 1

