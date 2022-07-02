package chess;

import saveGame.*;

import java.awt.*;

import gameFrame.GameFrame;

/**
 * @author 徐望成
 * 棋盘类
 * boardNum为棋盘格子的个数
 * boardRadius为棋盘格子的大小
 * boardSize为棋盘的大小
 * spaceWeight为棋盘左边留出距离
 * spaceHeight为棋盘上边留出距离
 * chessMap为棋盘数组，如果为1代表为白棋，为2代表黑棋
 * 在一个棋盘上有一个五子棋工厂factory，所有的棋子的产生来自于此
 * 棋盘上的拥有对战模式，isManPattern，true代表人人对战模式，false代表人机对战模式
 */
public class ChessBoard {
    private int boardNum = 15;
    private int boardRadius = 40;
    private int boardSize = (boardNum - 1) * boardRadius;
    private int spaceWight = 200;
    private int spaceHeight = 100;
    private GameFrame gameFrame;
    public int[][] chessMap = new int[boardNum][boardNum];
    private ChessFactory factory;
    private boolean isManPattern = true;
    private SaveGame saveGame = new SaveGame();
    private String information = null;

    /**
     * 定义一个数组，如果数值是0，说明没有棋子；
     * 如果是1，则是白棋；
     * 如果是2保存的是黑棋
     * 初始化棋盘，令所有的值为0
     */
    public void initChessBoard() {
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                chessMap[i][j] = 0;
            }
        }
    }

    /**
     * 将棋盘在窗口中绘画出来
     * @param g
     */
    public void drawBoard(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        for (int row = 0; row < boardNum; row++) {
            for (int col = 0; col < boardNum; col++) {
                g.drawLine(spaceWight, row * boardRadius + spaceHeight, spaceWight + boardSize, row * boardRadius + spaceHeight);
                g.drawLine(col * boardRadius + spaceWight, spaceHeight, col * boardRadius + spaceWight, spaceHeight + boardSize);
            }
        }
        g.setColor(c);
    }

    /**
     * @author 将棋盘中所有的棋子画出来
     * @param g
     */
    public void drawAllChess(Graphics g) {
        for (int i = 0; i < boardNum; i++) {
            for (int j = 0; j < boardNum; j++) {
                if (chessMap[i][j] == 1) {
                    factory.getChess(j * boardRadius + spaceWight, i * boardRadius + spaceHeight, "White").drawChess(g);
                } else if (chessMap[i][j] == 2) {
                    factory.getChess(j * boardRadius + spaceWight, i * boardRadius + spaceHeight, "Black").drawChess(g);
                }
            }
        }
    }

    public void drawInformation(Graphics g){
//        g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,30));
//        g.setFont(new Font(Font.DIALOG,Font.LAYOUT_RIGHT_TO_LEFT,30));
        g.setFont(new Font(Font.DIALOG,Font.BOLD,30));
        setInformation("游戏信息：轮到" + (this.gameFrame.getPlayer().isDrop() ? "白方" :"黑方"));
        g.drawString(getInformation(),350,710);
    }

    /**
     * 判断黑白方是否胜利
     * 目前判断的代码比较多，时间充足的话可以改进，减少重复的代码
     *
     * @param x
     * @param y
     * @return true is wined ,false is not wined
     */
    public boolean isWin(int x, int y) {
        int rows = 1, cols = 1, rightOblique = 1, leftOblique = 1;
        int color = chessMap[x][y];

        //横向是否连成5个
        int i = 1;
        while (x + i < boardNum && chessMap[x + i][y] == color) {
            rows++;
            i++;
        }
        i = 1;
        while (x - i >= 0 && chessMap[x - i][y] == color) {
            rows++;
            i++;
        }
        //纵向是否连成5个
        i = 1;
        while (y + i < boardNum && chessMap[x][y + i] == color) {
            cols++;
            i++;
        }
        i = 1;
        while (y - i >= 0 && chessMap[x][y - i] == color) {
            cols++;
            i++;
        }

        //左斜线上是否连成5个
        i = 1;
        while (x - i >= 0 && y - i >= 0 && chessMap[x - i][y - i] == color) {
            leftOblique++;
            i++;
        }
        i = 1;
        while (x + i < boardNum && y + i < boardNum && chessMap[x + i][y + i] == color) {
            leftOblique++;
            i++;
        }

        //右斜线上是否连成5个
        i = 1;
        while (x + i < boardNum && y - i >= 0 && chessMap[x + i][y - i] == color) {
            rightOblique++;
            i++;
        }
        i = 1;
        while (x - i >= 0 && y + i < boardNum && chessMap[x - i][y + i] == color) {
            rightOblique++;
            i++;
        }
        if (rows >= 5 || cols >= 5 || leftOblique >= 5 || rightOblique >= 5) {
            return true;
        }
        return false;

    }

    /**
     * 冗余代码，本来是打算做一个更新上面判断胜负的代码的
     * @param x
     * @param y
     * @return
     */
    public boolean isWined(int x, int y) {
        int[] range = new int[]{-1, 0, 1};
        int color = chessMap[x][y];
        for (int i : range) {
            for (int j : range) {
                if (checkCount(x, y, i, j, color) > 5) {
                    return true;
                }
            }
        }
        return false;
    }

    private int checkCount(int x, int y, int xChange, int yChange, int color) {
        int tempX = xChange;
        int tempY = yChange;
        int count = 1;
        while (chessMap[x + xChange][y + yChange] == color) {
            count++;
            if (xChange != 0) {
                xChange++;
            }
            if (yChange != 0) {
                yChange++;
            }
        }

        while (chessMap[x - xChange][y - yChange] == color) {
            count++;
            if (xChange != 0) {
                xChange++;
            }
            if (yChange != 0) {
                yChange++;
            }
        }
        return count;
    }

    public int getBoardNum() {
        return boardNum;
    }

    public void setBoardNum(int boardNum) {
        this.boardNum = boardNum;
    }

    public int getBoardRadius() {
        return boardRadius;
    }

    public void setBoardRadius(int boardRadius) {
        this.boardRadius = boardRadius;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getSpaceWight() {
        return spaceWight;
    }

    public void setSpaceWight(int spaceWight) {
        this.spaceWight = spaceWight;
    }

    public int getSpaceHeight() {
        return spaceHeight;
    }

    public void setSpaceHeight(int spaceHeight) {
        this.spaceHeight = spaceHeight;
    }

    public int[][] getChessMap() {
        return chessMap;
    }

    public void setChessMap(int[][] chessMap) {
        this.chessMap = chessMap;
    }

    public ChessFactory getFactory() {
        return factory;
    }

    public void setFactory(ChessFactory factory) {
        this.factory = factory;
    }
    public boolean isManPattern() {
        return isManPattern;
    }

    public void setManPattern(boolean manPattern) {
        this.isManPattern = manPattern;
    }

    public SaveGame getSaveGame() {
        return saveGame;
    }

    public void setSaveGame(SaveGame saveGame) {
        this.saveGame = saveGame;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}
