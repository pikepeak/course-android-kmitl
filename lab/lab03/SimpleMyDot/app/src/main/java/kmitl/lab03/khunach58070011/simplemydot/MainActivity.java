package kmitl.lab03.khunach58070011.simplemydot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;

import kmitl.lab03.khunach58070011.simplemydot.model.Dot;
import kmitl.lab03.khunach58070011.simplemydot.model.SetDot;
import kmitl.lab03.khunach58070011.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements SetDot.onDotChangedListener{

    private DotView dotView;
    private Dot dot;
    private SetDot setDot = new SetDot(this);

    //private ArrayList<Dot> set = new ArrayList<Dot>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dotView = (DotView) findViewById(R.id.dotView);
        dot = new Dot (0, 0 ,50);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            int[] realview = new int[2];
            dotView.getLocationOnScreen(realview);
            if (setDot.removesomedot(this, (int) event.getX() - realview[0], (int) event.getY() - realview[1])){
                dot = new Dot (0, 0 ,50);
                Random random = new Random();
                int centerX = (int) event.getX() - realview[0];
                int centerY = (int) event.getY() - realview[1];
                this.dot.setCenterX(centerX);
                this.dot.setCenterY(centerY);
                int r = random.nextInt(255);
                int b = random.nextInt(255);
                int g = random.nextInt(255);
                this.dot.setR(r);
                this.dot.setG(g);
                this.dot.setB(b);
                setDot.setSet(this.dot);
            }
        }
        return super.onTouchEvent(event);
    }

    public void onRandomDot(View view) {
        dot = new Dot (0, 0 ,50);
        Random random = new Random();
        int centerX = random.nextInt(this.dotView.getWidth());
        int centerY = random.nextInt(this.dotView.getHeight());
        int r = random.nextInt(255);
        int b = random.nextInt(255);
        int g = random.nextInt(255);
        this.dot.setCenterX(centerX);
        this.dot.setCenterY(centerY);
        this.dot.setR(r);
        this.dot.setG(g);
        this.dot.setB(b);
        setDot.setSet(this.dot);
        //set.add(this.dot);
    }

    @Override
    public void onDotChanged(SetDot setDot) {
        dotView.setDot(setDot);
        dotView.invalidate();
    }

    public void onRemoveDot(View view) {
        setDot.removeDot(this);
    }

    //@Override
    //public void onDotChanged(Dot dot) {
    //    dotView.setDot(dot, set);
    //    dotView.invalidate();
    //}
}
