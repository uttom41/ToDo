package com.uttom41mitra.todo.db;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class StringManipulation {

    public String getMonth(String month){
        String[] splitMonth = month.split(" ");
        List<String> monthlist = Arrays.asList(splitMonth);
        ArrayList<String> ArraylistMonth = new ArrayList<String>(monthlist);
        return ArraylistMonth.get(0);
    }

    public String getYear(String year){
        String[] splitYear = year.split(" ");
        List<String> yearlist = Arrays.asList(splitYear);
        ArrayList<String> ArraylistYear = new ArrayList<String>(yearlist);
        return ArraylistYear.get(2);
    }

    public String getDay(String day){
        String[] splitDay = day.split(" ");
        List<String> daylist = Arrays.asList(splitDay);
        ArrayList<String> ArraylistYear = new ArrayList<String>(daylist);

        String newday = ArraylistYear.get(1);
        String[] splitnewDay = newday.split(",");
        List<String> splitnewDaylist= Arrays.asList(splitnewDay);
        ArrayList<String> newDaysA = new ArrayList<String>(splitnewDaylist);
        return newDaysA.get(0);
    }

    public String getCurrentDate(){
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        Calendar calobj = Calendar.getInstance();
        return df.format(calobj.getTime());
    }

    public String getTomorrowDate(){
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("MMM dd, yyyy");
       // Calendar calobj = Calendar.getInstance();
       // calobj.set(Calendar.DATE,1);
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE,1);

        return df.format(gc.getTime());
    }

    public String getCurrentTime(){
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("hh:mm aa");
        Calendar calobj = Calendar.getInstance();
       return df.format(calobj.getTime());
    }

}
