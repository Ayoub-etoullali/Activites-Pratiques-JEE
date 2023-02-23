package ma.enset.blockingServer;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultiThreadChat extends Thread {
    private List<Conversation> conversations = new ArrayList<>();
    int clientCount;

    public static void main(String[] args) {
        new MultiThreadChat().start();
    }

    @Override
    public void run() {
        System.out.println("the server is started using the port 3");
        try {
            ServerSocket ss = new ServerSocket(3);
            while (true) {
                Socket socket = ss.accept();
                ++clientCount;
                Conversation conversation = new Conversation(socket, clientCount);
                conversations.add(conversation);
                conversation.start();
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

                    String message;
                    List<Integer> clientsTo = new ArrayList<>();
                    if (request.contains("=>")) {
                        String[] req = request.split("=>");
                        String clients = req[0];
                        message = req[1];
                        if (clients.contains(",")) {
                            String[] c = clients.split(",");
                            for (int i = 0; i < c.length; i++) {
                                clientsTo.add(Integer.parseInt(c[i]));
                            }
//                            for (                                                                                             String s:c){
//                                clientsTo.add(Integer.parseInt(s));
//                            }
                        } else {
                            clientsTo.add(Integer.parseInt(clients));
                        }
                    } else {
                        clientsTo = conversations.stream().map(c -> c.clientID).collect(Collectors.toList());
                        System.out.println("!!" + clientsTo + "!!");
                        message = request;
                    }
                    broadcastMessage(message, this, clientsTo);
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

    public void broadcastMessage(String message, Conversation from, List<Integer> clients) {
        try {
            for (Conversation conversation : conversations) {
                if (conversation != from && clients.contains(conversation.clientID)) {
                    Socket socket = conversation.socket;
                    OutputStream os = socket.getOutputStream();
                    PrintWriter pr = new PrintWriter(os, true);
                    pr.println(message);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

