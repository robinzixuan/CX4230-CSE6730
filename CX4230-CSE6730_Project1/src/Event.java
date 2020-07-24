

import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class Event{
    private int reportNum;
    private Date occurTime;
    private int dispatcherID;
    private long timestamp = System.currentTimeMillis();
    private String neighborhood;
    private String preEventState;
    private String nextEventState;
    private long duration;
    private Date birthDate;

    public Event() {}

    public Event(String neighborhood, int reportNum, String occurTime,
     String occurDate) {
        this.neighborhood = neighborhood;
        this.reportNum = reportNum;
        this.preEventState = "waiting";
        this.nextEventState = "answering";
        this.birthDate = new Date(this.timestamp);

        Random ran = new Random();
        double during = ran.nextGaussian() + 1.0;
        long duringMill = (long) (during * 60000);
        if (duringMill <= 0) {
            duringMill = 60000;
        }
        this.duration = duringMill;

        String correctFormatDate = "";
        if (occurDate.indexOf('/') >= 0) {
            String[] dateArr = occurDate.split("/");
            correctFormatDate += dateArr[2] + '-' + dateArr[1] + '-' + dateArr[0] + ' ';
        } else {
            correctFormatDate = occurDate;
        }

        int time = Integer.parseInt(occurTime);
        int hrs = time / 100;
        String hours = "" + hrs;
        if (hrs < 10) {
            hours = "0" + hrs;
        }
        int mins = time % 100;
        String minutes = "" + mins;
        if (mins < 10) {
            minutes = "0" + mins;
        }
        String timeInDateTime = correctFormatDate + " " + hours + ':' + minutes + ":00";
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date dateTime;
        try {
            dateTime = df.parse(timeInDateTime);
            this.occurTime = dateTime;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public Event(String neighborhood, int reportNum, int dispatcherID,
     String preEventState, long previousTimeStamp, Date preOccurTime,
     Date preBirthDate, long preDuration) {
        this.neighborhood = neighborhood;
        this.reportNum = reportNum;
        this.dispatcherID = dispatcherID;
        long diff = this.timestamp - previousTimeStamp;
        long prev = preOccurTime.getTime();
        this.occurTime = new Date(prev + diff);
        long prevBirth = preBirthDate.getTime();
        this.birthDate = new Date(prevBirth + preDuration);
        String[] states = {"waiting", "answering", "handling"};
        if (preEventState.equals(states[1])) {
            this.preEventState = states[1];
            this.nextEventState = states[2];
        }
        else if (preEventState.equals(states[2])) {
            this.preEventState = states[2];
            this.nextEventState = states[0];
        }
        else if (preEventState.equals(states[0])) {
            this.preEventState = states[0];
            this.nextEventState = states[1];
        }

        Random ran = new Random();
        double during = ran.nextGaussian() + 1.0;
        long duringMill = (long) (during * 60000);
        if (duringMill <= 0) {
            duringMill = 60000;
        }
        this.duration = duringMill;
    }

    public Event(String neighborhood, int reportNum, int dispatcherID,
     long timeStamp, Date occurTime,
     Date preBirthDate, long preDuration) {
        this.neighborhood = neighborhood;
        this.reportNum = reportNum;
        this.dispatcherID = dispatcherID;
        this.occurTime = occurTime;
        long prevBirth = preBirthDate.getTime();
        this.birthDate = new Date(prevBirth + preDuration);
        String[] states = {"waiting", "answering", "handling"};
        this.preEventState = states[0];
        this.nextEventState = states[1];

        Random ran = new Random();
        double during = ran.nextGaussian() + 1.0;
        long duringMill = (long) (during * 60000);
        if (duringMill <= 0) {
            duringMill = 60000;
        }
        this.duration = duringMill;
    }

    public int getReportNum() {
        return this.reportNum;
    }

    public Date getOccurTime() {
        return this.occurTime;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public int getDispatcherID() {
        return this.dispatcherID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public long getDuration() {
        return this.duration;
    }

    public String getNeighborhood() {
        return this.neighborhood;
    }

    public String getPreEventState() {
        return this.preEventState;
    }

    public String getNextEventState() {
        return this.nextEventState;
    }

    public void setTimeStamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setDispatcherID(int dispatcherID) {
        this.dispatcherID = dispatcherID;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        //TODO
        sb.append("Occur Time:" + occurTime + "\n");
        sb.append("Report Number: " + reportNum + "\n");
        sb.append("Police ID: " + dispatcherID + "\n");
        sb.append("Timestamp: " + timestamp + "\n");
        sb.append("neighorhood: " + neighborhood + "\n");
        sb.append("Previous Event State: " + preEventState + "\n");
        sb.append("Next Event State: " + nextEventState + "\n");
        sb.append("Duration: " + duration + "\n");
        sb.append("birthDate: " + birthDate + "\n");
        return sb.toString();
    }

}