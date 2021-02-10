package com.suola.project.ui.utils.decoratorlib.buttons;

/**
 * @ClassName Maximize
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 12:04
 * @Version 1.0
 **/
import com.sun.javafx.css.converters.EffectConverter;
import com.sun.javafx.css.converters.PaintConverter;
import com.sun.javafx.scene.control.skin.ButtonSkin;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Skin;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * @author   Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Creation  15/04/2018
 */
public class Maximize extends Button {


    private final ImageView viewMaximize = new ImageView(new Image("/styles/decorator/img/maximize.png"));
    private final ImageView viewRestore = new ImageView(new Image("/styles/decorator/img/restore.png"));

    public Maximize(){
        getStyleClass().add("gn-maximize");
        this.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
        this.setGraphic(viewMaximize);
    }

    public void updateState(boolean maximize){
        if(maximize)this.setGraphic(viewMaximize);
        else this.setGraphic(viewRestore);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new ButtonSkin(Maximize.this);
    }

    @Override
    public String getUserAgentStylesheet() {
        return getClass().getResource("/styles/decorator/css/controls/buttons.css").toExternalForm();
    }

    private final StyleableObjectProperty<Paint> defaultFill = new SimpleStyleableObjectProperty<>(StyleableProperties.DEFAULT_FILL,
            Maximize.this,
            "defaultFill",
            Color.WHITE);

    public Paint getdefaultFill() {
        return defaultFill.get();
    }

    public StyleableObjectProperty<Paint> defaultFillProperty() {
        return this.defaultFill;
    }

    public void setdefaultFill(Paint color) {
        this.defaultFill.set(color);
    }

    private static class StyleableProperties {

        private static final CssMetaData<Maximize, Paint> DEFAULT_FILL
                = new CssMetaData<Maximize, Paint>("-gn-fill",
                PaintConverter.getInstance(), Color.RED) {
            @Override
            public boolean isSettable(Maximize control) {
                return control.defaultFill == null || !control.defaultFill.isBound();
            }

            @Override
            public StyleableProperty<Paint> getStyleableProperty(Maximize control) {
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
            styleables.addAll(Maximize.getClassCssMetaData());
            STYLEABLES = Collections.unmodifiableList(styleables);
        }
        return STYLEABLES;
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return StyleableProperties.CHILD_STYLEABLES;
    }

}