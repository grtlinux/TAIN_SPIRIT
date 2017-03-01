@echo on

::------------------------------------------------------
:: COM_NAME
set COM_NAME=\\%COMPUTERNAME%
echo COM_NAME=%COM_NAME%
::goto END

::------------------------------------------------------
:: delete service
::sc %COM_NAME% delete SpiritJoint
sc delete SpiritJoint

::------------------------------------------------------
:END
timeout 3


