package saveGame;


import javax.swing.*;
import java.io.*;

public class SaveGame {
    private File goBangGame = new File("files/goBangGame.txt");

    public void save(int[][] chessMap) {
        int option = JOptionPane.showConfirmDialog(null,"是否保存游戏？","保存游戏",0);
        if (option == 0){
            try {
                OutputStream outputStream = new FileOutputStream(goBangGame);
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
                for (int i = 0; i < chessMap.length; i++) {
                    for (int j = 0; j < chessMap[i].length; j++) {
                        writer.write(chessMap[i][j] + ",");
                    }
                    writer.write("\n");
                }
                writer.close();
                outputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"保存成功！");
        }else {
            JOptionPane.showMessageDialog(null,"保存失败");
        }

    }

    public void readGame(int[][] chessMap) {
        int option = JOptionPane.showConfirmDialog(null,"是否读取游戏数据？","读取游戏数据",0);
        if (option == 0){
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(goBangGame));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = null;
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    if (line != null) {
                        String[] strings = line.split(",");
                        for (int j = 0; j < strings.length; j++) {
                            chessMap[i][j] = Integer.valueOf(strings[j]);
                        }
                        i++;
                    }
                }
                bufferedReader.close();
                inputStreamReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            JOptionPane.showMessageDialog(null,"读取成功！");
        }else {
            JOptionPane.showMessageDialog(null,"读取失败");
        }

    }
}
