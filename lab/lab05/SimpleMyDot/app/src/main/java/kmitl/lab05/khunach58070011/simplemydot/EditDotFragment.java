package kmitl.lab05.khunach58070011.simplemydot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import kmitl.lab05.khunach58070011.simplemydot.model.Dot;

import static kmitl.lab05.khunach58070011.simplemydot.MainActivity.check;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener{

    private Dot dot;
    private View rootView;
    public EditDotFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null){
            dot = savedInstanceState.getParcelable("dot");
        }else{
            dot = getArguments().getParcelable("dot");
        }

    }

    public Fragment newInstance(EditDotListener listener, Dot value) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        fragment.setListener(listener);
        args.putParcelable("dot", value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        Button doneBtn = rootView.findViewById(R.id.done);
        Button setposBtn = rootView.findViewById(R.id.setpos);

        doneBtn.setOnClickListener(this);
        setposBtn.setOnClickListener(this);

        EditText redDot = rootView.findViewById(R.id.r_editText);
        EditText greenDot = rootView.findViewById(R.id.g_editText);
        EditText blueDot = rootView.findViewById(R.id.b_editText);
        EditText radDot = rootView.findViewById(R.id.rad_editText);
        EditText xDot = rootView.findViewById(R.id.x_editText);
        EditText yDot = rootView.findViewById(R.id.y_editText);

        int red = dot.getR();
        int green = dot.getG();
        int blue = dot.getB();
        int rad = dot.getRadius();
        int x;
        int y;
        x = dot.getCenterX();
        y = dot.getCenterY();
        redDot.setText(String.valueOf(red));
        greenDot.setText(String.valueOf(green));
        blueDot.setText(String.valueOf(blue));
        radDot.setText(String.valueOf(rad));
        xDot.setText(String.valueOf(x));
        yDot.setText(String.valueOf(y));

        return rootView;
    }

    public interface EditDotListener{
        void showMapDot(Dot take);
    }
    private EditDotListener listener;
    public EditDotListener getListener() {
        return listener;
    }

    public void setListener(EditDotListener listener) {
        this.listener = listener;
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.done) {
            onDone();
        } else if (view.getId() == R.id.setpos){
            onSetPot();
        }
    }
    private void onSetPot(){
        EditText redDot = rootView.findViewById(R.id.r_editText);
        EditText greenDot = rootView.findViewById(R.id.g_editText);
        EditText blueDot = rootView.findViewById(R.id.b_editText);
        EditText radDot = rootView.findViewById(R.id.rad_editText);
        EditText xDot = rootView.findViewById(R.id.x_editText);
        EditText yDot = rootView.findViewById(R.id.y_editText);

        dot.setCenterX(Integer.parseInt(xDot.getText().toString()));
        dot.setCenterY(Integer.parseInt(yDot.getText().toString()));
        dot.setR((Integer.parseInt(redDot.getText().toString())));
        dot.setG((Integer.parseInt(greenDot.getText().toString())));
        dot.setB((Integer.parseInt(blueDot.getText().toString())));
        dot.setRadius((Integer.parseInt(radDot.getText().toString())));
        dot.setId(this.dot.getId());
        listener.showMapDot(dot);
        //getFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new mapDotFragment().newInstance(dot)).addToBackStack(null).commit();
    }

    @Override
    public void onResume() {
        EditText xDot = rootView.findViewById(R.id.x_editText);
        EditText yDot = rootView.findViewById(R.id.y_editText);
        xDot.setText(String.valueOf(dot.getCenterX()));
        yDot.setText(String.valueOf(dot.getCenterY()));
        super.onResume();
    }

    private void onDone() {
        EditText redDot = rootView.findViewById(R.id.r_editText);
        EditText greenDot = rootView.findViewById(R.id.g_editText);
        EditText blueDot = rootView.findViewById(R.id.b_editText);
        EditText radDot = rootView.findViewById(R.id.rad_editText);
        EditText xDot = rootView.findViewById(R.id.x_editText);
        EditText yDot = rootView.findViewById(R.id.y_editText);

        dot.setCenterX(Integer.parseInt(xDot.getText().toString()));
        dot.setCenterY(Integer.parseInt(yDot.getText().toString()));
        dot.setR((Integer.parseInt(redDot.getText().toString())));
        dot.setG((Integer.parseInt(greenDot.getText().toString())));
        dot.setB((Integer.parseInt(blueDot.getText().toString())));
        dot.setRadius((Integer.parseInt(radDot.getText().toString())));
        dot.setId(this.dot.getId());
        getFragmentManager().popBackStackImmediate();
    }
}
