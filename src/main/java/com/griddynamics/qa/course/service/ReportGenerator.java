package com.griddynamics.qa.course.service;

import com.griddynamics.qa.course.model.StudentData;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ReportGenerator {
    private int numberOfDays;
    private int numberOfHours;

    public void shortReport(StudentData students) {
        InputScanner inputScanner = new InputScanner();
        LocalDateTime dateTime = inputScanner.input();

        //Get answer difference
        int allHours = getDaysDiff(students, dateTime) * 8 + getHoursDiff(students, dateTime);
        int answerDays = 0;
        int answerHours = 0;
        //Training is not finished
        if (students.getDuration() > allHours) {
            answerDays = (students.getDuration() - allHours) / 8;
            answerHours = (students.getDuration() - allHours) % 8;
        }
        //Training is finished
        if (students.getDuration() < allHours) {
            answerDays = (allHours - students.getDuration()) / 8;
            answerHours = (allHours - students.getDuration()) % 8;
        }

        //Print answer
        if (students.getDuration() > allHours) {
            System.out.println(students.getStudent() + " (" + students.getCurriculum() + ")" +
                    " - Training is not finished. " + answerDays + " d "
                    + answerHours + " hours are left until the end.");
        }
        if (students.getDuration() < allHours) {
            System.out.println(students.getStudent() + " (" + students.getCurriculum() + ")" +
                    " - Training completed. " + answerDays + " d "
                    + answerHours + " hours have passed since the end.");
        }
    }

    //Loop full days difference between
    private int getDaysDiff(StudentData students, LocalDateTime dateTime) {
        LocalDateTime date1 = students.getDate();
        while (date1.isBefore(dateTime.minus(1, ChronoUnit.DAYS))) {
            if (date1.getDayOfWeek() != (DayOfWeek.SATURDAY)
                    && (date1.getDayOfWeek() != (DayOfWeek.SUNDAY))) {
                numberOfDays++;
            }
            date1 = date1.plus(1, ChronoUnit.DAYS);
        }
        return numberOfDays;
    }

    //Loop addhours difference between
    private int getHoursDiff(StudentData students, LocalDateTime dateTime) {
        LocalDateTime date1 = students.getDate();
        if (dateTime.getDayOfWeek() != DayOfWeek.SATURDAY && dateTime.getDayOfWeek() != DayOfWeek.SUNDAY
                && dateTime.getHour() < 18) {
            while (date1.getHour() < dateTime.getHour()) {
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
        return numberOfHours;
    }

}

