package com.suola.project.model;

/**
 * @ClassName TimePeriod
 * @Description TODO
 * @Author hewguo
 * @Date 2020-12-23 22:19
 * @Version 1.0
 **/
public class TimePeriod {
    private String fromDate;
    private String toDate;

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    @Override
    public String toString() {
        return "{" +
                "\"FromDate\":\"" + fromDate + "\"" +
                ", \"ToDate\":\"" + toDate + "\"" +
                '}';
    }
}
