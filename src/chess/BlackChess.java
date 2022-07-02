package chess;

import javax.swing.*;
import java.awt.*;

/**
 * @autho 徐望成
 * 黑棋类，使用单例模式
 */
public class BlackChess extends Chess{
    private static final BlackChess blackChess = new BlackChess();
    private ImageIcon imageIcon = new ImageIcon("images/blackChess.png");

    private BlackChess(){

    }

    public static BlackChess getBlackChess(){
        return blackChess;
    }


    @Override
    public void drawChess(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.BLACK);
        g.drawImage(imageIcon.getImage(),getX() - getSize() / 2,getY() - getSize() / 2,null);
//        g.fillOval(getX() - getSize() / 2,getY() - getSize() / 2,getSize(),getSize());
        g.setColor(c);
    }

    @Override
    public void move() {

    }
}
