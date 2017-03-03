@echo off
@setlocal
::------------------------------------------------------
:: env
set KANG_HOME=D:\IMSI\SPIRIT_20170301

set JAVA_HOME=%KANG_HOME%\jdk1.7.0_79
set JAVA_BIN=%JAVA_HOME%\bin
set JAVA_EXE=%JAVA_BIN%\java.exe

set SPIRIT_HOME=%KANG_HOME%\spirit
set SPIRIT_BIN=%SPIRIT_HOME%\bin
set SPIRIT_LOG=%SPIRIT_HOME%\log
set SPIRIT_LOG_FILE=%SPIRIT_LOG%\spirit_out.log

set NSSM_BIN=%SPIRIT_BIN%\nssm-2.24\win64
set NSSM_EXE=%NSSM_BIN%\nssm.exe

set SERVICE_NAME=Spirit_Joint_Client_ver1.0

echo KANG_HOME = %KANG_HOME%
echo.
echo JAVA_HOME = %JAVA_HOME%
echo JAVA_BIN = %JAVA_BIN%
echo JAVA_EXE = %JAVA_EXE%
echo.
echo SPIRIT_HOME = %SPIRIT_HOME%
echo SPIRIT_BIN = %SPIRIT_BIN%
echo SPIRIT_LOG = %SPIRIT_LOG%
echo SPIRIT_LOG_FILE = %SPIRIT_LOG_FILE%
echo.
echo NSSM_BIN = %NSSM_BIN%
echo NSSM_EXE = %NSSM_EXE%
echo.
echo Service Name = %SERVICE_NAME%
echo.
echo ------------------ Service Install(create) ----------------------
echo.

::goto END

::------------------------------------------------------
:: create service

%NSSM_EXE% install %SERVICE_NAME%                        "%JAVA_EXE%"

%NSSM_EXE% set     %SERVICE_NAME%   Application          %JAVA_EXE%
%NSSM_EXE% set     %SERVICE_NAME%   AppDirectory         %SPIRIT_BIN%
%NSSM_EXE% set     %SERVICE_NAME%   AppParameters        -jar tain-spirit-1.0.jar

%NSSM_EXE% set     %SERVICE_NAME%   DisplayName          Spirit Joint Client
%NSSM_EXE% set     %SERVICE_NAME%   Description          Spirit Joint Client is to make a connection from server to client
%NSSM_EXE% set     %SERVICE_NAME%   Start                SERVICE_AUTO_START

%NSSM_EXE% set     %SERVICE_NAME%   AppStdout            %SPIRIT_LOG_FILE%
%NSSM_EXE% set     %SERVICE_NAME%   AppStderr            %SPIRIT_LOG_FILE%


::------------------------------------------------------
:END
timeout 3

@endlocal

