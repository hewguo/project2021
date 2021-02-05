package com.suola.project.model;

/**
 * @ClassName PROJECTDB
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-04 12:40
 * @Version 1.0
 **/
public class PROJECTDB {
    private static PROJECTDB INSTANCE = null;

    public static PROJECTDB getInstance(){
        if (INSTANCE == null){
            INSTANCE = new PROJECTDB();
        }
        return INSTANCE;
    }

    public PROJECTDB(){

    }

    private ProjectModel projectModel;

    public ProjectModel getProjectModel() {
        return projectModel;
    }

    public void setProjectModel(ProjectModel projectModel) {
        this.projectModel = projectModel;
    }
}
