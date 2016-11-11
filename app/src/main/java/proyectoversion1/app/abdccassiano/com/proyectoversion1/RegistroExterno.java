package proyectoversion1.app.abdccassiano.com.proyectoversion1;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RegistroExterno extends AppCompatActivity{

    private Button btnRegistrarFinale;
    private TextView tvBackLogin;

    private EditText etnombre;
    private EditText etapellPat;
    private EditText etapellMat;
    private EditText etemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_externo);

        etnombre = (EditText) findViewById(R.id.etNombre);
        etapellPat = (EditText) findViewById(R.id.etApellPat);
        etapellMat = (EditText) findViewById(R.id.etApellMat);
        etemail = (EditText) findViewById(R.id.etEmail);

        btnRegistrarFinale = (Button)findViewById(R.id.btnRegisterFinales);
        tvBackLogin = (TextView)findViewById(R.id.tvRegresarLogin);

        /*btnRegistrarFinale.setOnClickListener(this);
        tvBackLogin.setOnClickListener(this);*/

        btnRegistrarFinale.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                final String Nombre = etnombre.getText().toString();
                final String ApellPat = etapellPat.getText().toString();
                final String ApellMat = etapellMat.getText().toString();
                final String Email = etemail.getText().toString();

                /*final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();*/

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroExterno.this);
                                builder.setMessage("Registro insertado!")
                                        .setNegativeButton("Cerarr", null)
                                        .create()
                                        .show();

                                //Limpiamos campos
                                etnombre.getText().clear();
                                etapellPat.getText().clear();
                                etapellMat.getText().clear();
                                etemail.getText().clear();

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegistroExterno.this);
                                builder.setMessage("No se pudo relizar el registro!")
                                        .setNegativeButton("Cerrar", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                RegistroExternoRequest registerRequest = new RegistroExternoRequest(Nombre, ApellPat, ApellMat , Email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegistroExterno.this);
                queue.add(registerRequest);
            }
        });

        //Regresar a LoginActivity
        tvBackLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistroExterno.this, LoginActivity.class));
            }
        });
    }


}
