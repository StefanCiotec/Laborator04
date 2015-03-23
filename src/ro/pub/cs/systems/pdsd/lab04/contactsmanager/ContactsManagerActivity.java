package ro.pub.cs.systems.pdsd.lab04.contactsmanager;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

class BasicDetailsFragment extends Fragment {
	  @Override
	  public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle state) {
	    return layoutInflater.inflate(R.layout.fragment_basic_details, container, false);
	  }
	  public void onActivityCreated(Bundle savedInstanceState) {
		    super.onActivityCreated(savedInstanceState);
		    Button save = (Button) getActivity().findViewById(R.id.save);
		    final Button show = (Button) getActivity().findViewById(R.id.show);
		    save.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		        	Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
		        	intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
		        	EditText name = (EditText) getActivity().findViewById(R.id.name);
		        	if (name != null) {
		        	  intent.putExtra(ContactsContract.Intents.Insert.NAME, name.getText().toString());
		        	  
		        	}
		        	EditText phone = (EditText) getActivity().findViewById(R.id.number);
		        	if (phone != null) {
		        	  intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone.getText().toString());
		        	}
		        	EditText jobTitle = (EditText) getActivity().findViewById(R.id.job);
		        	if (jobTitle != null) {
		        	  intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle.getText().toString());
		        	}
		        	EditText company = (EditText) getActivity().findViewById(R.id.company);
		        	if (company != null) {
		        	  intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company.getText().toString());
		        	}

		        	getActivity().startActivity(intent);
		        	
		        }
		   
		      });
		    
		    show.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View view) {
		        	android.app.FragmentManager fragmentManager = getActivity().getFragmentManager();
		        	FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
		        	AdditionalDetailsFragment additionalDetailsFragment = (AdditionalDetailsFragment)fragmentManager.findFragmentById(R.id.containerBottom);
		        	if (additionalDetailsFragment == null) {
		        	  fragmentTransaction.add(R.id.containerBottom, new AdditionalDetailsFragment());
		        	  show.setText(getActivity().getResources().getString(R.string.hide_additional_fields));
		        	  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_ENTER_MASK);
		        	} else {
		        	  fragmentTransaction.remove(additionalDetailsFragment);
		        	  show.setText(getActivity().getResources().getString(R.string.show_additional_fields));
		        	  fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_EXIT_MASK);
		        	}
		        	fragmentTransaction.commit();
		        }
		    });
	  }
}

class AdditionalDetailsFragment extends Fragment {
	  @Override
	  public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle state) {
	    return layoutInflater.inflate(R.layout.fragment_additional_details, container, false);
	  }
}


public class ContactsManagerActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        android.app.FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.containerTop, new BasicDetailsFragment());
        fragmentTransaction.commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.contacts_manager, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

