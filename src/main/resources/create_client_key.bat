rem keytool -genkey -v -keystore server_ks -alias serverkey -keyalg RSA -validity 3650 -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,c=cn" -storepass storepass_test -keypass keypass_test
rem keytool -genkey -v -alias bluedash-ssl-demo-server -keyalg RSA -keystore ./server_ks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass server -keypass 123123
keytool -genkey -v -alias bluedash-ssl-demo-client -keyalg RSA -keystore ./client_ks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass client -keypass 456456
