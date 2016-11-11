package fragments.Gerente;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;

public class MyHome extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_home_gerente, container, false);

		return v;
	}



}
