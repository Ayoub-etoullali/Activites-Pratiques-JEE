package ma.enset.blockingServer.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class MyTelnet {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 5);

            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is); //read one caracter
            BufferedReader br = new BufferedReader(isr); //read chaine caracter

            OutputStream os = socket.getOutputStream();
            PrintWriter pw = new PrintWriter(os, true);

            new Thread(() -> {
                try {

                    String req;
                    while ((req = br.readLine()) != null) {
                        String res = br.readLine();
                        System.out.println(res);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();

            while (true) {
                String request = scanner.nextLine();
                pw.println(request);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
