import java.net.Socket;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Item implements Delayed {
    public String time;
    public String message;
    public Socket socket;

    public Item(String time, String message,Socket socket) {
        this.time = time;
        this.message = message;
        this.socket = socket;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return 0;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}

