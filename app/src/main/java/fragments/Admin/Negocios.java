package fragments.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;

/**
 * Created by servus on 10/11/2016.
 */
public class Negocios extends Fragment{

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_negocios_admin, container, false);

        return v;
    }
}
