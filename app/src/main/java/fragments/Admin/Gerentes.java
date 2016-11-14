package fragments.Admin;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import adapters.NavListAdapter2;
import models.NavItem2;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.AppController;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.LoginActivity;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.RegisterGerente;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.Session;


public class Gerentes extends Fragment{

	private Session session;

	// json array response url php
	private String urlJsonArryPHP = "http://websandroid.esy.es/AppAndroid/Gerentes/Consulta.php";

	private static String TAG = Gerentes.class.getSimpleName();

	// Progress dialog
	private ProgressDialog pDialog;

	private TextView txtResponse;

	ListView lvNav;

	List<NavItem2> listNavItems;

	// temporary string to show the parsed response
	private String jsonResponse;

	//Botton animado
	private FloatingActionButton btnFab;

	private SearchView searchView;

	//Variable para atrapar el contexto del fragment
	public Context thisContext;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.fragment_gerentes_admin, container, false);

		//Atrapamos contexto del activity
		thisContext = container.getContext();

		//Se hace una instacia de Sesiones
		session = new Session(thisContext);

		if (!session.loggedin()){
			logout();
		}

		searchView = (SearchView)v.findViewById(R.id.searView);

		lvNav = (ListView)v.findViewById(R.id.listviewResponse);

		//buscamos el botton animado
		btnFab = (FloatingActionButton) v.findViewById(R.id.btnFloatingAction);
		//Iniciamos función de animación del boton
		setupUI();

		pDialog = new ProgressDialog(getActivity());
		pDialog.setMessage("Porfavor esperar...");
		pDialog.setCancelable(false);

		makeJsonArrayPHPRequest();

		return v;
	}

	private void setupUI() {

		btnFab.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				//Toast.makeText(getActivity(), "Hello FAB!", Toast.LENGTH_SHORT).show();

				startActivity(new Intent(getActivity(), RegisterGerente.class));

				// TODO issue: Rotate animation in pre-lollipop works only once, issue to be resolved!
               /* if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    rotateAnimation.setDuration(500);
                    rotateAnimation.setFillAfter(true);
                    rotateAnimation.setInterpolator(new FastOutSlowInInterpolator());
                    btnFab.startAnimation(rotateAnimation);
                } else {
                    btnFab.clearAnimation();
                    ViewPropertyAnimatorCompat animatorCompat = ViewCompat.animate(btnFab);
                    animatorCompat.setDuration(500);
                    animatorCompat.setInterpolator(new FastOutSlowInInterpolator());
                    animatorCompat.rotation(180);
                    animatorCompat.start();
                }*/
			}
		});
	}

	//Metodos para el menu

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getActivity().getMenuInflater();
		inflater.inflate(R.menu.main_button_animation, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		/*if (id == R.id.action_settings) {
			Toast.makeText(getContext(),"Bien Abd",Toast.LENGTH_SHORT).show();
			//return true;
		}else if (id == R.id.action_example){
			//Toast.makeText(getContext(),"Bien Abi",Toast.LENGTH_SHORT).show();
			startActivity(new Intent(getActivity(), RegisterGerente.class));
		}*/

		return super.onOptionsItemSelected(item);
	}


	//Conexión a un archivo PHP para hacer la consulta
	private void makeJsonArrayPHPRequest() {

		showpDialog();

		JsonArrayRequest req = new JsonArrayRequest(urlJsonArryPHP,
				new Response.Listener<JSONArray>() {
					@Override
					public void onResponse(JSONArray response) {
						Log.d(TAG, response.toString());

						try {
							// Parsing json array response
							// loop through each json object
							//jsonResponse = "";
							listNavItems = new ArrayList<NavItem2>();
							for (int i = 0; i < response.length(); i++) {

								JSONObject person = (JSONObject) response.get(i);

								String gerenteId = person.getString("gerenteId");
								String nombre = person.getString("nombre");
								String apellidoPaterno = person.getString("apellidoPaterno");
								String apellidoMaterno = person.getString("apellidoMaterno");
								String direccion = person.getString("direccion");
								String telefono = person.getString("telefono");
								String email = person.getString("email");

								String nombreCompleto = nombre+" "+apellidoPaterno+" "+apellidoMaterno;

								listNavItems.add(new NavItem2(gerenteId,nombreCompleto,direccion,telefono,email
										,R.drawable.user));

                                /*jsonResponse += "GerenteId: " + gerenteId + "\n\n";
                                jsonResponse += "Nombre: " + nombre + "\n\n";
                                jsonResponse += "Paterno: " + apellidoPaterno + "\n\n";
                                jsonResponse += "Materno: " + apellidoMaterno + "\n\n\n";*/

							}
							final NavListAdapter2 navListAdapter2 = new NavListAdapter2(
									getActivity(), R.layout.item_nav_list_consulta, listNavItems);

							lvNav.setAdapter(navListAdapter2);

							//LLamos a traer el arreglo para hacer una busqueda

							searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
								@Override
								public boolean onQueryTextSubmit(String query) {
									return false;
								}

								@Override
								public boolean onQueryTextChange(String searchquery) {
									navListAdapter2.filter(searchquery.toString().trim());
									lvNav.invalidate();
									return true;
								}
							});



							//txtResponse.setText(jsonResponse);

						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getContext(),
									"Error: " + e.getMessage(),
									Toast.LENGTH_LONG).show();
						}

						hidepDialog();
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				VolleyLog.d(TAG, "Error: " + error.getMessage());
				Toast.makeText(getContext(),
						error.getMessage(), Toast.LENGTH_SHORT).show();
				hidepDialog();
			}
		});

		// Adding request to request queue
		AppController.getInstance().addToRequestQueue(req);
	}

	private void showpDialog() {
		if (!pDialog.isShowing())
			pDialog.show();
	}

	private void hidepDialog() {
		if (pDialog.isShowing())
			pDialog.dismiss();
	}

	//función para salir
	public void logout(){

		session.setLoggedin(false);
		session.editor.clear();
		session.editor.commit();

		startActivity(new Intent(thisContext, LoginActivity.class));
		getActivity().finish();
	}
}
