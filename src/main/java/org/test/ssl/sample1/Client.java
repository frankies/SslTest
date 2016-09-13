/******************************************************************************/
/* SYSTEM     : IM Step2                                                        */
/*                                                                            */
/* SUBSYSTEM  :                                                            */
/******************************************************************************/
package org.test.ssl.sample1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
//客户端也非常简单：向服务端发起请求，发送一个”hello”字串，然后获得服务端的返回。把服务端运行起来后，执行客户端，我们将得到”hello”的返回
public class Client {

    public static void main(String[] args) throws Exception {

        Socket s = new Socket("localhost", 8080);

        PrintWriter writer = new PrintWriter(s.getOutputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
        writer.println("hello");
        writer.flush();
        System.out.println(reader.readLine());
        s.close();
    }

}