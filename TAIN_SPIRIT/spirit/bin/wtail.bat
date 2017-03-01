@echo on
@setlocal

::----------------------------------------------
:: ENV

::set TAIN_HOME=N:\PROG
set TAIN_HOME=D:\IMSI\SPIRIT_20170302
if not exist %TAIN_HOME% set TAIN_HOME=..\..

set JAVA_HOME=%TAIN_HOME%\jdk1.7.0_79
set SPIRIT_HOME=%TAIN_HOME%\sprit


set PATH=%JAVA_HOME%\bin;%SPIRIT_HOME%\bin;%SPIRIT_HOME%\bin\UnxUpdates;%SPIRIT_HOME%\bin\nssm-2.24\win64;%PATH%

cd %SPIRIT_HOME%\bin

::----------------------------------------------
:: START

tail -f ..\log\spirit_out.log



@endlocal

