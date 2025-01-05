package Log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Log {

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


