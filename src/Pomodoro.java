import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Pomodoro {
    private static final int POMODORO_DURATION = 25; // minutes
    private static final int SHORT_BREAK = 5; // minutes
    private static final int LONG_BREAK = 15; // minutes
    private static final int POMODOROS_BEFORE_LONG_BREAK = 4;
    
    private float rating;
    private int numberOfPomodoros;
    private int totalStudyTime; // in minutes
    private LocalTime startTime;
    
    public Pomodoro(float rating) {
        this.rating = rating;
        this.numberOfPomodoros = calculateNumberOfPomodoros(rating);
        this.totalStudyTime = calculateTotalTime();
        this.startTime = LocalTime.now();
    }
    
    // Calculate number of pomodoros based on rating (1.0 to 5.0)
    // Rating 1.0 → 1 pomodoro (25 min study)
    // Rating 5.0 → 6 pomodoros (150 min study + breaks ≈ 180 min total)
    private int calculateNumberOfPomodoros(float rating) {
        // Linear mapping: rating 1-5 maps to 1-6 pomodoros
        int pomodoros = (int) Math.round(rating * 1.25);
        
        // Ensure at least 1 pomodoro
        if (pomodoros < 1) {
            pomodoros = 1;
        }
        
        return pomodoros;
    }
    
    // Calculate total time including breaks
    private int calculateTotalTime() {
        int studyTime = numberOfPomodoros * POMODORO_DURATION;
        int numberOfLongBreaks = (numberOfPomodoros - 1) / POMODOROS_BEFORE_LONG_BREAK;
        int numberOfShortBreaks = (numberOfPomodoros - 1) - numberOfLongBreaks;
        int breakTime = (numberOfShortBreaks * SHORT_BREAK) + (numberOfLongBreaks * LONG_BREAK);
        
        return studyTime + breakTime;
    }
    
    // Display the complete schedule
    public void displaySchedule() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime currentTime = startTime;
        
        System.out.println("=".repeat(50));
        System.out.println("POMODORO STUDY SESSION SCHEDULE");
        System.out.println("=".repeat(50));
        System.out.println("Material Rating: " + String.format("%.2f", rating) + "/5.0");
        System.out.println("Number of Pomodoros: " + numberOfPomodoros);
        System.out.println("Total Session Time: " + totalStudyTime + " minutes");
        System.out.println("Start Time: " + currentTime.format(timeFormatter));
        System.out.println("=".repeat(50));
        System.out.println();
        
        for (int i = 1; i <= numberOfPomodoros; i++) {
            // Study interval
            LocalTime studyStart = currentTime;
            LocalTime studyEnd = currentTime.plusMinutes(POMODORO_DURATION);
            
            System.out.println("📚 POMODORO #" + i + ": STUDY");
            System.out.println("   " + studyStart.format(timeFormatter) + " - " + studyEnd.format(timeFormatter) + 
                             " (" + POMODORO_DURATION + " minutes)");
            
            currentTime = studyEnd;
            
            // Break interval (not after the last pomodoro)
            if (i < numberOfPomodoros) {
                boolean isLongBreak = (i % POMODOROS_BEFORE_LONG_BREAK == 0);
                int breakDuration = isLongBreak ? LONG_BREAK : SHORT_BREAK;
                String breakType = isLongBreak ? "LONG BREAK" : "SHORT BREAK";
                
                LocalTime breakStart = currentTime;
                LocalTime breakEnd = currentTime.plusMinutes(breakDuration);
                
                System.out.println("☕ " + breakType);
                System.out.println("   " + breakStart.format(timeFormatter) + " - " + breakEnd.format(timeFormatter) + 
                                 " (" + breakDuration + " minutes)");
                
                currentTime = breakEnd;
            }
            
            System.out.println();
        }
        
        System.out.println("=".repeat(50));
        System.out.println("✅ Session End Time: " + currentTime.format(timeFormatter));
        System.out.println("=".repeat(50));
    }
    
    // Getters
    public float getRating() {
        return rating;
    }
    
    public int getNumberOfPomodoros() {
        return numberOfPomodoros;
    }
    
    public int getTotalStudyTime() {
        return totalStudyTime;
    }
    
    public LocalTime getStartTime() {
        return startTime;
    }
}
