package com.suola.project.model;

import java.util.List;

/**
 * @ClassName BaseTask
 * @Description TODO
 * @Author hewguo
 * @Date 2020-12-24 10:46
 * @Version 1.0
 **/
public class BaseTask {
    private long uid;//任务UID(唯一性标识符)
    private String name;//任务名称
    private String start;//DateTime。开始日期,2010-01-01T00:00:00
    private String finish;//DateTime。完成日期,2010-01-01T00:00:00
    private long duration;//Number。工期
    private long percentComplete;//进度,0-100
    private int manual;//手动模式。0是自动，1是手动。
    private int constraintType;//限制类型：0越早越好；1越晚越好；2必须开始于；3必须完成于；4不得早于...开始；5不得晚于...开始；6不得早于...完成；7不得晚于...完成
    private String constraintDate; //DateTime。限制日期
    private int fixedDate;//1或0。是否固定日期(仅限于摘要任务使用)
    private long id;//Number。序号
    private String outlineNumber;//体现树形层次和顺序 ,'1.2.1'
    private long outlineLevel;//层次
    private long work;//工时
    private long weight;//权重
    private int milestone;//1或0。里程碑
    private int summary;//1或0。摘要任务
    private int critical;//1或0。关键任务
    private int priority;//重要级别,100
    private String notes;//任务备注
    private List<PredecessorLink> predecessorLinks;//前置任务

    private List<Assignment> assignments;//资源分配关系
    private String department;//部门
    private String principal;//负责人 ,'1,2'

    private String wbs;//WBS序号
    private long actualDuration;
    private String projectUID;//项目ID
    private String actualFinish;//实际结束时间
    private String actualStart;//实际开始时间

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getFinish() {
        return finish;
    }

    public void setFinish(String finish) {
        this.finish = finish;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getPercentComplete() {
        return percentComplete;
    }

    public void setPercentComplete(long percentComplete) {
        this.percentComplete = percentComplete;
    }

    public int getManual() {
        return manual;
    }

    public void setManual(int manual) {
        this.manual = manual;
    }

    public int getConstraintType() {
        return constraintType;
    }

    public void setConstraintType(int constraintType) {
        this.constraintType = constraintType;
    }

    public String getConstraintDate() {
        return constraintDate;
    }

    public void setConstraintDate(String constraintDate) {
        this.constraintDate = constraintDate;
    }

    public int getFixedDate() {
        return fixedDate;
    }

    public void setFixedDate(int fixedDate) {
        this.fixedDate = fixedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOutlineNumber() {
        return outlineNumber;
    }

    public void setOutlineNumber(String outlineNumber) {
        this.outlineNumber = outlineNumber;
    }

    public long getOutlineLevel() {
        return outlineLevel;
    }

    public void setOutlineLevel(long outlineLevel) {
        this.outlineLevel = outlineLevel;
    }

    public long getWork() {
        return work;
    }

    public void setWork(long work) {
        this.work = work;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    public int getMilestone() {
        return milestone;
    }

    public void setMilestone(int milestone) {
        this.milestone = milestone;
    }

    public int getSummary() {
        return summary;
    }

    public void setSummary(int summary) {
        this.summary = summary;
    }

    public int getCritical() {
        return critical;
    }

    public void setCritical(int critical) {
        this.critical = critical;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<PredecessorLink> getPredecessorLinks() {
        return predecessorLinks;
    }

    public void setPredecessorLinks(List<PredecessorLink> predecessorLinks) {
        this.predecessorLinks = predecessorLinks;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getWbs() {
        return wbs;
    }

    public void setWbs(String wbs) {
        this.wbs = wbs;
    }

    public long getActualDuration() {
        return actualDuration;
    }

    public void setActualDuration(long actualDuration) {
        this.actualDuration = actualDuration;
    }

    public String getProjectUID() {
        return projectUID;
    }

    public void setProjectUID(String projectUID) {
        this.projectUID = projectUID;
    }

    public String getActualFinish() {
        return actualFinish;
    }

    public void setActualFinish(String actualFinish) {
        this.actualFinish = actualFinish;
    }

    public String getActualStart() {
        return actualStart;
    }

    public void setActualStart(String actualStart) {
        this.actualStart = actualStart;
    }

    @Override
    public String toString() {
//        if(name.indexOf('\"')>0){
//            System.out.println(name.replaceAll("\"","\\\""));
//            String name1=name.replaceAll("\"","\\\\\"");
//            System.out.println(name1);
//
//        }
        String ret="";
        try {
            ret = "" +
                    "\"UID\":" + uid +
                    ", \"Name\": \"" + (name == null ? "null" : (name.indexOf('\"') > 0 ? name.replaceAll("\"", "\\\\\"") : name) )+ "\"" +
                    ", \"Start\": \"" + start + "\"" +
                    ", \"Finish\": \"" + finish + "\"" +
                    ", \"Duration\": " + duration +
                    ", \"PercentComplete\":" + percentComplete +
                    ", \"Manual\":" + manual +
                    ", \"ConstraintType\": " + constraintType +
                    ", \"ConstraintDate\": " + (constraintDate == null ? null : ("\"" + constraintDate + "\"")) + "" +
                    ", \"FixedDate\": " + fixedDate +
                    ", \"ID\": " + id +
                    ", \"OutlineNumber\": \"" + outlineNumber + "\"" +
                    ", \"OutlineLevel\": " + outlineLevel +
                    ", \"Work\": " + work +
                    ", \"Weight\": " + weight +
                    ", \"Milestone\": " + milestone +
                    ", \"Summary\": " + summary +
                    ", \"Critical\": " + critical +
                    ", \"Priority\": " + priority +
                    ", \"Notes\": \"" + (notes == null ? null : (notes.replaceAll("\\\r", "").replaceAll("\\\n", ""))) + "\"" +
                    ", \"WBS\": \"" + wbs + "\"" +
                    ", \"ActualDuration\": " + actualDuration +
                    ", \"ProjectUID\": \"" + projectUID + "\"" +
                    ", \"ActualFinish\": " + (actualFinish == null ? null : ("\"" + actualFinish + "\"")) + "" +
                    ", \"ActualStart\": " + (actualStart == null ? null : ("\"" + actualStart + "\"")) + "" +
                    ", \"PredecessorLink\": " + PrjUtils.arrayToStr(predecessorLinks) +
                    ", \"Assignments\": " + PrjUtils.arrayToStr(assignments) +
                    ", \"Department\": " + (department == null ? null : ("\"" + department + "\"")) + "" +
                    ", \"Principal\": " + (principal == null ? null : ("\"" + principal + "\"")) + ""
            ;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return ret;
    }
}
