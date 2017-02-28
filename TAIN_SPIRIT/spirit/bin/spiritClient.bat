@echo on
@setlocal

::-----------------------------------------------------------------------------------------
:ENV

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set PATH=%PATH%;%JAVA_HOME%\bin

set JAR_NAME=tain-spirit-1.0.jar

::-----------------------------------------------------------------------------------------
:OPTION

set OPTION= -Dclass=SpiritClient
set OPTION=%OPTION% -Dtain.kr.com.spirit.control.host=192.168.0.11
set OPTION=%OPTION% -Dtain.kr.com.spirit.control.port=20025
set OPTION=%OPTION% -Dtain.kr.com.spirit.control.retry=1

set OPTION=%OPTION% -Dtain.kr.com.spirit.target.host=192.168.0.11
set OPTION=%OPTION% -Dtain.kr.com.spirit.target.port=3389

set OPTION=%OPTION% -Dtain.kr.com.spirit.joint.host=192.168.0.11
set OPTION=%OPTION% -Dtain.kr.com.spirit.joint.port=13389

::-----------------------------------------------------------------------------------------
:RUN_JAR

java %OPTION% -jar %JAR_NAME%

::-----------------------------------------------------------------------------------------
:END

pause
::timeout 1

