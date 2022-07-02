package regretNum;

/**
 * @author 徐望成
 * 抽象悔棋类，下面有两个子类，一个是玩家悔棋类，一个是系统悔棋类
 */
public abstract class AbstractRegret {
    protected AbstractRegret abstractRegret;

    public AbstractRegret getAbstractRegret() {
        return abstractRegret;
    }

    public void setAbstractRegret(AbstractRegret abstractRegret) {
        this.abstractRegret = abstractRegret;
    }

    public abstract boolean isAgree(int regretNum);
}
