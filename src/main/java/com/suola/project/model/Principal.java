package com.suola.project.model;

/**
 * @ClassName Principal
 * @Description TODO 负责人
 * @Author hewguo
 * @Date 2020-12-24 10:27
 * @Version 1.0
 **/
public class Principal {
    private long uid;
    private String name;
    private long department;

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

    public long getDepartment() {
        return department;
    }

    public void setDepartment(long department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "{" +
                "\"UID\":" + uid +
                ", \"Name\":\"" + name + "\"" +
                ", \"Department\":" + department +
                '}';
    }
}
