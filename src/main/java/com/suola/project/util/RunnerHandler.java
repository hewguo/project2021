package com.suola.project.util;

import com.apple.eawt.AppEvent;
import com.apple.eawt.OpenFilesHandler;
import com.suola.project.model.PROJECTDB;
import com.suola.project.ui.controller.WebviewController;
import com.suola.project.ui.fx.StageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * springboot 加载完成后添加监听文件打开
 */
@Component
public class RunnerHandler implements ApplicationRunner, CommandLineRunner {
    private static Logger logger = LoggerFactory.getLogger(RunnerHandler.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("服务启动RunnerTest66666  ApplicationRunner执行启动加载任务.22222..");
        //logger.info("=================="+MppOpenFilesHandler.file.getName());
//        if(MppOpenFilesHandler.file!=null){
//            MppUtils mppUtils=new MppUtils();
//            mppUtils.readFile(MppOpenFilesHandler.file.getAbsolutePath());
//            PROJECTDB.getInstance().setProjectModel(mppUtils.readFile(MppOpenFilesHandler.file.getAbsolutePath()));
//        }
//
//        if(PROJECTDB.getInstance().getProjectModel()!=null) {
//            WebviewController webviewController = ApplicationContextProvider.getBean(WebviewController.class);
//            ServerConfig serverConfig = ApplicationContextProvider.getBean(ServerConfig.class);
//            int serverPort = 0;
//            try {
//                serverPort = serverConfig.getServerPort();
//            } catch (Exception ex) {
//
//            }
//            StageManager stageManager = ApplicationContextProvider.getBean(StageManager.class);
//
//            if (PROJECTDB.getInstance().getProjectModel() != null) {
//                webviewController.setUrl("http://localhost:" + serverPort + "/projectview");
//
//                stageManager.getDecorator().setTitle("Project2021-" + MppOpenFilesHandler.file.getName());
//            } else {
//                webviewController.setUrl("http://localhost:" + serverPort + "/");
//                stageManager.getDecorator().setTitle("Project2021");
//            }
//            logger.info("文件加载完成");
//        }


    }

    @Override
    public void run(String... args) throws Exception {
        logger.info("服务启动RunnerTest    CommandLineRunner 执行启动加载任务...");
    }
}
