package hyard.r.dofus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by admin on 28/05/2018.
 */

public class ListeClasseActivity extends ListActivity implements AdapterView.OnItemClickListener {

    ArrayList<String>  lesValeurs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeclasse);
        Intent intent = getIntent();
        GestionBD sgbd = new GestionBD(this);
        sgbd.open();

        lesValeurs = sgbd.donneLesClasses();
        Log.i("liste classe","les noms : "+lesValeurs);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lesValeurs);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
        sgbd.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent imageclasse = new Intent(this, DetailClasseActivity.class);
        Log.i("clic","après avoir cliqué : "+position);
        //Toast.makeText(this, " position  : " + position , Toast.LENGTH_LONG).show();
        imageclasse.putExtra("leChoix", position);
        startActivity(imageclasse);
    }

}