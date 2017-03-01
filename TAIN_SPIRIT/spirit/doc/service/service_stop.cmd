@echo on

::------------------------------------------------------
:: COM_NAME
set COM_NAME=\\%COMPUTERNAME%
echo COM_NAME=%COM_NAME%
::goto END

::------------------------------------------------------
:: stop service
net stop SpiritJoint

::------------------------------------------------------
:END
timeout 3
