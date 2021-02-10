package com.suola.project.ui.utils;

/**
 * @ClassName CalendarHeader
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:25
 * @Version 1.0
 **/
import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.time.LocalDate;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/12/2018
 */
public class CalendarHeader extends Label {

    private LocalDate date;

    CalendarHeader(int id, LocalDate date, String text) {
        setPrefSize(40, 40);
        date = date;
        setId(String.valueOf(id));
        setText(text);
    }

    CalendarHeader(String id, LocalDate date, String text) {
        setPrefSize(40, 40);
        setAlignment(Pos.CENTER);
        setStyle("-fx-background-color : red");
        date = date;
        setId(String.valueOf(id));
        setText(text);
    }
}