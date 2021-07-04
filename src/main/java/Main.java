import java.time.*;
import java.util.Scanner;
public class Main {
    public static void main (String[] agrs){
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
        System.out.println(fromConsole);

        //Student date
        StudentData st = new StudentData("Ivanov Ivan", "Java Developer",
                LocalDateTime.of(2020, Month.JUNE, 1, 15, 0), 56);
        System.out.println(st.getDate());

        //difference between dates
        Duration diff = Duration.between(LocalDateTime.from(fromConsole), LocalDateTime.from(st.getDate()));
        System.out.println(diff.getSeconds() / 3600);
    }
}
