package com.suola.project;

import com.suola.project.ui.fx.FxmlView;
import com.suola.project.util.MppOpenFilesHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.PrintStream;
import java.util.Arrays;

@SpringBootApplication
public class Launch {
    private static Logger logger = LoggerFactory.getLogger(Launch.class);
    public static void main(String[] args) {
        //============================================
//        String path="logs/System.out.log";
//        FileOutputStream puts = null;
//        try {
//            puts = new FileOutputStream(path,true);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        PrintStream out = new PrintStream(puts);
//        System.setOut(out);

        logger.info("------------2222--------------");
//        System.out.println("=============");
        if (System.getProperty("os.name").contains("OS X")){
            logger.info("操作系统是 os x openfilehandle");
            com.apple.eawt.Application osXapp=com.apple.eawt.Application.getApplication();
            MppOpenFilesHandler mppOpenFilesHandler=new MppOpenFilesHandler();
            osXapp.setOpenFileHandler(mppOpenFilesHandler);
        }
        //============================================
        ProjectViewerApplication.run(ProjectViewerApplication.class,
                Arrays.asList(new FxmlView[] { FxmlView.MODULE_WEBVIEW }),
                FxmlView.MODULE_WEBVIEW, args);
    }
}
