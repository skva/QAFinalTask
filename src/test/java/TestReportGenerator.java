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
    LocalDateTime dateTime = LocalDateTime.of(2020, 8, 7, 17, 00);
    LocalDateTime endDateForTest = LocalDateTime.of(2020, 6, 9, 10, 00);

    LocalDateTime beforeDate = LocalDateTime.of(2019, 6, 9, 10, 00);

    @Test
    public void testGetAllHours() {
        int hours = rep.getAllHours(st, dateTime);
        Assertions.assertEquals(399, hours);
    }
//    @Test
//    public void testGetAllHoursWithBeforeDate() {
//        int hours = rep.getAllHours(st, beforeDate);
//        Assertions.assertEquals(1, hours, "before start date");
//    }
    @Test
    public void testDaysDiff() {
        int numberOfDays = rep.getDaysDiff(st, dateTime);
        Assertions.assertEquals(49, numberOfDays);
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
