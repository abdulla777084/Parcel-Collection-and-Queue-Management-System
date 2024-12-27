public class Log {

        //LOG STUFF IS IN THE MANAGER NOT WORKER ?
    //log file - all events saved to text after iteration is over

    //Log class - to create a single log instance for containing log of events and related data.
    //You can use the inbuilt StringBuffer class to add these events to an instance of it.

    //ensure to use the Singleton pattern in a Log class which is used to record every event (i.e. customer joins queue, customer is removed from queue, processing details are displayed etc.)
    //The Log file is finally written to a text file and should be easy to read - string and not object



        //The singleton pattern ensures only one instance
        //exists across the application
        private static Log instance;

        public static Log getInstance() {if (instance == null) {instance = new Log();}return instance;}



}

/*
        public class Log {
    private static Log instance;
    private StringBuilder logBuffer;

    private Log() {
        logBuffer = new StringBuilder();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void addEvent(String event) {
        logBuffer.append(event).append("\n");
    }

    public void writeLogToFile(String filename) {
        try {
            Files.write(Paths.get(filename), logBuffer.toString().getBytes());
        } catch (IOException e) {
            System.err.println("Error writing log to file: " + e.getMessage());
        }
    }
}

*/
