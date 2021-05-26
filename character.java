package Project;

import javax.swing.*;
import java.awt.*;

public class character{
    public int id;
    public String name="";
    public ImageIcon [] lihui=new ImageIcon[233];//0为选人界面立绘，1为常态立绘，2为标记成功立绘，3为被炸立绘，4为胜利立绘，5为失败立绘,6为标记失败 350*800
    public character(int i){
        id=i;
        String t="";
        if(id==1) {
            name="乐园的巫女————博丽 灵梦";
            for(int j=0;j<=6;j++) {
                t="Project/lm"+(char)(j+'0')+".png";
                lihui[j]=change(new ImageIcon(t),350,750);
            }
        }
        if(id==2) {
            name="普通的魔法使————雾雨 魔理沙";
            for(int j=0;j<=6;j++) {
                t="Project/mls"+(char)(j+'0')+".png";
                lihui[j]=change(new ImageIcon(t),350,750);
            }
        }
        if(id==3) {
            name="七色的人偶使————爱丽丝 玛格特罗伊德";
            for(int j=0;j<=6;j++) {
                t="Project/alc"+(char)(j+'0')+".png";
                lihui[j]=change(new ImageIcon(t),350,750);
            }
        }
    }
    public ImageIcon change(ImageIcon image, int Iwidth, int Iheight) {

        Image img = image.getImage().getScaledInstance(Iwidth, Iheight, Image.SCALE_DEFAULT);//第三个值可以去查api是图片转化的方式
        ImageIcon image2 = new ImageIcon(img);

        return image2;
    }
}