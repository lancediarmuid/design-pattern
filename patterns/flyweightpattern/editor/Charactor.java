package flyweightpattern.editor;

/**
 * 业务实体类
 */
public class Charactor {
    private char c;
    private CharactorStyle style;

    public Charactor(char c, CharactorStyle style) {
        this.c = c;
        this.style = style;
    }
}
