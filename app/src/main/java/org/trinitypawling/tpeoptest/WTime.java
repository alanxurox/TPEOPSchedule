package org.trinitypawling.tpeoptest;

import java.util.Calendar;

public class WTime {
    int ticks; //one tenth of a second

    //constructors
    public WTime(int day, int hour, int minute, int second) {
        ticks = timeToTicks(day, hour, minute, second, 0);
    }

    public WTime(int day, int hour, int minute) {
        ticks = timeToTicks(day, hour, minute, 0, 0);
    }

    public WTime(int hour, int minute) {
        Calendar now = Calendar.getInstance();
        int day = now.get(Calendar.DAY_OF_WEEK) - 1;
        ticks = timeToTicks(day, hour, minute, 0, 0);
    }

    public WTime(int ticks) {
        this.ticks = ticks;
    }

    public WTime(WTime t) {
        ticks = t.ticks;
    }

    public WTime(WTime t, int min) {
        ticks = t.ticks + min * 60 * 10;
    }

    public WTime() {
        //get current time
        Calendar now = Calendar.getInstance();
        //i guess they start counting days from 1
        int day = now.get(Calendar.DAY_OF_WEEK) - 1;
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);
        int second = now.get(Calendar.SECOND);
        int tenth = now.get(Calendar.MILLISECOND) / 100;
        //System.out.println(day+":"+hour+":"+minute+" "+second+":"+tenth);
        ticks = timeToTicks(day, hour, minute, second, tenth);
    }

    private int timeToTicks(int day, int hour, int minute, int second, int tenth) {
        return day * 24 * 60 * 60 * 10 + hour * 60 * 60 * 10 + minute * 60 * 10 + second * 10 + tenth;
    }

    public int getDay() {
        return ticks / (24 * 60 * 60 * 10);
    }

    public String getDayString() {
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[getDay()];
    }

    public String getDayAbrev() {
        String[] days = {"Sun", "Mon", "Tues", "Wed", "Thurs", "Fri", "Sat"};
        return days[getDay()];
    }

    //Getters and setters for hours minutes seconds days
    public int getHour() {
        return ticks % (24 * 60 * 60 * 10) / (60 * 60 * 10);
    }

    public int getHourAMPM() {
        int h = getHour();
        if (h > 12)
            return (h - 12);
        return h;
    }

    public int getMinute() {
        return ticks % (60 * 60 * 10) / (60 * 10);
    }

    public String getMinuteS() {
        return getMinute() < 10 ? "0" + getMinute() : "" + getMinute();
    }

    public int getSeconds() {
        return ticks % (60 * 10) / 10;
    }

    public String getAMPM() {
        return (getHour() <= 12) ? "am" : "pm";
    }

    public int getTenths() {
        return ticks % 10;
    }

    public void addMinutes(int min) {
        ticks += min * 60 * 10;
    }

    public void addHours(int hours) {
        ticks += hours * 60 * 60 * 10;
    }

    public void addSeconds(int seconds) {
        ticks += seconds * 10;
    }

    public void addDays(int days) {
        ticks += days * 23 * 60 * 60 * 10;
    }

    public int compareTo(Object a) {
        return this.ticks - ((WTime) a).ticks;
    }

    public boolean isBefore(WTime a) {
        return (this.ticks <= a.ticks);
    }

    public boolean isAfter(WTime a) {
        return (this.ticks >= a.ticks);
    }

    public int diffTicks(WTime a) {
        return (this.ticks - a.ticks);
    }

    public String toString() {
        return " " + getDayString() + " " + getHourAMPM() + " " + getMinute() + " " + getSeconds() + " "
                + getAMPM() + " and " + getTenths() + " tenths";
    }
    /*public static void main (String[] args){
        WTime a = new WTime();
        WTime b =new WTime(6,8,20);
        System.out.println("a = "+a);
        System.out.println("b = "+b);
        System.out.println("a compared to b = "+a.compareTo(b));
        System.out.println("a is before b = "+a.isBefore(b));
        a.addMinutes(20);
        System.out.println("a + 20 Minutes = "+a);
        a.addSeconds(20);
        System.out.println("a + 20 Seconds = "+a);
        a.addDays(1);
        System.out.println("a + 1 day = "+a);
        WTime c = new WTime(a);
        System.out.println("c cloned from a ="+c);
    }*/
}
