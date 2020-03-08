package tw.org.iii.brad.brad06;
//註解留著是為了解開來後可以測試
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.LinkedList;

public class MyView extends View {
    //因為父類沒有無傳參數建構式故剛extends完會出現編譯錯誤,因沒有建構式(一般類別若不特別註明建構式會去找父類別無傳參數的)

    private LinkedList<HashMap<String, Float>> line;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        line = new LinkedList<>();
        //setBackgroundColor(Color.GREEN);

        //        setOnClickListener(new OnClickListener() {
        //            @Override
        //            public void onClick(View v) {
        //                Log.v("brad","onClick");
        //            }
        //        });
        //
        //        setOnLongClickListener(new OnLongClickListener() {
        //            @Override
        //            public boolean onLongClick(View v) {
        //                Log.v("brad","onLongClick()");
        //                return false;   //若為false,長按一下會先觸發onLongClick,放開再觸發onClick
        //            }
        //        });


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
             //Log.v("brad","onDraw()");
        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        for (int i = 1; i<line.size(); i++){//從第二點開始巡訪
            HashMap<String,Float> p0 = line.get(i-1);
            HashMap<String,Float> p1 = line.get(i);
            canvas.drawLine(p0.get("x"), p0.get("y"), p1.get("x"), p1.get("y"), paint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX(), ey = event.getY();
        Log.v("brad",ex+" x " + ey );
        HashMap<String,Float> point = new HashMap<>();
        point.put("x", ex); point.put("y", ey);
        line.add(point);
        invalidate();   //相當於java所寫的簽名程式的repaint
                //Log.v("brad","onTouchEvent()");
        return true; //super.onTouchEvent(event);//false只有剛碰到的一次,其他的東西也都不會觸發,true則會一直觸發onTouch,其他click不觸發,若super則其他click也會觸發
    }
}
