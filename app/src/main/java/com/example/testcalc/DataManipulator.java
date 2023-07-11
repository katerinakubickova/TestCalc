package com.example.testcalc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataManipulator {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat dateFormat;

    public static List<Date> sortDates(List<String> dateStrings) throws ParseException {
        List<Date> dates = new ArrayList<>();
        //Convert string dates to Data objects
        for (String dateString : dateStrings) {
            dates.add(sdf.parse(dateString));
        }

        //Sort the dates
        Collections.sort(dates);

        return dates;
    }

    public static List<Integer> testSorting() {
        List<Integer> numbers = new ArrayList<>();
        List<Integer> sortedList = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedList);
        return sortedList;
    }

    public static List<Integer> sortIntegers(List<Integer> integers) {
        List<Integer> sortedIntegers = new ArrayList<>(integers);
        Collections.sort(sortedIntegers);
        return sortedIntegers;
    }
/* * */
    public String dateFormating(String date) {
        this.dateFormat = new SimpleDateFormat("yyyy.MM.dd");

        String formatedDate = null;
        try {
            formatedDate = dateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Formated Date: " + formatedDate);
        return formatedDate;
    }

    public String formatingDate(String inputDate) {
        String inputPattern = "yyyy.MM.dd";
        String outputPattern = "yyyy-MM-dd";

        SimpleDateFormat inputFormat = new SimpleDateFormat(inputPattern);
        SimpleDateFormat outputFormat = new SimpleDateFormat(outputPattern);

        Date date = null;
        String outputDate = null;

        try {
            date = inputFormat.parse(inputDate);
            outputDate = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("OutputDate: " + outputDate);
        return outputDate;
    }

}
