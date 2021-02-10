package com.suola.project.ui.utils;

/**
 * @ClassName CalendarInShell
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:26
 * @Version 1.0
 **/
import java.text.DateFormatSymbols;
import java.time.*;
import java.time.chrono.Chronology;
import java.time.chrono.IsoChronology;
import java.time.format.TextStyle;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;

/**
 * @author Gleidson Neves da Silveira | gleidisonmt@gmail.com
 * Create on  09/12/2018
 */
public class CalendarInShell {

    private CalendarInShell(){};

    public static void main(String[] args) {

        int columns = 7;
        int rows = 6;
        int count;

//        Locale.setDefault(Locale.US);

        LocalDate localDate = LocalDate.of(2019, 3, 1);

        System.out.println("--------------------------------");
        System.out.println("|          Calendar            |");
        System.out.println("--------------------------------");

        System.out.print("| ");

        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] dayNames = symbols.getShortWeekdays();

        for(int k = 1; k < 8;k++){
            System.out.print(dayNames[k] + " ");
        }

        TemporalField fieldUS = WeekFields.of(Locale.US).dayOfWeek();
        count = (localDate.with(fieldUS, 1).getDayOfMonth() - 1);

        if(localDate.getMonthValue() == 1){
            localDate = LocalDate.of(localDate.minusYears(1).getYear(), 12, count);
        } else
            localDate = LocalDate.of(localDate.getYear(), localDate.minusMonths(1).getMonth(), count);


        System.out.println(" |");

        for (int i = 0; i < rows ; i++){
            System.out.print("| ");
            for (int j = 0; j < columns; j++){

                if(localDate.getDayOfYear() == 365 || localDate.getDayOfYear() == 366){
                    localDate = LocalDate.of(
                            localDate.plusYears(1).getYear(),
                            localDate.plusMonths(1).getMonth(),
                            localDate.plusDays(1).getDayOfMonth()
                    );
                }

                else if(localDate.getDayOfMonth() == localDate.getMonth().maxLength()){
                    localDate = LocalDate.of(
                            localDate.getYear(),
                            localDate.plusMonths(1).getMonth(),
                            localDate.plusDays(1).getDayOfMonth()
                    );
                } else {
                    localDate = LocalDate.of(
                            localDate.getYear(),
                            localDate.getMonth(),
                            localDate.plusDays(1).getDayOfMonth()
                    );
                }

                System.out.printf(" %2d " , localDate.getDayOfMonth());
//                System.out.print(localDate + " "); // view complete date
            }
            System.out.println(" |");
        }
        System.out.println("--------------------------------");
    }
}