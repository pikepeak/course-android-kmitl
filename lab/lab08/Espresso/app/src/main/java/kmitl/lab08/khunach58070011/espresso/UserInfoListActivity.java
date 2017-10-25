package kmitl.lab08.khunach58070011.espresso;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserInfoListActivity extends AppCompatActivity {

    public static final String EXTTRA_LIST = "EXTTRA_LIST";

    @BindView(R.id.list)
    public RecyclerView list;

    @BindView(R.id.textNotFound)
    public TextView textNotFound;

    private MyAdapter adapter;
    private CommonSharePreference preference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_list);
        ButterKnife.bind(this);
        preference = new CommonSharePreference(this);

        adapter = new MyAdapter();
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        final UserInfoList suggestSearchList = (UserInfoList) preference.read(UserInfoListActivity.EXTTRA_LIST, UserInfoList.class);
        if (suggestSearchList != null) {
            displaySuggestsList(suggestSearchList.getUserInfoList());
        } else {
            displaySuggestsList(new ArrayList<UserInfo>());
        }
        Button button = (Button) findViewById(R.id.clear_list);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preference.remove();
                Intent i = getBaseContext().getPackageManager()
                        .getLaunchIntentForPackage( getBaseContext().getPackageName() );
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
            }
        });
    }

    public void displaySuggestsList(List<UserInfo> suggestsList) {
        if (suggestsList.size() <= 0) {
            textNotFound.setVisibility(View.VISIBLE);
            list.setVisibility(View.GONE);
        } else {
            textNotFound.setVisibility(View.GONE);
            list.setVisibility(View.VISIBLE);
            adapter.setData(suggestsList);
            adapter.notifyDataSetChanged();
        }

    }
}
