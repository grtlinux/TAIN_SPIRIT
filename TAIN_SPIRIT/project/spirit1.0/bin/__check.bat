@echo on
@setlocal

::--------------------------------------------------------------
:START

set JAVA_HOME=N:\PROG\jdk1.7.0_79
if not exist %JAVA_HOME% set JAVA_HOME=..\..\jdk1.7.0_79

::--------------------------------------------------------------
:RESULT

echo %JAVA_HOME%

::--------------------------------------------------------------
:END
pause

@endlocal

