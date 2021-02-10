package com.suola.project.ui.utils;

/**
 * @ClassName GNCarouselBehavior
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:49
 * @Version 1.0
 **/
import com.sun.javafx.scene.control.behavior.BehaviorBase;
import com.sun.javafx.scene.control.behavior.KeyBinding;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.input.KeyCode;

public class GNCarouselBehavior extends BehaviorBase<GNCarousel> {

    protected static final List<KeyBinding> CAROUSEL_BINDINGS = new ArrayList<KeyBinding>();

    static {
        CAROUSEL_BINDINGS.add(new KeyBinding(KeyCode.LEFT, "previous"));
        CAROUSEL_BINDINGS.add(new KeyBinding(KeyCode.RIGHT, "next"));
    }

    public GNCarouselBehavior(final GNCarousel control) {
        super(control, CAROUSEL_BINDINGS);
    }

    @Override
    protected void callAction(String name) {
        GNCarousel carouselU = getControl();
        GNCarouselSkin skinU = (GNCarouselSkin) carouselU.getSkin();

        switch (name){
            case "previous" :
                skinU.previous();
                break;
            case "next" :
                skinU.next();
                break;
        }
    }
}