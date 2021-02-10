package com.suola.project.ui.utils;

/**
 * @ClassName CalendarPane
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:26
 * @Version 1.0
 **/
import javafx.scene.control.Control;
import javafx.scene.control.Skin;

import java.time.LocalDate;
import java.util.Locale;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/12/2018
 */
public class CalendarPane extends Control {

    private CalendarSkeleton skeleton = new CalendarSkeleton();
    private Locale locale;
    private LocalDate date;

    public CalendarPane(){
        this(LocalDate.now(), Locale.getDefault());
    }

    public CalendarPane(LocalDate date){
        this(date, Locale.getDefault());
    }

    public CalendarPane(LocalDate date, Locale locale) {
        super();
        this.date = date;
        this.locale = locale;
        skeleton.setLocale(locale);
        skeleton.setDate(date);
        getStyleClass().add("calendar-pane");
    }

    private boolean horizontalLines = true;

    public void setHorizontalLines(boolean lines){
        this.horizontalLines = lines;
    }

    public boolean isHorizontalLines() {
        return horizontalLines;
    }

    void refresh(LocalDate date) {
        skeleton.setDate(date);
        setSkin(new CalendarSkin(this));
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        return new CalendarSkin(this);
    }

    CalendarSkeleton getSkeleton() {
        return skeleton;
    }
}