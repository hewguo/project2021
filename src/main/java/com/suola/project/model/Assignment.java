package com.suola.project.model;

/**
 * @ClassName Assignment
 * @Description TODO 资源分配关系
 * @Author hewguo
 * @Date 2020-12-24 11:00
 * @Version 1.0
 **/
public class Assignment {
    private long resourceUID;
    private long taskUID;
    private double units;

    public long getResourceUID() {
        return resourceUID;
    }

    public void setResourceUID(long resourceUID) {
        this.resourceUID = resourceUID;
    }

    public long getTaskUID() {
        return taskUID;
    }

    public void setTaskUID(long taskUID) {
        this.taskUID = taskUID;
    }

    public double getUnits() {
        return units;
    }

    public void setUnits(double units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ResourceUID\":" + resourceUID +
                ", \"TaskUID\":" + taskUID +
                ", \"Units\":" + units +
                '}';
    }
}
