package kmitl.lab05.khunach58070011.simplemydot.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.content.FileProvider;
import android.view.View;

import java.io.File;
import java.io.FileOutputStream;

import kmitl.lab05.khunach58070011.simplemydot.BuildConfig;

public class DotFile {
    Bitmap bitmap;
    View screenView;
    File dirPath;
    File file;
    Uri uri;

    public DotFile(View rootView) {
        screenView = rootView.getRootView();
    }
    private void setViewToBitmap(){
        screenView.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(screenView.getDrawingCache());
        screenView.setDrawingCacheEnabled(false);
    }
    private void sendBitmapToFileStorage(){
        dirPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        file = new File(dirPath, "pic.jpg");
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 85, fOut);
            fOut.flush();
            fOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Uri getUri(Context context){
        setViewToBitmap();
        sendBitmapToFileStorage();
        uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
        return uri;
    }
}

