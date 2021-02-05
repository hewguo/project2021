package com.suola.project.model;

import java.util.List;

/**
 * @ClassName WeekDay
 * @Description TODO 星期日历
 * @Author hewguo
 * @Date 2020-12-21 16:10
 * @Version 1.0
 **/
public class WeekDay {
    private int dayWorking;//是否工作日，1表示工作，0表示休息
    private int dayType;//一周的第几天，1表示星期天，2表示星期一
    private List<WorkingTime> workingTimes=null;

    public int getDayWorking() {
        return dayWorking;
    }

    public void setDayWorking(int dayWorking) {
        this.dayWorking = dayWorking;
    }

    public int getDayType() {
        return dayType;
    }

    public void setDayType(int dayType) {
        this.dayType = dayType;
    }

    public List<WorkingTime> getWorkingTimes() {
        return workingTimes;
    }

    public void setWorkingTimes(List<WorkingTime> workingTimes) {
        this.workingTimes = workingTimes;
    }

    @Override
    public String toString() {
        String strWorkingTimes=PrjUtils.arrayToStr(workingTimes);
//        if(workingTimes!=null){
//            for(WorkingTime wt:workingTimes){
//                strWorkingTimes+=wt.toString()+",";
//            }
//            strWorkingTimes=strWorkingTimes.substring(0,strWorkingTimes.length()-1);
//            strWorkingTimes="["+strWorkingTimes+"]";
//        }
        if(strWorkingTimes.length()>0) {
            return  "{" +
                    "\"DayWorking\":" + dayWorking +
                    ", \"DayType\":" + dayType +
                    ", \"WorkingTimes\":" + strWorkingTimes +
                    '}';
        }else {
            return  "{" +
                    "\"DayWorking\":" + dayWorking +
                    ", \"DayType\":" + dayType +
                    '}';
        }
    }
}
