@echo on

::------------------------------------------------------
:: COM_NAME
set COM_NAME=\\%COMPUTERNAME%
echo COM_NAME=%COM_NAME%
::goto END

::------------------------------------------------------
:: create service
::sc %COM_NAME% create SpiritJoint binpath= "D:\IMSI\SPIRIT_20170225\spirit\bin\spiritServer.bat" DisplayName= "Spirit Joint ver1.0" start= auto
sc create SpiritJoint binpath= "D:\IMSI\SPIRIT_20170225\spirit\bin\spiritServer.bat" DisplayName= "Spirit Joint ver1.0" start= auto

::------------------------------------------------------
:: make service description
::sc %COM_NAME% description SpiritJoint "#### Description of Subversion..."
sc description SpiritJoint "#### Spirit Joint ver1.0..."

::------------------------------------------------------
:: start service
::net start SpiritJoint

::------------------------------------------------------
:END
timeout 3
