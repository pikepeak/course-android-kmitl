package kmitl.lab05.khunach58070011.simplemydot;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import kmitl.lab05.khunach58070011.simplemydot.model.Dot;
import kmitl.lab05.khunach58070011.simplemydot.model.SetDot;
import kmitl.lab05.khunach58070011.simplemydot.view.DotView;

import static kmitl.lab05.khunach58070011.simplemydot.MainActivity.check;


/**
 * A simple {@link Fragment} subclass.
 */
public class mapDotFragment extends Fragment implements Dot.onDotChangedListener , DotView.OnDotViewPressListener, View.OnClickListener {

    private DotView dotView;
    private Dot dot;
    private View rootView;

    public mapDotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_map_dot, container, false);
        Button doneBtn = rootView.findViewById(R.id.done);
        doneBtn.setOnClickListener(this);
        dotView = (DotView) rootView.findViewById(R.id.dotviewer);
        dotView.setOnDotViewPressListener(this);
        dot.setListener(this);
        dotView.setDot(dot);
        dotView.invalidate();
        return rootView;
    }

    public Fragment newInstance(Dot value) {
        Bundle args = new Bundle();
        mapDotFragment fragment = new mapDotFragment();
        args.putParcelable("dot", value);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.dot = getArguments().getParcelable("dot");
        }
    }

    @Override
    public void onDotChanged(Dot dot) {
        dotView.setDot(dot);
        dotView.invalidate();
    }

    @Override
    public void onDotViewPressed(int x, int y) {
        dot.setCenterXdot(x);
        dot.setCenterYdot(y);
    }

    @Override
    public void onDotViewLongPressed(int x, int y) {

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.done) {
            onDone();
        }
    }

    private void onDone() {
        getFragmentManager().popBackStackImmediate();
    }
}
