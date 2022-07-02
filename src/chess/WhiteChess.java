package chess;

import javax.swing.*;
import java.awt.*;

/**
 * @author 徐望成
 * 白棋类，使用单例模式，需要白棋获取就行，调用drawChess方法画一个白棋
 */
public class WhiteChess extends Chess{
    private static final WhiteChess whiteChess = new WhiteChess();
    private ImageIcon imageIcon = new ImageIcon("images/whiteChess.png");

    private WhiteChess() {

    }

    public static WhiteChess getWhiteChess(){
        return whiteChess;
    }

    @Override
    public void drawChess(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.drawImage(imageIcon.getImage(),getX() - getSize() / 2,getY() - getSize() / 2,null);
//        g.fillOval(getX() - getSize() / 2,getY() - getSize() / 2,getSize(),getSize());
        g.setColor(c);
    }

    @Override
    public void move() {

    }
}
