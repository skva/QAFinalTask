import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
        Scanner scAnswerType = new Scanner(System.in);
        System.out.println("Введите 0 для краткого отчета:");
        int answerType = scAnswerType.nextInt();
        LocalDateTime fromConsole = LocalDateTime.of(yearOf, monthOf, dayOf, hourOf, 0);



        //Student data
        ArrayList<StudentData> students = new ArrayList<>();
        StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 56);
        StudentData st2 = new StudentData("Sidorov Ivan", "J2EE Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 10, 00), 42);
        students.add(st);
        students.add(st2);



        //Print short answer
        if (answerType == 0) {
            System.out.println("Short (Generating report date - " + fromConsole.getDayOfMonth()
                    + " " + fromConsole.getMonth() + " " + fromConsole.getYear() + ", "
                    + fromConsole.getDayOfWeek() + ", " + fromConsole.getHour() + ":00");
            //For Array
            for (int i = 0; i < students.toArray().length; i++) {
                //Difference between dates
                Duration diff = Duration.between(LocalDateTime.from(students.get(i).getDate()), LocalDateTime.from(fromConsole));

                //Loop full days difference between
                int numberOfDays = 0;
                LocalDateTime date1 = students.get(i).getDate();
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
                LocalDateTime dateH1 = students.get(i).getDate();
                LocalDateTime dateH2 = fromConsole;
                if (dateH2.getDayOfWeek() != DayOfWeek.SATURDAY && dateH2.getDayOfWeek() != DayOfWeek.SUNDAY
                        && dateH2.getHour() < 18) {
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
                if (students.get(i).getDuration() > allHours) {
                    answerDays = (students.get(i).getDuration() - allHours) / 8;
                    answerHours = (students.get(i).getDuration() - allHours) % 8;
                }
                //Training is finished
                if (students.get(i).getDuration() < allHours) {
                    answerDays = (allHours - students.get(i).getDuration()) / 8;
                    answerHours = (allHours - students.get(i).getDuration()) % 8;
                }

                //Print answer
                if (students.get(i).getDuration() > allHours) {
                    System.out.println(students.get(i).getStudent() + " (" + students.get(i).getCurriculum() + ")" +
                            " - Training is not finished. " + answerDays + " d "
                            + answerHours + " hours are left until the end.");
                }
                if (students.get(i).getDuration() < allHours) {
                    System.out.println(students.get(i).getStudent() + " (" + students.get(i).getCurriculum() + ")" +
                            " - Training completed. " + answerDays + " d "
                            + answerHours + " hours have passed since the end.");
                }
            }
        }

        //Print full answer
        if (answerType != 0) {
            System.out.println("Full information:");
            //For Array
            for (int i = 0; i < students.toArray().length; i++) {
                //Difference between dates
                Duration diff = Duration.between(LocalDateTime.from(students.get(i).getDate()), LocalDateTime.from(fromConsole));

                //Loop full days difference between
                int numberOfDays = 0;
                LocalDateTime date1 = students.get(i).getDate();
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
                LocalDateTime dateH1 = students.get(i).getDate();
                LocalDateTime dateH2 = fromConsole;
                if (dateH2.getDayOfWeek() != DayOfWeek.SATURDAY && dateH2.getDayOfWeek() != DayOfWeek.SUNDAY
                        && (dateH2.getHour() < 18)) {
                    while (dateH1.getHour() < dateH2.getHour()) {
                        numberOfHours++;
                        dateH2 = dateH2.minus(1, ChronoUnit.HOURS);
                    }
                }
                if (dateH2.getHour() > 18) {
                    numberOfHours = 8;
                }
                if (dateH2.getDayOfWeek() == DayOfWeek.SATURDAY || dateH2.getDayOfWeek() == DayOfWeek.SUNDAY) {
                    numberOfHours = 0;
                }

                //Get answer difference
                int allHours = numberOfDays * 8 + numberOfHours;
                int answerDays = 0;
                int answerHours = 0;
                //Training is not finished
                if (students.get(i).getDuration() > allHours) {
                    answerDays = (students.get(i).getDuration() - allHours) / 8;
                    answerHours = (students.get(i).getDuration() - allHours) % 8;
                }
                //Training is finished
                if (students.get(i).getDuration() < allHours) {
                    answerDays = (allHours - students.get(i).getDuration()) / 8;
                    answerHours = (allHours - students.get(i).getDuration()) % 8;
                }

                //Print answer
                //End date
                LocalDateTime endDate = students.get(i).getDate();
                for (int j = students.get(i).getDuration()/8; j > 0; j--) {
                    if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                        endDate = endDate.plus(1, ChronoUnit.DAYS);

                    } if (endDate.getDayOfWeek() != DayOfWeek.SATURDAY && endDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                        endDate = endDate.plus(1, ChronoUnit.DAYS);
                    }

                }
                System.out.println(students.get(i).getStudent() + "\n" + "From 10 to 18\n"
                        + students.get(i).getCurriculum() + "\n" + students.get(i).getDuration() + "\n"
                        + students.get(i).getDate().getDayOfMonth() + " "
                        + students.get(i).getDate().getMonth() + " "
                        + students.get(i).getDate().getYear() + "\n"
                        + endDate.getDayOfMonth() + " "
                        + endDate.getMonth() + " "
                        + endDate.getYear());
                if (students.get(i).getDuration() > allHours) {
                    System.out.println("Training is not finished. " + answerDays + " d "
                            + answerHours + " hours are left until the end." + "\n");
                }
                if (students.get(i).getDuration() < allHours) {
                    System.out.println("Training completed. " + answerDays + " d "
                            + answerHours + " hours have passed since the end." + "\n");
                }
            }

        }
    }
}
