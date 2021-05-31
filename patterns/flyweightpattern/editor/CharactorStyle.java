package flyweightpattern.editor;

import java.awt.*;

/**
 * 享元类
 * 在编辑器中，将字体的style抽取出来，设计成享元模式
 */
public class CharactorStyle {
    private Font font;
    private int size;
    private int colorRGB;

    public CharactorStyle(Font font, int size, int colorRGB) {
        this.font = font;
        this.size = size;
        this.colorRGB = colorRGB;
    }

    @Override
    public boolean equals(Object obj) {
        CharactorStyle otherCharactorStyle = (CharactorStyle) obj;
        return font.equals(otherCharactorStyle.font) && size == otherCharactorStyle.size && colorRGB == otherCharactorStyle.colorRGB;
    }
}
