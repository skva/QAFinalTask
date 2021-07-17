import com.griddynamics.qa.course.model.StudentData;
import com.griddynamics.qa.course.service.InputScanner;
import com.griddynamics.qa.course.service.ReportGenerator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;


public class Main {
    public static void main(String[] agrs) {
        //Student data
        ArrayList<StudentData> students = new ArrayList<>();
        StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
        StudentData st2 = new StudentData("Sidorov Ivan", "J2EE Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 42);
        students.add(st);
        students.add(st2);

        ReportGenerator printRep = new ReportGenerator();
        InputScanner inputScanner = new InputScanner();
        if (inputScanner.answerType() == 0) {
            printRep.shortReport(st);
        } else {
            printRep.fullReport(st);
        }
    }
}






