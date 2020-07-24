import java.util.Date;

public class Data {

    private int id;
    private int reportNumber;
    private String occurDate;
    private String occurTime;
    private String neighborhood;

    public Data(int id, int reportNumber, String occurDate, String occurTime, String neighborhood) {
        this.id = id;
        this.reportNumber = reportNumber;
        this.occurDate = occurDate;
        this.occurTime = occurTime;
        this.neighborhood = neighborhood;
    }

    public int getId() {
        return id;
    }

    public int getReportNumber() {
        return reportNumber;
    }

    public String getOccurDate() {
        return occurDate;
    }

    public String getOccurTime() {
        return occurTime;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setReportNumber(int reportNumber) {
        this.reportNumber = reportNumber;
    }

    public void setOccurDate(String occurDate) {
        this.occurDate = occurDate;
    }

    public void setOccurTime(String occurTime) {
        this.occurTime = occurTime;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String toString() {
        return  id + ", " + reportNumber + ", " + occurDate + ", " + occurTime + ", " + neighborhood;
    }
}