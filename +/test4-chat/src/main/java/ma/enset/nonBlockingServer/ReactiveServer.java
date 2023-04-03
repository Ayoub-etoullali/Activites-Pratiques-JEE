package ma.enset.nonBlockingServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ReactiveServer {
    public static void main(String[] args) throws Exception {
        Selector selector=Selector.open();
        ServerSocketChannel ssc=ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.bind(new InetSocketAddress("localhost",5));
        ssc.register(selector,SelectionKey.OP_ACCEPT);
        while (true){
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey sk = iterator.next();
                if (sk.isAcceptable()){ handleAccept(selector,sk);}
                else if(sk.isReadable()){ handleRead(selector,sk); }
                iterator.remove();
            }}}
    private static void handleAccept(Selector selector,SelectionKey sk) throws IOException {
        ServerSocketChannel ssc= (ServerSocketChannel) sk.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector,SelectionKey.OP_READ);
    }
    private static void handleRead(Selector selector,SelectionKey sk) throws IOException {
        SocketChannel sc = (SocketChannel) sk.channel();
        ByteBuffer byteBuffer= ByteBuffer.allocate(1024);
        sc.read(byteBuffer);
        String request=new String(byteBuffer.array()).trim();
        String response="Request Size : "+request.length();
        ByteBuffer byteBufferResponse=ByteBuffer.allocate(1024);
        byteBufferResponse.put(response.getBytes());
        byteBufferResponse.flip();
        int writtenBytes = sc.write(byteBufferResponse);
        System.out.println("sending "+writtenBytes+" bytes");
    }
}