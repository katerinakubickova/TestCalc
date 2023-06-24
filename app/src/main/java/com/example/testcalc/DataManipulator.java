package com.example.testcalc;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DataManipulator {

    public static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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



}
