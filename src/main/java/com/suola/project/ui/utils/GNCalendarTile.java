package com.suola.project.ui.utils;

/**
 * @ClassName GNCalendarTile
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:28
 * @Version 1.0
 **/
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

public class GNCalendarTile extends Region {
    private VBox base = new VBox();

    public GNCalendarTile() {
        this.base.getStyleClass().add("calendar-tile");
        this.base.setAlignment(Pos.CENTER);
        Label title = new Label(LocalDate.now().getMonth().getDisplayName(TextStyle.FULL, Locale.US) + ", " + LocalDate.now().getYear());
        Label footer = new Label(String.valueOf(LocalDate.now().getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.US)));
        footer.setMinHeight(40.0D);
        title.setMinHeight(40.0D);
        title.getStyleClass().add("title");
        footer.getStyleClass().add("footer");
        CalendarPane tile = new CalendarPane();
        this.base.getChildren().add(title);
        this.base.getChildren().add(tile);
        this.base.getChildren().add(footer);
        this.setPrefSize(200.0D, 200.0D);
        this.getChildren().add(this.base);
        VBox.setVgrow(title, Priority.ALWAYS);
        VBox.setVgrow(tile, Priority.ALWAYS);
        VBox.setVgrow(footer, Priority.ALWAYS);
    }

    @Override
    public double computeMinWidth(double height) {
        return this.base.minWidth(height);
    }

    @Override
    public double computeMinHeight(double width) {
        return this.base.minHeight(width);
    }

    @Override
    public double computePrefWidth(double height) {
        return this.base.prefWidth(height) + this.snappedLeftInset() + this.snappedRightInset();
    }

    @Override
    public double computePrefHeight(double width) {
        return this.base.prefHeight(width) + this.snappedTopInset() + this.snappedBottomInset();
    }

    @Override
    public void layoutChildren() {
        double x = this.snappedLeftInset();
        double y = this.snappedTopInset();
        double w = this.getWidth() - (this.snappedLeftInset() + this.snappedRightInset());
        double h = this.getHeight() - (this.snappedTopInset() + this.snappedBottomInset());
        this.base.resizeRelocate(x, y, w, h);
    }

    @Override
    public String getUserAgentStylesheet() {
        return GNCalendarTile.class.getResource("/styles/calendar.css").toExternalForm();
    }
}
