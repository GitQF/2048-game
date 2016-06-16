package com.example.as.a2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    private gamebox gamebox= null;
    private int index;
    private boolean flag;
    private GestureDetector ges = new GestureDetector(this);
    private int[] aa = new int[4];
    private int[] a = new int[4];
    private int[] b = new int[4];
    private TextView[][] block = new TextView[4][4];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gamebox = (gamebox)findViewById(R.id.gamebox);
        block[0][0] = (TextView)findViewById(R.id.t00);
        block[0][1] = (TextView)findViewById(R.id.t01);
        block[0][2] = (TextView)findViewById(R.id.t02);
        block[0][3] = (TextView)findViewById(R.id.t03);
        block[1][0] = (TextView)findViewById(R.id.t10);
        block[1][1] = (TextView)findViewById(R.id.t11);
        block[1][2] = (TextView)findViewById(R.id.t12);
        block[1][3] = (TextView)findViewById(R.id.t13);
        block[2][0] = (TextView)findViewById(R.id.t20);
        block[2][1] = (TextView)findViewById(R.id.t21);
        block[2][2] = (TextView)findViewById(R.id.t22);
        block[2][3] = (TextView)findViewById(R.id.t23);
        block[3][0] = (TextView)findViewById(R.id.t30);
        block[3][1] = (TextView)findViewById(R.id.t31);
        block[3][2] = (TextView)findViewById(R.id.t32);
        block[3][3] = (TextView)findViewById(R.id.t33);
        gamebox.setBlock(block);
        gamebox.getrandomblock();
        gamebox.getrandomblock();
    }
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }
    @Override
    public void onLongPress(MotionEvent motionEvent) {
    }
    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float v1, float v2) {
        return false;
    }
    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }
    @Override
    public void onShowPress(MotionEvent motionEvent) {
    }
    @Override public boolean onFling(MotionEvent e1, MotionEvent e2, float v1, float v2) {
        if (e1.getX() - e2.getX() > 100 && Math.abs(v1) > 20) {
            slid(3);
        } else if (e2.getX() - e1.getX() > 100 && Math.abs(v1) > 20) {
            slid(1);
        }else if(e1.getY() - e2.getY() >100 && Math.abs(v2) > 20){
            slid(4);
        }else if(e2.getY() - e1.getY() >100 && Math.abs(v2) > 20){
            slid(2);
        }
        return false;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return ges.onTouchEvent(event);
    }
    public void setmap(int x,int y,int num){
        if(num != 0) {
            block[y][x].setText(String.valueOf(num));
        }else{
            block[y][x].setText("");
        }
        switch (num){
            case 0:
                block[y][x].setBackgroundResource(R.color.bgcolor2);
                break;
            case 2:
                block[y][x].setBackgroundResource(R.color.color2);
                break;
            case 4:
                block[y][x].setBackgroundResource(R.color.color4);
                break;
            case 8:
                block[y][x].setBackgroundResource(R.color.color8);
                break;
            case 16:
                block[y][x].setBackgroundResource(R.color.color16);
                break;
            case 32:
                block[y][x].setBackgroundResource(R.color.color32);
                break;
            case 64:
                block[y][x].setBackgroundResource(R.color.color64);
                break;
            case 128:
                block[y][x].setBackgroundResource(R.color.color128);
                break;
            case 256:
                block[y][x].setBackgroundResource(R.color.color256);
                break;
            case 512:
                block[y][x].setBackgroundResource(R.color.color512);
                break;
            case 1024:
                block[y][x].setBackgroundResource(R.color.color1024);
                break;
            case 2048:
                block[y][x].setBackgroundResource(R.color.color2048);
                break;
        }
    }
    private boolean canmerge(int a,int b){
        if(a == b && a != 0){
            return true;
        }else{
            return false;
        }
    }
    private boolean isfull(){
        for (int i = 0;i<4;i++){
            for (int j = 0;j<4;j++){
                if(gamebox.getnum(i,j) == 0){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean equal(int[] a,int[] b){
        if(a[0] == b[0]&&a[1] == b[1]&&a[2] == b[2]&&a[3] == b[3]){
            return true;
        }else return false;
    }
    private void slid(int dir){
        boolean flag;
        switch (dir){
            case 1:
                flag = true;
                for(int i = 0;i<4;i++){
                    init();
                    for(int j = 3;j>=0;j--){
                        aa[3-j] = gamebox.getnum(i,j);
                        if (gamebox.getnum(i, j) > 0) {
                            a[index] = gamebox.getnum(i, j);
                            index++;
                        }
                    }
                    merge();
                    for(int j = 0;j<4;j++){
                        setmap(i,3-j,b[j]);
                    }
                    if(!equal(aa,b)){
                        flag = false;
                    }
                }
                if((!isfull()) && (!flag)) {
                    gamebox.getrandomblock();
                }
                break;
            case 2:
                flag = true;
                for(int i = 0;i<4;i++){
                    init();
                    for(int j = 3;j>=0;j--){
                        aa[3-j] = gamebox.getnum(j,i);
                        if (gamebox.getnum(j,i) > 0) {
                            a[index] = gamebox.getnum(j,i);
                            index++;
                        }
                    }
                    merge();
                    for(int j = 0;j<4;j++){
                        setmap(3-j,i,b[j]);
                    }
                    if(!equal(aa,b)){
                        flag = false;
                    }
                }
                if((!isfull()) && (!flag)) {
                    gamebox.getrandomblock();
                }
                break;
            case 3:
                flag = true;
                for(int i = 0;i<4;i++) {
                    init();
                    for (int j = 0; j < 4; j++) {
                        aa[j] = gamebox.getnum(i,j);
                        if (gamebox.getnum(i, j) > 0) {
                            a[index] = gamebox.getnum(i, j);
                            index++;
                        }
                    }
                    merge();
                    for(int j = 0;j<4;j++){
                        setmap(i,j,b[j]);
                    }
                    if(!equal(aa,b)){
                        flag = false;
                    }
                }
                if((!isfull()) && (!flag)) {
                    gamebox.getrandomblock();
                }
                break;
            case 4:
                flag = true;
                for(int i = 0;i<4;i++){
                    init();
                    for(int j = 0;j<4;j++){
                        aa[j] = gamebox.getnum(j,i);
                        if (gamebox.getnum(j,i) > 0) {
                            a[index] = gamebox.getnum(j,i);
                            index++;
                        }
                    }
                    merge();
                    for(int j = 0;j<4;j++){
                        setmap(j,i,b[j]);
                    }
                    if(!equal(aa,b)){
                        flag = false;
                    }
                }
                if((!isfull()) && (!flag)) {
                    gamebox.getrandomblock();
                }
                break;
        }
    }
    private void merge() {
        if (a[0] == 0 || a[1] == 0) {
            b = a;
        } else if (a[2] == 0) {
            if (canmerge(a[0], a[1])) {
                b[0] = 2 * a[0];
                b[1] = b[2] = b[3] = 0;
            } else {
                b = a;
            }
        } else if (a[3] == 0) {
            if (canmerge(a[0], a[1])) {
                b[0] = 2 * a[0];
                b[1] = a[2];
                b[2] = b[3] = 0;
            } else if (canmerge(a[1], a[2])) {
                b[0] = a[0];
                b[1] = 2 * a[1];
                b[2] = b[3] = 0;
            } else {
                b = a;
            }
        } else if (a[3] != 0) {
            if (canmerge(a[0], a[1]) && canmerge(a[2], a[3])) {
                b[0] = 2 * a[0];
                b[1] = 2 * a[2];
                b[2] = b[3] = 0;
            } else if (canmerge(a[0], a[1]) && !canmerge(a[2], a[3])) {
                b[0] = 2 * a[0];
                b[1] = a[2];
                b[2] = a[3];
                b[3] = 0;
            } else if (!canmerge(a[0], a[1]) && canmerge(a[1], a[2])) {
                b[0] = a[0];
                b[1] = 2 * a[1];
                b[2] = a[3];
                b[3] = 0;
            } else if (!canmerge(a[0], a[1]) && !canmerge(a[1], a[2]) && canmerge(a[2], a[3])) {
                b[0] = a[0];
                b[1] = a[1];
                b[2] = 2 * a[3];
                b[3] = 0;
            } else {
                b = a;
            }
        }
    }
    private void init(){
        index = 0;
        for (int j = 0; j < 4; j++) {
            aa[j] = 0;
            a[j] = 0;
            b[j] = 0;
        }
    }
}
