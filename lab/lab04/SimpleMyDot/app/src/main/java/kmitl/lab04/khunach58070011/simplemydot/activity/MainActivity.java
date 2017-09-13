package kmitl.lab04.khunach58070011.simplemydot.activity;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;
import kmitl.lab04.khunach58070011.simplemydot.R;
import kmitl.lab04.khunach58070011.simplemydot.model.Dot;
import kmitl.lab04.khunach58070011.simplemydot.model.DotFile;
import kmitl.lab04.khunach58070011.simplemydot.model.SetDot;
import kmitl.lab04.khunach58070011.simplemydot.view.DotView;

public class MainActivity extends AppCompatActivity implements SetDot.onDotChangedListener{

    private DotView dotView;
    private Dot dot;
    private SetDot setDot = new SetDot(this);
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

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
                this.dot.setRadius(random.nextInt(100)+20);
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
        this.dot.setRadius(random.nextInt(100)+20);
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
    }

    @Override
    public void onDotChanged(SetDot setDot) {
        dotView.setDot(setDot);
        dotView.invalidate();
    }

    public void onRemoveDot(View view) {
        setDot.removeDot(this);
    }

    public void requestShare(){
        if(requestpermission()){
            shareByIntent(new DotFile(getWindow().getDecorView().findViewById(android.R.id.content)));
        }
    }
    public void shareByIntent(DotFile file){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, file.getUri(this));
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {

        }
    }
    public void onShare(View view) {
        this.requestShare();
    }

    public boolean requestpermission(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, PERMISSIONS_STORAGE, 1);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                this.requestShare();
            }
            return;
        }
    }
}
