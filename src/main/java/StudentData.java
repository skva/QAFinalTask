import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

public class StudentData {
    private String student;
    private String curriculum;
    private LocalDateTime date;
    private int duration;

    public StudentData(String student, String curriculum, LocalDateTime date, int duration){
        this.student = student;
        this.curriculum = curriculum;
        this.date = date;
        this.duration = duration;
    }

    //getters
    public String getStudent() {
        return student;
    }
    public String getCurriculum() {
        return curriculum;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public int getDuration() {
        return duration;
    }

    //setters
    public void setStudent(String student) {
        this.student = student;
    }
    public void setCurriculum(String curriculum) {
        this.curriculum = curriculum;
    }
    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
