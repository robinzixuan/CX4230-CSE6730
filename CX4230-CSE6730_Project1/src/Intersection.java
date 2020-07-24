

import java.util.*;

public class Intersection {

    private Engine waitingEngine;
    private Engine priorityEngine;
    private Engine[] policeEngines;

    public Intersection() {
        this.waitingEngine = new Engine();
        this.priorityEngine = new Engine();
        this.policeEngines = new Engine[4];
        int totalPolices = 4;
        for (int i = 0; i < totalPolices; i++) {
            this.policeEngines[i] = new Engine();
        }
    }

    public Intersection(int totalPolices) {
        this.waitingEngine = new Engine();
        this.priorityEngine = new Engine();
        this.policeEngines = new Engine[totalPolices];
        for (int i = 0; i < totalPolices; i++) {
            this.policeEngines[i] = new Engine();
        }
    }

    public Intersection(Engine[] policeEngines) {
        this.waitingEngine = new Engine();
        this.priorityEngine = new Engine();
        this.policeEngines = policeEngines;
    }

    public void waiting(Event e) {
        if (!e.getNextEventState().equals("answering")) {
            return;
        }
        String neighborhood = e.getNeighborhood();
        if (neighborhood.equals("North Buckhead") || neighborhood.equals("Lenox") ||
            neighborhood.equals("Midtown") || neighborhood.equals("Atlantic Station") ) {
                priorityEngine.eventList.add(e);
        } else {
            waitingEngine.eventList.add(e);
        }
    }

    public void waiting_process(Police[] policeList) {
        int policeId;
        Event e;
        for (int i = 0; i < policeList.length; i++) {
            policeId = policeList[i].getId();
            // System.out.println(!priorityEngine.eventList.isEmpty());
            if (!priorityEngine.eventList.isEmpty()) {
                e = priorityEngine.eventList.remove();
                e.setDispatcherID(policeId);
                policeList[policeId].setReportNum(e.getReportNum());
                policeList[policeId].setHandledEvents(e);
                this.policeEngines[i].eventList.add(e);
            } else {
                if (!waitingEngine.eventList.isEmpty()) {
                    e = waitingEngine.eventList.remove();
                    e.setDispatcherID(policeId);
                    policeList[policeId].setReportNum(e.getReportNum());
                    policeList[policeId].setHandledEvents(e);
                    this.policeEngines[i].eventList.add(e);
                }
            }
        }
    }

    private static int compare(Event a, Event b) {
        return a.getBirthDate().compareTo(b.getBirthDate());
    }

    public Event updateIntersection(Police[] policeList) {
        int earliest = 0;
        for (int i = 1; i < policeList.length; i++) {
            if (this.policeEngines[earliest].eventList.head() == null) {
                earliest = i;
            }
            else if (this.policeEngines[i].eventList.head() != null
                && compare(this.policeEngines[earliest].eventList.head(),
                this.policeEngines[i].eventList.head()) > 0){
                earliest = i;
            }
        }
        if (this.policeEngines[earliest].eventList.head() == null) {
            return null;
        }
        int policeId = earliest;
        Event e;
        String[] states = {"waiting", "answering", "handling"};
        Event removedEvent = this.policeEngines[policeId].eventList.remove();
        if (removedEvent.getNextEventState().equals(states[0])) {
            if (!priorityEngine.eventList.isEmpty()) {
                e = priorityEngine.eventList.remove();

                e = new Event(e.getNeighborhood(), e.getReportNum(),
                    policeId, e.getTimestamp(), e.getOccurTime(),
                    removedEvent.getBirthDate(), removedEvent.getDuration());
                policeList[policeId].setReportNum(e.getReportNum());
                policeList[policeId].setHandledEvents(e);
                this.policeEngines[policeId].eventList.add(e);
            } else {
                if (!waitingEngine.eventList.isEmpty()) {
                    e = waitingEngine.eventList.remove();

                    e = new Event(e.getNeighborhood(), e.getReportNum(),
                        policeId, e.getTimestamp(), e.getOccurTime(),
                        removedEvent.getBirthDate(), removedEvent.getDuration());
                    policeList[policeId].setReportNum(e.getReportNum());
                    policeList[policeId].setHandledEvents(e);
                    this.policeEngines[policeId].eventList.add(e);
                }
            }
        } else {
            e = new Event(removedEvent.getNeighborhood(), removedEvent.getReportNum(),
                removedEvent.getDispatcherID(), removedEvent.getNextEventState(),
                removedEvent.getTimestamp(), removedEvent.getOccurTime(),
                removedEvent.getBirthDate(), removedEvent.getDuration());
            this.policeEngines[policeId].eventList.add(e);
        }

        return removedEvent;
    }

    public Engine getWaitingEngine() {
        return this.waitingEngine;
    }

    public Engine getPriorityEngine() {
        return this.priorityEngine;
    }

    public Engine[] getPoliceEngines() {
        return this.policeEngines;
    }

    public boolean allEmpty() {
        boolean check = true;
        for (Engine engine : policeEngines) {
            if (engine.eventList.size() != 0) {
                check = false;
            }
        }
        return check;
    }

}

