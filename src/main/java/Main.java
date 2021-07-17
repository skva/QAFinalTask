import com.griddynamics.qa.course.model.StudentData;
import com.griddynamics.qa.course.service.InputScanner;
import com.griddynamics.qa.course.service.ReportGenerator;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;


public class Main {
    public static void main(String[] agrs) {
        StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
        StudentData st2 = new StudentData("Sidorov Ivan", "J2EE Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 42);

        ReportGenerator printRep = new ReportGenerator();
        InputScanner inputScanner = new InputScanner();
        if (inputScanner.answerType() == 0) {
            printRep.shortReport(st);
            printRep.shortReport(st2);
        } else {
            System.out.println("Full information:");
            printRep.fullReport(st);
            printRep.fullReport(st2);
        }
    }
}






