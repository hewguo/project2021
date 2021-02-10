package com.suola.project.ui.utils.decoratorlib.backgrund;

/**
 * @ClassName GNBackground
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:59
 * @Version 1.0
 **/
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

/**
 * @author   Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Creation  20/04/2018
 */
public class GNBackground extends StackPane {

    private static final String USER_AGENT_STYLESHEET = GNBackground.class.getResource("/styles/decorator/css/decorator/decorator.css").toExternalForm();

    public GNBackground() {
        super();
        getStyleClass().add("gn-decorator");
        setAlignment(Pos.CENTER);
    }


    @Override
    public String getUserAgentStylesheet() {
        return USER_AGENT_STYLESHEET;
    }
}