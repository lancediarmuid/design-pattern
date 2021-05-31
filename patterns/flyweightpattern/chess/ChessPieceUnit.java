package flyweightpattern.chess;

/**
 * 享元类
 * 抽象出棋子固定不会变化的部分，有：
 * 棋子名，棋子颜色的信息
 */
public class ChessPieceUnit {
    private int id;
    private String text;
    private Color color;

    public ChessPieceUnit(int id, String text, Color color){
        this.id = id;
        this.text = text;
        this.color = color;
    }

    public static enum Color{
        RED, BLACK
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        return color;
    }
}
