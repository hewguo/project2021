package com.suola.project.ui.fx;

import com.apple.eawt.AppEvent;
import com.apple.eawt.OpenFilesHandler;
import com.suola.project.model.PROJECTDB;
import com.suola.project.ui.controller.WebviewController;
import com.suola.project.util.ApplicationContextProvider;
import com.suola.project.util.MppUtils;
import com.suola.project.util.ServerConfig;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@Deprecated
public class GlobalMenu extends ContextMenu {
    private static GlobalMenu INSTANCE = null;
    private static Logger logger = LoggerFactory.getLogger(GlobalMenu.class);

    private ServerConfig serverConfig;

    private static WebviewController webviewController;
//    protected static ConfigurableApplicationContext applicationContext;

    private GlobalMenu(Stage primaryStage)
    {
        //============================================
        logger.info("--------------------------");
        if (System.getProperty("os.name").contains("OS X")){
            logger.info("操作系统是 os x openfilehandle");
            com.apple.eawt.Application osXapp=com.apple.eawt.Application.getApplication();
            osXapp.setOpenFileHandler(new OpenFilesHandler() {
                @Override
                public void openFiles(AppEvent.OpenFilesEvent openFilesEvent) {
                    logger.info("-------======================");
                    if(openFilesEvent.getFiles().size()>0){
                        //读取文件
                        logger.info("-----------------"+openFilesEvent.getFiles().get(0).getAbsolutePath());
                        MppUtils mppUtils=new MppUtils();
                        mppUtils.readFile(openFilesEvent.getFiles().get(0).getAbsolutePath());
                        PROJECTDB.getInstance().setProjectModel(mppUtils.readFile(openFilesEvent.getFiles().get(0).getAbsolutePath()));
                        logger.info("-----------------");
                    }
                }
            });
        }

        //============================================
        MenuItem openFileMenu=new MenuItem("打开文件");
        MenuItem saveFileMenu=new MenuItem("保存文件");
        webviewController= ApplicationContextProvider.getBean(WebviewController.class);
        serverConfig=ApplicationContextProvider.getBean(ServerConfig.class);

        getItems().add(openFileMenu);
        getItems().add(saveFileMenu);

        openFileMenu.setOnAction(event -> {
            //文件选择对话框
            System.out.println("open the file");
            FileChooser fileChooser=new FileChooser();
            fileChooser.setTitle("打开Project文件");

            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Microsoft Project文件(*.mpp)","*.mpp"));
            //fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("项目","*.mpp"));
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("所有文件(*.*)","*.*"));

            File file=fileChooser.showOpenDialog(primaryStage);
            if(file!=null){

                MppUtils mppUtils=new MppUtils();
                PROJECTDB.getInstance().setProjectModel(mppUtils.readFile(file.getAbsolutePath()));

                int serverPort=0;
                try{
                    serverPort=serverConfig.getServerPort();
                }catch (Exception ex){

                }

                StageManager stageManager=ApplicationContextProvider.getBean(StageManager.class);

                if(PROJECTDB.getInstance().getProjectModel()!=null){
                    webviewController.setUrl("http://localhost:"+serverPort+"/projectview");

                    stageManager.getDecorator().setTitle("Project2021-"+file.getName());
                    stageManager.getDecorator().centralizeTitle();
                }else {
                    webviewController.setUrl("http://localhost:"+serverPort+"/");
                    stageManager.getDecorator().setTitle("Project2021");
                    stageManager.getDecorator().centralizeTitle();
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
