package ma.enset.nonBlockingServer;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SingleThread {
    public static void main(String[] args) throws Exception {

        System.out.println("the server is started using the port 4");

        Selector selector=Selector.open();

        ServerSocketChannel serverSocketChannel=ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0",4));
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true){
            int readyCount=selector.select();
            if (readyCount==0) continue;
            Set<SelectionKey> selectionKeys=selector.selectedKeys();
            Iterator<SelectionKey> keys=selectionKeys.iterator();
            while (keys.hasNext()){
                SelectionKey selectionKey=keys.next();
                if (selectionKey.isAcceptable()) handleAccept(selector,selectionKey);
                if (selectionKey.isReadable()) handleReadWrite(selectionKey);
            }
        }
    }

    private static void handleAccept(Selector selector, SelectionKey Key) throws Exception {
        /* Get the ServerChannel using de the selection key */
        ServerSocketChannel serverSocketChannel= (ServerSocketChannel) Key.channel();
        /* Get the Client SocketChannel by accepting the connection request */
        SocketChannel client=serverSocketChannel.accept();
        /* Non Blocking I/O for the accepted connection */
        client.configureBlocking(false);
        /* Register the client for read/write operations (Here we have used it for read) */
        client.register(selector,SelectionKey.OP_READ);
        System.out.println("############################");
        /* Print the client Remote Adress*/
        System.out.println("New Connection :"+ client.getRemoteAddress());
        /* Print the name of the current thread to show that our server use only one thread */
        System.out.println("Thread:"+Thread.currentThread().getName());
    }

    private static void handleReadWrite( SelectionKey selectionKey)throws Exception {
        SocketChannel socketChannel= (SocketChannel) selectionKey.channel();
        /* Read bytes coming from the client */
        ByteBuffer byteBuffer = ByteBuffer.allocate(256);
        int numRead;
        numRead=socketChannel.read(byteBuffer);
        /* If the conection is closed by the client */
        if (numRead == -1) {
            Socket socket = socketChannel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection closed by client: " + remoteAddr);
            socketChannel.close();
            selectionKey.cancel();
            return;
        }
        /*Converting bytes de String message*/
        String message =new String(byteBuffer.array());
        System.out.println("********************");
        System.out.println("Nouveau message:"+message);
        System.out.println("Client :"+socketChannel.getRemoteAddress().toString());
        System.out.println("Thread:"+Thread.currentThread().getName());
        /* Preparing the response message */
        String resp="Bonjour "+message;
        byte[] data=resp.getBytes();
        byteBuffer=ByteBuffer.wrap(data);
        /*Send the response to the client*/
        socketChannel.write(byteBuffer);
    }
}
