package kmitl.lab05.khunach58070011.simplemydot;


import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Random;

import kmitl.lab05.khunach58070011.simplemydot.model.Dot;
import kmitl.lab05.khunach58070011.simplemydot.model.DotFile;
import kmitl.lab05.khunach58070011.simplemydot.model.SetDot;
import kmitl.lab05.khunach58070011.simplemydot.view.DotView;

import static kmitl.lab05.khunach58070011.simplemydot.MainActivity.Gdot;
import static kmitl.lab05.khunach58070011.simplemydot.MainActivity.check;


/**
 * A simple {@link Fragment} subclass.
 */
public class DotViewFragment extends Fragment implements SetDot.onDotChangedListener, DotView.OnDotViewPressListener {

    private DotView dotView;
    private Dot dot;
    private Dot Editdot;
    private SetDot setDot = new SetDot(this);
    private DotViewListener listener;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    public DotViewFragment() {

    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.Editdot = getArguments().getParcelable("dot");
        }
    }

    public DotViewListener getListener() {
        return listener;
    }

    public void setListener(DotViewListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dot_view, container, false);

        dotView = (DotView) rootView.findViewById(R.id.dotView);
        dot = new Dot (0, 0 ,50);
        dotView.setOnDotViewPressListener(this);
        Button changeTextInB = rootView.findViewById(R.id.button);
        changeTextInB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRandomDot();

            }
        });
        Button changeTextInA = rootView.findViewById(R.id.button2);
        changeTextInA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRemoveDot(view);
            }
        });
        Button share = rootView.findViewById(R.id.share);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onShare(view);
            }
        });
        dotView.setDot(setDot);
        dotView.invalidate();
        if (check == 1){
            dot = setDot.getMarkDotbyid(Gdot.getId());
            dot.setRadius(Gdot.getRadius());
            dot.setCenterX(Gdot.getCenterX());
            dot.setCenterY(Gdot.getCenterY());
            dot.setR(Gdot.getR());
            dot.setG(Gdot.getG());
            dot.setB(Gdot.getB());
            setDot.setSet(dot);
            check = 0;
        }
        return rootView;

    }
    public void onDot(int x, int y){
        if (setDot.removesomedot(this, (int) x, (int) y)){
            dot = new Dot (0, 0 ,50);
            Random random = new Random();
            dot.setRadius(random.nextInt(100)+50);
            int centerX = (int) x;
            int centerY = (int) y;
            dot.setCenterX(centerX);
            dot.setCenterY(centerY);
            int r = random.nextInt(255);
            int b = random.nextInt(255);
            int g = random.nextInt(255);
            dot.setR(r);
            dot.setG(g);
            dot.setB(b);
            setDot.setSet(dot);
        }
    }
    public void onDotEdit(int x, int y){
        Dot take;
        take = setDot.getMarkDot(this, (int) x, (int) y);
        if (take != null){
            listener.showEditDot(take);
        }
        }

    public void onRemoveDot(View view) {
        setDot.removeDot(this);
    }

    public void onRandomDot() {
        dot = new Dot (0, 0 ,50);
        Random random = new Random();
        this.dot.setRadius(random.nextInt(100)+50);
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

    public static Fragment newInstance(DotViewListener listener, Dot value) {
        Bundle args = new Bundle();
        DotViewFragment fragment = new DotViewFragment();
        fragment.setListener(listener);
        args.putParcelable("dot", value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDotChanged(SetDot setDot) {
        dotView.setDot(setDot);
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        onDot(x, y);
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {
        onDotEdit(x, y);
    }

    public interface DotViewListener{
        void showEditDot(Dot take);
    }

    public void onShare(View view) {
        this.requestShare();
    }

    private void requestShare() {
        if(requestpermission()){
            shareByIntent(new DotFile(getActivity().getWindow().getDecorView().findViewById(android.R.id.content)));
        }
    }

    private boolean requestpermission() {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(PERMISSIONS_STORAGE, 1);
            return false;
        }else{
            return true;
        }
    }

    public void shareByIntent(DotFile file){
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.setType("image/*");
        intent.putExtra(android.content.Intent.EXTRA_SUBJECT, "");
        intent.putExtra(android.content.Intent.EXTRA_TEXT, "");
        intent.putExtra(Intent.EXTRA_STREAM, file.getUri(getActivity()));
        try {
            startActivity(Intent.createChooser(intent, "Share Screenshot"));
        } catch (ActivityNotFoundException e) {

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
