package com.example.as.a2048;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by as on 2016/5/31.
 */
public class gamebox extends ViewGroup {
    int line = 10;
    TextView[][] block = new TextView[4][4];
    public gamebox(Context context){
        super(context);
    }
    public gamebox(Context context, AttributeSet attributeSet){
        super(context,attributeSet);
    }
    public void setBlock(TextView[][] block){
        this.block = block;
    }
    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        int wid = getWidth()-getPaddingLeft()-getPaddingRight();
        int l = (wid-line*5)/4;
        for(int j = 0;j<16;j++){
            block[j/4][j%4] = (TextView) getChildAt(j);
        }
        for(int j = 0;j<4;j++){
            for(int k = 0;k<4;k++){
                block[j][k].layout((j+1)*line+j*l,(k+1)*line+k*l,(j+1)*line+(j+1)*l,(k+1)*line+(k+1)*l);
            }
        }
    }
    public void setmap(int x,int y,int num){
        if(num != 0) {
            block[x][y].setText(String.valueOf(num));
        }else{
            block[x][y].setText("");
        }
        switch (num){
            case 0:
                block[x][y].setBackgroundResource(R.color.bgcolor2);
                break;
            case 2:
                block[x][y].setBackgroundResource(R.color.color2);
                break;
            case 4:
                block[x][y].setBackgroundResource(R.color.color4);
                break;
            case 8:
                block[x][y].setBackgroundResource(R.color.color8);
                break;
            case 16:
                block[x][y].setBackgroundResource(R.color.color16);
                break;
            case 32:
                block[x][y].setBackgroundResource(R.color.color32);
                break;
            case 64:
                block[x][y].setBackgroundResource(R.color.color64);
                break;
            case 128:
                block[x][y].setBackgroundResource(R.color.color128);
                break;
            case 256:
                block[x][y].setBackgroundResource(R.color.color256);
                break;
            case 512:
                block[x][y].setBackgroundResource(R.color.color512);
                break;
            case 1024:
                block[x][y].setBackgroundResource(R.color.color1024);
                break;
            case 2048:
                block[x][y].setBackgroundResource(R.color.color2048);
                break;
        }
    }
    public int getnum(int x,int y){
        if(block[y][x].getText() != "") {
            String s = block[y][x].getText().toString();
            return Integer.parseInt(s);
        }else{
            return 0;
        }
    }
    public void getrandomblock(){
        int x,y;
        do {
            x = (int) (3.999 * Math.random());
            y = (int) (3.999 * Math.random());
        }while (block[x][y].getText().toString() != "");
        int rand = (int)(1.999*Math.random());
        if(rand == 0){
            setmap(x,y,2);
        }else if(rand == 1){
            setmap(x,y,4);
        }
    }
}
