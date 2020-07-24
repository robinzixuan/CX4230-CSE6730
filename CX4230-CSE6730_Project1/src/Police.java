import java.util.*;

public class Police {
    private int id;
    private int reportNum;
    private boolean available;
    private ArrayList<Event> handledEvents;

    public Police(int id) {
        this.id = id;
        this.reportNum = 0;
        this.available = true;
        this.handledEvents = new ArrayList<Event>();
    }


    public void setBusy() {
        this.available = false;
    }

    public void setAvaliable() {
        this.available = true;
    }

    public boolean getAvaliable() {
        return this.available;
    }

    public int getId() {
        return this.id;
    }

    public void setReportNum(int reportNum){
        this.reportNum = reportNum;
    }

    public int getReportNum(){
        return this.reportNum;
    }

    public ArrayList<Event> getHandledEvents() {
        return this.handledEvents;
    }

    public void setHandledEvents(Event e) {
        this.handledEvents.add(e);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        //TODO
        sb.append("id:" + id + "\n");
        sb.append("reportNum: " + reportNum + "\n");
        sb.append("available: " + available + "\n");
        sb.append("Duration: " + handledEvents.toString() + "\n");
        return sb.toString();
    }

}