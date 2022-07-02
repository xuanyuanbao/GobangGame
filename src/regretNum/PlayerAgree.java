package regretNum;

import javax.swing.*;

/**
 * @author 徐望成
 * 玩家同意，如果某一方玩家悔棋，需要通过对手的同意方可悔棋，如果不同意悔棋，则悔棋失败
 */
public class PlayerAgree extends AbstractRegret{
    private boolean approve = false;

    public boolean isApprove() {
        return approve;
    }

    public void setApprove(boolean approve) {
        this.approve = approve;
    }

    @Override
    public boolean isAgree(int regretNum) {
        int option = JOptionPane.showConfirmDialog(null,"是否同意悔棋？","悔棋",1);
        if (option == 0){
            this.setApprove(true);
        }else {
            this.setApprove(false);
        }
        return this.isApprove();
    }
}
