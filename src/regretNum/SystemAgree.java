package regretNum;

/**
 * @author 徐望成
 * 系统悔棋类，刚开始，系统只会提供三次悔棋机会，如果超过三次，则交付给玩家处理
 */
public class SystemAgree extends AbstractRegret{
    private int maxRegretNum = 3;

    public int getMaxRegretNum() {
        return maxRegretNum;
    }

    public void setMaxRegretNum(int maxRegretNum) {
        this.maxRegretNum = maxRegretNum;
    }

    @Override
    public boolean isAgree(int regretNum) {
        if(regretNum < maxRegretNum){
            return true;
        }else {
            return this.abstractRegret.isAgree(regretNum);
        }
    }
}
