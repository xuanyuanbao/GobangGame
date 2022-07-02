package player;

import memento.CareTaker;
import gameFrame.GameFrame;
import regretNum.PlayerAgree;
import regretNum.SystemAgree;

import javax.swing.*;
import java.awt.*;

/**
 * @author 徐望成
 * 玩家类
 * name
 */
public class Player {
    protected String name;
    protected String grade;
    protected ImageIcon imageIcon;
    protected CareTaker careTaker;
    protected boolean canPlay = false;
    protected boolean isDrop = true;
    protected PlayerAgree playerAgree = new PlayerAgree();
    protected SystemAgree systemAgree = new SystemAgree();
    protected ImageIcon headPortrait;
    private GameFrame gameFrame;

    public void draw(Graphics g){
        g.setFont(new Font(Font.MONOSPACED,Font.ITALIC,20));
        g.drawImage(headPortrait.getImage(),50,150,null);
        g.drawString("昵称:" + getName(),50,300);
        g.drawString("等级:" + getGrade() ,50,350);
    }

    public void setGameFrame(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public boolean isCanPlay() {
        return canPlay;
    }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public Player(String name, String grade, ImageIcon imageIcon) {
        this.name = name;
        this.grade = grade;
        this.imageIcon = imageIcon;
        careTaker = new CareTaker();
        this.headPortrait = new ImageIcon("images/headPortrait.jpg");
        headPortrait.setImage(headPortrait.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public ImageIcon getImageIcon() {
        return imageIcon;
    }

    public void setImageIcon(ImageIcon imageIcon) {
        this.imageIcon = imageIcon;
    }

    public CareTaker getCareTaker() {
        return careTaker;
    }
    public boolean isDrop() {
        return isDrop;
    }

    public void setDrop(boolean drop) {
        isDrop = drop;
    }

    public PlayerAgree getPlayerAgree() {
        return playerAgree;
    }

    public void setPlayerAgree(PlayerAgree playerAgree) {
        this.playerAgree = playerAgree;
    }

    public SystemAgree getSystemAgree() {
        return systemAgree;
    }

    public void setSystemAgree(SystemAgree systemAgree) {
        this.systemAgree = systemAgree;
    }


}
