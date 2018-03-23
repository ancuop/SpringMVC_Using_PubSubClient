package com.example.simple_resful.models;


public class FishControl {
    private String milligram;
    private String time;

    public FishControl() {
    }

    public FishControl(String milligram, String time) {
        this.milligram = milligram;
        this.time = time;
    }

    public void setMilligram(String milligram) {
        this.milligram = milligram;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMilligram() {

        return milligram;
    }

    public String getTime() {
        return time;
    }
}
