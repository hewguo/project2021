package com.suola.project.ui.utils;

/**
 * @ClassName GNCarouselBase
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:47
 * @Version 1.0
 **/
import com.sun.javafx.css.converters.BooleanConverter;
import com.sun.javafx.css.converters.DurationConverter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.beans.DefaultProperty;
import javafx.css.CssMetaData;
import javafx.css.SimpleStyleableBooleanProperty;
import javafx.css.SimpleStyleableObjectProperty;
import javafx.css.Styleable;
import javafx.css.StyleableBooleanProperty;
import javafx.css.StyleableObjectProperty;
import javafx.css.StyleableProperty;
import javafx.scene.control.Control;
import javafx.util.Duration;

@DefaultProperty("children")
public abstract class GNCarouselBase extends Control {

    private StyleableObjectProperty<Duration> transitionDuration;
    private StyleableBooleanProperty          autoRide;

    private List<CssMetaData<? extends Styleable, ?>> STYLEABLES;

    public GNCarouselBase() {

        this.setPrefHeight(200D);
        this.setPrefWidth(200D);

        this.transitionDuration = new SimpleStyleableObjectProperty<>(StyleableProperties.TRANSITION_DURATION, this, "transitionDuration", Duration.millis(300D));

        this.autoRide = new SimpleStyleableBooleanProperty(StyleableProperties.AUTO_RIDE, this , "autoRide", false);

    }

    @Override
    public String getUserAgentStylesheet() {
        return GNCarouselBase.class.getResource("/styles/carousel.css").toExternalForm();
    }

    private static class StyleableProperties {

        private static final List<CssMetaData<? extends Styleable, ?>> CHILD_STYLEABLES;

        private static final CssMetaData<GNCarouselBase, Duration> TRANSITION_DURATION;

        private static final CssMetaData<GNCarouselBase, Boolean> AUTO_RIDE;

        private StyleableProperties(){}

        static {

            TRANSITION_DURATION = new CssMetaData<GNCarouselBase, Duration>("-gn-transition-duration", DurationConverter.getInstance(), Duration.millis(300D)) {

                @Override
                public boolean isSettable(GNCarouselBase styleable) {
                    return styleable.transitionDuration == null || !styleable.transitionDuration.isBound();
                }

                @Override
                public StyleableProperty<Duration> getStyleableProperty(GNCarouselBase styleable) {
                    return styleable.transitionDurationProperty();
                }
            };

            AUTO_RIDE = new CssMetaData<GNCarouselBase, Boolean>("-gn-auto-ride", BooleanConverter.getInstance(), false) {
                @Override
                public boolean isSettable(GNCarouselBase styleable) {
                    return styleable.autoRide == null || !styleable.autoRide.isBound();
                }

                @Override
                public StyleableProperty<Boolean> getStyleableProperty(GNCarouselBase styleable) {
                    return styleable.autoRideProperty();
                }
            };


            List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList(Control.getClassCssMetaData());
            Collections.addAll(styleables, AUTO_RIDE, TRANSITION_DURATION);
            CHILD_STYLEABLES = Collections.unmodifiableList(styleables);
        }
    }

    public List<CssMetaData<? extends Styleable, ?>> getControlCssMetaData() {
        if (this.STYLEABLES == null) {
            List<CssMetaData<? extends Styleable, ?>> styleables = new ArrayList<>(Control.getClassCssMetaData());
            styleables.addAll(getClassCssMetaData());
            styleables.addAll(Control.getClassCssMetaData());
            this.STYLEABLES = Collections.unmodifiableList(styleables);
        }
        return this.STYLEABLES;
    }

    public static List<CssMetaData<? extends Styleable, ?>> getClassCssMetaData() {
        return GNCarouselBase.StyleableProperties.CHILD_STYLEABLES;
    }

    public Duration getTransitionDuration() {
        return transitionDuration.get();
    }

    public StyleableObjectProperty<Duration> transitionDurationProperty() {
        return transitionDuration;
    }

    public void setTransitionDuration(Duration transitionDuration) {
        this.transitionDuration.set(transitionDuration);
    }

    public boolean isAutoRide() {
        return autoRide.get();
    }

    public StyleableBooleanProperty autoRideProperty() {
        return autoRide;
    }

    public void setAutoRide(boolean autoRide) {
        this.autoRide.set(autoRide);
    }
}