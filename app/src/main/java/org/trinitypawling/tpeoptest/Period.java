package org.trinitypawling.tpeoptest;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextPaint;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * The Period class adapted from Jay's work, with additional methods as commented
 */
public class Period {
    static int lastTicks;
    static boolean isAWeek;
    boolean extend;
    static ArrayList<Period> periods;
    private static Course course;
    private static FireBaseCallback fireBaseCallback;
    WTime start;
    WTime end;
    String period;
    String teacher, room, subject;
    final static List<Period> PERIOD_LIST = new ArrayList<>();

    static DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    static DatabaseReference periodARef = rootRef.child("PeriodA");
    static DatabaseReference periodBRef = rootRef.child("PeriodB");


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
                    } catch (NullPointerException e) {
                    }
                }
            }
        } catch (NullPointerException e) {
        }
    }


    /**
     * Method to load the a week times and period numbers.
     * @param fireBaseCallback from MainActivity that holds the drawing functions
     */
    public static void loadPeriodsA(final FireBaseCallback fireBaseCallback) {
        periods.clear();
        PERIOD_LIST.clear();


        if (periods.size() < 33) {
            periodARef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot periodSnapshot : dataSnapshot.getChildren()) {
                        try {
                            //Retrieve data and add to course

                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    String.valueOf(periodSnapshot.child("period").getValue(Long.class)))
                            );
                        } catch (DatabaseException e) {


                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    periodSnapshot.child("period").getValue(String.class))
                            );
                        }
                    }

                    for (Period p : PERIOD_LIST) {
                        periods.add(p);
                    }

                    //After setting the period, load the courses to show the teacher, subject, and period
                    loadCourses();
                    
                    
                    //After loading the periods, draw the schedule in MainActivity
                    fireBaseCallback.onCallBack(periods);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    /**
     * Method to load the a week times and period numbers.
     */
    public static void loadPeriodsA() {
        periods.clear();
        PERIOD_LIST.clear();


        if (periods.size() < 33) {
            periodARef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot periodSnapshot : dataSnapshot.getChildren()) {
                        try {
                            //Retrieve data and add to course

                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    String.valueOf(periodSnapshot.child("period").getValue(Long.class)))
                            );
                        } catch (DatabaseException e) {


                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    periodSnapshot.child("period").getValue(String.class))
                            );
                        }
                    }

                    //After setting the period, load the courses to show the teacher, subject, and period
                    for (Period p : PERIOD_LIST) {
                        periods.add(p);
                    }

                    loadCourses();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public static boolean AWeek() {
        isAWeek = periods.size() < 33;
        return isAWeek;
    }
    
    /**
     * Method to load the b week times and period numbers.
     * @param fireBaseCallback from MainActivity that holds the drawing functions
     */
    
    public static void loadPeriodsB(final FireBaseCallback fireBaseCallback) {
        periods.clear();
        PERIOD_LIST.clear();

        if (periods.size() < 37) {
            periodARef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot periodSnapshot : dataSnapshot.getChildren()) {
                        try {
                            //Retrieve data and add to course

                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    String.valueOf(periodSnapshot.child("period").getValue(Long.class)))
                            );
                        } catch (DatabaseException e) {
                            String s;

                            switch (periodSnapshot.child("period").getValue(String.class)) {
                                case "Extra Help":
                                    s = "Practicum";
                                    break;
                                case "Lunch":
                                    s = "Lunch";
                                    break;
                                case "Flex Block":
                                    s = "Flex Block";
                                default:
                                    s = "";
                            }
                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    s)
                            );
                        }
                    }

                    for (Period p : PERIOD_LIST) {
                        periods.add(p);
                    }

                    //After setting the period, load the courses to show the teacher, subject, and period
                    loadCourses();
                    
                    
                    //After loading the periods, draw schedule
                    fireBaseCallback.onCallBack(periods);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }


    /**
     * Method to load the a week times and period numbers.
     */
    public static void loadPeriodsB() {
        periods.clear();
        PERIOD_LIST.clear();

        if (periods.size() < 37) {
            periodBRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot periodSnapshot : dataSnapshot.getChildren()) {
                        try {
                            //Retrieve data and add to course

                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    String.valueOf(periodSnapshot.child("period").getValue(Long.class)))
                            );
                        } catch (DatabaseException e) {
                            String s;

                            switch (periodSnapshot.child("period").getValue(String.class)) {
                                case "Extra Help":
                                    s = "Practicum";
                                    break;
                                case "Lunch":
                                    s = "Lunch";
                                    break;
                                case "Flex Block":
                                    s = "Flex Block";
                                default:
                                    s = "";
                            }
                            PERIOD_LIST.add(new Period(new WTime(

                                    periodSnapshot.child("day").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Hour").getValue(Long.class).intValue(),

                                    periodSnapshot.child("Min").getValue(Long.class).intValue()),

                                    periodSnapshot.child("Len").getValue(Long.class).intValue(),

                                    s)
                            );
                        }
                    }

                    //After setting the period, load the courses to show the teacher, subject, and period
                    for (Period p : PERIOD_LIST) {
                        periods.add(p);
                    }

                    loadCourses();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


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

    public static void drawTodayPeriods(Canvas c, int day) {
        for (Period p :
                periods) {
            int today = p.getStart().getDay();
            if (today == day)
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

    public void setExtend(boolean extend) {
        this.extend = extend;
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
        paint.setColor(Color.WHITE);

        int dayOfWeek = start.getDay();

        WTime eight = new WTime(dayOfWeek, 8, 00);
        int cs = (start.ticks - eight.ticks) / 150;
        int ce = (end.ticks - eight.ticks) / 150;

        Rect areaRect = new Rect(150 * (dayOfWeek - 1) + 100, cs, 150 * (dayOfWeek - 1) + 250, ce);
        Paint bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        bgPaint.setARGB(90, 255, 255, 255);

        c.drawRect(areaRect, bgPaint);
        drawText(subject, c, areaRect, -40);
        drawText(period, c, areaRect, -15);

        c.drawText(start.getHourAMPM() + ":" + start.getMinuteS(), 150 * (dayOfWeek - 1) + 105, cs + 20, paint);
        drawText(teacher, c, areaRect, 12);
        c.drawText(end.getHourAMPM() + ":" + end.getMinuteS(), end.getHourAMPM() > 9 ?
                150 * (dayOfWeek - 1) + 194 : 150 * (dayOfWeek - 1) + 203, ce - 8, paint);


    }
    
    
    //Helper method that draws the text on the schedule
    public void drawText(String text, Canvas c, Rect areaRect, int offset) {
        RectF bounds = new RectF(areaRect);
        TextPaint paint = new TextPaint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        paint.setStrokeWidth(2);
        //paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        
        //Check if the text needs to be wrapped so that it doesnt go off the border
        if (extend) offset += 25;
        if (text.length() > 11 && text.split(" ", 3).length > 1) {
            String[] temp = text.split(" ", 3);
            String[] strings = temp;
            if (temp[0].length() < 10) {
                setExtend(true);
                strings = Arrays.copyOfRange(temp, 1, temp.length);
                strings[0] = temp[0] + " " + strings[0];
            } else setExtend(false);
            for (String s : strings) {
            
                //Create a rectangle with the dimensions of the period
                bounds = new RectF(areaRect);
                bounds.right = paint.measureText(s, 0, s.length());
                bounds.bottom = paint.descent() - paint.ascent();
                bounds.left += (areaRect.width() - bounds.right) / 2.0f;
                bounds.top += (areaRect.height() - bounds.bottom) / 2.0f;

                c.drawText(s, bounds.left, bounds.top - paint.ascent() + offset, paint);
                
                //Move next line lower
                offset += paint.descent() - paint.ascent();
            }
            //if text does not need to be wrapped, center it and draw it
        } else {
            bounds.right = paint.measureText(text, 0, text.length());
            bounds.bottom = paint.descent() - paint.ascent();
            bounds.left += (areaRect.width() - bounds.right) / 2.0f;
            bounds.top += (areaRect.height() - bounds.bottom) / 2.0f;
            c.drawText(text, bounds.left, bounds.top - paint.ascent() + offset, paint);
        }
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
