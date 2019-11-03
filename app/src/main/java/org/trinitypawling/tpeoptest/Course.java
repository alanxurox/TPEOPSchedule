package org.trinitypawling.tpeoptest;

import java.util.ArrayList;
import java.util.List;

public class Course {
    static ArrayList<Course> courses = new ArrayList<>();
    int courseNo;
    String teacher;
    int period;
    String classRoom;
    char term;
    int section;
    String name;

    public Course(int courseNo, String teacher, int period, String classRoom, int section, String name) {
        this.courseNo = courseNo;
        this.teacher = teacher;
        this.period = period;
        this.classRoom = classRoom;
        this.section = section;
        this.name = name;
    }

    public Course(String teacher, int period, String classRoom, String name) {
        this.teacher = teacher;
        this.period = period;
        this.classRoom = classRoom;
        this.name = name;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(List<Course> c) {
        courses = (ArrayList<Course>) c;
    }

    public static String[] getNameList() {
        int size = getCourses().size();
        String[] names = new String[size];

        for (int i = 0; i < size; i++) {
            names[i] = getCourses().get(i).getName();
        }

        return names;
    }

    public int getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(int courseNo) {
        this.courseNo = courseNo;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public char getTerm() {
        return term;
    }

    public void setTerm(char term) {
        this.term = term;
    }

    public int getSection() {
        return section;
    }

    public void setSection(int section) {
        this.section = section;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
