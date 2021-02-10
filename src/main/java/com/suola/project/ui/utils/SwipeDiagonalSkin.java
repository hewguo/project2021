package com.suola.project.ui.utils;

/**
 * @ClassName SwipeDiagonalSkin
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
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  14/12/2018
 */
public class SwipeDiagonalSkin extends SkinBase<Labeled>{

    private Label title = new Label("Button");

    private Paint firstColor;

    private StackPane rect = ((GNButton) getSkinnable()).rect;

    private ObjectProperty<Duration> velocity = new SimpleObjectProperty<>(this, "velocity");

    SwipeDiagonalSkin(GNButton control) {
        super(control);

        SVGPath shape = new SVGPath();
        shape.setContent("M 250 200 L 250 300 L 500 300 L 450 200 L 250 200 ");
        rect.setShape(shape);

        rect.setPrefWidth(Region.USE_COMPUTED_SIZE);
        rect.setMaxWidth(Region.USE_COMPUTED_SIZE);

        rect.setPrefHeight(Region.USE_COMPUTED_SIZE);
        rect.setMaxHeight(Region.USE_COMPUTED_SIZE);

//        rect.setTranslateX(0);

        getChildren().clear();

        getChildren().add(rect);
        getChildren().add(title);

        velocity.bind( ((GNButton)getSkinnable()).transitionDurationProperty());
        title.textProperty().bind(getSkinnable().textProperty());
        title.fontProperty().bind(getSkinnable().fontProperty());
        title.textFillProperty().bind(getSkinnable().textFillProperty());
        title.underlineProperty().bind(getSkinnable().underlineProperty());
        title.textAlignmentProperty().bind(getSkinnable().textAlignmentProperty());
        title.contentDisplayProperty().bind(getSkinnable().contentDisplayProperty());
        title.ellipsisStringProperty().bind( getSkinnable().ellipsisStringProperty() );
        title.backgroundProperty().bind(getSkinnable().backgroundProperty());
        title.alignmentProperty().bind(getSkinnable().alignmentProperty());
        title.textOverrunProperty().bind(getSkinnable().textOverrunProperty());


        Rectangle clip = new Rectangle();
        clip.setArcWidth(0);
        clip.setArcHeight(0);
        getSkinnable().setClip(clip);

        clip.widthProperty().bind(getSkinnable().widthProperty());
        clip.heightProperty().bind(getSkinnable().heightProperty());

        Timeline timeEntered = new Timeline();
        Timeline timeExited = new Timeline();

        firstColor = getSkinnable().getTextFill();

        getSkinnable().textFillProperty().addListener((observable, oldValue, newValue) -> {
            if(timeEntered.getStatus() == Animation.Status.STOPPED && timeExited.getStatus() == Animation.Status.STOPPED ) {
                firstColor = newValue;
            }
        });


        getSkinnable().setOnMouseEntered(event -> {
            timeEntered.getKeyFrames().clear();

            timeEntered.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.translateXProperty(), rect.getTranslateX())),
                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),

                    new KeyFrame(velocity.get(), new KeyValue(rect.translateXProperty(), rect.getWidth())),
                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), ((GNButton) getSkinnable()).getTransitionText()))
            );

            if (timeExited.getStatus() == Animation.Status.RUNNING){
                timeExited.stop();
            }

            timeEntered.play();

        });

        getSkinnable().setOnMouseExited(event -> {
            timeExited.getKeyFrames().clear();
            timeExited.getKeyFrames().addAll(
                    new KeyFrame(Duration.ZERO, new KeyValue(rect.translateXProperty(), rect.getTranslateX())),

                    new KeyFrame(Duration.ZERO, new KeyValue(getSkinnable().textFillProperty(), getSkinnable().getTextFill())),

                    new KeyFrame(velocity.get(),
                            new KeyValue(rect.translateXProperty(), 0D)),
                    new KeyFrame(velocity.get(), new KeyValue(getSkinnable().textFillProperty(), firstColor))

            );

            if (timeEntered.getStatus() == Animation.Status.RUNNING){
                timeEntered.stop();
            }

            timeExited.play();
        });
    }

    @Override
    protected double computeMinWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return rect.minWidth(height);
    }

    @Override
    protected double computeMinHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return rect.minHeight(width);
    }

    @Override
    protected double computePrefWidth(double height, double topInset, double rightInset, double bottomInset, double leftInset) {
        return rect.prefWidth(height) + leftInset + rightInset;
    }

    @Override
    protected double computePrefHeight(double width, double topInset, double rightInset, double bottomInset, double leftInset) {
        return rect.prefHeight(width) + topInset + bottomInset;
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        super.layoutChildren(contentX, contentY, contentWidth, contentHeight);

        layoutInArea(rect, (contentWidth * -1) -(contentWidth / 4), contentY, contentWidth + (contentWidth / 4) , contentHeight, 0,
                HPos.LEFT,  VPos.CENTER);

        layoutInArea(title, contentX, contentY, contentWidth,contentHeight, 0,
                title.getAlignment().getHpos(),  title.getAlignment().getVpos());
    }
}