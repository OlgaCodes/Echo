import java.io.DataOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

public class Queue implements Runnable{
    private DelayQueue queue;
    private DataOutputStream out;
    public Queue(DelayQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Item item=null;
        while (true) {
            if (queue.size() > 0) {
                try {
                    item = (Item) queue.take();
                    out = new DataOutputStream(item.socket.getOutputStream());

                    LocalTime time = LocalTime.now();
                    LocalTime requestedTime = LocalTime.parse(item.time, DateTimeFormatter.ofPattern("H:m"));

                    if (time.getHour() == requestedTime.getHour() && time.getMinute() == requestedTime.getMinute()) {
                        out.writeUTF(item.message);
                    }
                    else if(requestedTime.isBefore(time))
                        throw new WrongTimeException("This our is not available!",time,requestedTime);
                    else {
                        queue.add(item);
                    }
                } catch (InterruptedException | IOException e) {
                    e.printStackTrace();
                    System.out.println("Try to connect again.");
                    return;
                } catch (WrongTimeException e){
                    try {
                        out.writeUTF(e.getMessage());
                        out.writeUTF("Now the time is: "+e.getTimeNow()+"and you asked for: "+ e.getTimeOfRequestedItem());
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

            }
        }
    }
}