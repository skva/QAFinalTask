import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;
public class Main {
    public static void main(String[] agrs) {
        //Date from console
        Scanner scDay = new Scanner(System.in);
        System.out.println("Введите число:");
        int dayOf = scDay.nextInt();
        Scanner scMonth = new Scanner(System.in);
        System.out.println("Введите номер месяца:");
        int monthOf = scMonth.nextInt();
        Scanner scYear = new Scanner(System.in);
        System.out.println("Введите номер года:");
        int yearOf = scYear.nextInt();
        Scanner scHour = new Scanner(System.in);
        System.out.println("Введите время:");
        int hourOf = scHour.nextInt();
        LocalDateTime fromConsole = LocalDateTime.of(yearOf, monthOf, dayOf, hourOf, 0);

        //Student date
        StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 0), 56);

        //Difference between dates
        Duration diff = Duration.between(LocalDateTime.from(st.getDate()), LocalDateTime.from(fromConsole));

        //Loop full days difference between
        int numberOfDays = 0;
        LocalDateTime date1 = st.getDate();
        LocalDateTime date2 = fromConsole;
        while (date1.isBefore(date2.minus(1, ChronoUnit.DAYS))) {
            if (date1.getDayOfWeek() != (DayOfWeek.SATURDAY)
                    && (date1.getDayOfWeek() != (DayOfWeek.SUNDAY))) {
                numberOfDays++;
            }
            date1 = date1.plus(1, ChronoUnit.DAYS);
        }

        //Loop addhours difference between
        int numberOfHours = 0;
        LocalDateTime dateH1 = st.getDate();
        LocalDateTime dateH2 = fromConsole;
        if ((dateH2.getDayOfWeek() != DayOfWeek.SATURDAY) && (dateH2.getDayOfWeek() != DayOfWeek.SUNDAY)
                && (dateH2.getHour() < 18)) {
            while (dateH1.getHour() < dateH2.getHour()) {
                numberOfHours++;
                dateH2 = dateH2.minus(1, ChronoUnit.HOURS);
            }
        }
        if (dateH2.getHour() > 18) {
            numberOfHours = 8;
        }
        if ((dateH2.getDayOfWeek() == DayOfWeek.SATURDAY) || (dateH2.getDayOfWeek() == DayOfWeek.SUNDAY)) {
            numberOfHours = 0;
        }

        //Get answer difference
        int allHours = numberOfDays * 8 + numberOfHours;
        int answerDays = 0;
        int answerHours = 0;
        //Training is not finished
        if (st.getDuration() > allHours) {
            answerDays = (st.getDuration() - allHours) / 8;
            answerHours = (st.getDuration() - allHours) % 8;
        }
        //Training is finished
        if (st.getDuration() < allHours) {
            answerDays = (allHours - st.getDuration()) / 8;
            answerHours = (allHours - st.getDuration()) % 8;
        }
        if (st.getDuration() > allHours) {
            System.out.println(st.getStudent() + "(" + st.getCurriculum() + ") " +
                    " - Training is not finished. " + answerDays + " d "
                            + answerHours + " hours are left until the end.");
        } if (st.getDuration() < allHours) {
            System.out.println(st.getStudent() + "(" + st.getCurriculum() + ")" +
                    " - Training completed. " + answerDays + " d "
                    + answerHours + " hours have passed since the end.");
        }
    }
}
