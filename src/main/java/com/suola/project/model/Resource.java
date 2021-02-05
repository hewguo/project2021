package com.suola.project.model;

/**
 * @ClassName Resource
 * @Description TODO 资源
 * @Author hewguo
 * @Date 2020-12-24 10:31
 * @Version 1.0
 **/
public class Resource {
    private long uid;
    private String name;
    private int type;
    private int maxUnits;
    private double cost;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public double getMaxUnits() {
        return maxUnits;
    }

    public void setMaxUnits(int maxUnits) {
        this.maxUnits = maxUnits;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "{" +
                "\"UID\":" + uid +
                ", \"Name\":\"" + name + "\"" +
                ", \"Type\":" + type +
                ", \"MaxUnits\":" + maxUnits +
                ", \"Cost\":" + cost +
                '}';
    }
}
