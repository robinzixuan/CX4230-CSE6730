

import java.util.PriorityQueue;
import java.util.*;

public class Engine {

    class SortbyDatetime implements Comparator<Event> {
        public int compare(Event a, Event b) {
            return a.getOccurTime().compareTo(b.getOccurTime());
        }
    }

    class EventList {
        private PriorityQueue<Event> pq;
        private int count;

        public EventList() {
            this.count = 0;
            this.pq = new PriorityQueue<Event>(100, new SortbyDatetime());
        }

        public void add(Event event) {
            this.pq.add(event);
            this.count++;
        }

        public Event head() {
            return this.pq.peek();
        }

        public Event remove() {
            this.count--;
            return this.pq.poll();
        }


        public boolean remove(Event event) {
            this.count--;
            return this.pq.remove(event);
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public int size() {
            return count;
        }

        public PriorityQueue<Event> getPQ() {
            return this.pq;
        }
    }



    public EventList eventList = new EventList();

    public String toString() {
        StringBuilder sb = new StringBuilder();
        //TODO
        Event[] EngineList = eventList.getPQ().toArray(new Event[eventList.size()]);
        for (Event e : EngineList) {
            sb.append("Event:" + e.toString() + "\n");
        }
        return sb.toString();
    }

    public Engine() {}

}