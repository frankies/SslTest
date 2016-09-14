@echo off
keytool -list -rfc    -keystore ./client_ks -storepass client -keypass 456456
pause