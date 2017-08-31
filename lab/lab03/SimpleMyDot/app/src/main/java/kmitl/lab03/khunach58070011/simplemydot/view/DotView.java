package kmitl.lab03.khunach58070011.simplemydot.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;


import kmitl.lab03.khunach58070011.simplemydot.model.Dot;
import kmitl.lab03.khunach58070011.simplemydot.model.SetDot;

public class DotView extends View {
    private Paint paint;
    private SetDot set;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(this.set != null){
            for (Dot item : set.getSet()){
                paint.setColor(Color.argb(100, item.getR(), item.getG(), item.getB()));
                canvas.drawCircle(item.getCenterX(),item.getCenterY(),item.getRadius(),paint);
            }
        }
    }

    public DotView(Context context) {
        super(context);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
    }

    public DotView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
    }


    public void setDot(SetDot set) {
        this.set = set;
    }
}
