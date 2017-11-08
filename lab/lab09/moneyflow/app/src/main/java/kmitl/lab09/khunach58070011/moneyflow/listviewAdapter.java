package kmitl.lab09.khunach58070011.moneyflow;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class listviewAdapter extends BaseAdapter {
    public ArrayList<HashMap> list;
    Activity activity;
    public listviewAdapter(Activity activity, ArrayList<HashMap> list) {
        super();
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();

        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.txtFirst = (TextView) convertView.findViewById(R.id.FirstText);
            holder.txtSecond = (TextView) convertView.findViewById(R.id.SecondText);
            holder.txtThird = (TextView) convertView.findViewById(R.id.ThirdText);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        final HashMap map = list.get(position);
        holder.txtFirst.setText((CharSequence) map.get("FIRST_COLUMN"));
        holder.txtSecond.setText((CharSequence) map.get("SECOND_COLUMN"));
        holder.txtThird.setText(String.valueOf(map.get("THIRD_COLUMN")));

        Button mod = (Button) convertView.findViewById(R.id.mod);
        mod.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TransactionActivity.class);
                intent.putExtra("ID", String.valueOf(map.get("ID_COLUMN")));
                activity.startActivity(intent);
            }
        });

        Button del = (Button) convertView.findViewById(R.id.del);
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, TransactionActivity.class);
                intent.putExtra("ID", String.valueOf("-"+map.get("ID_COLUMN")));
                activity.startActivity(intent);
            }
        });


        return convertView;
    }
}
