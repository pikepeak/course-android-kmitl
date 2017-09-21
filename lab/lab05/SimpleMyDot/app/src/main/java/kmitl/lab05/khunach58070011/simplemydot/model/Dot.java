package kmitl.lab05.khunach58070011.simplemydot.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Dot implements Parcelable {
    protected Dot(Parcel in) {
        centerX = in.readInt();
        centerY = in.readInt();
        radius = in.readInt();
        r = in.readInt();
        g = in.readInt();
        b = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Dot> CREATOR = new Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel in) {
            return new Dot(in);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(centerX);
        parcel.writeInt(centerY);
        parcel.writeInt(radius);
        parcel.writeInt(r);
        parcel.writeInt(g);
        parcel.writeInt(b);
        parcel.writeInt(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public interface onDotChangedListener{
        void onDotChanged(Dot dot);
    }

    private onDotChangedListener listener;

    public void setListener(onDotChangedListener listener) {
        this.listener = listener;
    }

    private int centerX;
    private int centerY;
    private int radius;
    private int r;
    private int g;
    private int b;
    private int id;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Dot(onDotChangedListener listener, int centerX, int centerY, int radius, int r, int g, int b) {
        this.listener = listener;
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
        this.r = r;
        this.g = g;
        this.b = b;


    }

    public Dot(int centerX, int centerY, int radius) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }
    public void setCenterXdot(int centerX) {
        this.centerX = centerX;
        listener.onDotChanged(this);
    }

    public int getCenterY() {
        return centerY;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }
    public void setCenterYdot(int centerY) {
        this.centerY = centerY;
        listener.onDotChanged(this);
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
