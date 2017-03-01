@echo on

setlocal

::----------------------------------------------
:: ENV
set JAVA_HOME=N:\PROG\jdk1.7.0_79
if not exist %JAVA_HOME% set JAVA_HOME=..\..\jdk1.7.0_79

set CATALINA=N:\PROG\apache-tomcat-7.0.68-x64

set PATH=%JAVA_HOME%\bin;%CATALINA%\bin;%PATH%

cd %CATALINA%\bin

::----------------------------------------------
:: administrator
net user administrator /active:yes

::----------------------------------------------
:: START

start



::endlocal

