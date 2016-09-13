/******************************************************************************/
/* SYSTEM     : IM Step2                                                        */
/*                                                                            */
/* SUBSYSTEM  :                                                            */
/******************************************************************************/
package org.test.ssl.samplessl2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.KeyStore;

import javax.net.ServerSocketFactory;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author  Lin zhanwang
 * @version 1.0
 * @since   1.0
 *
 * <MODIFICATION HISTORY>
 *  (Rev.)		(Date)     	(Name)        (Comment)
 *  1.0    		2016年9月13日    	Lin zhanwang       New making
 */
public class BiSslServer extends Thread {
    private Socket socket;

    public BiSslServer(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            System.out.println("Server start....");
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            String data = reader.readLine();
            writer.println(data);
            writer.close();
            socket.close();
        } catch (IOException e) {

        }
    }

    private static String SERVER_KEY_STORE = new File(".", "/src/main/resources/server_ks").getAbsolutePath();
    private static String SERVER_KEY_STORE_PASSWORD = "123123";

    public static void main(String[] args) throws Exception {

        System.out.println("Server store file: " + SERVER_KEY_STORE);
        System.setProperty("javax.net.ssl.trustStore", SERVER_KEY_STORE);
        SSLContext context = SSLContext.getInstance("TLS");

        KeyStore ks = KeyStore.getInstance("jceks");
        ks.load(new FileInputStream(SERVER_KEY_STORE), null);
        KeyManagerFactory kf = KeyManagerFactory.getInstance("SunX509");
        kf.init(ks, SERVER_KEY_STORE_PASSWORD.toCharArray());

        context.init(kf.getKeyManagers(), null, null);

        ServerSocketFactory factory = context.getServerSocketFactory();
        ServerSocket _socket = factory.createServerSocket(8443);
        ((SSLServerSocket) _socket).setNeedClientAuth(true); //false: 客户端验证服务端的证书，服务端不认证客户端的证书; true: 让服务端也认证客户端的身份，双向认证


        while (true) {
            new BiSslServer(_socket.accept()).start();
        }
    }
}
