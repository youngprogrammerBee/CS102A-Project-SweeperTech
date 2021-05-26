package Project;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.*;
import java.net.URI;
import java.net.URL;

public class gamestate {
    public int row, col, steps, minecnt, stepcnt; //n,m为棋盘大小,k为每人每回合走的步数,l为剩余雷数
    public int[][] board = new int[100][100];
    public int[][] boardstate = new int[100][100]; //0=未被翻开，1=被翻开,2被标记
    public int[] score=new int [3];
    public int[] miss=new int[3];
    public character[] cha=new character[3];
    boolean first=true;
    public gamestate(int a,int b,int c,int d,int cha1,int cha2){//new game
        cha[1]=new character(cha1);
        cha[2]=new character(cha2);
        for(int i=1;i<=2;i++) score[i]=miss[i]=0;
        stepcnt=0;
        row =a;
        col =b;
        steps =c;
        minecnt =d;
        for(int i = 1; i<= row; i++){
            for(int j = 1; j<= col; j++){
                boardstate[i][j]=0;
            }
        }
        do{
            for(int N=1;N<=minecnt;N++){
                int i = (int) (Math.random() * (row  - 1)+1);
                int j = (int) (Math.random() * (col  - 1)+1);
                if(board[i][j]==9){
                    N--;
                    continue;
                }
                board[i][j]=9;
            }
        }while(!check());
        renew();
    }
    public void renew(){
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                if(board[i][j]==0) {
                    for (int p = i-1; p <= i + 1; p++) {
                        for (int q = j-1; q <= j + 1; q++) {
                            if (board[p][q] == 9) board[i][j] ++;
                        }
                    }
                }
            }
        }
    }
    public boolean check(){
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                boolean t=false;
                for (int a = i; a <= i+3; a++) {
                    for (int b = j; b <= j+3; b++) {
                        if(board[a][b]==0) t=true;
                    }
                }
                if(!t) {
                    board=new int[100][100];
                    return false;
                }
            }
        }
        return true;
    }
    public gamestate(String f) {//load
        first=false;
        try {
            int i=1,j=1,k=0;
//            ch = reader.read();
            FileReader reader = new FileReader(f);
            int ch=0,in=0;
            while((ch = reader.read()) != -1) {
                if(ch=='\r'|| ch=='\n') continue;
                if(ch == ' ') {
                    k++;
                    if(k==1) this.row =in;
                    if(k==2) this.col =in;
                    if(k==3) this.steps =in;
                    if(k==4) this.minecnt =in;
                    if(k>4&&k<=4+ row * col){
                        if(j== col +1) {
                            i++;
                            j=1;
                        }
                        board[i][j]=in;
                        j++;
                    }
                    if(k==4+ row * col){ i=1;j=1; }
                    if(k>4+ row * col &&k<=4+2* row * col){
                        if(j== col +1) {
                            i++;
                            j=1;
                        }
                        boardstate[i][j]=in;
                        j++;
                    }
                    if(k>4+2* row * col &&k<=6+2* row * col){
                        score[k-(4+2* row * col)]=in;
                    }
                    if(k>6+2* row * col &&k<=8+2* row * col){
                        miss[k-(6+2* row * col)]=in;
                    }
                    if(k>8+2* row * col &&k<=10+2* row * col){
                        cha[k-(8+2* row * col)]=new character(in);
                    }
                    if(k==11+2*row*col) stepcnt=in;
                    in=0;
                }
                else in=in*10+ch-'0';

            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save(String Filename){
        File f = new File(Filename);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(f));
            bw.write(row +" "+ col +" "+ steps +" "+ minecnt+" " );
            bw.newLine();
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    bw.write(board[i][j]+" ");
                }
                bw.newLine();
            }
            for (int i = 1; i <= row; i++) {
                for (int j = 1; j <= col; j++) {
                    bw.write(boardstate[i][j]+" ");
                }
                bw.newLine();
            }
            bw.write(score[1]+" "+score[2]+" ");
            bw.newLine();
            bw.write(miss[1]+" "+miss[2]+" ");
            bw.newLine();
            bw.write(cha[1].id+" "+cha[2].id+" ");
            bw.newLine();
            bw.write(stepcnt+" ");
            bw.newLine();
            bw.flush();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void reset(int a,int b){
        board=new int[100][100];
        do{
            for(int N=1;N<=minecnt;N++){
                int i = (int) (Math.random() * (row  - 1)+1);
                int j = (int) (Math.random() * (col  - 1)+1);
//                System.out.println(i+" "+j);
                if(board[i][j]==9){
                    N--;
                    continue;
                }
                if(i==a&&b==j){
                    N--;
                    continue;
                }
                board[i][j]=9;
            }
        }while(!check());
        renew();
    }
    public boolean leftclick(int i,int j,int player){
        if(first){
            if(board[i][j]==9){
                reset(i,j);
            }
            first=false;
        }
        if(i<1||j<1||i> row ||j> col) return true;
        if(boardstate[i][j]==1||boardstate[i][j]==2) return true;
        boardstate[i][j]=1;
        if(board[i][j]==0) {
            leftclick(i + 1, j, player);
            leftclick(i - 1, j, player);
            leftclick(i, j + 1, player);
            leftclick(i, j - 1, player);
            leftclick(i + 1, j+1, player);
            leftclick(i - 1, j-1, player);
            leftclick(i+1, j - 1, player);
            leftclick(i-1, j + 1, player);
        }
        if(board[i][j]==9) {
            biu();
            minecnt--;
            score[player]--;
            return false;
        }
        return true;
    }
    public boolean rightclick(int i,int j,int player){
        boardstate[i][j]=2;
        if(board[i][j]==9) {
            minecnt--;
            score[player]++;
            return true;
        }
        else{
            miss[player]++;
            return false;
        }
    }
    public boolean checkturn(){
        return minecnt <= 0 || Math.abs(score[1] - score[2]) > minecnt;
    }
    public int p1win(){
        if(score[1]>score[2]) return 1;
        if(score[1]==score[2]&&miss[1]<miss[2]) return 1;
        if(score[1]==score[2]&&miss[1]==miss[2]) return 0;
        return -1;
    }
    public static void biu(){   //注意，java只能播放无损音质，如.wav这种格式
        File f;
        URI uri;
        URL url;
        try {
            f = new File("Project/biu.wav"); //绝对路径
            uri = f.toURI();
            url = uri.toURL(); //解析路径
            AudioClip aau;
            aau = Applet.newAudioClip(url);
            aau.play();  //单曲循环
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
//gamestate a=new gamestate();

//int b=0;
//读取玩家1点的位置i,j,左键
//while(!leftclick(i,j,1)) a= new gamestate();
//while(a.checkturn()){
//          if(b==0){ a.steps--;}
//        for(int k=1;k<=a.steps;k++){
//        //读取玩家1点的位置i,j,左键还是右键
//            if(leftclick(i,j,1)){
//                //立绘改变，用a.cha[1].lihui[1~5]获得立绘
//            }
//            if(rightclick(i,j,1)){
//                //立绘改变，用a.cha[1].lihui[1~5]获得立绘
//            }
//            //查询board和boardstate的状态并反映在棋盘上
//        }
//          if(b==0){ a.steps++;b=1;}
//        for(int k=1;k<=a.steps;k++){
//        //读取玩家2点的位置i,j,左键还是右键
//            if(leftclick(i,j,2)){
//                //立绘改变，用a.cha[2].lihui[1~5]获得立绘
//            }
//            if(rightclick(i,j,2)){
//                //立绘改变，用a.cha[2].lihui[1~5]获得立绘
//            }
//            //查询board和boardstate的状态并反映在棋盘上
//        }
//}
//if(a.p1win()==1){
//      返回p1胜利的立绘并显示p1win；
//}
//if(a.p1win()==0){
//平局
//}
//if(a.p1win()==-1){
//    返回p2胜利的立绘并显示p2win；
//}
