package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Code Reference: the Alarm System Application
 * Represents a log of alarm system events.
 * We use the Singleton Design Pattern to ensure that there is only
 * one EventLog in the system and that the system has global access
 * to the single instance of the EventLog.
 */
public class EventLog implements Iterable<Event> {

    // the only EventLog in the system (Singleton Design )
    private static EventLog theLog;
    private Collection<Event> events;

    // EFFECTS: Prevent external construction.
    private EventLog() {
        events = new ArrayList<Event>();
    }


    // MODIFIES: theLog
    // EFFECTS: Gets instance of EventLog - creates i if it doesn't already exist.
    // (Singleton Design Pattern)
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    // REQUIRES: An event
    // MODIFIES: events
    // EFFECTS: Adds an event to the event log
    public void logEvent(Event e) {
        events.add(e);
    }

    // MODIFIES: events
    // EFFECTS: Clears the event log and logs the event.
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}