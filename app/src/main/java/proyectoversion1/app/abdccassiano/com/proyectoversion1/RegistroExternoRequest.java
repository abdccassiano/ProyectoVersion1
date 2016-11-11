package proyectoversion1.app.abdccassiano.com.proyectoversion1;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by servus on 11/11/2016.
 */
public class RegistroExternoRequest extends StringRequest{

    private static final String REGISTER_REQUEST_URL = "http://websandroid.esy.es/AppAndroid/UsuariosFinales/Register.php";
    private Map<String, String> params;

    public RegistroExternoRequest(String nombre, String apellidoPaterno, String apellidoMaterno, String email, Response.Listener<String> listener) {
        super(Request.Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("nombre", nombre);
        //params.put("age", age + "");
        params.put("apellidoPat", apellidoPaterno);
        params.put("apellidoMat", apellidoMaterno);
        params.put("email", email);
        //params.put("foto", Foto);
        //Tiempo de espera para la petición al servidor
        /*setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        /*setRetryPolicy(new DefaultRetryPolicy(1000 * 60,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
