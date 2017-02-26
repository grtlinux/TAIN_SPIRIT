@echo on
@setlocal

::-----------------------------------------------------------------------------------------
:ENV

set JAVA_HOME=N:\PROG\jdk1.7.0_79
set PATH=%PATH%;%JAVA_HOME%\bin

set LIB_PATH=L:\WORK\GITHUB\KIEA_RUNJAR\RunJar\libs
set JOB_NAME=tain-runjar-1.0

::-----------------------------------------------------------------------------------------
:BACKUP

if exist _%JOB_NAME%.jar  goto MK_DIR

copy %JOB_NAME%.jar _%JOB_NAME%.jar

::-----------------------------------------------------------------------------------------
:: backup and make dir
:MK_DIR

mkdir %JOB_NAME%
cd %JOB_NAME%

::-----------------------------------------------------------------------------------------
:JAR_XVF

jar xvf ../%JOB_NAME%.jar

::-----------------------------------------------------------------------------------------
:COPY_LIBS

mkdir libs

copy %LIB_PATH%\ant.jar              libs
copy %LIB_PATH%\commons-net-3.3.jar  libs
copy %LIB_PATH%\log4j-1.2.17.jar     libs

::-----------------------------------------------------------------------------------------
:MAKE_MANIFEST

echo Manifest-Version: 1.0> manifest.txt
echo Rsrc-Class-Path: ./>> manifest.txt
echo   libs/ant.jar>> manifest.txt
echo   libs/commons-net-3.3.jar>> manifest.txt
echo   libs/log4j-1.2.17.jar>> manifest.txt
:: echo Class-Path: .>> manifest.txt
echo Rsrc-Main-Class: tain.kr.test.main.Main>> manifest.txt
echo Main-Class: tain.kr.jar.v02.JarRsrcLoader>> manifest.txt

::-----------------------------------------------------------------------------------------
:: jar cvfm ../%JOB_NAME%.jar manifest.txt *
:: jar cvfM ../%JOB_NAME%.jar *
:JAR_CVMF

jar cvfm ../%JOB_NAME%.jar manifest.txt *

::-----------------------------------------------------------------------------------------
:RM_FOLDER

cd ..

::rmdir /S /Q %JOB_NAME%
rmdir /S /Q %JOB_NAME%

::-----------------------------------------------------------------------------------------
:RUN_JAR

java -jar %JOB_NAME%.jar

::-----------------------------------------------------------------------------------------
:END

timeout 1

