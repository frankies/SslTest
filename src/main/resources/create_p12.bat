keytool -genkey -v -alias my-p12 -keyalg RSA -keystore ./my.p12 -keyalg RSA -storetype PKCS12 -validity 3650 -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass p12-1234 -keypass 123456
