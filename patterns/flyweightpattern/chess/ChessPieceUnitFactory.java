package flyweightpattern.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * 享元类工厂
 * 将享元类给缓存下来
 */
public class ChessPieceUnitFactory {
    // 用于将享元类对象，缓存下来，节约享元类的内存开销
    private static final Map<Integer, ChessPieceUnit> pieces = new HashMap<>();

    static {
        pieces.put(1, new ChessPieceUnit(1, "车", ChessPieceUnit.Color.RED));
        pieces.put(2, new ChessPieceUnit(2, "车", ChessPieceUnit.Color.BLACK));
        pieces.put(3, new ChessPieceUnit(3, "马", ChessPieceUnit.Color.RED));
        pieces.put(4, new ChessPieceUnit(4, "马", ChessPieceUnit.Color.BLACK));

        // ……
    }

    public static ChessPieceUnit getChessPiece(Integer chessPieceId){
        return pieces.get(chessPieceId);
    }

}
