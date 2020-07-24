import java.io.IOException;
import java.util.*;
import java.lang.*;
import java.io.*;



public class main {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Police number:");
        String policeNumber = input.nextLine();
        int policeNum = Integer.parseInt(policeNumber);
        if (policeNum <= 0) {
            System.out.println("Could not input the police number small than 1. Enter Police number:");
            policeNumber = input.nextLine();
            policeNum = Integer.parseInt(policeNumber);
        }

        int period = 100;
        int capacity = 10;
        boolean begin = true;
        String eventStr = "";
        PrintWriter out_csv0;
        Police[] polices = new Police[policeNum];
        for (int i = 0; i < policeNum; i++) {
            polices[i] = new Police(i);
        }

        //sleep
        String path = "sampleCOBRA-2019.csv";
        //String path = "sample2019.csv";
        Map<Integer, Data> calls = FileIO.readData(path);
        int count = 0;
        int idx = 0;
        String Policetxt ="";
        Intersection eventEngine = new Intersection(policeNum);
        Event removedEvent = null;

        int countNumber = 0;
        boolean importFinish = false;
        out_csv0 = new PrintWriter(new FileWriter("EventEngine.csv", true));
        while (!calls.isEmpty() || removedEvent != null || !eventEngine.allEmpty()) {
            Policetxt ="";
            // to ensure have enough calling events in waiting list
            if (eventEngine.getWaitingEngine().eventList.size() < 4) {
                count = eventEngine.getWaitingEngine().eventList.size();
            }
            while (count < capacity && !importFinish) {
                count++;
                Data e = calls.remove(idx);
                idx++;
                while (e == null && !importFinish) {
                    e = calls.remove(idx);
                    idx++;
                    if (calls.isEmpty()) {
                        importFinish = true;
                    }
                }
                if (e != null) {
                    Event event = new Event(e.getNeighborhood(), e.getReportNumber(), e.getOccurTime(), e.getOccurDate());
                        eventEngine.waiting(event);
                }
            }

            if (begin) {
                begin = false;
                eventEngine.waiting_process(polices);
            }
            removedEvent = eventEngine.updateIntersection(polices);
            countNumber++;
            if (removedEvent != null) {
                System.out.println(removedEvent.toString());
                System.out.println(countNumber);
                eventStr += countNumber;
                eventStr += "\n";
                eventStr += removedEvent.toString();
                eventStr += "\n";
            }

            

            for (int i = 0; i < polices.length; i++) {
                Event[] EngineList0 = (eventEngine.getPoliceEngines())[i].eventList.getPQ().toArray(new Event[(eventEngine.getPoliceEngines())[i].eventList.size()]);
                for (Event e0 : EngineList0) { 
                    out_csv0.println(e0.getReportNum() + "," + e0.getDuration() + "," + e0.getPreEventState()  + "," + e0.getBirthDate() + "," + e0.getOccurTime());
                }
            }
            System.out.println(removedEvent != null);
            System.out.println(removedEvent != null);
            System.out.println(!eventEngine.allEmpty());
        }


        PrintWriter out = new PrintWriter(new FileWriter("Event.txt", true));
        out.println(eventStr);
        out.close();
        out_csv0.close();
    }
}