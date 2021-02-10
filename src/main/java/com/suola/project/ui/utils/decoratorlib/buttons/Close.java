package com.suola.project.ui.utils.decoratorlib.buttons;

/**
 * @ClassName Close
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 12:02
 * @Version 1.0
 **/
import com.sun.javafx.css.converters.PaintConverter;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.suola.project.ui.utils.decoratorlib.backgrund.GNBackground;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Skin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author   Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Creation  15/04/2018
 */
public class Close extends Button {

    private static final String USER_AGENT_STYLESHEET = GNBackground.class.getResource("/styles/decorator/css/controls/buttons.css").toExternalForm();
    private final ImageView viewClose = new ImageView(new Image("/styles/decorator/img/close.png"));

    public Close(){
        getStyleClass().add("gn-close");
        super.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        super.setGraphic(viewClose);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ButtonSkin(Close.this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return USER_AGENT_STYLESHEET;
    }

    private final StyleableObjectProperty<Paint> defaultFill = new SimpleStyleableObjectProperty<>(StyleableProperties.DEFAULT_FILL,
            Close.this,
            "defaultFill",
            Color.WHITE);

    public Paint getDefaultFill() {
        return defaultFill.get();
    }

    public StyleableObjectProperty<Paint> defaultFillProperty() {
        return this.defaultFill;
    }

    public void setDefaultFill(Paint color) {
        this.defaultFill.set(color);
        this.setStyle("-gn-fill : white");
    }

    private static class StyleableProperties {

        private static final CssMetaData<Close, Paint> DEFAULT_FILL
                = new CssMetaData<Close, Paint>("-gn-fill",
                PaintConverter.getInstance(), Color.RED) {
            @Override
            public boolean isSettable(Close control) {
                return control.defaultFill == null || !control.defaultFill.isBound();
            }

            @Override
            public StyleableProperty<Paint> getStyleableProperty(Close control) {
                return control.defaultFillProperty();
            }
        };

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        static {
            final List<CssMetaData<? extends Styleable, ?>> styleables
                    = new ArrayList<>(Button.getClassCssMetaData());
            Collections.addAll(styleables,
                    DEFAULT_FILL);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    // inherit the styleable properties from parent
    private List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    @Override
    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        if (STYLEABLES == null) {
            final List<CssMetaData<? extends Styleable, ?>> styleables
                    = new ArrayList<>(Button.getClassCssMetaData());
            styleables.addAll(getClassCssMetaData());
            styleables.addAll(Close.getClassCssMetaData());
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
        return STYLEABLES;
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.CHILD_STYLEABLES;
    }

}