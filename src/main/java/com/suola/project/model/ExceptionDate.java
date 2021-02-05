package com.suola.project.model;

/**
 * @ClassName ExceptionDate
 * @Description TODO
 * @Author hewguo
 * @Date 2020-12-23 22:22
 * @Version 1.0
 **/
public class ExceptionDate {
    private int dayType;
    private int dayWorking;
    private String name;
    private TimePeriod timePeriod;

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public int getDayWorking() {
        return dayWorking;
    }

    public void setDayWorking(int dayWorking) {
        this.dayWorking = dayWorking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    @Override
    public String toString() {
        return "{" +
                "\"DayType\":" + dayType +
                ", \"DayWorking\":" + dayWorking +
                ", \"Name\":\"" + name + "\"" +
                ", \"TimePeriod\":" + timePeriod.toString() +
                '}';
    }
}
