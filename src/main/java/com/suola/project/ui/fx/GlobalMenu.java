package com.suola.project.ui.fx;

import com.suola.project.model.PROJECTDB;
import com.suola.project.ui.controller.WebviewController;
import com.suola.project.util.ApplicationContextProvider;
import com.suola.project.util.MppUtils;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;

/**
 * @ClassName GlobalMenu
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-04 10:58
 * @Version 1.0
 **/
public class GlobalMenu extends ContextMenu {
    private static GlobalMenu INSTANCE = null;

    private static WebviewController webviewController;
//    protected static ConfigurableApplicationContext applicationContext;

    private GlobalMenu(Stage primaryStage)
    {
        MenuItem openFileMenu=new MenuItem("打开文件");
        MenuItem saveFileMenu=new MenuItem("保存文件");
//        System.out.println("globalmenu init..................");
//        WebApplicationContext wac= ContextLoader.getCurrentWebApplicationContext();
//        webviewController=wac.getBean(WebviewController.class);
        webviewController= ApplicationContextProvider.getBean(WebviewController.class);

        getItems().add(openFileMenu);
        getItems().add(saveFileMenu);

        openFileMenu.setOnAction(event -> {
            //文件选择对话框
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("打开Project文件");

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft Project文件(*.mpp)","*.mpp"));
            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("项目","*.mpp"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("所有文件(*.*)","*.*"));

            File file=fileChooser.showOpenDialog(primaryStage);
            if(file!=null){

                MppUtils mppUtils=new MppUtils();
                PROJECTDB.getInstance().setProjectModel(mppUtils.readFile(file.getAbsolutePath()));

//                System.out.println(PROJECTDB.getInstance().getProjectModel().toString());

                if(PROJECTDB.getInstance().getProjectModel()!=null){
                    webviewController.setUrl("http://localhost:8088/projectview");
                }else {
                    webviewController.setUrl("http://localhost:8088/");
                }
            }

        });
    }

    public static GlobalMenu getInstance(Stage primaryStage)
    {
        if (INSTANCE == null)
        {
            INSTANCE = new GlobalMenu(primaryStage);
        }

        return INSTANCE;
    }

}