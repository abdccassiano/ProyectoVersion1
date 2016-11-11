package proyectoversion1.app.abdccassiano.com.proyectoversion1;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import adapters.NavListAdapter;
import fragments.Gerente.MyHome;
import fragments.Gerente.Logout;
import models.NavItem;

public class PanelGerente extends ActionBarActivity{

    //LLamos a la clase Session
    private Session session;

    DrawerLayout drawerLayout;
    RelativeLayout drawerPane;
    ListView lvNav;

    List<NavItem> listNavItems;
    List<Fragment> listFragments;

    ActionBarDrawerToggle actionBarDrawerToggle;

    //Variable del panel de usuario
    private TextView tvEmailGerente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panel_gerente);

        //Se hace una instacia de Sesiones
        session = new Session(this);
        if (!session.loggedin()){
            logout();
        }

        //Recibimos los datos del LoginActivity que manda por Intent
        //Extraemos la variable email de session.prefs
        String email = session.prefs.getString("email", null);

        tvEmailGerente = (TextView)findViewById(R.id.tvEmailGerente);
        tvEmailGerente.setText(email);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerPane = (RelativeLayout) findViewById(R.id.drawer_pane);
        lvNav = (ListView) findViewById(R.id.nav_list);

        listNavItems = new ArrayList<NavItem>();
        listNavItems.add(new NavItem("Inicio", "Página de inicio",
                R.drawable.ic_action_home));
        listNavItems.add(new NavItem("Salir", "Salir del sistema",
                R.drawable.ic_action_logout));

        NavListAdapter navListAdapter = new NavListAdapter(
                getApplicationContext(), R.layout.item_nav_list, listNavItems);

        lvNav.setAdapter(navListAdapter);

        listFragments = new ArrayList<Fragment>();
        listFragments.add(new MyHome());
        listFragments.add(new Logout());

        // load first fragment as default:
        final FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.main_content, listFragments.get(0)).commit();

        setTitle(listNavItems.get(0).getTitle());
        lvNav.setItemChecked(0, true);
        drawerLayout.closeDrawer(drawerPane);

        // set listener for navigation items:
        lvNav.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //salimos del sistema
                if (id == 1){
                    //Toast.makeText(getApplication(),"Salir del sistema "+id,Toast.LENGTH_SHORT).show();
                    session.setLoggedin(false);
                    session.editor.clear();
                    session.editor.commit();

                    startActivity(new Intent(PanelGerente.this,LoginActivity.class));
                    finish();
                }

                // replace the fragment with the selection correspondingly:
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager
                        .beginTransaction()
                        .replace(R.id.main_content, listFragments.get(position))
                        .commit();

                setTitle(listNavItems.get(position).getTitle());
                lvNav.setItemChecked(position, true);
                drawerLayout.closeDrawer(drawerPane);

            }
        });

        // create listener for drawer layout
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_opened, R.string.drawer_closed) {

            @Override
            public void onDrawerOpened(View drawerView) {
                // TODO Auto-generated method stub
                invalidateOptionsMenu();
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                // TODO Auto-generated method stub
                invalidateOptionsMenu();
                super.onDrawerClosed(drawerView);
            }

        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the MyHome/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    public void logout(){

        session.setLoggedin(false);
        session.editor.clear();
        session.editor.commit();

        startActivity(new Intent(PanelGerente.this, LoginActivity.class));
        finish();
    }

}
