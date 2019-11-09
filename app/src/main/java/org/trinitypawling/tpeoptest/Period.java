package org.trinitypawling.tpeoptest;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import java.util.ArrayList;

/**
 * The Period class adapted from Jay's work, with additional methods as commented
 */
public class Period {
    static int lastTicks;
    static boolean isAWeek;
    static ArrayList<Period> periods;
    private static Course course;
    WTime start;
    WTime end;
    String period;
    String teacher, room, subject;


    public Period(WTime start, int periodLength, String period) {
        this.start = start;
        this.end = new WTime(start, periodLength);
        this.period = period;
        subject = "";
        room = "";
        teacher = "";
    }

    public Period(Period a) {
        start = a.start;
        end = a.end;
        period = a.period;
        teacher = a.teacher;
        room = a.room;
        subject = a.subject;
    }

    public Period(WTime start, WTime end, String period) {
        this.start = start;
        this.end = end;
        this.period = period;
        subject = "";
        room = "";
        teacher = "";
    }

    /**
     * Method loops through the periods and courses, if found two same subjects, show on the schedule page
     */
    public static void loadCourses() {
        try {
            for (Period p : periods) {
                for (String s : SettingsActivity.schedule) {

                    //A method that finds a course with the teacher, name, and period
                    course = Course.find(s);
                    try {
                        if (Integer.parseInt(p.getPeriod()) == course.getPeriod()) {
                            p.setSubject(course.getName());
                            p.setTeacher(course.getTeacher());
                            p.setRoom(course.getClassRoom());
                        }
                    } catch (NumberFormatException e) {
                        Log.i("info", "this is a lunch/flex/practicum block");
                    }
                }
            }
        } catch (NullPointerException e) {
            Log.i("info", "didn't find a course: perhaps the course list in settings is empty or course info not in the database?");
        }
    }


    /**
     * Method to load the a week times and period numbers.
     */
    public static void loadPeriodsA() {
        periods.clear();
        Log.i("info", "load a");
        //Monday
        periods.add(new Period(new WTime(1, 8, 20), 70, "1"));
        periods.add(new Period(new WTime(1, 9, 35), 45, "2"));
        periods.add(new Period(new WTime(1, 10, 25), 70, "3"));
        periods.add(new Period(new WTime(1, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(1, 12, 30), 45, "4"));
        periods.add(new Period(new WTime(1, 13, 20), 45, "5"));
        periods.add(new Period(new WTime(1, 14, 10), 45, "6"));

        //Tuesday
        periods.add(new Period(new WTime(2, 8, 20), 70, "7"));
        periods.add(new Period(new WTime(2, 9, 35), 45, "4"));
        periods.add(new Period(new WTime(2, 10, 25), 70, "2"));
        periods.add(new Period(new WTime(2, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(2, 12, 30), 45, "5"));
        periods.add(new Period(new WTime(2, 13, 20), 45, "1"));
        periods.add(new Period(new WTime(2, 14, 10), 45, "3"));

        //Wednesday
        periods.add(new Period(new WTime(3, 8, 20), 45, "5"));
        periods.add(new Period(new WTime(3, 9, 10), 45, "6"));
        periods.add(new Period(new WTime(3, 10, 00), 45, "1"));
        periods.add(new Period(new WTime(3, 10, 50), 45, "7"));

        //Thursday
        periods.add(new Period(new WTime(4, 8, 20), 70, "4"));
        periods.add(new Period(new WTime(4, 9, 35), 45, "7"));
        periods.add(new Period(new WTime(4, 10, 25), 70, "6"));
        periods.add(new Period(new WTime(4, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(4, 12, 30), 45, "2"));
        periods.add(new Period(new WTime(4, 13, 20), 45, "3"));
        periods.add(new Period(new WTime(4, 14, 10), 45, "1"));

        //Friday
        periods.add(new Period(new WTime(5, 8, 00), 45, "3"));
        periods.add(new Period(new WTime(5, 8, 50), 45, "2"));
        periods.add(new Period(new WTime(5, 9, 40), 70, "5"));
        periods.add(new Period(new WTime(5, 11, 00), 40, "Chapel"));
        periods.add(new Period(new WTime(5, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(5, 12, 30), 45, "6"));
        periods.add(new Period(new WTime(5, 13, 20), 45, "7"));
        periods.add(new Period(new WTime(5, 14, 10), 45, "4"));

        //After setting the period, load the courses to show the teacher, subject, and period
        loadCourses();
    }

    public static boolean AWeek() {
        isAWeek = periods.size() < 34;
        return isAWeek;
    }

    /**
     * Method to load the b week times and period numbers.
     */
    public static void loadPeriodsB() {
        periods.clear();

        //Monday
        periods.add(new Period(new WTime(1, 8, 20), 70, "1"));
        periods.add(new Period(new WTime(1, 9, 35), 45, "2"));
        periods.add(new Period(new WTime(1, 10, 25), 70, "3"));
        periods.add(new Period(new WTime(1, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(1, 12, 30), 45, "Practicum"));
        periods.add(new Period(new WTime(1, 13, 20), 45, "4"));
        periods.add(new Period(new WTime(1, 14, 10), 45, "6"));

        //Tuesday
        periods.add(new Period(new WTime(2, 8, 20), 70, "7"));
        periods.add(new Period(new WTime(2, 9, 35), 45, "4"));
        periods.add(new Period(new WTime(2, 10, 25), 70, "2"));
        periods.add(new Period(new WTime(2, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(2, 12, 30), 45, "Practicum"));
        periods.add(new Period(new WTime(2, 13, 20), 45, "5"));
        periods.add(new Period(new WTime(2, 14, 10), 45, "3"));

        //Wednesday
        periods.add(new Period(new WTime(3, 8, 00), 55, "Flex Block"));
        periods.add(new Period(new WTime(3, 9, 00), 45, "5"));
        periods.add(new Period(new WTime(3, 9, 50), 45, "6"));
        periods.add(new Period(new WTime(3, 10, 40), 45, "1"));

        //Thursday
        periods.add(new Period(new WTime(4, 8, 20), 70, "4"));
        periods.add(new Period(new WTime(4, 9, 35), 45, "7"));
        periods.add(new Period(new WTime(4, 10, 25), 70, "6"));
        periods.add(new Period(new WTime(4, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(4, 12, 30), 45, "2"));
        periods.add(new Period(new WTime(4, 13, 20), 45, "3"));
        periods.add(new Period(new WTime(4, 14, 10), 45, "1"));

        //Friday
        periods.add(new Period(new WTime(5, 8, 00), 45, "3"));
        periods.add(new Period(new WTime(5, 8, 50), 45, "2"));
        periods.add(new Period(new WTime(5, 9, 40), 70, "5"));
        periods.add(new Period(new WTime(5, 11, 00), 40, "Chapel"));
        periods.add(new Period(new WTime(5, 11, 45), 25, "Lunch"));
        periods.add(new Period(new WTime(5, 12, 30), 45, "Practicum"));
        periods.add(new Period(new WTime(5, 13, 20), 45, "7"));
        periods.add(new Period(new WTime(5, 14, 10), 45, "4"));

        //Saturday
        periods.add(new Period(new WTime(6, 8, 30), 45, "6"));
        periods.add(new Period(new WTime(6, 9, 20), 45, "1"));
        periods.add(new Period(new WTime(6, 10, 10), 45, "7"));
        periods.add(new Period(new WTime(6, 11, 00), 45, "5"));

        //After setting the period, load the courses to show the teacher, subject, and period
        loadCourses();
    }


    public static void setAllPeriodInfo(String num, String meets, String className, String
            room, String teacher) {
        System.out.println("num " + num + " meets " + meets + " className " + className + " Room " + room + " Teacher " + teacher);
        int meetsSession = 1;
        int meetsNum = 0;
        for (Period p : periods) {
            if (p.period.equals(num)) {
                if (meets.length() < 1) {
                    p.setSubject(className);
                    p.setRoom(room);
                    p.setTeacher(teacher);
                } else if (meets.length() > meetsNum && meets.substring(meetsNum, meetsNum + 1).equals("" + meetsSession)) {
                    meetsNum++;
                    meetsSession++;
                    p.setSubject(className);
                    p.setRoom(room);
                    p.setTeacher(teacher);
                }
            }
        }
    }

    public static void setAllPeriodInfo(String num, String className) {
        setAllPeriodInfo(num, "", className, "", "");
    }

    public static Period findContainingPeriod(WTime target) {
        for (Period p : periods) {
            if (p.contains(target)) {
                return p;
            }
        }
        return null;
    }

    //Returns the period before the current passing block or the previous period to the current period
    // if between last period of the week and the first period of the week return the last period of the week
    public static Period findPreviousPeriod(WTime target) {
        Period previous = null;
        Period current = null;
        current = findContainingPeriod(target);
        if (current != null) {  //return the previous period
            int index = periods.indexOf(current);
            if (index > 0)
                return periods.get(index - 1);
            else
                return periods.get(periods.size() - 1);
        } else {    //not a current period ie a passing block
            current = findNextPeriod(target);
            if (periods.indexOf(current) == 0)
                return periods.get(periods.size() - 1);
            else
                return periods.get(periods.indexOf(current) - 1);
        }
        // return null;
    }

    //Returns the period after the current passing block or the period after current period
    // if between last period of the week and the first period of the week return the first period of the week
    public static Period findNextPeriod(WTime target) {
        Period previous = null;
        for (Period p : periods) {
            if (!p.startsAfter(target))
                return p;
        }
        return periods.get(0);
    }

    public static int getLastTicks() {
        return lastTicks;
    }

    public static void setLastTicks(int ticks) {
        lastTicks = ticks;
    }

    public static void drawAllPeriods(Canvas c) {
        for (Period p :
                periods) {
            p.draw(c);
        }
    }

    public static void drawMainPeriods(Canvas c, WTime now) {
        if (findContainingPeriod(now) != null) {
            findContainingPeriod(now).drawThis(c);
            findNextPeriod(now).drawNext(c);
        } else
            findNextPeriod(now).drawNext(c);
    }

    public static void drawTodayPeriods(Canvas c) {
        for (Period p :
                periods) {
            if (p.getStart().getDay() == new WTime().getDay())
                p.draw(c);
        }
    }

    public static ArrayList<Period> getTodaysPeriods(int dDay) {
        ArrayList<Period> dayPeriods = new ArrayList<Period>();
        for (Period p : periods) {
            if (p.start.getDay() == dDay)
                dayPeriods.add(p);
        }
        return dayPeriods;
    }

    // for testing
    public static void main(String[] args) {
        Period test1 = new Period(new WTime(1, 8, 40), new WTime(1, 9, 30), "1");
        System.out.println(test1);
        setAllPeriodInfo("4", "Lunch");
        setAllPeriodInfo("5", "App Development");
        WTime a = new WTime();
        // WTime a = new WTime(6,14,30,0);  //saturday after classes
        //  WTime a = new WTime(0,14,30,0);   // Sunday Morning
        //WTime a = new WTime(1,8,30,0);   // Monday Morning
        //WTime a = new WTime(1, 9, 32, 0);   // Monday Morning passing block
        System.out.println(a);
        System.out.println(Period.findContainingPeriod(a));
        System.out.println(Period.findNextPeriod(a));
        System.out.println(Period.findPreviousPeriod(a));
        //  for (Period a: periods){
        //    System.out.println(a);
        //  }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String g) {
        subject = g;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String g) {
        room = g;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String g) {
        teacher = g;
    }

    public String getPeriod() {
        return period;
    }

    public WTime getStart() {
        return start;
    }

    public void setStart(WTime a) {
        start = a;
    }

    public WTime getEnd() {
        return end;
    }

    public void setEnd(WTime end) {
        this.end = end;
    }

    public boolean startsAfter(WTime target) {
        return start.isBefore(target);
    }

    public boolean contains(WTime target) {
        return start.isBefore(target) && end.isAfter(target);
    }

    public void setPeriodNumber(String g) {
        period = g;
    }

    /**
     * Method adapted from javafx, added drawText for teachers, names, and periods
     *
     * @param c
     */
    public void draw(Canvas c) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        paint.setStrokeWidth(2);

        int dayOfWeek = start.getDay();

        WTime eight = new WTime(dayOfWeek, 8, 0);
        int cs = (start.ticks - eight.ticks) / 150;
        int ce = (end.ticks - eight.ticks) / 150;
        c.drawRect(150 * (dayOfWeek - 1) + 100, cs, 150 * (dayOfWeek - 1) + 250, ce, paint);
        c.drawText(start.getHourAMPM() + ":" + start.getMinuteS(), 150 * (dayOfWeek - 1) + 105, cs + 20, paint);
        c.drawText(end.getHourAMPM() + ":" + end.getMinuteS(), 150 * (dayOfWeek - 1) + 209, ce - 10, paint);
        c.drawText(subject, 150 * (dayOfWeek - 1) + 140, (ce - cs) / 3 + cs, paint);
        c.drawText(getPeriod(), 150 * (dayOfWeek - 1) + 140, (ce + cs) / 2, paint);
        c.drawText(getTeacher(), 150 * (dayOfWeek - 1) + 105, ((ce - cs) / 3 + 75) + cs, paint);
    }

    /**
     * Method adapted from javafx, added drawText for teachers, names, and periods
     *
     * @param c
     */
    public void drawThis(Canvas c) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        paint.setStrokeWidth(2);

        WTime now = new WTime();
        WTime eight = new WTime(start.getDay(), 8, 0);
        int cs = (start.ticks - start.ticks) / 150;
        int ce = (end.ticks - start.ticks) / 150;
        setLastTicks(start.ticks);
        Log.i("aaaa", "start.ticks = " + start.ticks);
        c.drawRect(0, cs, 150, ce, paint);
        c.drawText(start.getHourAMPM() + ":" + start.getMinuteS(), 5, cs + 20, paint);
        c.drawText(end.getHourAMPM() + ":" + end.getMinuteS(), 109, ce - 10, paint);
        c.drawText(subject, 40, (ce + cs) / 2, paint);
        c.drawText(getPeriod(), 72, (ce - cs) / 3 + cs, paint);
    }

    public void drawNext(Canvas c) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        paint.setStrokeWidth(2);

        WTime current = new WTime();
        int cs = (start.ticks - getLastTicks()) / 150;
        int ce = (end.ticks - getLastTicks()) / 150;
        Log.i("aaaa", "cs next: " + cs);
        Log.i("aaaa", "ce next: " + ce);
        Log.i("aaaa", "" + getLastTicks());

        c.drawRect(0, cs, 150, ce, paint);
        c.drawText(start.getHourAMPM() + ":" + start.getMinuteS(), 5, cs + 20, paint);
        c.drawText(end.getHourAMPM() + ":" + end.getMinuteS(), 100, ce - 10, paint);
        c.drawText(subject, 40, (ce + cs) / 2, paint);
        c.drawText(getPeriod(), 72, (ce - cs) / 3 + cs, paint);
    }

    @Override
    public String toString() {
        return "Period{" +
                "start=" + start +
                ", end=" + end +
                ", period='" + period + '\'' +
                ", teacher='" + teacher + '\'' +
                ", room='" + room + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
