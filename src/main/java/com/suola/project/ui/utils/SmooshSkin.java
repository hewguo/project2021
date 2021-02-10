package com.suola.project.ui.utils;

/**
 * @ClassName SmooshSkin
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:17
 * @Version 1.0
 **/
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
public class SmooshSkin extends SkinBase<Labeled> {

    private Rectangle clip = new Rectangle();
    private Label title = new Label("Button");

    private Paint firstColor;

    private StackPane rect = ((GNButton) getSkinnable()).rect;
    private StackPane rect_bottom = new StackPane();

    private ObjectProperty<Duration> velocity = new SimpleObjectProperty<>(this, "velocity");

    SmooshSkin(GNButton control) {
        super(control);

        rect.setShape(null);

        rect.setPrefHeight(0);
        rect.setMaxHeight(0);

        rect.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect.setMaxWidth(Region.USE_COMPUTED_SIZE);

        rect_bottom.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect_bottom.setMaxWidth(Region.USE_COMPUTED_SIZE);

        rect_bottom.setMaxHeight(0);
        rect_bottom.setPrefHeight(0);

        getChildren().clear();

        getChildren().add(rect);
        getChildren().add(rect_bottom);
        getChildren().add(title);

        velocity.bind( ((GNButton)getSkinnable()).transitionDurationProperty());
        title.textProperty().bind(getSkinnable().textProperty());
        title.fontProperty().bind(getSkinnable().fontProperty());
        title.textFillProperty().bind(getSkinnable().textFillProperty());
        title.underlineProperty().bind(getSkinnable().underlineProperty());
        title.textAlignmentProperty().bind(getSkinnable().textAlignmentProperty());
        title.contentDisplayProperty().bind(getSkinnable().contentDisplayProperty());
        title.ellipsisStringProperty().bind(getSkinnable().ellipsisStringProperty());
        title.backgroundProperty().bind(getSkinnable().backgroundProperty());
        title.alignmentProperty().bind(getSkinnable().alignmentProperty());
        title.textOverrunProperty().bind(getSkinnable().textOverrunProperty());

        clip.setArcWidth(0);
        clip.setArcHeight(0);
        getSkinnable().setClip(clip);

        clip.widthProperty().bind(getSkinnable().widthProperty());
        clip.heightProperty().bind(getSkinnable().heightProperty());

        Timeline timeEntered = new Timeline();
        Timeline timeExited = new Timeline();

        firstColor = getSkinnable().getTextFill();

        rect_bottom.setBackground(new Background(new BackgroundFill(
                ((GNButton)getSkinnable()).getTransitionColor(), CornerRadii.EMPTY, Insets.EMPTY
        )));

        ((GNButton)getSkinnable()).transitionColorProperty().addListener(new ChangeListener<Paint>() {
            @Override
            public void changed(ObservableValue<? extends Paint> observable, Paint oldValue, Paint newValue) {
                rect_bottom.setBackground(new Background(new BackgroundFill(newValue, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        });

        getSkinnable().textFillProperty().addListener((observable, oldValue, newValue) -> {
            if (timeEntered.getStatus() == Animation.Status.STOPPED && timeExited.getStatus() == Animation.Status.STOPPED) {
                firstColor = newValue;
            }
        });


        getSkinnable().setOnMouseEntered(event -> {
            timeEntered.getKeyFrames().clear();


            timeEntered.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), rect.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect_bottom.prefHeightProperty(), rect_bottom.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect_bottom.maxHeightProperty(), rect_bottom.getHeight())),


                    new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), getSkinnable().getHeight() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.maxHeightProperty(), getSkinnable().getHeight() / 2)),

                    new KeyFrame(velocity.get(), new KeyValue(rect_bottom.prefHeightProperty(), getSkinnable().getHeight() / 2)),
                    new KeyFrame(velocity.get(), new KeyValue(rect_bottom.maxHeightProperty(), getSkinnable().getHeight() / 2)),


                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),
                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), ((GNButton) getSkinnable()).getTransitionText()))

            );

            if (timeExited.getStatus() == Animation.Status.RUNNING) {
                timeExited.stop();
            }

            timeEntered.play();

        });

        getSkinnable().setOnMouseExited(event -> {
            timeExited.getKeyFrames().clear();
            timeExited.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.prefHeightProperty(), rect.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.maxHeightProperty(), rect.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(rect_bottom.prefHeightProperty(), rect_bottom.getHeight())),
                    new KeyFrame(Duration.ZERO, new KeyValue(rect_bottom.maxHeightProperty(), rect_bottom.getHeight())),

                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),

                    new KeyFrame(velocity.get(), new KeyValue(rect.prefHeightProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect.maxHeightProperty(), 0D)),

                    new KeyFrame(velocity.get(), new KeyValue(rect_bottom.prefHeightProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(rect_bottom.maxHeightProperty(), 0D)),

                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), firstColor))

            );

            if (timeEntered.getStatus() == Animation.Status.RUNNING) {
                timeEntered.stop();
            }

            timeExited.play();
        });
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.minWidth(height);
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.minHeight(width);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.prefWidth(height) + leftInset + rightInset;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return title.prefHeight(width) + topInset + bottomInset;
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);

        layoutInArea(rect, contentX, 0 + snappedTopInset(), contentWidth, contentHeight, 0,
                HPos.LEFT, VPos.TOP);

        layoutInArea(rect_bottom, contentX, contentY, contentWidth, contentHeight, 0,
                HPos.LEFT, VPos.BOTTOM);

        layoutInArea(title, contentX, contentY, contentWidth, contentHeight, 0,
                title.getAlignment().getHpos(), title.getAlignment().getVpos());
    }
}