package fragments.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import proyectoversion1.app.abdccassiano.com.proyectoversion1.R;

public class MyHome extends Fragment{

	@Override
	public View onCreateView(LayoutInflater inflater,
							 @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_home_admin, container, false);

		/*Button button = (Button) v.findViewById(R.id.btn15);

		button.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast.makeText(getActivity(),"Diste Click",Toast.LENGTH_SHORT).show();
			}
		});*/

		return v;
	}





}
