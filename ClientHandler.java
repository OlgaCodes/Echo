import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

public class ClientHandler implements Runnable{
    private Socket socket;
    private int id;
    private DelayQueue queue;
    DataInputStream in;
    DataOutputStream out;

    public ClientHandler(Socket socket1, int id,DelayQueue queue1) throws IOException {
        socket = socket1;
        queue = queue1;
        this.id=id;
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(socket.getInputStream());
    }

    @Override
    public void run() {

        try {
            while (true) {
                out.writeUTF("What do you want to do? M/m- message, e/E- exit");
                String answer = in.readUTF();
                if (answer.equals("e")||answer.equals("E")) break;
                service(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("Client" + id + " disconnected");
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    public void service(Socket socket) throws IOException {
        out.writeUTF("What message you want to send?: ");
        String message = in.readUTF();
        if (!message.equals(""))
            System.out.println("Client: " + this.id + "has already sent message.");

        out.writeUTF("What time?(HH:mm): ");
        String time = in.readUTF();
        LocalTime parsedTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("H:m"));

        Item item = new Item(time, message, socket);
        queue.add(item);
    }

}
