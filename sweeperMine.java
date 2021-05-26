package Project;

import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class sweeperMine implements ActionListener, MouseListener {
    /*总体使用绝对布局 w10*35+30*35+10*35=1750   height 24*35+2*60=960
    左右双方可对称布置，同样进行绝对布局 w10*35  h 960
    中间使用border layout，NORTH放图片相关1050*60，CENTER放雷区，其中雷区使用gridlayout 1050*840，SOUTH使用GridBag layout 1050*60
     */


    ImageIcon guessIcon = new ImageIcon("Project/guess.png");
    ImageIcon background = new ImageIcon("Project/banner.png");
    ImageIcon flagIcon = new ImageIcon("Project/flagIcon.png");

    ImageIcon gif1 = new ImageIcon("Project/alc.gif");
    ImageIcon gif2 = new ImageIcon("Project/lm.gif");
    ImageIcon gif3 = new ImageIcon("Project/mls.gif");

    ImageIcon FontIcon=new ImageIcon("Project/Font.png");

ImageIcon test1Icon=new ImageIcon("Project/GameBackground.png");
ImageIcon Bomb=new ImageIcon("Project/Bomb.png");
JLabel Iconlabel1;
    public gamestate game1;
    JFrame frame = new JFrame("MineSweeper");
    JButton cheatButton=new JButton("Cheating");
    JButton saveButton=new JButton("Save");

    JButton labelGif1=new JButton(gif1);
    JLabel labelGif2=new JLabel(gif2);
    JLabel labelGif3=new JLabel(gif3);

 int stepcnt=0;
    //中间的
    int oppened=0;
    int unoppened=0;
int flagCnt=0;
    int seconds=0;//时钟
    Timer timer = new Timer(1000, this);	//1000ms触发一次，并找this.perforem
    JLabel northLabel;//顶部
    JButton[][] btns;//按钮
    int[][] buttons = new int[100][100];//board state;
    int[][] btnn = new int[100][100];//board
    int[] score = new int[3];
    int[] miss = new int[3];
int []STEPS=new int[3];
    int ROW;
    int COL;
    int MineCnt;
 int k=-1;
    //左右侧玩家
    int C1;
    int C2;
    int sff=0;
    int steps;
    JLabel Turn = new JLabel("Your Turn!");
    JLabel stepLEFTtext = new JLabel("剩余步数：");
    JLabel stepsleft = new JLabel(steps + "步");


    JLabel P1Score = new JLabel("分    数：");
    JLabel P2Score = new JLabel("分    数：");
    JLabel P1missed = new JLabel("失误次数：");
    JLabel P2missed = new JLabel("失误次数：");

    JLabel p1score ;
    JLabel p2score ;
    JLabel p1miss ;
    JLabel p2miss ;
    JLabel labelP1;//人物图像
    JLabel labelP2;
    JLabel labelP3 = new JLabel();
    JLabel labelP4 = new JLabel();



    JLabel labelP12 = new JLabel();
    //
    JLabel label1 = new JLabel("待开："+unoppened);
    JLabel label2 = new JLabel("已开："+oppened);
    JLabel label3 = new JLabel("标雷："+flagCnt);
    JLabel label4 = new JLabel("用时："+seconds+"s");

    JLabel label5 = new JLabel("行");
    JLabel label6 = new JLabel("列");
    JLabel label7 = new JLabel("雷");

    JLabel text1 = new JLabel(ROW+"");
    JLabel text2 = new JLabel(COL+"");
    JLabel text3 = new JLabel(MineCnt+"");


    sweeperMine(String f) {
        game1 = new gamestate(f);
        C1=game1.cha[1].id;
        C2=game1.cha[2].id;
        p1score = new JLabel(game1.score[1] + "分");
        p2score = new JLabel(game1.score[2] + "分");
        p1miss = new JLabel(game1.miss[1] + "次");
        p2miss = new JLabel(game1.miss[2] + "次");

        ROW = game1.row;
        COL = game1.col;
        unoppened=game1.row* game1.col;
        oppened=game1.row* game1.col-unoppened;
        STEPS[1]= game1.steps;
        STEPS[2]= game1.steps;
        this.MineCnt = game1.minecnt;
        this.steps = game1.steps;

        frame.setSize(1750, 1020);
        frame.setResizable(false);//是否可改变size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭后是否关闭进程
        frame.setLayout(null);
        ImageIcon ccc1=change(test1Icon,1750,1020);
        Iconlabel1=new JLabel(ccc1);
        Iconlabel1.setBounds(0,0,1750,1020);
        frame.getLayeredPane().add(Iconlabel1,new Integer(Integer.MIN_VALUE));

        JPanel jp=(JPanel)frame.getContentPane();
        jp.setOpaque(false);
        addPlayer1(game1.cha[1].id);//增加玩家1，在左侧

        addPlayer2(game1.cha[2].id);//增加玩家2，在右侧

        k=game1.stepcnt;

        displayturn(k);


        addChessBoard(game1.row, game1.col);

        Music(true,"Project/bgm2.wav");
        timer.start();
        frame.setVisible(true);
        update();
        displayNumbers();
        displayFlag();
    }


    sweeperMine(int row, int col, int MineCnt, int steps, int c1, int c2) {
        game1 = new gamestate(row, col, steps, MineCnt, c1, c2);
        C1=c1;
        C2=c2;
        p1score = new JLabel(game1.score[1] + "分");
        p2score = new JLabel(game1.score[2] + "分");
        p1miss = new JLabel(game1.miss[1] + "次");
        p2miss = new JLabel(game1.miss[2] + "次");

        ROW = game1.row;
        COL = game1.col;
        unoppened=game1.row* game1.col;
        oppened=game1.row* game1.col-unoppened;
        STEPS[1]= game1.steps;
        STEPS[2]= game1.steps;stepcnt=k;
        this.MineCnt = game1.minecnt;
        this.steps = game1.steps;
        frame.setSize(1750, 1020);
        frame.setResizable(false);//是否可改变size
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭后是否关闭进程
        frame.setLayout(null);
        ImageIcon ccc1=change(test1Icon,1750,1020);
        Iconlabel1=new JLabel(ccc1);
       Iconlabel1.setBounds(0,0,1750,1020);
        frame.getLayeredPane().add(Iconlabel1,new Integer(Integer.MIN_VALUE));
        JPanel jp=(JPanel)frame.getContentPane();
        jp.setOpaque(false);
        addPlayer1(c1);//增加玩家1，在左侧

        addPlayer2(c2);//增加玩家2，在右侧
        displayturn(k);
        addChessBoard(row, col);

        Music(true,"Project/bgm2.wav");
        timer.start();
        frame.setVisible(true);

    }

    void displayFlag(){
        int Width=0;
        int Height=0;
        int flagCNT=0;
        for (int i = 1; i <= game1.row; i++) {
            for (int j = 1; j <= game1.col; j++) {
                if(game1.boardstate[i][j]==2){

                    Width=btns[i][j].getWidth();
                    Height=btns[i][j].getHeight();
                    ImageIcon newFlag=change(flagIcon,Width,Height);
                    btns[i][j].setIcon(newFlag);
                    btns[i][j].setDisabledIcon(newFlag);
                    btns[i][j].setEnabled(false);
flagCNT++;
                }
            }}
    }


    void updateIcon(){
        update();
        int Width=0;
        int Height=0;
        for (int i = 1; i <= game1.row; i++) {
            for (int j = 1; j <= game1.col; j++) {
                if (game1.boardstate[i][j] == 1 && game1.board[i][j] <= 8) {
                    btns[i][j].setEnabled(false);
                    btns[i][j].setIcon(null);
                    btns[i][j].setBackground(Color.lightGray);
                    if (game1.board[i][j] != 0) {
                        Width=btns[i][j].getWidth();

                        btns[i][j].setText(game1.board[i][j] + "");
                        btns[i][j].setFont(new Font("黑体", Font.BOLD,Width ) );
                       // btns[i][j].setMargin(new Insets(0,0,0,0));
                    }

                }
                if (game1.boardstate[i][j] == 1 && game1.board[i][j] == 9) {
                    btns[i][j].setEnabled(false);
                    Width=btns[i][j].getWidth();
                    Height=btns[i][j].getHeight();
                    ImageIcon wwbomb=change(Bomb,Width,Height);
                    btns[i][j].setIcon(wwbomb);
                    btns[i][j].setDisabledIcon(wwbomb);
                    btns[i][j].setBackground(Color.red);
                }

            }
        }


    }

    void addChessBoard(int row, int col) {
        addNorth();
        addCenterBoard(row, col);
        addSouth();


    }

    public ImageIcon change(ImageIcon image, int Iwidth, int Iheight) {

        Image img = image.getImage().getScaledInstance(Iwidth, Iheight, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2 = new ImageIcon(img);

        return image2;
    }


    void addNorth() {
        ImageIcon icon11=change(FontIcon,500,60);
        labelGif1=new JButton(icon11);
        labelGif1.setOpaque(false);
        labelGif1.setEnabled(false);
        labelGif1.setLayout(null);
        labelGif1.setBounds(680,0,500,60);
        labelGif1.setVisible(true);
        frame.add(labelGif1);

    }

//override
    void addCenterBoard(int row, int col,int p) {
        Container container = new Container();
        container.setBounds(350, 60, 1050, 840);
        btns = new JButton[row + 1][col + 1];
        int Width=0;
        container.setLayout(new GridLayout(row, col));//设置雷区布局
        update();
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if(game1.boardstate[i][j]==0) {
                    JButton button = new JButton(guessIcon);
                    btns[i][j] = button;//为了用到
                    btns[i][j].setBackground(Color.DARK_GRAY);

                    }
                 if(game1.boardstate[i][j]==2)
                {
                    JButton button=new JButton(flagIcon);
                    btns[i][j] = button;//为了用到
                    btns[i][j].setEnabled(false);
                    btns[i][j].setBackground(Color.DARK_GRAY);

                }
                if (game1.boardstate[i][j]==1) {
                    JButton button=new JButton(game1.board[i][j]+"");
                    Width=btns[i][j].getWidth();
                    btns[i][j].setBackground(Color.lightGray);
                    btns[i][j].setEnabled(false);
                    btns[i][j].setFont(new Font("黑体", Font.BOLD,Width ) );
                    btns[i][j].setMargin(new Insets(0,0,0,0));
                        btns[i][j] = button;//为了用到

                }

                btns[i][j].setOpaque(true);

                btns[i][j].addMouseListener(this);
                container.add( btns[i][j]);//为了显示雷

            }
        }

        frame.add(container);
    }
    void addCenterBoard(int row, int col) {
        Container container = new Container();
        container.setBounds(350, 60, 1050, 840);
        btns = new JButton[row + 1][col + 1];

        container.setLayout(new GridLayout(row, col));//设置雷区布局
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                JButton button = new JButton(guessIcon);
                button.setOpaque(true);
                button.setBackground(Color.DARK_GRAY);
                button.addMouseListener(this);
                container.add(button);//为了显示雷
                btns[i][j] = button;//为了用到
            }
        }

        frame.add(container);
    }

    public static void main(String[] args) {
        new sweeperMine(10,10,9,5,1,1);
    }
    void addSouth() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBounds(350, 900, 1050, 60);
        text1.setText(game1.row + "");
        text2.setText(game1.col + "");
        text3.setText(game1.minecnt + "");


        label1.setOpaque(true);
        label1.setBackground(Color.white);
        label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label2.setOpaque(true);
        label2.setBackground(Color.white);
        label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label3.setOpaque(true);
        label3.setBackground(Color.white);
        label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label4.setOpaque(true);
        label4.setBackground(Color.white);
        label4.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints c2 = new GridBagConstraints(0, 0, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label1, c2);
        GridBagConstraints c3 = new GridBagConstraints(2, 0, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label2, c3);
        GridBagConstraints c4 = new GridBagConstraints(4, 0, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label3, c4);
        GridBagConstraints c5 = new GridBagConstraints(6, 0, 2, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label4, c5);

        label5.setOpaque(true);
        label5.setBackground(Color.white);
        label5.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label6.setOpaque(true);
        label6.setBackground(Color.white);
        label6.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label7.setOpaque(true);
        label7.setBackground(Color.white);
        label7.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        text1.setOpaque(true);
        text1.setBackground(Color.white);
        text1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        text2.setOpaque(true);
        text2.setBackground(Color.white);
        text2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        text3.setOpaque(true);
        text3.setBackground(Color.white);
        text3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        GridBagConstraints c9 = new GridBagConstraints(0, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label5, c9);
        GridBagConstraints c6 = new GridBagConstraints(1, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(text1, c6);
        GridBagConstraints c10 = new GridBagConstraints(2, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label6, c10);
        GridBagConstraints c7 = new GridBagConstraints(3, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(text2, c7);
        GridBagConstraints c11 = new GridBagConstraints(4, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(label7, c11);
        GridBagConstraints c8 = new GridBagConstraints(5, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(text3, c8);

        GridBagConstraints c1 = new GridBagConstraints(6, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(cheatButton,c1);
        cheatButton.addMouseListener(this);

        GridBagConstraints c12 = new GridBagConstraints(7, 1, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0);
        panel.add(saveButton,c12);
        saveButton.addMouseListener(this);

        frame.add(panel);
    }

    void addPlayer1(int c) {
        character qwq1=new character(c);
        Turn.setBounds(50, 800, 140, 40);
        Turn.setFont(new Font("黑体", Font.BOLD, 22));
       // stepLEFTtext.setBounds(50, 850, 70, 40);
        labelP1 = new JLabel(qwq1.lihui[1]);
        stepLEFTtext.setBounds(50, 850, 70, 40);
frame.add(stepLEFTtext);

        labelP1.setBounds(0, 0, 350, 800);
        labelP1.setVisible(true);
        P1Score.setBounds(50, 900, 70, 30);
        p1score.setBounds(150, 900, 50, 30);
        P1missed.setBounds(50, 940, 70, 30);
        p1miss.setBounds(150, 940, 50, 30);
        frame.add(P1Score);
        frame.add(p1score);
        frame.add(P1missed);
        frame.add(p1miss);
        frame.add(labelP1);
        frame.add(Turn);


    }

    void addPlayer2(int c) {
        character qwq2=new character(c);
        labelP2 = new JLabel(qwq2.lihui[1]);
        labelP2.setBounds(1400, 0, 350, 800);
        labelP2.setVisible(true);
        P2Score.setBounds(1500, 900, 70, 30);
        p2score.setBounds(1600, 900, 50, 30);
        P2missed.setBounds(1500, 940, 70, 30);
        p2miss.setBounds(1600, 940, 50, 30);
        frame.add(P2Score);
        frame.add(p2score);
        frame.add(P2missed);
        frame.add(p2miss);
        frame.add(labelP2);
    }

    void setFlag(int i, int j) {
        JButton btn = btns[i][j];
        int Width=btns[i][j].getWidth();
        int Height=btns[i][j].getHeight();
        ImageIcon newFlag=change(flagIcon,Width,Height);

        if (btn.isEnabled()) {
            if(game1.board[i][j]==9){
                btn.setIcon(newFlag);
                btn.setDisabledIcon(newFlag);
                flagCnt++;
            btn.setEnabled(false);
        }
         if(game1.board[i][j]<=8){
             btns[i][j].setIcon(null);
             btns[i][j].setEnabled(false);
             btns[i][j].setBackground(Color.lightGray);
             btns[i][j].setText(game1.board[i][j]+"");
             btns[i][j].setFont(new Font("黑体", Font.BOLD,Width ));
             btns[i][j].setMargin(new Insets(0,0,0,0));
         }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof Timer) {
            seconds++;
            label4.setText("用时："+seconds+"s");
            return;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int  pl = 0;


        JButton btn = (JButton) e.getSource();
        if(btn.equals(saveButton)){
            startSave();
        }
        for (int i = 1; i <= game1.row; i++) {
            for (int j = 1; j <= game1.col; j++) {
                //获取按键坐标
                update();
                if (btn.equals(btns[i][j])) {
                    if(btns[i][j].isEnabled()){
                    if (e.getButton() == MouseEvent.BUTTON3) {
                        k++;
                        game1.stepcnt=k;
                        displayturn(k);
                        setFlag(i, j);
                        if ((k / steps) % 2 != 1) {

                            pl = 1;
                        } else {
                            pl = 2;
                        }
                        if (game1.rightclick(i, j, pl)) {
                            //改成立绘4
                            update();
                         if(pl==1)   labelP1.setIcon(game1.cha[pl].lihui[2]);
                         if(pl==2) labelP2.setIcon(game1.cha[pl].lihui[2]);
                        } else {
                            update();
                          if(pl==1)  labelP1.setIcon(game1.cha[pl].lihui[6]);
                            if(pl==2) labelP2.setIcon(game1.cha[pl].lihui[6]);
                        }

                        if (game1.checkturn()) {
                            timer.stop();

                            if (game1.p1win() == 1) {
                                labelP1.setIcon(game1.cha[1].lihui[4]);
                                labelP2.setIcon(game1.cha[2].lihui[5]);
                                //  返回p1胜利的立绘并显示p1win；
                                JOptionPane.showMessageDialog(frame, "恭喜玩家1"+game1.cha[1].name+"获胜","Win！",JOptionPane.PLAIN_MESSAGE);
                            }
                            if (game1.p1win() == 0) {
                                labelP1.setIcon(game1.cha[1].lihui[5]);
                                labelP2.setIcon(game1.cha[2].lihui[5]);
                                // 平局
                                JOptionPane.showMessageDialog(frame, "双方平局！","平局",JOptionPane.PLAIN_MESSAGE);
                            }
                            if (game1.p1win() == -1) {
                                labelP1.setIcon(game1.cha[2].lihui[4]);
                                labelP2.setIcon(game1.cha[1].lihui[5]);
                                JOptionPane.showMessageDialog(frame, "恭喜玩家1"+game1.cha[2].name+"获胜","Win！",JOptionPane.PLAIN_MESSAGE);
                                // 返回p2胜利的立绘并显示p2win；
                            }

                            frame.dispose();
                        }
                    }
                    if (e.getButton() == MouseEvent.BUTTON1 && btn.isEnabled()&&!btns[i][j].getIcon().equals(flagIcon)) {
                        k++;
                        game1.stepcnt=k;
                        displayturn(k);
                        if ((k / steps) % 2 != 1) {

                            pl = 1;
                        } else {
                            pl = 2;
                        }
                        if (game1.leftclick(i, j, pl)) {
                            //改成立绘4
                            update();
                            displayNumbers();
                            if(pl==1) labelP1.setIcon(game1.cha[pl].lihui[1]);
                            if(pl==2)  labelP2.setIcon(game1.cha[pl].lihui[1]);
                            btns[i][j].setEnabled(false);
                        } else {
                            update();
                            displayNumbers();
                            if(pl==1) labelP1.setIcon(game1.cha[pl].lihui[3]);
                            if(pl==2)  labelP2.setIcon(game1.cha[pl].lihui[3]);
                            btns[i][j].setEnabled(false);
                        }
                        if (game1.checkturn()) {
                            timer.stop();
                            if (game1.p1win() == 1) {
                                labelP1.setIcon(game1.cha[1].lihui[4]);
                                labelP2.setIcon(game1.cha[2].lihui[5]);
                                //  返回p1胜利的立绘并显示p1win；

                                JOptionPane.showMessageDialog(frame, "恭喜玩家1"+game1.cha[1].name+"获胜","Win！",JOptionPane.PLAIN_MESSAGE);
                            }
                            if (game1.p1win() == 0) {
                                labelP1.setIcon(game1.cha[1].lihui[5]);
                                labelP2.setIcon(game1.cha[2].lihui[5]);
                                // 平局

                                JOptionPane.showMessageDialog(frame, "双方平局！","平局",JOptionPane.PLAIN_MESSAGE);

                            }
                            if (game1.p1win() == -1) {
                                labelP1.setIcon(game1.cha[2].lihui[4]);
                                labelP2.setIcon(game1.cha[1].lihui[5]);
                                // 返回p2胜利的立绘并显示p2win；

                                JOptionPane.showMessageDialog(frame, "恭喜玩家1"+game1.cha[2].name+"获胜","Win！",JOptionPane.PLAIN_MESSAGE);

                            }

                            frame.dispose();
                        }

                    }
                }
            }
            }
        }
        addScore();
        addBoard();

    }
    void addBoard(){
        label1.setText("待开："+unoppened);
        label2.setText("已开："+oppened);
        label3.setText("标雷："+flagCnt);
    }
    void addScore(){
        p1score.setText(game1.score[1]+"分");
        p2score.setText(game1.score[2]+"分");
        p1miss.setText(game1.miss[1]+"次");
        p2miss.setText(game1.miss[2]+"次");
        frame.add(p1score);
        frame.add(p2score);
        frame.add(p1miss);
        frame.add(p2miss);
    }



    void displayturn(int t) {
    t=k+1;
        if ((t / steps) % 2 != 1) {//1
            Turn.setBounds(50, 800, 140, 40);
            stepLEFTtext.setBounds(50, 850, 70, 40);
            stepsleft.setText(( game1.steps-t% game1.steps)+"步");
            stepsleft.setBounds(120,850,70,40);
        }
        else  if((t / steps) % 2 == 1){//2     5 6 7 8 9
            Turn.setBounds(1500, 800, 140, 40);
            stepLEFTtext.setBounds(1500, 850, 70, 40);
            stepsleft.setText((game1.steps-t% game1.steps)+"步");
            stepsleft.setBounds(1570,850,70,40);
        }
        Turn.setFont(new Font("黑体", Font.BOLD, 22));

        frame.add(Turn);
        frame.add(stepLEFTtext);
        frame.add(stepsleft);

    }

    void update() {
        text1.setText(game1.row + "");
        text2.setText(game1.col + "");
        text3.setText(game1.minecnt + "");
        for (int i = 1; i <= game1.row; i++) {
            for (int j = 1; j <= game1.col; j++) {
                btnn[i][j] = game1.board[i][j];
                buttons[i][j] = game1.boardstate[i][j];
            }
        }
        for (int i = 1; i < 3; i++) {

            score[i] = game1.score[i];
            miss[i] = game1.miss[i];

        }



    }

    void displayNumbers() {
        int sd=0;
        int Width=0;
      int Height=0;
        for (int i = 1; i <= game1.row; i++) {
            for (int j = 1; j <= game1.col; j++) {
                if (game1.boardstate[i][j] == 1 && game1.board[i][j] <= 8) {
                    btns[i][j].setEnabled(false);
                    btns[i][j].setIcon(null);
                    btns[i][j].setBackground(Color.lightGray);
                    if (game1.board[i][j] != 0) {
                        Width=btns[i][j].getWidth();

                        btns[i][j].setText(game1.board[i][j] + "");
                        btns[i][j].setFont(new Font("黑体", Font.BOLD,Width ) );
                        btns[i][j].setMargin(new Insets(0,0,0,0));
                    }
                }
                if (game1.boardstate[i][j] == 1 && game1.board[i][j] == 9) {
                    btns[i][j].setEnabled(false);
                    Width=btns[i][j].getWidth();
                    Height=btns[i][j].getHeight();
                    ImageIcon wwbomb=change(Bomb,Width,Height);
                    btns[i][j].setIcon(wwbomb);
                    btns[i][j].setDisabledIcon(wwbomb);
                    btns[i][j].setBackground(Color.red);
                }
                if(game1.boardstate[i][j] == 0){ sd++;}
            }
        } unoppened=sd;
        oppened=game1.col* game1.row-sd;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        int Width=0;
        int Height=0;
        if(btn==cheatButton){
            for (int i = 1; i <= game1.row; i++) {
                for (int j = 1; j <= game1.col; j++) {
                    if(game1.boardstate[i][j]==0)
                    {
                        if(game1.board[i][j]==9){
                            Width=btns[i][j].getWidth();
                            Height=btns[i][j].getHeight();
                            ImageIcon wwbomb=change(Bomb,Width,Height);
                            btns[i][j].setIcon(wwbomb);
                            btns[i][j].setBackground(Color.red);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        JButton btn = (JButton)e.getSource();
        if(btn==cheatButton){
            for (int i = 1; i <= game1.row; i++) {
                for (int j = 1; j <= game1.col; j++) {
                    if(game1.boardstate[i][j]==0)
                    {
                        if(game1.board[i][j]==9){
                            btns[i][j].setIcon(guessIcon);
                            btns[i][j].setBackground(Color.DARK_GRAY);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton btn = (JButton) e.getSource();
        if(btn.equals(labelGif1)){
            if(sff==0){
                sff++;
                Music(true,"Project/bgm1.wav");}
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    void startSave(){
        char c=JOptionPane.showInputDialog("请输入存档位置(1~9)").charAt(0);
        game1.save("save"+c+".txt");
    }

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
