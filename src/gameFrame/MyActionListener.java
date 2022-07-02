package gameFrame;

import memento.MementoChess;
import regretNum.AbstractRegret;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyActionListener implements ActionListener {
    private GameFrame gameFrame;
    private int regretNum = 0;

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("开始游戏")) {
            this.gameFrame.getPlayer().setCanPlay(true);
        } else if (e.getActionCommand().equals("重新开始")) {
            int option = JOptionPane.showConfirmDialog(this.gameFrame, "是否重新开始？");
            if (option == 0) {
                this.gameFrame.getPlayer().setCanPlay(true);
                this.regretNum = 0;
                this.gameFrame.getChessBoard().initChessBoard();
                this.gameFrame.getPlayer().getCareTaker().initMemento();
                this.gameFrame.getPlayer().setDrop(true);

                //清空保存记录

                this.gameFrame.repaint();
            }
        } else if (e.getActionCommand().equals("悔棋")) {
            //悔棋
            AbstractRegret systemAgree = this.gameFrame.getPlayer().getSystemAgree();
            systemAgree.setAbstractRegret(this.gameFrame.getPlayer().getPlayerAgree());

            int[][] chessMap = this.gameFrame.getChessBoard().getChessMap();
            if (systemAgree.isAgree(regretNum) && this.gameFrame.getPlayer().isCanPlay()) {
                regretNum++;
                MementoChess mementoChess = this.gameFrame.getPlayer().getCareTaker().regret();
                if (mementoChess != null) {
                    if (this.gameFrame.getPlayer().isDrop() == true) {
                        this.gameFrame.getPlayer().setDrop(false);
                    } else {
                        this.gameFrame.getPlayer().setDrop(true);
                    }
                    chessMap[mementoChess.getX()][mementoChess.getY()] = 0;
                    this.gameFrame.repaint();
                }
            } else if (systemAgree.isAgree(regretNum - 1)) {
                JOptionPane.showMessageDialog(this.gameFrame, "你这棋悔的忒离谱了！");
            } else {
                JOptionPane.showMessageDialog(this.gameFrame, "不好意思，你已悔到尽头！");
            }


        } else if (e.getActionCommand().equals("前进") && this.gameFrame.getPlayer().isCanPlay()) {
            //前进，放弃悔棋，还是觉得上一步棋下的好
            MementoChess mementoChess = this.gameFrame.getPlayer().getCareTaker().forward();
            int[][] chessMap = this.gameFrame.getChessBoard().getChessMap();
            if (mementoChess != null) {
                if (this.gameFrame.getPlayer().isDrop() == true) {
                    this.gameFrame.getPlayer().setDrop(false);
                } else {
                    this.gameFrame.getPlayer().setDrop(true);
                }
                chessMap[mementoChess.getX()][mementoChess.getY()] = !this.gameFrame.getPlayer().isDrop() ? 1 : 2;
            }

        } else if (e.getActionCommand().equals("选择模式") || e.getActionCommand().equals("人人对战")) {
//            this.gameFrame.getButtonFactory().initAIPattern();

            this.gameFrame.getChessBoard().setManPattern(false);
        } else if (e.getActionCommand().equals("人机对战")) {
//            this.gameFrame.getButtonFactory().initPersonPattern();
            this.gameFrame.getChessBoard().setManPattern(true);
        } else if (e.getActionCommand().equals("保存")) {
            //将数组保存到文本文件中，点击保存游戏，可以下次再玩
            int[][] chessMap = this.gameFrame.getChessBoard().getChessMap();
            this.gameFrame.getChessBoard().getSaveGame().save(chessMap);
        } else if (e.getActionCommand().equals("读取")) {
            int[][] chessMap = this.gameFrame.getChessBoard().getChessMap();
            this.gameFrame.getChessBoard().getSaveGame().readGame(chessMap);
            this.gameFrame.repaint();
        } else if (e.getActionCommand().equals("认输")) {
            JOptionPane.showMessageDialog(this.gameFrame, (!this.gameFrame.getPlayer().isDrop() ? "白方" : "黑方") + "胜利！");
            this.gameFrame.getPlayer().setCanPlay(false);
        }else if (e.getActionCommand().equals("选择主题")||e.getActionCommand().equals("个性主题")){
            this.gameFrame.getButtonFactory().initPersonalityTheme();
            this.gameFrame.getButtonJPanel().setBackground(new Color(1,255,255));
        }else if (e.getActionCommand().equals("简化主题")){
            this.gameFrame.getButtonFactory().initSimpleTheme();
            this.gameFrame.getButtonJPanel().setBackground(new Color(224,255,255));
        }
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}
