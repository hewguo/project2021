package com.suola.project.model;

/**
 * @ClassName PredecessorLink
 * @Description TODO 前置任务
 * @Author hewguo
 * @Date 2020-12-24 10:55
 * @Version 1.0
 **/
public class PredecessorLink {
    private long taskid;
    private long linkLag;
    private int type;
    private long lagFormat;
    private long predecessorUID;

    public long getTaskid() {
        return taskid;
    }

    public void setTaskid(long taskid) {
        this.taskid = taskid;
    }

    public long getLinkLag() {
        return linkLag;
    }

    public void setLinkLag(long linkLag) {
        this.linkLag = linkLag;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public long getLagFormat() {
        return lagFormat;
    }

    public void setLagFormat(long lagFormat) {
        this.lagFormat = lagFormat;
    }

    public long getPredecessorUID() {
        return predecessorUID;
    }

    public void setPredecessorUID(long predecessorUID) {
        this.predecessorUID = predecessorUID;
    }

    @Override
    public String toString() {
        return "{" +
                "\"TaskUID\":" + taskid +
                ", \"LinkLag\":" + linkLag +
                ", \"LagFormat\":" + lagFormat +
                ", \"Type\":" + type +
                ", \"PredecessorUID\":" + predecessorUID +
                '}';
    }
}
