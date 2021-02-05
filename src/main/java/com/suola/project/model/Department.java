package com.suola.project.model;

/**
 * @ClassName Department
 * @Description TODO 部门
 * @Author hewguo
 * @Date 2020-12-24 10:30
 * @Version 1.0
 **/
public class Department {
    private long uid;
    private String name;

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

    @Override
    public String toString() {
        return "{" +
                "\"UID\":" + uid +
                ", \"Name\":\"" + name + "\"" +
                '}';
    }
}
