package flyweightpattern.editor;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CharactorStyleFactory {
    private static final List<CharactorStyle> styles = new ArrayList<>();

    public static CharactorStyle getStyle(Font font, int size, int colorRGB) {
        CharactorStyle newStyle = new CharactorStyle(font, size, colorRGB);
        for (CharactorStyle style : styles) {
            if (style.equals(newStyle)) {
                return style;
            }
        }
        styles.add(newStyle);
        return newStyle;
    }
}
