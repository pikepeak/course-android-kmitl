package kmitl.lab05.khunach58070011.simplemydot;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kmitl.lab05.khunach58070011.simplemydot.model.Dot;

public class MainActivity extends AppCompatActivity implements DotViewFragment.DotViewListener{
    public static Dot Gdot = new Dot(0, 0, 50);
    public static int check = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null) {
            initialFragment();
        }
    }

    private void initialFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.fragmentContainer, DotViewFragment.newInstance(MainActivity.this, null)).commit();
    }
    private void viewFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void showEditDot(Dot take) {
        viewFragment(new EditDotFragment().newInstance(take));
    }
}
