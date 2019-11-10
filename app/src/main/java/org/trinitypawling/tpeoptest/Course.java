package org.trinitypawling.tpeoptest;

import java.util.ArrayList;
import java.util.List;

/**
 * This class holds information of a subject and various methods built around them
 * Alan Xu
 */

public class Course {
    static ArrayList<Course> courses = new ArrayList<>();
    int courseNo;
    String teacher;
    int period;
    String classRoom;
    char term;
    int section;
    String name;

    /**
     * Constructor
     *
     * @param courseNo  Course number
     * @param teacher   Teacher
     * @param period    Period number
     * @param classRoom Classroom number
     * @param section   Section number
     * @param name      Subject name
     */

    public Course(int courseNo, String teacher, int period, String classRoom, int section, String name) {
        this.courseNo = courseNo;
        this.teacher = teacher;
        this.period = period;
        this.classRoom = classRoom;
        this.section = section;
        this.name = name;
    }

    public static ArrayList<Course> getCourses() {
        return courses;
    }

    public static void setCourses(List<Course> c) {
        courses = (ArrayList<Course>) c;
    }

    /**
     * This method finds all the subjects that has period int period.
     * @param period
     * @return
     */

    public static String[] getNameList(int period) {
        ArrayList<Course> temp = new ArrayList<>();
        for (Course c : courses) {
            if (c.getPeriod() == period)
                temp.add(c);
        }
        String[] names = new String[temp.size()];

        for (int i = 0; i < temp.size(); i++) {

            //Shows the name, teacher, and period so that users can see on the AutoComplete
            names[i] = temp.get(i).getName() + ", " + temp.get(i).getTeacher() + ", " + temp.get(i).getPeriod();
        }

        return names;
    }

    /**
     * This method finds a Course object with the string nameTeacherPeriod and return the Course
     *
     * @param nameTeacherPeriod
     * @return
     */

    public static Course find(String nameTeacherPeriod) {

        String[] tokens = nameTeacherPeriod.split(", ");

        for (Course c : courses) {

            if (tokens[0].equals(c.getName()) &&
                    tokens[1].equals(c.getTeacher()) &&
                    Integer.parseInt(tokens[2]) == c.getPeriod())

                return c;
        }
        return null;
    }

    /*
    Getters and setters for various instance objects
     */

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

    /**
     * To string method for potential debugging
     * @return
     */

    @Override
    public String toString() {
        return "Course{" +
                "courseNo=" + courseNo +
                ", teacher='" + teacher + '\'' +
                ", period=" + period +
                ", classRoom='" + classRoom + '\'' +
                ", term=" + term +
                ", section=" + section +
                ", name='" + name + '\'' +
                '}';
    }
}
