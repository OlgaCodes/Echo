import java.time.LocalTime;

public class WrongTimeException  extends Exception{
    private LocalTime timNow;
    private LocalTime time2;

    public WrongTimeException(String message,LocalTime time1, LocalTime time2) {
        super(message);
        this.timNow=time1;
        this.time2=time2;
    }

    public LocalTime getTimeNow(){
        return timNow;
    }
    public LocalTime getTimeOfRequestedItem(){
        return time2;
    }
}