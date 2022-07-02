package Theme;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * @author 徐望成
 * 按钮工厂，所有的的按钮都在此产生
 * 目前共有6个按钮：
 * 开始游戏按钮、重新开始游戏按钮、悔棋按钮、前进按钮、保存游戏按钮、选择模式按钮
 * buttonWidth为按钮的宽、buttonHeight为按钮的高
 * firstButtonX为第一个按钮的x坐标，firstButtonY为第一个按钮的y坐标
 * buttonSpace为按钮之间的距离
 */
public class ButtonFactory {
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private ArrayList<ImageIcon> imageIcons = new ArrayList<ImageIcon>();
    private JButton beginButton;
    private JButton restartButton;
    private JButton regretButton;
    private JButton advanceButton;
    private JButton selectPattern;
    private JButton saveGameButton;
    private JButton readGameButton;
    private JButton giveUPButton;
    private JButton selectThemeButton;
    private int buttonWidth = 120;
    private int buttonHeight = 40;
    private int firstButtonX = 3;
    private int firstButtonY = 10;
    private int buttonSpace = buttonWidth + 4;

    /**
     * @author 徐望成
     * 简化主题，比较的符合老中少三代人的主题
     */
    public void initSimpleTheme() {
        //初始化图片
        imageIcons.clear();
        selectThemeButton.setText("个性主题");
        ImageIcon beginImage = new ImageIcon("images/beginGame.png");
        ImageIcon restartImage = new ImageIcon("images/restartGame.png");
        ImageIcon regretImage = new ImageIcon("images/regret.png");
        ImageIcon forwardImage = new ImageIcon("images/forward.png");
        ImageIcon patternImage = new ImageIcon("images/person.png");
        ImageIcon saveImage = new ImageIcon("images/saveGame.png");
        ImageIcon readImage = new ImageIcon("");
        ImageIcon giveUpImage = new ImageIcon("");
        ImageIcon selectThemeImage = new ImageIcon("");

        imageIcons.add(beginImage);
        imageIcons.add(restartImage);
        imageIcons.add(regretImage);
        imageIcons.add(forwardImage);
        imageIcons.add(patternImage);
        imageIcons.add(saveImage);
        imageIcons.add(readImage);
        imageIcons.add(giveUpImage);
        imageIcons.add(selectThemeImage);

        for (int i = 0; i < imageIcons.size(); i++) {
            imageIcons.get(i).setImage(imageIcons.get(i).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            buttons.get(i).setIcon(imageIcons.get(i));
            buttons.get(i).setBackground(new Color(224, 255, 255));
        }
    }

    /**
     * @author 徐望成
     * 个性主题，符合当代年轻人个性的主题
     * 目前还没有做初始化，后续需要做处理
     */
    public void initPersonalityTheme() {
        imageIcons.clear();
        selectThemeButton.setText("简化主题");
        ImageIcon beginImage = new ImageIcon("images/beginGame1.png");
        ImageIcon restartImage = new ImageIcon("images/restartGame1.png");
        ImageIcon regretImage = new ImageIcon("images/regret1.png");
        ImageIcon forwardImage = new ImageIcon("images/forward1.png");
        ImageIcon patternImage = new ImageIcon("images/person1.png");
        ImageIcon saveImage = new ImageIcon("images/saveGame1.png");
        ImageIcon readImage = new ImageIcon("1");
        ImageIcon giveUpImage = new ImageIcon("1");
        ImageIcon selectThemeImage = new ImageIcon("1");

        imageIcons.add(beginImage);
        imageIcons.add(restartImage);
        imageIcons.add(regretImage);
        imageIcons.add(forwardImage);
        imageIcons.add(patternImage);
        imageIcons.add(saveImage);
        imageIcons.add(readImage);
        imageIcons.add(giveUpImage);
        imageIcons.add(selectThemeImage);
        for (int i = 0; i < imageIcons.size(); i++) {
            imageIcons.get(i).setImage(imageIcons.get(i).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
            buttons.get(i).setIcon(imageIcons.get(i));
            buttons.get(i).setBackground(new Color(1, 255, 255));
        }

    }

    //初始化所有按钮
    public ButtonFactory() {
        this.beginButton = new JButton("开始游戏");
        this.restartButton = new JButton("重新开始");
        this.regretButton = new JButton("悔棋");
        this.advanceButton = new JButton("前进");
        this.selectPattern = new JButton("选择模式");
        this.saveGameButton = new JButton("保存");
        this.readGameButton = new JButton("读取");
        this.giveUPButton = new JButton("认输");
        this.selectThemeButton = new JButton("选择主题");

        buttons.add(0, beginButton);
        buttons.add(1, restartButton);
        buttons.add(2, regretButton);
        buttons.add(3, advanceButton);
        buttons.add(4, selectPattern);
        buttons.add(5, saveGameButton);
        buttons.add(6, readGameButton);
        buttons.add(7, giveUPButton);
        int i = 0;
        for (JButton button : buttons) {
            button.setBounds(firstButtonX + i * buttonSpace, firstButtonY, buttonWidth, buttonHeight);
            i++;
        }
        selectThemeButton.setBounds(firstButtonX,firstButtonY + 60,buttonWidth,buttonHeight);
        buttons.add(8,selectThemeButton);
        this.initSimpleTheme();
    }

    /**
     * @author 徐望成
     * 初始化人机对战模式
     */
    public void initAIPattern() {
        this.getSelectPattern().setText("人机对战");
        ImageIcon aiImage = new ImageIcon("images/ai.png");
        aiImage.setImage(aiImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        this.getSelectPattern().setIcon(aiImage);
    }

    /**
     * @author 徐望成
     * 初始化人人对战模式
     */
    public void initPersonPattern() {
        this.getSelectPattern().setText("人人对战");
        ImageIcon aiImage = new ImageIcon("images/person.png");
        aiImage.setImage(aiImage.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
        this.getSelectPattern().setIcon(aiImage);
    }

    public ArrayList<JButton> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<JButton> buttons) {
        this.buttons = buttons;
    }

    public JButton getBeginButton() {
        return beginButton;
    }

    public void setBeginButton(JButton beginButton) {
        this.beginButton = beginButton;
    }

    public JButton getRestartButton() {
        return restartButton;
    }

    public void setRestartButton(JButton restartButton) {
        this.restartButton = restartButton;
    }

    public JButton getRegretButton() {
        return regretButton;
    }

    public void setRegretButton(JButton regretButton) {
        this.regretButton = regretButton;
    }

    public JButton getSaveGameButton() {
        return saveGameButton;
    }

    public void setSaveGameButton(JButton saveGameButton) {
        this.saveGameButton = saveGameButton;
    }

    public JButton getAdvanceButton() {
        return advanceButton;
    }

    public void setAdvanceButton(JButton advanceButton) {
        this.advanceButton = advanceButton;
    }

    public JButton getSelectPattern() {
        return selectPattern;
    }

    public void setSelectPattern(JButton selectPattern) {
        this.selectPattern = selectPattern;
    }

    public JButton getReadGameButton() {
        return readGameButton;
    }

    public void setReadGameButton(JButton readGameButton) {
        this.readGameButton = readGameButton;
    }

    public JButton getGiveUPButton() {
        return giveUPButton;
    }

    public void setGiveUPButton(JButton giveUPButton) {
        this.giveUPButton = giveUPButton;
    }

    public JButton getSelectThemeButton() {
        return selectThemeButton;
    }

    public void setSelectThemeButton(JButton selectThemeButton) {
        this.selectThemeButton = selectThemeButton;
    }
}
