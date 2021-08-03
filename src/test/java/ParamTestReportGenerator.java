import com.griddynamics.qa.course.model.StudentData;
import com.griddynamics.qa.course.service.ReportGenerator;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ParamTestReportGenerator {
    private StudentData st;
    private LocalDateTime date;
    private int expectedResult;
    private ReportGenerator rep;

    public ParamTestReportGenerator(StudentData st, LocalDateTime date, int expectedResult) {
        super();
        this.st = st;
        this.date = date;
        this.expectedResult = expectedResult;
    }

    @Before
    public void initialize() {
        rep = new ReportGenerator();
    }

    @Parameterized.Parameters
    public static Collection input() {
        StudentData st1 = new StudentData("Ivanov Ivan1", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
        StudentData st2 = new StudentData("Ivanov Ivan2", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
        LocalDateTime date1 = LocalDateTime.of(2020, 8, 7, 17, 00);
        LocalDateTime date2 = LocalDateTime.of(2021, 8, 7, 17, 00);

        return Arrays.asList(new Object[][] {{st1, date1, 399}, {st2, date2, 2480}});
    }

    @Test
    public void testGetAllHoursWithParams() {
        Assertions.assertEquals(expectedResult, rep.getAllHours(st, date));
    }


}
