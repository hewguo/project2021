package com.suola.project.ui.utils;

/**
 * @ClassName GNCarousel
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:46
 * @Version 1.0
 **/
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.AccessibleRole;
import javafx.scene.Node;
import javafx.scene.control.Skin;

public class GNCarousel extends GNCarouselBase {

    private static final String DEFAULT_STYLE_CLASS = "gn-carousel";

    private ObjectProperty<ObservableList<Node>> items;

    private BooleanProperty arrows = new SimpleBooleanProperty(this, "arrows", true);
    private BooleanProperty autoRide = new SimpleBooleanProperty(this, "autoRide", false);

    public GNCarousel(){
        this(FXCollections.observableArrayList());
    }

    public GNCarousel(ObservableList<Node> items) {
        initialize();
        setAccessibleRole(AccessibleRole.NODE);
        setItems(items);
    }

    private void initialize(){
        this.getStyleClass().add(DEFAULT_STYLE_CLASS);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new GNCarouselSkin(this);
    }

    public final ObservableList<Node> getItems() {
        return items == null ? null : items.get();
    }

    public void setItems(ObservableList<Node> items) {
        this.itemsProperty().set(items);
    }

    public final ObjectProperty<ObservableList<Node>> itemsProperty() {
        if (items == null) {
            items = new SimpleObjectProperty<>(this, "items");
        }
        return items;
    }

    public boolean isArrows() {
        return arrows.get();
    }

    public BooleanProperty arrowsProperty() {
        return arrows;
    }

    public void setArrows(boolean arrows) {
        this.arrows.set(arrows);
    }
}