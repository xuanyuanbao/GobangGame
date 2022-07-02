package chess;

import java.awt.*;

/**
 * @author 徐望�?
 * 抽象棋子类，白棋和黑棋均继承于此
 * x，y代表棋子的坐�?
 * size代表棋子的大�?
 */
public abstract class Chess {
    protected int x;
    protected int y;
    protected int size = 30;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public abstract void move();
    public abstract void drawChess(Graphics g);
}
