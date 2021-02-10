package com.suola.project.ui.utils;

/**
 * @ClassName CalendarSkin
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 13:20
 * @Version 1.0
 **/
import com.suola.project.ui.utils.CalendarCell;
import com.suola.project.ui.utils.CalendarPane;
import com.suola.project.ui.utils.CalendarSkeleton;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Separator;
import javafx.scene.control.SkinBase;

import java.time.LocalDate;
import java.util.Locale;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/12/2018
 */
public class CalendarSkin extends SkinBase<CalendarPane> {

    private CalendarSkeleton skeleton;

    protected CalendarSkin(CalendarPane control) {
        super(control);
        skeleton = getSkinnable().getSkeleton();
        refresh();
    }

    private void refresh() {
        getChildren().clear();

        String[][] val = skeleton.createSkeleton(skeleton.getLocale());

        for(int k = 0; k < skeleton.getColumns(); k++){
            CalendarCell cell = new CalendarCell(
                    val[0][k],
                    LocalDate.now(),
                    val[0][k]
            );
            getChildren().add(cell);
        }

        for (int i = 1; i < skeleton.getRows(); i++) {
            for(int j = 0; j < skeleton.getColumns(); j++){
                CalendarCell cell = null;

                cell = new CalendarCell(
                        "day" + String.valueOf(LocalDate.parse(val[i][j]).getDayOfMonth()),
                        LocalDate.now(),
                        String.valueOf(LocalDate.parse(val[i][j]).getDayOfMonth())
                );

                // disable week buttons
                if(LocalDate.parse(val[i][j]).getMonthValue()  != skeleton.getDate().getMonthValue()){
                    cell.setDisable(true);
                }

                // select actual date
                if(LocalDate.parse(val[i][j]).equals(LocalDate.now())){
                    cell.setSelected(true);
                }
//                cell.setMouseTransparent(true);
                getChildren().add(cell);


            }
        }
    }

    @Override
    protected void layoutChildren(double contentX, double contentY, double contentWidth, double contentHeight) {
        double width = getSkinnable().getWidth();
        double height = getSkinnable().getHeight();

        double cellWidth = width / skeleton.getColumns();
        double cellHeight = height / skeleton.getRows();

        for (int i = 0; i < skeleton.getRows(); i++) {
            for (int j = 0; j < skeleton.getColumns(); j++) {

                if (getChildren().size() <= ((i * (skeleton.getColumns() )) + j)) {
                    break;
                }

                layoutInArea(getChildren().get((i * (skeleton.getColumns() )) + j), j * cellWidth, i * cellHeight, cellWidth, cellHeight, 0.0d, HPos.CENTER, VPos.CENTER);
            }
        }
    }
}
