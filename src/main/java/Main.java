import com.griddynamics.qa.course.model.StudentData;
import com.griddynamics.qa.course.service.InputScanner;
import com.griddynamics.qa.course.service.ReportGenerator;

import java.time.LocalDateTime;
import java.time.Month;


public class Main {
    public static void main(String[] agrs) {
        StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
        StudentData st2 = new StudentData("Sidorov Ivan", "J2EE Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 42);


        InputScanner inputScanner = new InputScanner();
        ReportGenerator printRep = new ReportGenerator(inputScanner);
        if (inputScanner.inputAnswerType() == 0) {
            printRep.printShortResultInfo();
            printRep.getShortReport(st);
            printRep.getShortReport(st2);
        } else {
            System.out.println("Full information:");
            printRep.getFullReport(st);
            printRep.getFullReport(st2);
        }
    }
}






