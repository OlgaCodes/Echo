import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.DelayQueue;

public class Server {
    public static List<ClientHandler> clients = new ArrayList<>();
    public static void main(String[] args) {
        int PORT = 5321;
        DelayQueue delayQueue = new DelayQueue<Item>();
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                Socket socket = serverSocket.accept();

                Random random = new Random();
                int id = random.nextInt(100);

                System.out.println("Client" + id + " connected");

                ClientHandler clientHandler = new ClientHandler(socket, id, delayQueue);
                clients.add(clientHandler);
                Thread t2=new Thread(clientHandler);
                t2.start();
                Thread t = new Thread(new Queue(delayQueue));
                t.start();

            }
        } catch (IOException IOE) {
            System.out.println("Connection with Client failed!");
        }
    }
}
