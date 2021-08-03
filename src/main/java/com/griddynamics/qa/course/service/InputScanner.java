package com.griddynamics.qa.course.service;

import java.time.LocalDateTime;
import java.util.Scanner;

public class InputScanner {



    public LocalDateTime scanDate() {
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

        return LocalDateTime.of(yearOf, monthOf, dayOf, hourOf, 0);
    }

    public int scanReportType() {
        Scanner scAnswerType = new Scanner(System.in);
        System.out.println("Введите 0 для краткого отчета:");
        return scAnswerType.nextInt();
    }
}
