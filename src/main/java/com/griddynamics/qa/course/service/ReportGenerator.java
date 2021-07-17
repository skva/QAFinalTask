package com.griddynamics.qa.course.service;

import com.griddynamics.qa.course.model.StudentData;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ReportGenerator {
    InputScanner inputScanner = new InputScanner();
    LocalDateTime dateTime = inputScanner.input();

    public void shortReport(StudentData students) {
        printShortResult(students, dateTime);
    }
    public void fullReport(StudentData students) {
        printFullResult(students, dateTime);
    }

    //Days difference between training start date and input date
    private int getDaysDiff(StudentData students, LocalDateTime dateTime) {
        int numberOfDays = 0;
        LocalDateTime date = students.getDate();
        while (date.isBefore(dateTime.minus(1, ChronoUnit.DAYS))) {
            if (date.getDayOfWeek() != (DayOfWeek.SATURDAY)
                    && (date.getDayOfWeek() != (DayOfWeek.SUNDAY))) {
                numberOfDays++;
            }
            date = date.plus(1, ChronoUnit.DAYS);
        }
        return numberOfDays;
    }
    //Hours difference in last day
    private int getHoursDiff(StudentData students, LocalDateTime dateTime) {
        LocalDateTime date
                = students.getDate();
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
        return numberOfHours;
    }
    //Full hours difference
    private int getAllHours(StudentData students, LocalDateTime dateTime) {
        return getDaysDiff(students, dateTime) * 8 + getHoursDiff(students, dateTime);
    }

    private int getAnswerDays(StudentData students, LocalDateTime dateTime) {
        //Get answer difference
        int answerDays = 0;
        //Training is not finished
        if (students.getDuration() > getAllHours(students, dateTime)) {
            answerDays = (students.getDuration() - getAllHours(students, dateTime)) / 8;
        }
        //Training is finished
        if (students.getDuration() < getAllHours(students, dateTime)) {
            answerDays = (getAllHours(students, dateTime) - students.getDuration()) / 8;
        }
        return answerDays;
    }
    private int getAnswerHours(StudentData students, LocalDateTime dateTime) {
        //Get answer difference
        int answerHours = 0;
        //Training is not finished
        if (students.getDuration() > getAllHours(students, dateTime)) {
            answerHours = (students.getDuration() - getAllHours(students, dateTime)) % 8;
        }
        //Training is finished
        if (students.getDuration() < getAllHours(students, dateTime)) {
            answerHours = (getAllHours(students, dateTime) - students.getDuration()) % 8;
        }
        return answerHours;
    }
    //Training end date
    private LocalDateTime getEndDate(StudentData students) {
        LocalDateTime endDate = students.getDate();
        //Count training end date except SA/SU
        for (int j = students.getDuration()/8; j > 0; j--) {
            if (endDate.getDayOfWeek() == DayOfWeek.SATURDAY || endDate.getDayOfWeek() == DayOfWeek.SUNDAY) {
                endDate = endDate.plus(1, ChronoUnit.DAYS);
            } if (endDate.getDayOfWeek() != DayOfWeek.SATURDAY && endDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                endDate = endDate.plus(1, ChronoUnit.DAYS);
            }
        }
        return endDate;
    }

    private void printShortResult(StudentData students, LocalDateTime dateTime) {
        System.out.println("Short (Generating report date - " + dateTime.getDayOfMonth()
                + " " + dateTime.getMonth() + " " + dateTime.getYear() + ", "
                + dateTime.getDayOfWeek() + ", " + dateTime.getHour() + ":00");
        if (students.getDuration() > getAllHours(students, dateTime)) {
            System.out.println(students.getStudent() + " (" + students.getCurriculum() + ")" +
                    " - Training is not finished. " + getAnswerDays(students, dateTime) + " d "
                    + getAnswerHours(students, dateTime) + " hours are left until the end.");
        }
        if (students.getDuration() < getAllHours(students, dateTime)) {
            System.out.println(students.getStudent() + " (" + students.getCurriculum() + ")" +
                    " - Training completed. " + getAnswerDays(students, dateTime) + " d "
                    + getAnswerHours(students, dateTime) + " hours have passed since the end.");
        }
    }
    private void printFullResult(StudentData students, LocalDateTime dateTime) {
        getEndDate(students);
        System.out.println(students.getStudent() + "\n" + "From 10 to 18\n"
                + students.getCurriculum() + "\n" + students.getDuration() + "\n"
                + students.getDate().getDayOfMonth() + " "
                + students.getDate().getMonth() + " "
                + students.getDate().getYear() + "\n"
                + getEndDate(students).getDayOfMonth() + " "
                + getEndDate(students).getMonth() + " "
                + getEndDate(students).getYear());
        if (students.getDuration() > getAllHours(students, dateTime)) {
            System.out.println("Training is not finished. " + getAnswerDays(students, dateTime) + " d "
                    + getAnswerHours(students, dateTime) + " hours are left until the end." + "\n");
        }
        if (students.getDuration() < getAllHours(students, dateTime)) {
            System.out.println("Training completed. " + getAnswerDays(students, dateTime) + " d "
                    + getAnswerHours(students, dateTime) + " hours have passed since the end." + "\n");
        }
    }
}

