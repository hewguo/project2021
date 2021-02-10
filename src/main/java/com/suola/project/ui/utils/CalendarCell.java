package com.suola.project.ui.utils;

/**
 * @ClassName CalendarCell
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:25
 * @Version 1.0
 **/
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;

import java.time.LocalDate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/12/2018
 */
public class CalendarCell extends ToggleButton {

    private LocalDate date;

    CalendarCell(int id, LocalDate date, String text) {
        setPrefSize(40, 40);
        date = date;
        setId(String.valueOf(id));
        setText(text);
    }


    CalendarCell(String id, LocalDate date, String text) {
        setPrefSize(50, 50);
        setAlignment(Pos.CENTER);
        getStyleClass().add("calendar-cell");
        date = date;
        setId(String.valueOf(id));
        setText(text);
    }
}