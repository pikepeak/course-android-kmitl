package kmitl.lab07.khunach58070011.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wang.avi.AVLoadingIndicatorView;

import kmitl.lab07.khunach58070011.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.khunach58070011.mylazyinstagram.api.LazyInstagramApi;
import kmitl.lab07.khunach58070011.mylazyinstagram.api.UserProfile;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private UserProfile userProfile;
    private int switchview = 1;
    private Button button;
    private int userSelect = 0;
    private String[] items;
    private View loadingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AVLoadingIndicatorView avi = new AVLoadingIndicatorView(this);
        avi.show();

        loadingScreen = findViewById(R.id.loadingall);
        Spinner dropdown = (Spinner)findViewById(R.id.spinner1);
        items = new String[]{"android", "nature", "cartoon"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);
        button = (Button) findViewById(R.id.button);

    }
    private void getUserProfile(String name){
        loadingScreen.setVisibility(View.VISIBLE);
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(LazyInstagramApi.BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        LazyInstagramApi api = retrofit.create(LazyInstagramApi.class);
        Call<UserProfile> call = api.getProfile(name);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    onDispaly(response);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });
    }

    public void onChange(View view) {
        PostAdapter postAdapter = new PostAdapter(MainActivity.this, userProfile.getPosts());
        onSwithView(postAdapter);
    }
    public void onSwithView(PostAdapter postAdapter){
        if (switchview == 0){
            RecyclerView recyclerView = findViewById(R.id.list);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
            postAdapter.setType(switchview);
            recyclerView.setAdapter(postAdapter);
            switchview = 1;
            button.setText("List");
        }
        else{
            RecyclerView recyclerView = findViewById(R.id.list);
            recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            postAdapter.setType(switchview);
            recyclerView.setAdapter(postAdapter);
            switchview = 0;
            button.setText("Grid");
        }
    }
    public void onDispaly(Response<UserProfile> response){
        userProfile = response.body();
        TextView textBio = (TextView) findViewById(R.id.textBio);
        textBio.setText(userProfile.getBio());
        TextView textFollower = (TextView) findViewById(R.id.textFollower);
        textFollower.setText("Follower\n"+userProfile.getFollower());
        TextView textFollowing = (TextView) findViewById(R.id.textFollowing);
        textFollowing.setText("Following\n"+userProfile.getFollowing());
        TextView textPost = (TextView) findViewById(R.id.textPost);
        textPost.setText("Post\n"+userProfile.getPost());
        ImageView imageProfile = findViewById(R.id.imageProﬁle);
        Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
        PostAdapter postAdapter = new PostAdapter(MainActivity.this, userProfile.getPosts());
        onSwithView(postAdapter);
        loadingScreen.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i) {
            case 0:
                userSelect = 0;
                if (switchview == 1){
                    switchview = 0;
                }else{
                    switchview = 1;
                }
                getUserProfile(items[userSelect]);
                break;
            case 1:
                userSelect = 1;
                if (switchview == 1){
                    switchview = 0;
                }else{
                    switchview = 1;
                }
                getUserProfile(items[userSelect]);
                break;
            case 2:
                userSelect = 2;
                if (switchview == 1){
                    switchview = 0;
                }else{
                    switchview = 1;
                }
                getUserProfile(items[userSelect]);
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
