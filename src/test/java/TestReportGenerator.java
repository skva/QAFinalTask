import com.griddynamics.qa.course.model.StudentData;
import com.griddynamics.qa.course.service.ReportGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;

public class TestReportGenerator {
    StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
            LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
    LocalDateTime date = LocalDateTime.of(2020, 7, 7, 10, 00);
    LocalDateTime dateTime = LocalDateTime.of(2020, 8, 7, 17, 00);
    LocalDateTime endDateForTest = LocalDateTime.of(2020, 6, 9, 10, 00);


    @Test
    public void testDaysDiff() {
        int numberOfDays = 0;
        while (date.isBefore(dateTime.minus(1, ChronoUnit.DAYS))) {
            if (date.getDayOfWeek() != (DayOfWeek.SATURDAY)
                    && (date.getDayOfWeek() != (DayOfWeek.SUNDAY))) {
                numberOfDays++;
            }
            date = date.plus(1, ChronoUnit.DAYS);
        }
        Assertions.assertEquals(23, numberOfDays);
    }
    @Test
    public void testDiffHours() {
        int numberOfHours = 0;
        if (dateTime.getDayOfWeek() != DayOfWeek.SATURDAY && dateTime.getDayOfWeek() != DayOfWeek.SUNDAY
                && dateTime.getHour() < 18) {
            while (date.getHour() < dateTime.getHour()) {
                numberOfHours++;
                dateTime = dateTime.minus(1, ChronoUnit.HOURS);
            }
        }
        if (dateTime.getHour() > 18) {
            numberOfHours = 8;
        }
        if ((dateTime.getDayOfWeek() == DayOfWeek.SATURDAY) || (dateTime.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            numberOfHours = 0;
        }
        Assertions.assertEquals(7, numberOfHours);
    }
    @Test
    public void testAllHours() {
        ReportGenerator rep = new ReportGenerator();
        int hours;
        hours = rep.getDaysDiff(st, date) * 8 + rep.getHoursDiff(st, date);
        Assertions.assertEquals(200, hours);
    }
    @Test
    public void testGetEndDate() {
        LocalDateTime endDate = st.getDate();
        //Count training end date except SA/SU
        for (int j = st.getDuration()/8; j > 0; j--) {
            if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                endDate = endDate.plus(1, ChronoUnit.DAYS);
            } if (endDate.getDayOfWeek() != DayOfWeek.SATURDAY && endDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                endDate = endDate.plus(1, ChronoUnit.DAYS);
            }
        }
        Assertions.assertEquals(endDateForTest, endDate);
    }
}
