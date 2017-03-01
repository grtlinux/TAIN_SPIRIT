@echo on

::------------------------------------------------------
:: COM_NAME
set COM_NAME=\\%COMPUTERNAME%
echo COM_NAME=%COM_NAME%
::goto END

::------------------------------------------------------
:: start service
net start SpiritJoint

::------------------------------------------------------
:END
timeout 3
