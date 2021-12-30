package com.juran.crowd.entity;

/**
 * 作者： Juran on 2021/12/30 19:11
 * 作者博客：iit.la
 */
public class Subject {
    private String subjectName;
    private String subjectScore;
    public Subject(){

    }

    public Subject(String subjectName, String subjectScore) {
        super();
        this.subjectName = subjectName;
        this.subjectScore = subjectScore;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(String subjectScore) {
        this.subjectScore = subjectScore;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "subjectName='" + subjectName + '\'' +
                ", subjectScore='" + subjectScore + '\'' +
                '}';
    }
}
