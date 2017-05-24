package comman;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class PreferenceHelper {

    private final SharedPreferences preferences;

    public PreferenceHelper(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void addString(String key, String value) {
        Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }


}
