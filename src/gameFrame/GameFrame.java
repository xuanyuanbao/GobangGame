package gameFrame;

import Theme.ButtonFactory;
import chess.ChessBoard;
import chess.ChessFactory;
import player.ComputerPlayer;
import player.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 徐望成
 * WIDTH、HEIGHT　代表窗口的宽和高
 * locationX、locationY　代表窗口的左上角坐标
 */
public class GameFrame extends JFrame {
    public final int WIDTH = 1000;
    public final int HEIGHT = 800;
    private final int locationX = 600;
    private final int locationY = 100;

    private JPanel buttonJPanel;

    private Image iBuffer;
    private Graphics gBuffer;

    private Player player = new Player("不败顽童","中级",null);

    private ComputerPlayer aiPlayer = new ComputerPlayer("小智","初级",null);

    private ButtonFactory buttonFactory = new ButtonFactory();
    private ChessFactory chessFactory = new ChessFactory();

    private ChessBoard chessBoard = new ChessBoard();


    public GameFrame(){
        this.setSize(WIDTH, HEIGHT);
        this.setTitle("五子棋");
        this.setLocation(locationX,locationY);
        this.setVisible(true);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        chessBoard.setGameFrame(this);
        //鼠标监听器
        MyMouseListener mouseListener = new MyMouseListener(this);
        chessBoard.setFactory(chessFactory);
        mouseListener.setChessBoard(chessBoard);
        this.addMouseListener(mouseListener);

        //按钮监听器
        MyActionListener actionListener = new MyActionListener();
        actionListener.setGameFrame(this);
        buttonJPanel = new JPanel();

        buttonJPanel.setLayout(null);
        for (JButton button : buttonFactory.getButtons()) {
            button.addActionListener(actionListener);
            buttonJPanel.add(button);
        }
        buttonJPanel.setBackground(new Color(224,255,255));
        this.add(buttonJPanel);

        /**
         * 开启一个线程，让repaint方法50ms重画一次
         */
        new Thread(){
            @Override
            public void run() {
                super.run();
                while (true){
                    try{
                        Thread.sleep(50);
                        repaint();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        }.start();
    }

    /**
     * 绘画的方法均集成于此
     * @param g
     */
    @Override
    public void paint(Graphics g){
        super.paint(g);
        chessBoard.drawBoard(g);
        chessBoard.drawAllChess(g);
        chessBoard.drawInformation(g);
        player.draw(g);
        aiPlayer.draw(g);
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Player getPlayer() {
        return player;
    }
    public ComputerPlayer getAiPlayer() {
        return aiPlayer;
    }

    public void setAiPlayer(ComputerPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public ButtonFactory getButtonFactory() {
        return buttonFactory;
    }

    public JPanel getButtonJPanel() {
        return buttonJPanel;
    }
}
