package com.suola.project.model;

import java.util.List;

/**
 * @ClassName ProjectModel
 * @Description TODO 项目信息树
 * @Author hewguo
 * @Date 2020-12-24 11:08
 * @Version 1.0
 **/
public class ProjectModel {
    private String uid;
    private String name;
    private String startDate;
    private String finishDate;
    private long calendarUID;
    private List<Calendar> calendars;
    private List<Task> tasks;
    private List<Resource> resources;
    private List<Department> departments;
    private List<Principal> principals;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(String finishDate) {
        this.finishDate = finishDate;
    }

    public long getCalendarUID() {
        return calendarUID;
    }

    public void setCalendarUID(long calendarUID) {
        this.calendarUID = calendarUID;
    }

    public List<Calendar> getCalendars() {
        return calendars;
    }

    public void setCalendars(List<Calendar> calendars) {
        this.calendars = calendars;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Principal> getPrincipals() {
        return principals;
    }

    public void setPrincipals(List<Principal> principals) {
        this.principals = principals;
    }

    @Override
    public String toString() {
        return "{" +
                "\"UID\":\"" + uid +"\"" +
                ", \"Name\":\"" + name + "\"" +
                ", \"StartDate\":\"" + startDate + "\"" +
                ", \"FinishDate\":\"" + finishDate + "\"" +
                ", \"CalendarUID\":" + calendarUID +
                ", \"Calendars\":" + PrjUtils.arrayToStr(calendars) +
                ", \"Tasks\":" + PrjUtils.arrayToStr(tasks) +
                ", \"Resources\":" + PrjUtils.arrayToStr(resources) +
                ", \"Departments\":" + PrjUtils.arrayToStr(departments) +
                ", \"Principals\":" + PrjUtils.arrayToStr(principals) +
                '}';
    }
}
