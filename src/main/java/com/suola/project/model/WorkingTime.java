package com.suola.project.model;

/**
 * @ClassName WorkingTime
 * @Description TODO 工作时间
 * @Author hewguo
 * @Date 2020-12-21 15:52
 * @Version 1.0
 **/
public class WorkingTime {
    private String fromTime; //格式HH:MM:SS
    private String toTime;//格式HH:MM:SS

    public String getFromTime() {
        return fromTime;
    }

    public void setFromTime(String fromTime) {
        this.fromTime = fromTime;
    }

    public String getToTime() {
        return toTime;
    }

    public void setToTime(String toTime) {
        this.toTime = toTime;
    }

    public WorkingTime() {
    }

    public WorkingTime(String fromTime, String toTime) {
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"FromTime\":\"" + fromTime + "\"" +
                ", \"ToTime\":\"" + toTime + "\"" +
                '}';
    }
}
