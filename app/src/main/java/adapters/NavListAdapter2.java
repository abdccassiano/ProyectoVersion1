package adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import models.NavItem2;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;

/**
 * Created by servus on 10/11/2016.
 */
public class NavListAdapter2 extends ArrayAdapter<NavItem2>{

    Context context;
    int resLayout;
    List<NavItem2> listNavItems;

    public NavListAdapter2(Context context, int resLayout, List<NavItem2> listNavItems) {
        super(context, resLayout, listNavItems);

        this.context = context;
        this.resLayout = resLayout;
        this.listNavItems = listNavItems;
    }

    @SuppressLint("ViewHolder") @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, resLayout, null);

        TextView gerenteid = (TextView) v.findViewById(R.id.gerenteid);
        TextView nombre  = (TextView) v.findViewById(R.id.nombre);
        TextView direccion = (TextView) v.findViewById(R.id.direccion);
        TextView telefono = (TextView) v.findViewById(R.id.telefono);
        TextView email = (TextView) v.findViewById(R.id.email);
        ImageView navIcon =  (ImageView) v.findViewById(R.id.nav_icon);

        NavItem2 navItem = listNavItems.get(position);

        gerenteid.setText(navItem.getGerenteId());
        nombre.setText(navItem.getNombre());
        direccion.setText(navItem.getDireccion());
        telefono.setText(navItem.getTelefono());
        email.setText(navItem.getEmail());
        navIcon.setImageResource(navItem.getResIcon());

        return v;
    }
}
