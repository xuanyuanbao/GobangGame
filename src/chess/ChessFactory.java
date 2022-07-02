package chess;

import java.util.ArrayList;

/**
 * @author 徐望成
 * 棋子工厂
 * 需要什么直接取就可以
 */
public class ChessFactory {
    private ArrayList<Chess> chessList = new ArrayList<Chess>();

    /**
     * @author 徐望成
     * 初始化五子棋工厂，将白棋和黑棋放入到五子棋工厂内
     */
    public ChessFactory(){
        WhiteChess whiteChess = WhiteChess.getWhiteChess();
        BlackChess blackChess = BlackChess.getBlackChess();
        chessList.add(whiteChess);
        chessList.add(blackChess);
    }

    /**
     * @author 徐望成
     * 需要什么棋子，只需要传入x，y的坐标和棋子的颜色，就可以返回需要的棋子
     * @param x
     * @param y
     * @param color
     * @return 如果color为white，返回一颗白棋；如果color为black，返回一颗黑棋
     */
    public Chess getChess(int x, int y,String color) {
        if (color.equals("White")){
            Chess chess = chessList.get(0);
            chess.setX(x);
            chess.setY(y);
            return chess;
        }else if (color.equals("Black")){
            Chess chess = chessList.get(1);
            chess.setX(x);
            chess.setY(y);
            return chess;
        }else {
            System.out.println("输入有误，请检查后重新输入");
            return null;
        }
    }
}
