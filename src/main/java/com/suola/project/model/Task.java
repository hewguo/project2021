package com.suola.project.model;

import java.util.List;

/**
 * @ClassName Task
 * @Description TODO 单项任务
 * @Author hewguo
 * @Date 2020-12-24 11:05
 * @Version 1.0
 **/
public class Task {
    private BaseTask baseTask;
    private List<Task> children;//下一级子任务, 体现树形结构

    public BaseTask getBaseTask() {
        return baseTask;
    }

    public void setBaseTask(BaseTask baseTask) {
        this.baseTask = baseTask;
    }

    public List<Task> getChildren() {
        return children;
    }

    public void setChildren(List<Task> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "{" +baseTask.toString()+
                ", \"children\":" + PrjUtils.arrayToStr(children) +
                '}';
    }
}
