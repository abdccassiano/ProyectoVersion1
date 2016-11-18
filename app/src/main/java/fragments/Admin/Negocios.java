package fragments.Admin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proyectoversion1.app.abdccassiano.com.proyectoversion1.LoginActivity;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;
import proyectoversion1.app.abdccassiano.com.proyectoversion1.Session;

/**
 * Created by servus on 10/11/2016.
 */
public class Negocios extends Fragment {

    //LLamos a la clase Session
    private Session session;

    //Variable para atrapar el contexto del fragment
    public Context thisContext;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_negocios_admin, container, false);

        //Atrapamos contexto del activity
        thisContext = container.getContext();

        //Se hace una instacia de Sesiones
        session = new Session(thisContext);

        if (!session.loggedin()) {
            logout();
        }

        return v;
    }

    //función para salir
    public void logout() {

        session.setLoggedin(false);
        session.editor.clear();
        session.editor.commit();

        startActivity(new Intent(thisContext, LoginActivity.class));
        getActivity().finish();
    }
}
