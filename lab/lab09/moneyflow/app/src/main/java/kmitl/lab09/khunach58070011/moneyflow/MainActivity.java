package kmitl.lab09.khunach58070011.moneyflow;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.Transaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ArrayList<HashMap> list;
    public static TransactionInfoDatabase transactionInfoDatabase;
    public static Activity activity;
    public static int total;
    public static int income;
    private TextView totalShow;
    private ListView viewList;
    float percent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transactionInfoDatabase = Room.databaseBuilder(getApplicationContext(), TransactionInfoDatabase.class, "TRANINFO").allowMainThreadQueries().build();
        viewList = (ListView) findViewById(R.id.list);
        totalShow = findViewById(R.id.totalshow);
        total = 0;
        income = 0;
        showTransaction();
        activity = this;
        Button add_button = findViewById(R.id.AddButton);
        add_button.setOnClickListener(this);
    }

    @SuppressLint("StaticFieldLeak")
    private void showTransaction(){
        list = new ArrayList<HashMap>();
        HashMap temp = new HashMap();
        new AsyncTask<Void, Void, List<TransactionInfo>>() {
            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                List<TransactionInfo> result = transactionInfoDatabase.transactionDAO().getAll();
                return result;
            }

            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                for (TransactionInfo items: transactionInfos) {
                    HashMap temp = new HashMap();
                    temp.put("FIRST_COLUMN", items.getStatus());
                    temp.put("SECOND_COLUMN", items.getName());
                    temp.put("THIRD_COLUMN", items.getMoney());
                    temp.put("ID_COLUMN", items.getId());
                    String s = items.getStatus();
                    if (s.equals("+")){
                        total = total + items.getMoney();
                        income += items.getMoney();
                    }else{
                        total = total - items.getMoney();
                    }
                    list.add(temp);
                }
                setColorText();
                totalShow.setText("Money : "+String.valueOf(total));
                listviewAdapter adapter = new listviewAdapter(MainActivity.this, list);
                viewList.setAdapter(adapter);
            }

            @Override
            protected void onProgressUpdate(Void... values) {
            }
        }.execute();
    }
    public void setColorText(){
        if (income == 0 && total == 0){
            totalShow.setTextColor(Color.parseColor("#db1a1a"));
        }else{
            percent = 0;

            percent =  ((float)(total*100)/(float)income);

            if (percent > 50.00){
                totalShow.setTextColor(Color.parseColor("#00B800"));
            }else if (percent >= 25.00 && percent <= 50.00){
                totalShow.setTextColor(Color.parseColor("#ffdb00"));
            }else if(percent < 25.00){
                totalShow.setTextColor(Color.parseColor("#db1a1a"));
            }
        }
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.AddButton) {
            Intent intent = new Intent(this, TransactionActivity.class);
            intent.putExtra("ID", "0");
            startActivity(intent);
        }
    }
}
