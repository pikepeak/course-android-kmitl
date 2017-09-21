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

import static kmitl.lab05.khunach58070011.simplemydot.MainActivity.Gdot;
import static kmitl.lab05.khunach58070011.simplemydot.MainActivity.check;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditDotFragment extends Fragment implements View.OnClickListener{

    private Dot dot;
    private View rootView;
    private EditDotListener editDotListener;
    public EditDotFragment() {
        // Required empty public constructor
    }
    public interface EditDotListener{
        void backToMain(Dot back);
    }

    public EditDotListener getEditDotListener() {
        return editDotListener;
    }

    public void setEditDotListener(EditDotListener editDotListener) {
        this.editDotListener = editDotListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.dot = getArguments().getParcelable("dot");
        }
    }

    public Fragment newInstance(Dot value) {
        Bundle args = new Bundle();
        EditDotFragment fragment = new EditDotFragment();
        args.putParcelable("dot", value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_edit_dot, container, false);
        Button cancelBtn = rootView.findViewById(R.id.cancel);
        Button doneBtn = rootView.findViewById(R.id.done);

        cancelBtn.setOnClickListener(this);
        doneBtn.setOnClickListener(this);

        EditText redDot = rootView.findViewById(R.id.r_editText);
        EditText greenDot = rootView.findViewById(R.id.g_editText);
        EditText blueDot = rootView.findViewById(R.id.b_editText);
        EditText radDot = rootView.findViewById(R.id.rad_editText);
        EditText xDot = rootView.findViewById(R.id.x_editText);
        EditText yDot = rootView.findViewById(R.id.y_editText);

        int red = this.dot.getR();
        int green = this.dot.getG();
        int blue = this.dot.getB();
        int rad = this.dot.getRadius();
        int x = this.dot.getCenterX();
        int y = this.dot.getCenterY();

        redDot.setText(String.valueOf(red));
        greenDot.setText(String.valueOf(green));
        blueDot.setText(String.valueOf(blue));
        radDot.setText(String.valueOf(rad));
        xDot.setText(String.valueOf(x));
        yDot.setText(String.valueOf(y));

        return rootView;
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.cancel) {
            onCancel();
        } else if (view.getId() == R.id.done) {
            onDone();
        }
    }

    private void onDone() {
        EditText redDot = rootView.findViewById(R.id.r_editText);
        EditText greenDot = rootView.findViewById(R.id.g_editText);
        EditText blueDot = rootView.findViewById(R.id.b_editText);
        EditText radDot = rootView.findViewById(R.id.rad_editText);
        EditText xDot = rootView.findViewById(R.id.x_editText);
        EditText yDot = rootView.findViewById(R.id.y_editText);

        Gdot.setCenterX(Integer.parseInt(xDot.getText().toString()));
        Gdot.setCenterY(Integer.parseInt(yDot.getText().toString()));
        Gdot.setR((Integer.parseInt(redDot.getText().toString())));
        Gdot.setG((Integer.parseInt(greenDot.getText().toString())));
        Gdot.setB((Integer.parseInt(blueDot.getText().toString())));
        Gdot.setRadius((Integer.parseInt(radDot.getText().toString())));
        Gdot.setId(this.dot.getId());
        check = 1;
        getFragmentManager().popBackStackImmediate();
    }
    private void onCancel() {
        check = 0;
        getFragmentManager().popBackStackImmediate();
    }
}
