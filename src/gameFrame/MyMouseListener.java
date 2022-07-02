package gameFrame;

import chess.ChessBoard;
import memento.MementoChess;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    private ChessBoard chessBoard;
    private GameFrame gameFrame;
    private MementoChess mementoChess;
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        boolean isManPattern = this.gameFrame.getChessBoard().isManPattern();
        if (this.gameFrame.getPlayer().isCanPlay()){
            int x = e.getX();
            int y = e.getY();
            if (x > chessBoard.getSpaceWight() && x <= chessBoard.getSpaceWight() + chessBoard.getBoardNum() * chessBoard.getBoardRadius() && y > chessBoard.getSpaceHeight() && y <= chessBoard.getSpaceHeight() + chessBoard.getBoardNum() * chessBoard.getBoardRadius()) {
                int tempJ = (x - 200) / chessBoard.getBoardRadius();
                int tempI = (y - 100) / chessBoard.getBoardRadius();
                int[][] chessMap = chessBoard.getChessMap();
                if (chessMap[tempI][tempJ] == 0) {
                    //白方下棋
                    if (this.gameFrame.getPlayer().isDrop() == true) {
                        chessMap[tempI][tempJ] = 1;
                        this.gameFrame.getPlayer().setDrop(false);
                        //机器下棋（黑方）
                        if (!isManPattern){
                            mementoChess = new MementoChess(tempI,tempJ);
                            this.gameFrame.getPlayer().getCareTaker().save(mementoChess);
                            if (chessBoard.isWin(tempI, tempJ)) {
                                JOptionPane.showMessageDialog(this.gameFrame,( this.gameFrame.getPlayer().isDrop() == false ? "白棋" : "黑棋") + "胜利！");
                                this.gameFrame.getPlayer().setCanPlay(false);
                            }else {
                                Point point = this.gameFrame.getAiPlayer().dropChess(chessMap);
                                tempI = point.x;
                                tempJ = point.y;
                                chessMap[tempI][tempJ] = 2;
                                this.gameFrame.repaint();
                                this.gameFrame.getPlayer().setDrop(true);
                            }
                        }
                    } else {
                        //黑方下棋
                        chessMap[tempI][tempJ] = 2;
                        this.gameFrame.getPlayer().setDrop(true);
                    }
                    mementoChess = new MementoChess(tempI,tempJ);
                    this.gameFrame.getPlayer().getCareTaker().save(mementoChess);
                    if (chessBoard.isWin(tempI, tempJ)) {
                        JOptionPane.showMessageDialog(this.gameFrame,( this.gameFrame.getPlayer().isDrop() == false ? "白棋" : "黑棋") + "胜利！");
                        this.gameFrame.getPlayer().setCanPlay(false);
                    }
                } else {
                    JOptionPane.showMessageDialog(this.gameFrame, "此处已有棋子，请重新选择！");
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public MyMouseListener(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void setChessBoard(ChessBoard chessBoard) {
        this.chessBoard = chessBoard;
    }

}
