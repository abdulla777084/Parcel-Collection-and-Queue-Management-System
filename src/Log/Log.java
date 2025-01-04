package Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {


    //log file - all events saved to text after iteration is over

    //Log.Log class - to create a single log instance for containing log of events and related data.
    //You can use the inbuilt StringBuffer class to add these events to an instance of it.

    //ensure to use the Singleton pattern in a Log class which is used to record every event (i.e. customer joins queue, customer is removed from queue, processing details are displayed etc.)
    //The Log.Log file is finally written to a text file and should be easy to read - string and not object

    //The singleton pattern ensures only one instance exists across the application
    private static Log instance;

    private final StringBuffer logBuffer = new StringBuffer();

    public static Log getInstance() {
        if (instance == null) {instance = new Log();}
        return instance;
    }


    public void addEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    public void saveLogToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {

            addEvent("Log saved to " + filename);
            writer.write(logBuffer.toString());

            writer.flush();
        } catch (IOException e) {e.printStackTrace();}
    }
}


