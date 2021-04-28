package com.suola.project.util;

import com.apple.eawt.AppEvent;
import com.apple.eawt.OpenFilesHandler;
import com.suola.project.model.PROJECTDB;
import com.suola.project.ui.controller.WebviewController;
import com.suola.project.ui.fx.StageManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class MppOpenFilesHandler implements OpenFilesHandler {
    private static Logger logger = LoggerFactory.getLogger(MppOpenFilesHandler.class);
    public static File file = null;
    @Override
    public void openFiles(AppEvent.OpenFilesEvent openFilesEvent) {
        logger.info("-------======读取文件22222================");
        for (File file1: openFilesEvent.getFiles()) {
            file=file1;
            try {
                logger.info("打开文件为：：" + file.getName() + "------" + file.getAbsolutePath());
            }catch (Exception ex){
                logger.error(ex.toString());
            }
        }
//        if(file!=null){
//            System.out.println("读取文件为：："  + file.getAbsolutePath());
//        }
//        if(file!=null){
//            logger.info("读取文件为：："  + file.getAbsolutePath());
////            MppUtils mppUtils=new MppUtils();
////            mppUtils.readFile(file.getAbsolutePath());
////            PROJECTDB.getInstance().setProjectModel(mppUtils.readFile(openFilesEvent.getFiles().get(0).getAbsolutePath()));
////            logger.info("文件读取111完成"+PROJECTDB.getInstance().getProjectModel().toString());
//
//        }
//        if(openFilesEvent.getFiles().size()>0){
//            //读取文件
//            logger.info("-------读取文件----------"+openFilesEvent.getFiles().get(0).getAbsolutePath());
////            MppUtils mppUtils=new MppUtils();
////            mppUtils.readFile(openFilesEvent.getFiles().get(0).getAbsolutePath());
////            PROJECTDB.getInstance().setProjectModel(mppUtils.readFile(openFilesEvent.getFiles().get(0).getAbsolutePath()));
//            logger.info("-------读取文件11----------");
//        }
    }
}
