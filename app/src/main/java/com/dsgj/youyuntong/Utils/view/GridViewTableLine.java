package com.dsgj.youyuntong.Utils.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.dsgj.youyuntong.R;

/**
 *TODO:
 * Created by 张云浩  on 2017/5/6.
 *邮箱：943332771@qq.com
 */

public class GridViewTableLine extends GridView {
    public GridViewTableLine(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }
    public GridViewTableLine(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public GridViewTableLine(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    protected void dispatchDraw(Canvas canvas){
        super.dispatchDraw(canvas);
        View localView1 = getChildAt(0);
        int column = getWidth() / localView1.getWidth();
        int childCount = getChildCount();
        Paint localPaint;
        localPaint = new Paint();
        localPaint.setStrokeWidth((float) 2.0);
        localPaint.setStyle(Paint.Style.STROKE);
        localPaint.setColor(ContextCompat.getColor(getContext(), R.color.gray11));
        for(int i = 0;i < childCount;i++){
            View cellView = getChildAt(i);
            if((i + 1) % column == 0||i<=column){
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getTop(), cellView.getRight(), cellView.getTop(), localPaint);
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }else if((i + 1) > (childCount - (childCount % column))){
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
            }else{
                canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
            }
        }
        if(childCount % column != 0){
            for(int j = 0 ;j < (column-childCount % column) ; j++){
                View lastView = getChildAt(childCount - 1);
                canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth()* j, lastView.getBottom(), localPaint);
            }
        }
    }
}

