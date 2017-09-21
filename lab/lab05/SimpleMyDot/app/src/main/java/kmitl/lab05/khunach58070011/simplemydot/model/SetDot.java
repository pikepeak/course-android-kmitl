package kmitl.lab05.khunach58070011.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import kmitl.lab05.khunach58070011.simplemydot.DotViewFragment;

public class SetDot implements Parcelable{
        private ArrayList<Dot> set;
        private Dot dot;
        private int count = 1;

    protected SetDot(Parcel in) {
        set = in.createTypedArrayList(Dot.CREATOR);
        dot = in.readParcelable(Dot.class.getClassLoader());
    }

    public static final Creator<SetDot> CREATOR = new Creator<SetDot>() {
        @Override
        public SetDot createFromParcel(Parcel in) {
            return new SetDot(in);
        }

        @Override
        public SetDot[] newArray(int size) {
            return new SetDot[size];
        }
    };

    public Dot getMarkDot(DotViewFragment dotViewFragment, int x, int y) {
        if(this.set != null){
            for (int i = 0; i < set.size(); i++){
                if (Math.abs((x - set.get(i).getCenterX())) <= set.get(i).getRadius() && Math.abs(y - set.get(i).getCenterY()) <= set.get(i).getRadius()){
                    return set.get(i);
                }
            }
        }
        return null;
    }

    public Dot getMarkDotbyid(int id) {
        if(this.set != null){
            for (int i = 0; i < set.size(); i++){
                if (set.get(i).getId() == id){
                    return set.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeTypedList(set);
        parcel.writeParcelable(dot, i);
    }

    public interface onDotChangedListener{
            void onDotChanged(SetDot setDot);
        }
        private onDotChangedListener listener;

        public ArrayList<Dot> getSet() {
            return set;
        }

        public void setSet(Dot dot) {
            dot.setId(count);
            count += 1;
            this.set.add(dot);
            this.listener.onDotChanged(this);
        }

        public Dot getDot() {
            return dot;
        }

        public void setDot(Dot dot) {
            this.dot = dot;
        }

        public SetDot(onDotChangedListener listener) {
            this.set = new ArrayList();
            this.listener = listener;
        }
        public void removeDot(onDotChangedListener listener){
            this.set = new ArrayList();
            count = 1;
            this.listener.onDotChanged(this);
        }
        public boolean removesomedot(onDotChangedListener listener, int x, int y){
            boolean check = true;
            if(this.set != null){
                for (int i = 0; i < set.size(); i++){
                    if (Math.abs((x - set.get(i).getCenterX())) <= set.get(i).getRadius() && Math.abs(y - set.get(i).getCenterY()) <= set.get(i).getRadius()){
                        set.remove(i);
                        this.listener.onDotChanged(this);
                        return check = false;
                    }
                }
            }
            return check;
        }
}
