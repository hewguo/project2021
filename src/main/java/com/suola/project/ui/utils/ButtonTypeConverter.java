package com.suola.project.ui.utils;

/**
 * @ClassName ButtonTypeConverter
 * @Description TODO
 * @Author hewguo
 * @Date 2021-02-10 11:15
 * @Version 1.0
 **/
import com.sun.javafx.css.StyleConverterImpl;
import java.util.logging.Logger;
import javafx.css.ParsedValue;
import javafx.css.StyleConverter;
import javafx.scene.text.Font;

public class ButtonTypeConverter extends StyleConverterImpl<String, ButtonType> {

    private ButtonTypeConverter() {
    }

    public static StyleConverter<String, ButtonType> getInstance() {
        return ButtonTypeConverter.Holder.INSTANCE;
    }

    @Override
    public ButtonType convert(ParsedValue<String, ButtonType> value, Font notUsedFont) {
        String string = (String) value.getValue();

        try {
            return ButtonType.valueOf(string.toUpperCase());
        } catch (NullPointerException | IllegalArgumentException var5) {
            Logger.getLogger(ButtonTypeConverter.class.getName()).info(String.format("Invalid button type value '%s'", string));
            return ButtonType.SWIPE;
        }
    }

    @Override
    public String toString() {
        return "ButtonTypeConverter";
    }

    private static class Holder {
        static final ButtonTypeConverter INSTANCE = new ButtonTypeConverter();

        private Holder() {
            throw new IllegalAccessError("Holder class");
        }
    }
}