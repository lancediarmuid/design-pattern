package flyweightpattern.chess;

import java.util.HashMap;
import java.util.Map;

/**
 * 棋盘类（主方法调用类）
 */
public class ChessBoard {
    private Map<Integer, ChessPiece> chessPieces = new HashMap<>();

    public ChessBoard(){
        init();
    }

    private void init() {
        // 摆放棋子
        chessPieces.put(1, new ChessPiece(ChessPieceUnitFactory.getChessPiece(1), 0, 0));
        chessPieces.put(2, new ChessPiece(ChessPieceUnitFactory.getChessPiece(2), 1, 0));
        // 省略……
    }

    /**
     * 棋子移动
     * @param chessPieceId
     * @param toPositionX
     * @param toPositionY
     */
    public void move(int chessPieceId, int toPositionX, int toPositionY){
        //……
    }
}
