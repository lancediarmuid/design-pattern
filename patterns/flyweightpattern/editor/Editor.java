package flyweightpattern.editor;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

/**
 * 业务逻辑类
 */
public class Editor {
    private List<Charactor> chars = new ArrayList<>();

    public void appendCharacter(char c, Font font, int size, int colorRGB) {
        Charactor charactor = new Charactor(c, CharactorStyleFactory.getStyle(font, size, colorRGB));
        chars.add(charactor);
    }
}
