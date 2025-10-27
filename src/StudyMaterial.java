import java.time.LocalDate;

public class StudyMaterial{
    private int difficulty;
    private int understanding;

    private String name;
    private String file;

    private float rating;
    private int time; // in minutes

    private boolean isStudying;
    private boolean isReviewing;

    private LocalDate dueDate;
    private LocalDate dateAdded;
    
    private Subject subject;

    public StudyMaterial(String name, String file, int difficulty, int understanding, LocalDate dueDate, Subject subject) {
        this.name = name;
        this.file = file;
        this.difficulty = difficulty;
        this.understanding = understanding;
        this.time = 0;
        this.isStudying = true;
        this.isReviewing = false;

        this.dateAdded = LocalDate.now();
        this.dueDate = dueDate;
        this.subject = subject;  // Just store the enum reference

        this.rating = computeRating(difficulty, understanding, dateAdded, dueDate);
    }

    public int getDifficulty() {
        return difficulty;
    }
    public int getUnderstanding() {
        return understanding;
    }
    public String getName() {
        return name;
    }
    public String getFile() {
        return file;
    }

    public float getRating() {
        return rating;
    }
    public int getTime() {
        return time;
    }
    public boolean isStudying() {
        return isStudying;
    }
    public boolean isReviewing() {
        return isReviewing;
    }

    public float computeRating(int difficulty, int understanding, LocalDate dateAdded, LocalDate dueDate) {
        // Calculate days between added date and due date
        long daysDifference = java.time.temporal.ChronoUnit.DAYS.between(dateAdded, dueDate);
        
        // Clamp to reasonable bounds (treat overdue/same-day as 1, max as 98)
        if (daysDifference <= 0) {
            daysDifference = 1;
        } else if (daysDifference > 98) {
            daysDifference = 98;
        }
        
        // Urgency factor: inversely proportional to days (1 = most urgent, 98 = least urgent)
        // Normalize to 1-10 scale where 1 day = 10, 98 days = 1
        double urgency = 10.0 - (9.0 * (daysDifference - 1) / 97.0);
        
        // Combined rating using weights similar to your old formula:
        // 40% difficulty (directly proportional)
        // 30% understanding (inversely proportional)
        // 30% urgency (inversely proportional to days)
        double rating = (0.4 * difficulty) + (0.3 * (11 - understanding)) + (0.3 * urgency);
        
        // Normalize to 1-5 scale
        // Max possible: (0.4 * 10) + (0.3 * 10) + (0.3 * 10) = 10
        // Min possible: (0.4 * 1) + (0.3 * 1) + (0.3 * 1) = 1
        float finalRating = (float) ((rating - 1.0) * 4.0 / 9.0 + 1.0);
        
        return finalRating;
    }

    public Subject getSubject() {
        return subject;
    }
}
