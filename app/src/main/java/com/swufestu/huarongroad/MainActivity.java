package com.swufestu.huarongroad;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.swufestu.huarongroad.R;

public class MainActivity extends AppCompatActivity {
    ImageButton ib00,ib01,ib02,ib03,ib10,ib11,ib12,ib13,ib20,ib21,ib22,ib23,ib30,ib31,ib32,ib33;
    Button restartBtn;
    TextView timeTv;

    //    每行的图片个数
    private int imageX = 4;
    private int imageY = 4;  //每列的图片的个数

    //    图片的总数目
    private int imgCount = imageX*imageY;
    //    空白区域的位置
    private int blankSwap = imgCount-1;
    //    初始化空白区域的按钮id
    private int blankImgid = R.id.pt_ib_03x03;

    //    定义计数时间的变量
    int time = 0;
    //    存放碎片的数组，便于进行统一的管理
    private int[]image = {R.mipmap.img_1,R.mipmap.img_2,R.mipmap.img_3,R.mipmap.img_4,
            R.mipmap.img_5,R.mipmap.img_6,R.mipmap.img_7,R.mipmap.img_8,R.mipmap.img_9,
            R.mipmap.img_10,R.mipmap.img_11,R.mipmap.img_12,R.mipmap.img_13,R.mipmap.img_14,R.mipmap.img_15,R.mipmap.img_16};
    //  声明一个图片数组的下标数组，随机排列这个数组
    private int[]imageIndex = new int[image.length];
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==1) {
                time++;
                timeTv.setText("时间 : "+time+" 秒");
                handler.sendEmptyMessageDelayed(1,1000);
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
//        打乱碎片的函数
        disruptRandom();
        handler.sendEmptyMessageDelayed(1,1000);
    }

    //  随机打乱数组当中元素，以不规则的形式进行图片显示
    private void disruptRandom() {
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
//        规定20次，随机选择两个角标对应的值进行交换
        int rand1,rand2;
        for (int j = 0; j < 20; j++) {
//            随机生成第一个角标   生成0-15之间的随机数
            rand1 = (int)(Math.random()*(imageIndex.length-1));
//            第二次随机生成的角标，不能和第一次随机生成的角标相同，如果相同，就不方便交换了
            do {
                rand2 = (int)(Math.random()*(imageIndex.length-1));
                if (rand1!=rand2) {
                    break;
                }
            }while (true);
//            交换两个角标上对应的值
            swap(rand1,rand2);
        }
//        随机排列到指定的控件上
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib03.setImageResource(image[imageIndex[3]]);
        ib10.setImageResource(image[imageIndex[4]]);
        ib11.setImageResource(image[imageIndex[5]]);
        ib12.setImageResource(image[imageIndex[6]]);
        ib13.setImageResource(image[imageIndex[7]]);
        ib20.setImageResource(image[imageIndex[8]]);
        ib21.setImageResource(image[imageIndex[9]]);
        ib22.setImageResource(image[imageIndex[10]]);
        ib23.setImageResource(image[imageIndex[11]]);
        ib30.setImageResource(image[imageIndex[12]]);
        ib31.setImageResource(image[imageIndex[13]]);
        ib32.setImageResource(image[imageIndex[14]]);
        ib33.setImageResource(image[imageIndex[15]]);
    }
    //  交换数组指定角标上的数据
    private void swap(int rand1, int rand2) {
        int temp = imageIndex[rand1];
        imageIndex[rand1] = imageIndex[rand2];
        imageIndex[rand2] = temp;
    }

    /* 初始化控件*/
    private void initView() {
        ib00 = findViewById(R.id.pt_ib_00x00);
        ib01 = findViewById(R.id.pt_ib_00x01);
        ib02 = findViewById(R.id.pt_ib_00x02);
        ib03 = findViewById(R.id.pt_ib_00x03);
        ib10 = findViewById(R.id.pt_ib_01x00);
        ib11 = findViewById(R.id.pt_ib_01x01);
        ib12 = findViewById(R.id.pt_ib_01x02);
        ib13 = findViewById(R.id.pt_ib_01x03);
        ib20 = findViewById(R.id.pt_ib_02x00);
        ib21 = findViewById(R.id.pt_ib_02x01);
        ib22 = findViewById(R.id.pt_ib_02x02);
        ib23 = findViewById(R.id.pt_ib_02x03);
        ib30 = findViewById(R.id.pt_ib_03x00);
        ib31 = findViewById(R.id.pt_ib_03x01);
        ib32 = findViewById(R.id.pt_ib_03x02);
        ib33 = findViewById(R.id.pt_ib_03x03);
        timeTv = findViewById(R.id.pt_tv_time);
        restartBtn = findViewById(R.id.pt_btn_restart);
    }

    public void onClick(View view) {
        int id = view.getId();
//        16个按钮执行的点击事件的逻辑应该是相同的，如果有空格在周围，可以改变图片显示的位置，否则点击事件不响应
        switch (id) {
            case R.id.pt_ib_00x00:
                move(R.id.pt_ib_00x00,0);
                break;
            case R.id.pt_ib_00x01:
                move(R.id.pt_ib_00x01,1);
                break;
            case R.id.pt_ib_00x02:
                move(R.id.pt_ib_00x02,2);
                break;
            case R.id.pt_ib_00x03:
                move(R.id.pt_ib_00x03,3);
                break;
            case R.id.pt_ib_01x00:
                move(R.id.pt_ib_01x00,4);
                break;
            case R.id.pt_ib_01x01:
                move(R.id.pt_ib_01x01,5);
                break;
            case R.id.pt_ib_01x02:
                move(R.id.pt_ib_01x02,6);
                break;
            case R.id.pt_ib_01x03:
                move(R.id.pt_ib_01x03,7);
                break;
            case R.id.pt_ib_02x00:
                move(R.id.pt_ib_02x00,8);
                break;
            case R.id.pt_ib_02x01:
                move(R.id.pt_ib_02x01,9);
                break;
            case R.id.pt_ib_02x02:
                move(R.id.pt_ib_02x02,10);
                break;
            case R.id.pt_ib_02x03:
                move(R.id.pt_ib_02x03,11);
                break;
            case R.id.pt_ib_03x00:
                move(R.id.pt_ib_03x00,12);
                break;
            case R.id.pt_ib_03x01:
                move(R.id.pt_ib_03x01,13);
                break;
            case R.id.pt_ib_03x02:
                move(R.id.pt_ib_03x02,14);
                break;
            case R.id.pt_ib_03x03:
                move(R.id.pt_ib_03x03,15);
                break;
        }
    }
    /*表示移动指定位置的按钮的函数，将图片和空白区域进行交换*/
    private void move(int imagebuttonId, int site) {
//            判断选中的图片在第几行
        int sitex = site / imageX;
        int sitey = site % imageY; //第几列
//        获取空白区域的坐标
        int blankx = blankSwap / imageX;
        int blanky = blankSwap % imageY;
//        可以移动的条件有两个
//        1.在同一行，列数相减，绝对值为1，可移动   2.在同一列，行数相减，绝对值为1，可以移动
        int x = Math.abs(sitex-blankx);
        int y = Math.abs(sitey-blanky);
        if ((x==0&&y==1)||(y==0&&x==1)){
//            通过id，查找到这个可以移动的按钮
            ImageButton clickButton = findViewById(imagebuttonId);
            clickButton.setVisibility(View.INVISIBLE);
//            查找到空白区域的按钮
            ImageButton blankButton = findViewById(blankImgid);
//            将空白区域的按钮设置图片
            blankButton.setImageResource(image[imageIndex[site]]);
//            移动之前是不可见的，移动之后，将控件设置为可见
            blankButton.setVisibility(View.VISIBLE);
//            将改变角标的过程记录到存储图片位置数组当中
            swap(site,blankSwap);
//            新的空白区域位置更新等于传入的点击按钮的位置
            blankSwap = site;
            blankImgid = imagebuttonId;
        }
//      判断本次移动完成后，是否完成了拼图游戏
        judgeGameOver();

    }

    /* 判断拼图是否成功*/
    private void judgeGameOver() {
        boolean loop = true;   //定义标志位
        for (int i = 0; i < imageIndex.length; i++) {
            if (imageIndex[i]!=i) {
                loop = false;
                break;
            }
        }
        if (loop) {
//            拼图成功了
//            停止计时
            handler.removeMessages(1);
//            拼图成功后，禁止玩家继续移动按钮
            ib00.setClickable(false);
            ib01.setClickable(false);
            ib02.setClickable(false);
            ib03.setClickable(false);
            ib10.setClickable(false);
            ib11.setClickable(false);
            ib12.setClickable(false);
            ib13.setClickable(false);
            ib20.setClickable(false);
            ib21.setClickable(false);
            ib22.setClickable(false);
            ib23.setClickable(false);
            ib30.setClickable(false);
            ib31.setClickable(false);
            ib32.setClickable(false);
            ib33.setImageResource(image[15]);
            ib33.setVisibility(View.VISIBLE);
//            弹出提示用户成功的对话框
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("恭喜，拼图成功！您用的时间为"+time+"秒")
                    .setPositiveButton("确认",null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    /* 重新开始按钮的点击事件*/
    public void restart(View view) {
//        将状态还原
        restore();
//       将拼图重新打乱
        disruptRandom();
        handler.removeMessages(1);
//        将时间重新归0，并且重新开始计时
        time = 0;
        timeTv.setText("时间 : "+time+" 秒");
        handler.sendEmptyMessageDelayed(1,1000);
    }

    private void restore() {
        //      拼图游戏重新开始，允许完成移动碎片按钮
        ib00.setClickable(true);
        ib01.setClickable(true);
        ib02.setClickable(true);
        ib10.setClickable(true);
        ib11.setClickable(true);
        ib12.setClickable(true);
        ib20.setClickable(true);
        ib21.setClickable(true);
        ib22.setClickable(true);
//        还原被点击的图片按钮变成初始化的模样
        ImageButton clickBtn = findViewById(blankImgid);
        clickBtn.setVisibility(View.VISIBLE);
//        默认隐藏第章图片
        ImageButton blankBtn = findViewById(R.id.pt_ib_03x03);
        blankBtn.setVisibility(View.INVISIBLE);
        blankImgid = R.id.pt_ib_03x03;   //初始化空白区域的按钮id
        blankSwap = imgCount - 1;
    }
}
