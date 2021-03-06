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

set OPTION= -Dclass=TestServer
set OPTION=%OPTION% -Dtain.kr.com.spirit.test.server.port=13389

::-----------------------------------------------------------------------------------------
:RUN_JAR

java %OPTION% -jar %JAR_NAME%

::-----------------------------------------------------------------------------------------
:END

pause
::timeout 1

@endlocal


