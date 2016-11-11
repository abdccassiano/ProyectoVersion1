package proyectoversion1.app.abdccassiano.com.proyectoversion1;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import fragments.Admin.Gerentes;

public class RegisterGerente extends AppCompatActivity {

    private Button buttonChoose;

    private ImageView imageView;

    private EditText etNombre;
    private EditText etApellPat;
    private EditText etApellMat;
    private EditText etDireccion;
    private EditText etTelefono;
    private EditText etEmail;

    private Button bRegister;

    private EditText editTextName;

    private Bitmap bitmap;

    private int PICK_IMAGE_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_gerente);


        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellPat = (EditText) findViewById(R.id.etApellPat);
        etApellMat = (EditText) findViewById(R.id.etApellMat);
        etDireccion = (EditText) findViewById(R.id.etDireccion);
        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEmail = (EditText) findViewById(R.id.etEmail);
        //final EditText etFoto = (EditText) findViewById(R.id.etFoto);

        imageView  = (ImageView) findViewById(R.id.imageView);

        bRegister = (Button) findViewById(R.id.bRegister);
        buttonChoose = (Button) findViewById(R.id.buttonChoose);

        //Seleccionamos foto
        buttonChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //seleccionamos iamgen
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Selecciona imagen"), PICK_IMAGE_REQUEST);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String Image = getStringImage(bitmap);
                final String Nombre = etNombre.getText().toString();
                final String ApellPat = etApellPat.getText().toString();
                final String ApellMat = etApellMat.getText().toString();
                final String Direccion = etDireccion.getText().toString();
                final String Telefono = etTelefono.getText().toString();
                final String Email = etEmail.getText().toString();
                //final String Foto = etFoto.getText().toString();


                /*final int age = Integer.parseInt(etAge.getText().toString());
                final String password = etPassword.getText().toString();*/

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterGerente.this);
                                builder.setMessage("Registro insertado!")
                                        .setNegativeButton("Cerarr", null)
                                        .create()
                                        .show();

                                //Limpiamos campos
                                imageView.setImageResource(0);
                                etNombre.getText().clear();
                                etApellPat.getText().clear();
                                etApellMat.getText().clear();
                                etDireccion.getText().clear();
                                etTelefono.getText().clear();
                                etEmail.getText().clear();
                                //startActivity(new Intent(RegisterGerente.this, PanelAdmin.class));
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterGerente.this);
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

                RegisterRequestGerentes registerRequest = new RegisterRequestGerentes(Image, Nombre,ApellPat ,ApellMat , Direccion, Telefono, Email, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterGerente.this);
                queue.add(registerRequest);
            }
        });

    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
    //pasamos la imagen seleccionada a imageView para mostrarla en el formulario
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                //Getting the Bitmap from Gallery
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //Setting the Bitmap to ImageView
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
