package utils;

public class StopWatch {

    private Long startMs = null;
    private Long endMs = null;

    public void start(){
        startMs = System.currentTimeMillis();
        endMs = null;
    }

    public void stop(){
        endMs = System.currentTimeMillis();
    }

    @Override
    public String toString() {
        if(startMs == null) return "Stop watch was not started!";
        if(endMs == null) return "Stop watch is running!";
        return "Stopped time: " + (endMs - startMs);
    }
}
