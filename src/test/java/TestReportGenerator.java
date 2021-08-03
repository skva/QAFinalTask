import com.griddynamics.qa.course.model.StudentData;
import com.griddynamics.qa.course.service.ReportGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;

public class TestReportGenerator {

    ReportGenerator rep = new ReportGenerator();
    StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
            LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
    LocalDateTime date = LocalDateTime.of(2020, 7, 7, 10, 00);
    LocalDateTime dateTime = LocalDateTime.of(2020, 8, 7, 17, 00);
    LocalDateTime endDateForTest = LocalDateTime.of(2020, 6, 9, 10, 00);

    @Test
    public void testGetAllHours() {
        int hours = rep.getAllHours(st, date);
        Assertions.assertEquals(200, hours);
    }
    @Test
    public void testDaysDiff() {
        int numberOfDays = rep.getDaysDiff(st, date);
        Assertions.assertEquals(25, numberOfDays);
    }
    @Test
    public void testHoursDiff() {
        int numberOfHours = rep.getHoursDiff(st, dateTime);
        Assertions.assertEquals(7, numberOfHours);
    }
    @Test
    public void testEndDate() {
        Assertions.assertEquals(endDateForTest, rep.getEndDate(st));
    }
}
