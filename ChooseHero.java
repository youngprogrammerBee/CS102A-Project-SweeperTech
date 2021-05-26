package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ChooseHero implements MouseListener {
    JFrame HeroFrame = new JFrame("choose hero");
    JButton hero1 = new JButton();
    JButton hero2 = new JButton();
    JButton hero3 = new JButton();
    JButton[] HeroButton = {hero1, hero2, hero3};
    JLabel Herolabel1 = new JLabel();
    JLabel Herolabel2 = new JLabel();
    JLabel Herolabel3 = new JLabel();
    JLabel[] HeroLabel = {Herolabel1, Herolabel2, Herolabel3};

    int Ca1 = 0;
    int Ca2 = 0;
    int click = 0;
    void ChooseHero() {

        JButton[] button = {hero1, hero2, hero3};
        HeroFrame.setSize(1050, 1020);
        HeroFrame.setResizable(false);//是否可改变size
        HeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭后是否关闭进程
        HeroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        HeroFrame.setLayout(null);


        character character1 = new character(1);
        character character2 = new character(2);
        character character3 = new character(3);

        Herolabel1.setText(character1.name);
        Herolabel2.setText(character2.name);
        Herolabel3.setText(character3.name);

        for (int i = 0; i < HeroLabel.length; i++) {
            HeroLabel[i].setFont(new Font("黑体", Font.BOLD, 15));
            HeroLabel[i].setBounds(50 + 350 * i, 910, 350, 30);
            HeroLabel[i].setVisible(true);
            HeroFrame.add(HeroLabel[i]);
        }

        hero1.setIcon(character1.lihui[0]);
        hero1.setBounds(0, 0, 350, 900);
        hero2.setIcon(character2.lihui[0]);
        hero2.setBounds(350, 0, 350, 900);
        hero3.setIcon(character3.lihui[0]);
        hero3.setBounds(700, 0, 350, 900);

        for (int i = 0; i < button.length; i++) {
            button[i].setContentAreaFilled(false);
            button[i].setBorder(null);
            button[i].setBorderPainted(false);
            button[i].setVisible(true);
            button[i].addMouseListener(this);
            HeroFrame.add(button[i]);
        }

        HeroFrame.add(hero1);
        HeroFrame.add(hero2);
        HeroFrame.add(hero3);
        HeroFrame.setVisible(true);


    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if (click < 2) {
            for (int i = 0; i < HeroButton.length; i++) {
                if (btn.equals(HeroButton[i])) {
                    if (click == 0) {
                        Ca1 = i + 1;

                    }if(click==1){
                        Ca2=i+1;
                    }
                }

            }
        }
        click++;
        System.out.println(click+  " " +"Ca1: "+Ca1+"Ca2: "+Ca2);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        for (int i = 0; i < HeroButton.length; i++) {
            if (btn.equals(HeroButton[i])) {
                HeroButton[i].setContentAreaFilled(true);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        for (int i = 0; i < HeroButton.length; i++) {
            if (btn.equals(HeroButton[i])) {
                HeroButton[i].setContentAreaFilled(false);
            }
        }
    }
}
