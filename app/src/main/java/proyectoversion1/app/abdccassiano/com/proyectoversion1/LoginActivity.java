package proyectoversion1.app.abdccassiano.com.proyectoversion1;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity{
    //LLamos a la clase Session
    private Session session;

    private EditText etEmail;
    private EditText etPass;
    private Button btnLogin;
    private Button btnReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnReg = (Button)findViewById(R.id.btnReg);

        session = new Session(this);
        int tipoUser = session.prefs.getInt("tipoUser", -1);

        if (session.loggedin() && tipoUser == 1){
            startActivity(new Intent(LoginActivity.this, PanelAdmin.class));
            finish();
        }else if (session.loggedin() && tipoUser == 2){
            startActivity(new Intent(LoginActivity.this, PanelGerente.class));
            finish();
        }

        /*btnReg.setOnClickListener(this);
        btnLogin.setOnClickListener(this);*/

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String username = etEmail.getText().toString();
                final String password = etPass.getText().toString();

                // Response received from the server
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                session.setLoggedin(true);

                                String email = jsonResponse.getString("email");
                                int tipoUsuario = jsonResponse.getInt("tipoId");

                                //Agregamos variables y sus respectivos valores para la session
                                session.editor.putString("email", email);
                                session.editor.putInt("tipoUser", tipoUsuario);
                                session.editor.commit();

                                if (tipoUsuario == 1){

                                    session.setLoggedin(true);
                                    startActivity(new Intent(LoginActivity.this, PanelAdmin.class));
                            /*Intent intent = new Intent(LoginActivity.this, PanelAdmin.class);
                            intent.putExtra("email", email);
                            LoginActivity.this.startActivity(intent);*/

                                }else if(tipoUsuario == 2){

                                    session.setLoggedin(true);
                                    startActivity(new Intent(LoginActivity.this, PanelGerente.class));
                                }


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Ha fallado el inicio de sesión!")
                                        .setNegativeButton("Cerrar", null)
                                        .create()
                                        .show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest loginRequest = new LoginRequest(username, password, responseListener);
                RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
                queue.add(loginRequest);

            }
        }); //cierre del btnLogin

        btnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegistroExterno.class));
            }
        });

    }


}
