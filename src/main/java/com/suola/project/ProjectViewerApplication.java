package com.suola.project;

import com.suola.project.ui.fx.AbstractFxApplication;
import com.suola.project.ui.fx.FxmlView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @ClassName ProjectViewerApplication
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-03 08:10
 * @Version 1.0
 **/
@SpringBootApplication
public class ProjectViewerApplication extends AbstractFxApplication {
    public static void main(String[] args) {
        run(ProjectViewerApplication.class,
                Arrays.asList(new FxmlView[] { FxmlView.MODULE_WEBVIEW }),
                FxmlView.MODULE_WEBVIEW, args);
    }
}
