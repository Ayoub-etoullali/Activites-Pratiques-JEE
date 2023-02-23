package ma.enset.blockingServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThread {
    public static void main(String[] args) throws IOException{

        System.out.println("the server is started using the port 1");

        ServerSocket ss = new ServerSocket(1);

        System.out.println("\nI'm waiting new connection");
        Socket socket= ss.accept();
        InputStream is=socket.getInputStream(); //N.B// l'échange de données ne dépasse pas 1 octet
        OutputStream os=socket.getOutputStream();

        System.out.println("I'm waiting data");
        int nbr = is.read();
        int res=nbr*10; //operation

        System.out.println("I'm sending response");
        os.write(res);

        socket.close();
    }
}
