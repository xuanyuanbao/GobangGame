package player;

import gameFrame.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class ComputerPlayer extends Player{
    private static HashMap<String,Integer> map = new HashMap<String, Integer>();
    private int[][] weightArray = new int[15][15];
    private GameFrame gameFrame;

    public ComputerPlayer(String name, String grade, ImageIcon imageIcon) {
        super(name, grade, imageIcon);
        this.headPortrait = new ImageIcon("images/IronMan.jpg");
        headPortrait.setImage(headPortrait.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    }

    @Override
    public void draw(Graphics g){
        g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,20));
        g.drawImage(headPortrait.getImage(),820,150,null);
        g.drawString("昵称:" + getName(),820,300);
        g.drawString("等级:" + getGrade() ,820,350);
    }

    /**
     * 初始化WeightArray数组，全部置为0
     */
    public void initWeightArray(){
        for (int i = 0; i < weightArray.length; i++) {
            for (int j = 0; j < weightArray[i].length; j++) {
                weightArray[i][j] = 0;
            }
        }
    }

    /**
     * 此部分代码是借鉴学长的代码所写
     * 在棋盘上寻找一个最佳的落子位置
     * @param chessMap
     * @return 返回一个point坐标
     */
    public Point dropChess(int[][] chessMap){
        initWeightArray();
        for (int i = 0; i < chessMap.length; i++) {
            for (int j = 0; j < chessMap[i].length; j++) {
                if (chessMap[i][j] == 0) {
                    // 往左延伸
                    String ConnectType = "0";
                    int jmin = Math.max(0, j - 4);
                    for (int positionj = j - 1; positionj >= jmin; positionj--) {
                        // 依次加上前面的棋子
                        ConnectType = ConnectType + chessMap[i][positionj];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置中
                    Integer valueleft = map.get(ConnectType);
                    if (valueleft != null)
                        this.weightArray[i][j] += valueleft;

                    // 往右延伸
                    ConnectType = "0";
                    int jmax = Math.min(14, j + 4);
                    for (int positionj = j + 1; positionj <= jmax; positionj++) {
                        // 依次加上前面的棋子
                        ConnectType = ConnectType + chessMap[i][positionj];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置中
                    Integer valueright = this.map.get(ConnectType);
                    if (valueright != null)
                        this.weightArray[i][j] += valueright;

                    // 联合判断，判断行
                    this.weightArray[i][j] += unionWeight(valueleft, valueright);

                    // 往上延伸
                    ConnectType = "0";
                    int imin = Math.max(0, i - 4);
                    for (int positioni = i - 1; positioni >= imin; positioni--) {
                        // 依次加上前面的棋子
                        ConnectType = ConnectType + chessMap[positioni][j];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置中
                    Integer valueup = this.map.get(ConnectType);
                    if (valueup != null)
                       this.weightArray[i][j] += valueup;

                    // 往下延伸
                    ConnectType = "0";
                    int imax = Math.min(14, i + 4);
                    for (int positioni = i + 1; positioni <= imax; positioni++) {
                        // 依次加上前面的棋子
                        ConnectType = ConnectType + chessMap[positioni][j];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置中
                    Integer valuedown = this.map.get(ConnectType);
                    if (valuedown != null)
                        this.weightArray[i][j] += valuedown;

                    // 联合判断，判断列
                    this.weightArray[i][j] += unionWeight(valueup, valuedown);

                    // 往左上方延伸,i,j,都减去相同的数
                    ConnectType = "0";
                    for (int position = -1; position >= -4; position--) {
                        if ((i + position >= 0) && (i + position <= 14) && (j + position >= 0)
                                && (j + position <= 14))
                            ConnectType = ConnectType + chessMap[i + position][j + position];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置
                    Integer valueLeftUp = this.map.get(ConnectType);
                    if (valueLeftUp != null)
                        this.weightArray[i][j] += valueLeftUp;

                    // 往右下方延伸,i,j,都加上相同的数
                    ConnectType = "0";
                    for (int position = 1; position <= 4; position++) {
                        if ((i + position >= 0) && (i + position <= 14) && (j + position >= 0)
                                && (j + position <= 14))
                            ConnectType = ConnectType + chessMap[i + position][j + position];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置
                    Integer valueRightDown = this.map.get(ConnectType);
                    if (valueRightDown != null)
                        this.weightArray[i][j] += valueRightDown;

                    // 联合判断，判断行
                    this.weightArray[i][j] += unionWeight(valueLeftUp, valueRightDown);

                    // 往左下方延伸,i加,j减
                    ConnectType = "0";
                    for (int position = 1; position <= 4; position++) {
                        if ((i + position >= 0) && (i + position <= 14) && (j - position >= 0)
                                && (j - position <= 14))
                            ConnectType = ConnectType + chessMap[i + position][j - position];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置
                    Integer valueLeftDown = this.map.get(ConnectType);
                    if (valueLeftDown != null)
                        this.weightArray[i][j] += valueLeftDown;

                    // 往右上方延伸,i减,j加
                    ConnectType = "0";
                    for (int position = 1; position <= 4; position++) {
                        if ((i - position >= 0) && (i - position <= 14) && (j + position >= 0)
                                && (j + position <= 14))
                            ConnectType = ConnectType + chessMap[i - position][j + position];
                    }
                    // 从数组中取出相应的权值，加到权值数组的当前位置
                    Integer valueRightUp = this.map.get(ConnectType);
                    if (valueRightUp != null)
                        this.weightArray[i][j] += valueRightUp;

                    // 联合判断，判断行
                    this.weightArray[i][j] += unionWeight(valueLeftDown, valueRightUp);
                }
            }
        }
        // 取出最大的权值
        int AIi = 0, AIj = 0;
        int weightmax = 0;
        for (int tempI = 0; tempI < weightArray.length; tempI++) {
            for (int tempJ = 0; tempJ < weightArray[tempI].length; tempJ++) {
                if (weightmax < weightArray[tempI][tempJ]) {
                    weightmax = weightArray[tempI][tempJ];
                    AIi = tempI;
                    AIj = tempJ;
                }
            }
        }

        return new Point(AIi,AIj);
    }

    public Integer unionWeight(Integer a, Integer b) {
        // 必须要先判断a,b两个数值是不是null
        if ((a == null) || (b == null))
            return 0;
            // 一一:101/202
        else if ((a >= 22) && (a <= 25) && (b >= 22) && (b <= 25))
            return 60;
            // 一二、二一:1011/2022
        else if (((a >= 22) && (a <= 25) && (b >= 76) && (b <= 80))
                || ((a >= 76) && (a <= 80) && (b >= 22) && (b <= 25)))
            return 800;
            // 一三、三一、二二:10111/20222
        else if (((a >= 10) && (a <= 25) && (b >= 1050) && (b <= 1100))
                || ((a >= 1050) && (a <= 1100) && (b >= 10) && (b <= 25))
                || ((a >= 76) && (a <= 80) && (b >= 76) && (b <= 80)))
            return 3000;
            // 眠三连和眠一连。一三、三一
        else if (((a >= 22) && (a <= 25) && (b >= 140) && (b <= 150))
                || ((a >= 140) && (a <= 150) && (b >= 22) && (b <= 25)))
            return 3000;
            // 二三、三二:110111
        else if (((a >= 76) && (a <= 80) && (b >= 1050) && (b <= 1100))
                || ((a >= 1050) && (a <= 1100) && (b >= 76) && (b <= 80)))
            return 3000;
        else
            return 0;
    }

    /**
     * 使用static语句块，将map数组只初始化一次
     */
    static {

        // 被堵住
        map.put("01", 25);// 眠1连
        map.put("02", 22);// 眠1连
        map.put("001", 17);// 眠1连
        map.put("002", 12);// 眠1连
        map.put("0001", 17);// 眠1连
        map.put("0002", 12);// 眠1连

        map.put("0102", 25);// 眠1连，15
        map.put("0201", 22);// 眠1连，10
        map.put("0012", 15);// 眠1连，15
        map.put("0021", 10);// 眠1连，10
        map.put("01002", 25);// 眠1连，15
        map.put("02001", 22);// 眠1连，10
        map.put("00102", 17);// 眠1连，15
        map.put("00201", 12);// 眠1连，10
        map.put("00012", 15);// 眠1连，15
        map.put("00021", 10);// 眠1连，10

        map.put("01000", 25);// 活1连，15
        map.put("02000", 22);// 活1连，10
        map.put("00100", 19);// 活1连，15
        map.put("00200", 14);// 活1连，10
        map.put("00010", 17);// 活1连，15
        map.put("00020", 12);// 活1连，10
        map.put("00001", 15);// 活1连，15
        map.put("00002", 10);// 活1连，10

        // 被墙堵住
        map.put("0101", 65);// 眠2连，40
        map.put("0202", 60);// 眠2连，30
        map.put("0110", 80);// 眠2连，40
        map.put("0220", 76);// 眠2连，30
        map.put("011", 80);// 眠2连，40
        map.put("022", 76);// 眠2连，30
        map.put("0011", 65);// 眠2连，40
        map.put("0022", 60);// 眠2连，30

        map.put("01012", 65);// 眠2连，40
        map.put("02021", 60);// 眠2连，30
        map.put("01102", 80);// 眠2连，40
        map.put("02201", 76);// 眠2连，30
        map.put("01120", 80);// 眠2连，40
        map.put("02210", 76);// 眠2连，30
        map.put("00112", 65);// 眠2连，40
        map.put("00221", 60);// 眠2连，30

        map.put("01100", 80);// 活2连，40
        map.put("02200", 76);// 活2连，30
        map.put("01010", 75);// 活2连，40
        map.put("02020", 70);// 活2连，30
        map.put("00110", 75);// 活2连，40
        map.put("00220", 70);// 活2连，30
        map.put("00011", 75);// 活2连，40
        map.put("00022", 70);// 活2连，30

        // 被堵住
        map.put("0111", 150);// 眠3连，100
        map.put("0222", 140);// 眠3连，80

        map.put("01112", 150);// 眠3连，100
        map.put("02221", 140);// 眠3连，80

        map.put("01110", 1100);// 活3连
        map.put("02220", 1050);// 活3连
        map.put("01101", 1000);// 活3连，130
        map.put("02202", 800);// 活3连，110
        map.put("01011", 1000);// 活3连，130
        map.put("02022", 800);// 活3连，110

        map.put("01111", 3000);// 4连，300
        map.put("02222", 3500);// 4连，280
    }

    public static HashMap<String, Integer> getMap() {
        return map;
    }

    public static void setMap(HashMap<String, Integer> map) {
        ComputerPlayer.map = map;
    }

    public int[][] getWeightArray() {
        return weightArray;
    }

    public void setWeightArray(int[][] weightArray) {
        this.weightArray = weightArray;
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    @Override
    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }
}
