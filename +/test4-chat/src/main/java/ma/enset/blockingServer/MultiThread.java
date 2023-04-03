package ma.enset.blockingServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThread extends Thread {
    int clientCount;
    public static void main(String[] args) {
        new MultiThread().start();
    }

    @Override
    public void run() {
        System.out.println("the server is started using the port 2");
        try {
            ServerSocket ss = new ServerSocket(2);
            while (true) {
                Socket socket = ss.accept();
                ++clientCount;
                new Conversation(socket, clientCount).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    class Conversation extends Thread {
        private Socket socket;
        private int clientID;
        public Conversation(Socket socket, int clientID) {
            this.socket = socket;
            this.clientID = clientID;
        }

        @Override
        public void run() {
            try {
                InputStream is = socket.getInputStream();
                InputStreamReader isr = new InputStreamReader(is); //read one caracter
                BufferedReader br = new BufferedReader(isr); //read chaine caracter

                OutputStream os = socket.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true); // pr.println() va etre envoyer automatiquement aux client

                String ip = socket.getRemoteSocketAddress().toString();
                System.out.println("New Client connection : " + clientID + "\n  IP : " + ip);
                pw.println("Welcome, your ID is : " + clientID);

                while (true) {
                    String request = br.readLine();
                    System.out.println("New Request =>\t" + "IP : " + ip + "\n  request : " + request);
                    String res = "Size = " + request.length();
                    pw.println(res);
                }
                /*               __cas true__
                    PrintWriter pr=new PrintWriter(osw,false); za
                    pr.println("response");
                    pr.flush();
                 */

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

