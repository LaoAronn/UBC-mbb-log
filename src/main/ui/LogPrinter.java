package ui;

import model.EventLog;
import model.exception.LogException;

// CODE REFERENCE: Alarm System
public interface LogPrinter {

    void printLog(EventLog el) throws LogException;
}
