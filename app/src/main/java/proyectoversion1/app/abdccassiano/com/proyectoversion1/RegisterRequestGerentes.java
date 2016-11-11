package proyectoversion1.app.abdccassiano.com.proyectoversion1;

/**
 * Created by servus on 14/10/2016.
 */
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequestGerentes extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://websandroid.esy.es/AppAndroid/Gerentes/Register.php";
    private Map<String, String> params;

    public RegisterRequestGerentes(String Image, String Nombre, String ApellPat, String ApellMat, String Direccion, String Telefono, String Email, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("image", Image);
        params.put("nombre", Nombre);
        //params.put("age", age + "");
        params.put("apellidoPat", ApellPat);
        params.put("apellidoMat", ApellMat);
        params.put("direccion", Direccion);
        params.put("telefono", Telefono);
        params.put("email", Email);
        //params.put("foto", Foto);
        //Tiempo de espera para la petición al servidor
        /*setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        setRetryPolicy(new DefaultRetryPolicy(1000 * 60,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}