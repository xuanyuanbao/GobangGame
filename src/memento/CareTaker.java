package memento;

import java.util.ArrayList;

public class CareTaker {
    private ArrayList<MementoChess> chessArrayList = new ArrayList<MementoChess>();
    private int counter = 0;

    /**
     * 悔棋
     * @return
     */
    public MementoChess regret() {
        if (counter >= 0) {
            if (chessArrayList.size() == 0) {
                return null;
            }
            return chessArrayList.get(counter--);
        }
        return null;
    }

    /**
     * 每次开始落下一个棋子的时候，将数组chessArrayList大于counter的棋子元素清楚掉
     * 落子之后还能前进，这不符合常理
     * 传入当前棋子的坐标，保存到chessArrayList中
     * @param mementoChess
     */
    public void save(MementoChess mementoChess) {
        removeChess();
        chessArrayList.add(mementoChess);
        counter = chessArrayList.size() - 1;
    }

    /**
     * 擦除大于counter的棋子，以防落子之后还能够前进
     */
    private void removeChess() {
        int size = chessArrayList.size();
        for (int i = size - 1; i > counter; i--) {
            chessArrayList.remove(i);
        }
    }

    public MementoChess forward() {
        if (counter <= chessArrayList.size() - 1) {
            if (chessArrayList.size() - 1 == counter) {
                return null;
            }
            return chessArrayList.get(++counter);
        }
        return null;
    }

    /**
     * @author 徐望成
     * 点击重新开始游戏之后，清空备忘录中的所有信息，一切重新开始
     */
    public void initMemento() {
        this.chessArrayList.clear();
    }

}
