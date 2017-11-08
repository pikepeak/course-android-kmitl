package kmitl.lab09.khunach58070011.moneyflow;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.List;

import static kmitl.lab09.khunach58070011.moneyflow.MainActivity.activity;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button saveTran;
    private EditText name;
    private EditText money;
    private String id;
    private ToggleButton swap_button;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transaction_activity);
        id = getIntent().getStringExtra("ID");
        name = findViewById(R.id.Items_add);
        money = findViewById(R.id.Add_Amount);
        saveTran = findViewById(R.id.Save);
        saveTran.setOnClickListener(this);
        swap_button = findViewById(R.id.toggleButton);
        if (id.equals("0")){
            swap_button.setChecked(false);
            name.setText("");
            money.setText("");
        }else if(Integer.parseInt(id) < 0){
            delTransaction(id);
        }
        else {
            loadTransaction(id);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.Save){
            if (id.equals("0")){
                addTransaction();
            }
            else {
                modTransaction(id);
            }
            Intent intent = new Intent(this, MainActivity.class);
            activity.finish();
            startActivity(intent);
            finish();
        }
    }
    @SuppressLint("StaticFieldLeak")
    private void delTransaction(final String id){
        new AsyncTask<Void, Void, List<TransactionInfo>>(){

            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                return null;
            }
            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                int Intid = Integer.parseInt(id)*-1;
                MainActivity.transactionInfoDatabase.transactionDAO().deleteid(Intid);
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
        Intent intent = new Intent(this, MainActivity.class);
        activity.finish();
        startActivity(intent);
        finish();
    }
    @SuppressLint("StaticFieldLeak")
    private void loadTransaction(final String id){
        new AsyncTask<Void, Void, List<TransactionInfo>>(){

            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                List<TransactionInfo> result = MainActivity.transactionInfoDatabase.transactionDAO().getAll();
                return result;
            }
            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                int Intid = Integer.parseInt(id);
                for (TransactionInfo items: transactionInfos) {
                    if (Intid == items.getId()){
                        name.setText(items.getName());
                        money.setText(String.valueOf(items.getMoney()));
                        String s = items.getStatus();
                        if (s.equals("+")){
                            swap_button.setChecked(false);
                        }else{
                            swap_button.setChecked(true);
                        }
                    }
                }
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void addTransaction(){
        final TransactionInfo transactionInfo = new TransactionInfo();
        if (swap_button.isChecked()){
            transactionInfo.setStatus("-");
        }else{
            transactionInfo.setStatus("+");
        }
        transactionInfo.setName(name.getText().toString());
        transactionInfo.setMoney(Integer.parseInt(money.getText().toString()));
        new AsyncTask<Void, Void, List<TransactionInfo>>(){

            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                MainActivity.transactionInfoDatabase.transactionDAO().insert(transactionInfo);
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    private void modTransaction(final String id){
        new AsyncTask<Void, Void, List<TransactionInfo>>(){

            @Override
            protected List<TransactionInfo> doInBackground(Void... voids) {
                return null;
            }

            @Override
            protected void onPostExecute(List<TransactionInfo> transactionInfos) {
                int Intid = Integer.parseInt(id);
                if (swap_button.isChecked()){
                    MainActivity.transactionInfoDatabase.transactionDAO().UpdateColumn("-", name.getText().toString(), Integer.parseInt(money.getText().toString()), Intid);
                }else{
                    MainActivity.transactionInfoDatabase.transactionDAO().UpdateColumn("+", name.getText().toString(), Integer.parseInt(money.getText().toString()), Intid);
                }
            }
            @Override
            protected void onProgressUpdate(Void... values) {
                super.onProgressUpdate(values);
            }
        }.execute();
    }

}
