
## sample 1
- 服务端很简单：侦听8080端口，并把客户端发来的字符串返回去。下面是客户端的代码：
- 客户端也非常简单：向服务端发起请求，发送一个”hello”字串，然后获得服务端的返回。把服务端运行起来后，执行客户端，我们将得到”hello”的返回。


就是这样一套简单的网络通信的代码，我们来把它改造成使用SSL通信。

## Sampel ssl 1

就是这样一套简单的网络通信的代码，我们来把它改造成使用SSL通信。在SSL通信协议中，我们都知道首先服务端必须有一个数字证书，当客户端连 接到服务端时，
会得到这个证书，然后客户端会判断这个证书是否是可信的，如果是，则交换信道加密密钥，进行通信。如果不信任这个证书，则连接失败。


- 因此，我们首先要为服务端生成一个数字证书。Java环境下，数字证书是用keytool生成的，这些证书被存储在store的概念中，就是证书仓库。我们来调用keytool命令为服务端生成数字证书和保存它使用的证书仓库:

`keytool -genkey -v -alias bluedash-ssl-demo-server -keyalg RSA -keystore ./server_ks -dname "CN=localhost,OU=cn,O=cn,L=cn,ST=cn,C=cn" -storepass server -keypass 123123`



可以看到，服务端的Socket准备设置工作大大增加了，增加的代码的作用主要是将证书导入并进行使用。此外，所使用的Socket变成了 SSLServerSocket，另外端口改到了8443（这个不是强制的，仅仅是为了遵守习惯）。另外，最重要的一点，服务端证书里面的CN一定和服务 端的域名统一，我们的证书服务的域名是localhost，那么我们的客户端在连接服务端时一定也要用localhost来连接，否则根据SSL协议标 准，域名与证书的CN不匹配，说明这个证书是不安全的，通信将无法正常运行

有了服务端，我们原来的客户端就不能使用了，必须要走SSL协议。由于服务端的证书是我们自己生成的，没有任何受信任机构的签名，所以客户端是无 法验证服务端证书的有效性的，通信必然会失败。所以我们需要为客户端创建一个保存所有信任证书的仓库，然后把服务端证书导进这个仓库。这样，当客户端连接 服务端时，会发现服务端的证书在自己的信任列表中，就可以正常通信了


可以看到，除了把一些类变成SSL通信类以外，客户端也多出了使用信任证书仓库的代码。以上，我们便完成了SSL单向握手通信。即：客户端验证服务端的证书，服务端不认证客户端的证书。
以上便是Java环境下SSL单向握手的全过程。因为我们在客户端设置了日志输出级别为
