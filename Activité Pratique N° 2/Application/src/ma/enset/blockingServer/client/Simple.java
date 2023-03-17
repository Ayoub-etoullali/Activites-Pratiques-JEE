package ma.enset.blockingServer.client;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Simple {
    public static void main(String[] args) throws IOException {

        Socket socket=new Socket("localhost",5);
        Scanner scanner=new Scanner(System.in);

        InputStream is=socket.getInputStream();
        OutputStream os=socket.getOutputStream();

        System.out.print("\nSaiser un nombre entier : "); // moins de 25 => 250
        int nb = scanner.nextInt();
        os.write(nb);
        int rep=is.read();
        System.out.println("La réponse = "+rep);
    }
}
