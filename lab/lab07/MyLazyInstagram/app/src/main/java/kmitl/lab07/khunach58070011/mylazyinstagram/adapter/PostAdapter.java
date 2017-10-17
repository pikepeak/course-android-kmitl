package kmitl.lab07.khunach58070011.mylazyinstagram.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import kmitl.lab07.khunach58070011.mylazyinstagram.MainActivity;
import kmitl.lab07.khunach58070011.mylazyinstagram.R;
import kmitl.lab07.khunach58070011.mylazyinstagram.model.listposts;

class Holder extends RecyclerView.ViewHolder{
    public ImageView image;
    public TextView like;
    public TextView Comments;


    public Holder(View item){
        super(item);
        image = itemView.findViewById(R.id.image);
        like = itemView.findViewById(R.id.Like);
        Comments = itemView.findViewById(R.id.Comment);
    }

}

public class PostAdapter extends RecyclerView.Adapter<Holder>{
    public int type;
    private List<listposts> data ;
    private Context context;
    public PostAdapter(MainActivity mainActivity, List<listposts> data) {
        context = mainActivity;
        this.data = new ArrayList<>();
        setdata(data);
    }

    public void setdata(List<listposts> data) {
        this.data = data;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView;
        if (type == 1){
            itemView = inflater.inflate(R.layout.post_item, null, false);
        }else{
            itemView = inflater.inflate(R.layout.post_item_grid, null, false);
        }
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        if (type == 1){
            WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            DisplayMetrics metrics = new DisplayMetrics();
            wm.getDefaultDisplay().getMetrics(metrics);
            image.getLayoutParams().width = metrics.widthPixels;
        }
        Glide.with(context).load(this.data.get(position).getUrl()).into(image);
        holder.like.setText("Like :" + String.valueOf(this.data.get(position).getLike()));
        holder.Comments.setText("Comments :" + String.valueOf(this.data.get(position).getComment()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setType(int switchview) {
        this.type = switchview;
    }
}
