package com.wokun.dset.ucenter.quanyi;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

import com.wokun.dset.R;


/**
 * 1、解决与ScrollView 事件冲突，只显示一行数据
 * 2、设置边框（默认有边框）
 *
 * @author 陈白衣
 * @time 2018/1/23 15:26
 */
public class CustomGridView extends GridView {
    private boolean setFrame = true;    // 是否设置边框（默认 true, 有边框）
    private int frameColor = 0x00FFFFFF;   // 边框颜色


    public CustomGridView(Context context) {
        super(context);
    }

    public CustomGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.gridStyleable);
        setFrame = array.getBoolean(R.styleable.gridStyleable_setFrame, true);
        frameColor = array.getColor(R.styleable.gridStyleable_frameColor, frameColor);
        array.recycle();
    }

    public CustomGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 解决 scrollview 与 gridview 只显示一行数据的问题
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }


    /**
     * 重绘，绘制边框
     *
     * @param canvas
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (setFrame) {
            View localView1 = getChildAt(0);
            if (localView1 != null) {
                int column = getWidth() / localView1.getWidth();
                int childCount = getChildCount();
                Paint localPaint;
                localPaint = new Paint();
                localPaint.setStyle(Paint.Style.STROKE);
                localPaint.setStrokeWidth(18);
                localPaint.setColor(frameColor);
                for (int i = 0; i < childCount; i++) {
                    View cellView = getChildAt(i);
                    if ((i + 1) % column == 0) {
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                    } else if ((i + 1) > (childCount - (childCount % column))) {
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                    } else {
                        canvas.drawLine(cellView.getRight(), cellView.getTop(), cellView.getRight(), cellView.getBottom(), localPaint);
                        canvas.drawLine(cellView.getLeft(), cellView.getBottom(), cellView.getRight(), cellView.getBottom(), localPaint);
                    }
                }
                if (childCount % column != 0) {
                    for (int j = 0; j < (column - childCount % column); j++) {
                        View lastView = getChildAt(childCount - 1);
                        canvas.drawLine(lastView.getRight() + lastView.getWidth() * j, lastView.getTop(), lastView.getRight() + lastView.getWidth() * j, lastView.getBottom(), localPaint);
                    }
                }
            }
        }
    }
}