package com.suola.project.model;

import java.util.List;

/**
 * @ClassName Calendar
 * @Description TODO 日历信息
 * @Author hewguo
 * @Date 2020-12-21 16:23
 * @Version 1.0
 **/
public class Calendar {
    private List<WeekDay> weekDays;
    private String name;
    private String uid;
    private String baseCalendarUID;
    private int isBaseCalendar;
    private List<ExceptionDate> exceptions;

    public List<WeekDay> getWeekDays() {
        return weekDays;
    }

    public void setWeekDays(List<WeekDay> weekDays) {
        this.weekDays = weekDays;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getBaseCalendarUID() {
        return baseCalendarUID;
    }

    public void setBaseCalendarUID(String baseCalendarUID) {
        this.baseCalendarUID = baseCalendarUID;
    }

    public int getIsBaseCalendar() {
        return isBaseCalendar;
    }

    public void setIsBaseCalendar(int isBaseCalendar) {
        this.isBaseCalendar = isBaseCalendar;
    }

    public List<ExceptionDate> getExceptions() {
        return exceptions;
    }

    public void setExceptions(List<ExceptionDate> exceptions) {
        this.exceptions = exceptions;
    }

    @Override
    public String toString() {
        String strWeekDays=PrjUtils.arrayToStr(weekDays);
//        if(weekDays!=null&&weekDays.size()>0){
//            for(WeekDay weekDay:weekDays){
//                strWeekDays+=weekDays.toString()+",";
//            }
//            strWeekDays=strWeekDays.substring(0,strWeekDays.length()-1);
//            strWeekDays="["+strWeekDays+"]";
//        }

        String strException=PrjUtils.arrayToStr(exceptions);
        if(strException.equals("[]")){
            strException="";
        }else {
            strException=", \"Exceptions\":" +strException;
        }
        return "{" +
                "\"WeekDays\":" + strWeekDays +
                ", \"Name\":\"" + name + "\"" +
                ", \"UID\":\"" + uid + "\"" +
                ", \"BaseCalendarUID\":\"" + baseCalendarUID + "\"" +
                ", \"IsBaseCalendar\":" + isBaseCalendar +
                strException+
                '}';
    }
}
