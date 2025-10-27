public enum Subject {
    // Define predefined subjects as enum constants
    DATABASE_SYSTEMS("Computer Science", "Database Systems", 4, 2, 3),
    OPERATING_SYSTEMS("Computer Science", "Operating Systems", 4, 2, 4);
    
    // Instance fields for each enum constant
    private final String major;
    private final String name;
    private final int semester;
    private final int year;
    private final int credits;

    // Private constructor for enum constants
    Subject(String major, String name, int semester, int year, int credits) {
        this.major = major;
        this.name = name;
        this.semester = semester;
        this.year = year;
        this.credits = credits;
    }
    
    // Getters
    public String getMajor() {
        return major;
    }
    
    public String getName() {
        return name;
    }
    
    public int getSemester() {
        return semester;
    }
    
    public int getYear() {
        return year;
    }
    
    public int getCredits() {
        return credits;
    }
}
