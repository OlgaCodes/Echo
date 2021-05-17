import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        String SERVER_IP = "127.0.0.1";
        int PORT = 5321;

        try {
            Socket socket = new Socket(SERVER_IP, PORT);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Connected");

            Scanner scanner = new Scanner(System.in);
            Message message = new Message(in);
            message.start();

            while (true) {
                String choice = scanner.nextLine();
                switch (choice) {
                    case "M","m":
                    {
                        out.writeUTF(choice);
                        out.writeUTF(getMessage());
                        out.writeUTF(getTime());
                        break;
                    }
                    default:
                        System.out.println("Your choice is wrong! Try Again.");
                        break;
                }
                if (choice.equals("E") || choice.equals("e")) {
                    break;
                }
            }
            in.close();
            out.close();
            socket.close();
        } catch (IOException IOE) {
            System.out.println("Failed to connect to Server.");
        } finally {
            System.out.println("Disconnected.");

        }
    }
    public static String getMessage(){
        Scanner scanner=new Scanner(System.in);
        String mess=scanner.nextLine();
        return mess;
    }
    public static String getTime(){
        Scanner scanner=new Scanner(System.in);
        String time=scanner.nextLine();
        return time;
    }
}
