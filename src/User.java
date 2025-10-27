public class User {
    private String name;
    private int semester;
    private String major;
    private int id;

    public User(String name, int semester, String major, int id) {
        this.name = name;
        this.semester = semester;
        this.major = major;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public int getSemester() {
        return semester;
    }
    public String getMajor() {
        return major;
    }
    public int getId() {
        return id;
    }
}
