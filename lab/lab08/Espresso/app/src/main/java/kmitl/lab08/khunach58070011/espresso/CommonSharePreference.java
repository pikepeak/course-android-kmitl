package kmitl.lab08.khunach58070011.espresso;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class CommonSharePreference {
    public static final String NAME = "UserInfoApp";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public CommonSharePreference(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void save(String key, Object data) {
        Gson gson = new Gson();
        String string = gson.toJson(data);
        editor.putString(key, string);
        editor.apply();
    }

    public Object read(String key, Class c) {
        Object result = null;
        Gson gson = new Gson();
        String string = sharedPreferences.getString(key, null);
        if (string != null) {
            result = gson.fromJson(string, c);
        }
        return result;
    }
    public void remove(){
        editor.clear();
        editor.commit();
    }
}
