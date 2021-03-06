package proyectoversion1.app.abdccassiano.com.proyectoversion1;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by servus on 21/10/2016.
 */
public class Session {
    public SharedPreferences prefs;
    public SharedPreferences.Editor editor;
    public Context ctx;

    public Session(Context ctx){
        this.ctx = ctx;
        prefs = ctx.getSharedPreferences("myapp", Context.MODE_PRIVATE);
        editor = prefs.edit();
    }

    public void setLoggedin(boolean logggedin){
        editor.putBoolean("loggedInmode", logggedin);
        editor.commit();
    }

    public boolean loggedin(){
        return prefs.getBoolean("loggedInmode", false);
    }
}