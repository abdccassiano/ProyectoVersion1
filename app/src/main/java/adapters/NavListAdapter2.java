package adapters;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import models.NavItem2;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;

/**
 * Created by servus on 10/11/2016.
 */
public class NavListAdapter2 extends ArrayAdapter<NavItem2>{

    Context context;
    int resLayout;
    List<NavItem2> listNavItems;
    ArrayList<NavItem2> arrayList;

    public Context thisContext;

    public NavListAdapter2(Context context, int resLayout, List<NavItem2> apps) {
        super(context, resLayout, apps);

        this.context = context;
        this.resLayout = resLayout;
        this.listNavItems = apps;

        //Añadimos la lista de arreglos a un arreglo para hacer la busqueda
        arrayList = new ArrayList<NavItem2>();
        arrayList.addAll(listNavItems);
    }

    @SuppressLint("ViewHolder") @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = View.inflate(context, resLayout, null);


        Button buttonEdit = (Button) v.findViewById(R.id.buttonEdit);
        Button buttonDelete = (Button) v.findViewById(R.id.buttonDelete);

        TextView gerenteid = (TextView) v.findViewById(R.id.gerenteid);
        TextView nombre  = (TextView) v.findViewById(R.id.nombre);
        TextView direccion = (TextView) v.findViewById(R.id.direccion);
        TextView telefono = (TextView) v.findViewById(R.id.telefono);
        TextView email = (TextView) v.findViewById(R.id.email);
        ImageView navIcon =  (ImageView) v.findViewById(R.id.nav_icon);

        final NavItem2 navItem = listNavItems.get(position);

        gerenteid.setText(navItem.getGerenteId());
        nombre.setText(navItem.getNombre());
        direccion.setText(navItem.getDireccion());
        telefono.setText(navItem.getTelefono());
        email.setText(navItem.getEmail());
        navIcon.setImageResource(navItem.getResIcon());

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"Gerente id: "+navItem.getGerenteId(),Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("¿Quiere Eliminar al Usuario?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getContext(), "OK", Toast.LENGTH_LONG).show();
                            }
                        });
                builder.setNegativeButton("Cancela", null)
                        .create()
                        .show();

            }
        });


        return v;
    }

    //Buscador para la consulta
    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());

        listNavItems.clear();
        if (charText.length() == 0) {
            listNavItems.addAll(arrayList);

        } else {
            for (NavItem2 postDetail : arrayList) {
                if (charText.length() != 0 && postDetail.getNombre().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listNavItems.add(postDetail);
                }

                else if (charText.length() != 0 && postDetail.getNombre().toLowerCase(Locale.getDefault()).contains(charText)) {
                    listNavItems.add(postDetail);
                }
            }
        }
        notifyDataSetChanged();
    }


}
