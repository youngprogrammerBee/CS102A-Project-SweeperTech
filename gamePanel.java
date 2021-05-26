package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.applet.AudioClip;
import java.io.*;
import java.applet.Applet;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;

public class gamePanel extends JFrame implements MouseListener {
    ImageIcon background = new ImageIcon("Project/StartBackground.png");
    ImageIcon backgif = new ImageIcon("Project/sakura.gif");
    JPanel myPanel;

    JLabel label1 = new JLabel(background);        //把背景图片添加到标签里
    JLabel label2 = new JLabel(backgif);
    JButton button1 = new JButton("开始游戏");   //创建一个按钮
    JButton button2 = new JButton("读取存档");
    JButton button3 = new JButton("联系我们");
    JButton button4 = new JButton("关于");

    JButton button5 = new JButton("Easy");
    JButton button6 = new JButton("Hard");
    JButton button7 = new JButton("Lunatic");
    JButton button8 = new JButton("自定义");
    JButton musicbutton=new JButton(" ");

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


    JButton[] buttons = {button1, button2, button3, button4, button5, button6, button7, button8};

    String player1;//玩家1
    String player2;//玩家2
    int p1;//回合内步数
    int ROW;//棋盘行
    int COL;//棋盘列
    int MineCnt;//雷数

     int sff=0;
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        new gamePanel();

    }

    gamePanel() {


        label1.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());    //把标签设置为和图片等高等宽
        label2.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());
        myPanel = (JPanel) this.getContentPane();        //把我的面板设置为内容面板
        myPanel.setOpaque(false);                    //把我的面板设置为不可视
        myPanel.setLayout(null);        //把我的面板设置为绝对布局
        this.getLayeredPane().setLayout(null);        //把分层面板的布局置空
        AddLabel();//添加俩背景
        setFFont();                                 //设置字体以及按钮
        AddButton();                        //把按钮添加到我的面板里
        this.setTitle("CS102a-Project-MineSweeper");
        this.setBounds(100, 100, background.getIconWidth(), background.getIconHeight());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        bgm bgm1= new bgm();
        bgm1.play();

        this.setVisible(true);
    }

    void AddLabel() {
        this.getLayeredPane().add(label1, new Integer(Integer.MIN_VALUE));        //把标签添加到分层面板的最底层
        this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE + 1));//添加gif
    }

    void AddButton() {
        for (int i = 0; i < 4; i++) {
            myPanel.add(buttons[i]);
            buttons[i].addMouseListener(this);
        }
        myPanel.add(musicbutton);
        musicbutton.addMouseListener(this);
       /* myPanel.add(button1);
        myPanel.add(button2);
        myPanel.add(button3);
        myPanel.add(button4);
        button1.addMouseListener(this);
        button2.addMouseListener(this);
        button3.addMouseListener(this);
        button4.addMouseListener(this);*/

    }

    void AddButton2() {
        for (int i = 4; i < 8; i++) {
            myPanel.add(buttons[i]);
            buttons[i].addMouseListener(this);
        }
    }

    void RemoveButton1() {
        for (int i = 0; i < 4; i++) {
            myPanel.remove(buttons[i]);
        }
    }
void RemoveButton2(){
    for (int i = 4; i < 8; i++) {
        myPanel.remove(buttons[i]);
    }
}
    void setFFont() {
        for (int i = 0; i < 8; i++) {
            buttons[i].setFont(new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 30));
            buttons[i].setForeground(Color.red);
            if (i < 4) buttons[i].setBounds(100, 325 + 100 * i, 200, 40);
            if (4 <= i && i < 8) buttons[i].setBounds(100, 325 + 100* (i - 4), 200, 40);
            buttons[i].setContentAreaFilled(false);//设置按钮透明
            buttons[i].setBorder(null);//取消边框
        }
        musicbutton.setContentAreaFilled(false);
        musicbutton.setBorder(null);
        musicbutton.setBounds(100,725,1000,40);


     /*  button2.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
       button2.setBounds(100,80,110,40);
       button2.setContentAreaFilled(false);//设置按钮透明
       button2.setBorder(null);//取消边框

       button3.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
       button3.setBounds(100,110,110,40);
       button3.setContentAreaFilled(false);//设置按钮透明
       button3.setBorder(null);//取消边框

       button4.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
       button4.setBounds(100,140,110,40);
       button4.setContentAreaFilled(false);//设置按钮透明
       button4.setBorder(null);//取消边框

       button5.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
       button5.setBounds(100,140,110,40);
       button5.setContentAreaFilled(false);//设置按钮透明
       button5.setBorder(null);//取消边框

       button6.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
       button6.setBounds(100,140,110,40);
       button6.setContentAreaFilled(false);//设置按钮透明
       button6.setBorder(null);//取消边框

       button7.setFont(new Font("黑体",Font.LAYOUT_LEFT_TO_RIGHT,18));
       button7.setBounds(100,140,110,40);
       button7.setContentAreaFilled(false);//设置按钮透明
       button7.setBorder(null);//取消边框*/

    }

    void Startfirst() {
        //number

        String str1 = JOptionPane.showInputDialog("玩家一姓名");
        String str2 = JOptionPane.showInputDialog("玩家二姓名");
        p1 = Integer.parseInt(JOptionPane.showInputDialog("请设置每人回合行动步数"));
        player1 = str1;
        player2 = str2;
    }

    void setDifficulty() {
        RemoveButton1();
        AddButton2();                        //把按钮添加到我的面板里

    }

    void ChooseHero() {

        JButton[] button = {hero1, hero2, hero3};
        HeroFrame.setSize(1050, 1020);
        HeroFrame.setResizable(false);//是否可改变size
        HeroFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭后是否关闭进程
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
        if(click<=1){
        HeroFrame.setVisible(true);}
        else{HeroFrame.dispose();
            HeroFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);}


    }


    void StartGame(){
        ChooseHero();
    }


    void Start1(JButton button) throws URISyntaxException, IOException {//点击按钮之后

        if (button.equals(button1)) {
            Startfirst();
            setDifficulty();

        }
        if (button.equals(button2)) {
            char c=JOptionPane.showInputDialog("请输入你想读取的存档").charAt(0);
            new sweeperMine("save"+c+".txt");
        }
        if (button.equals(button3)) {
         java.net.URI uri = new java.net.URI("http://www.baidu.com");
            java.awt.Desktop.getDesktop().browse(uri);
        }
        if (button.equals(button4)) {
            JOptionPane.showMessageDialog(null,  "作者：毕明阳 夏星晨\nSID：12010709 12010721\n南方科技大学\n2021.5.20","关于",
                    JOptionPane.PLAIN_MESSAGE);
        }
    }

    void Start2(JButton button) {//根据难度设置雷区
        int t=4;
        for (; t <7 ; t++) {
            if(button.equals(buttons[t])) break;
        }
        switch (t){
            case 4:
                COL=9;
                ROW=9;
                MineCnt=10;
                StartGame();
                break;
            case 5:
                COL=16;
                ROW=16;
                MineCnt=40;
                StartGame();
                break;
            case 6:
                COL=30;
                ROW=16;
                MineCnt=99;
                StartGame();
                break;
        }


    }

    void Start22() {//自定义雷区，有24*30 half mine 的限制
        int row = Integer.parseInt(JOptionPane.showInputDialog("输入行数"));
        while (row > 24 || row <= 0) { row = Integer.parseInt(JOptionPane.showInputDialog("输入行数")); }
        ROW = row;

        int col = Integer.parseInt(JOptionPane.showInputDialog("输入列数"));
        while (col > 30 || col <= 0) { col = Integer.parseInt(JOptionPane.showInputDialog("输入列数")); }
        COL = col;

        int minecnt = Integer.parseInt(JOptionPane.showInputDialog("输入雷数"));
        while (minecnt > ROW * COL / 2 || minecnt <= 0) { col = Integer.parseInt(JOptionPane.showInputDialog("输入列数")); }
        MineCnt=minecnt;
        StartGame();

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        for (int i = 0; i < buttons.length; i++) {
            if (i < 4) {
                if (btn.equals(buttons[i])) {
                    try {
                        Start1(buttons[i]);//主界面点完后进入的界面
                    } catch (URISyntaxException uriSyntaxException) {
                        uriSyntaxException.printStackTrace();
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }

                }
            }
            if (i >= 4 && i < 7) {//选择难度之后
                if (btn.equals(buttons[i])) {
                    Start2(buttons[i]);//设置棋盘属性
                }
            }
            if (i == 7) {//自定义难度
                if (btn.equals(buttons[i])) {
                    Start22();//设置棋盘属性
                }
            }
            }

            for (int i = 0; i < HeroButton.length; i++) {
                if (btn.equals(HeroButton[i])&&click<2) {

                    if(click==1){
                        Ca2=i+1; click++;
                        HeroFrame.dispose();


                        new sweeperMine(ROW,COL,MineCnt,p1,Ca1,Ca2);
                    }  if (click == 0) {
                        Ca1 = i + 1;click++;

                    }
                }

            }


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

        for (int i = 0; i < buttons.length; i++) {
            if (btn.equals(buttons[i])) {
                buttons[i].setFont(new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 35));
                buttons[i].setForeground(Color.blue);
            }
        }
        for (int i = 0; i < HeroButton.length; i++) {
            if (btn.equals(HeroButton[i])) {
                HeroButton[i].setContentAreaFilled(true);
            }
        }
        if(btn.equals(musicbutton)){
            if(sff==0){
            sff++;
            Music(true,"Project/bgm1.wav");}
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        for (int i = 0; i < buttons.length; i++) {
            if (btn.equals(buttons[i])) {
                buttons[i].setFont(new Font("黑体", Font.LAYOUT_LEFT_TO_RIGHT, 30));
                buttons[i].setForeground(Color.red);
            }

        }
        for (int i = 0; i < HeroButton.length; i++) {
            if (btn.equals(HeroButton[i])) {
                HeroButton[i].setContentAreaFilled(false);
            }
        }
    }
    //
    public static void Music(boolean st,String fi){   //注意，java只能播放无损音质，如.wav这种格式
        File f;
        URI uri;
        URL url;
        try {
            f = new File(fi); //绝对路径
            uri = f.toURI();
            url = uri.toURL(); //解析路径
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.loop();  //单曲循环
            if(!st) aau.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
