package hyard.r.dofus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by admin on 30/05/2018.
 */

public class DetailDofusActivity extends AppCompatActivity {

    /**
     * Variables images
     */
    private Integer[] imgDofusIds = {
            R.drawable.dofusebene,
            R.drawable.dofusemeraude,
            R.drawable.dofusivoire,
            R.drawable.dofusocre,
            R.drawable.dofuspourpre,
            R.drawable.dofusturquoise,
    };

    /**
     * Variables interface
     */
    ImageView imageDofusIHM;
    TextView nomDofusIHM;
    TextView levelDofusIHM;
    TextView descriptionDofusIHM;
    TextView effetsDofusIHM;
    TextView descripEffetIHM;


    ArrayList<String> lesDofus = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detaildofus);
        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesDofus = sgbd.donneLesDofus();
        Intent intent = getIntent();
        int laPosition = intent.getIntExtra("leChoix", 0);
        //Toast.makeText(this, " le choix : "+laPosition, Toast.LENGTH_LONG).show();
        Log.i("detail","la position : "+laPosition);
        String dofusChoisi =lesDofus.get(laPosition);

        Dofus lechoixdofus = sgbd.donneUnDofus(dofusChoisi);
        imageDofusIHM = (ImageView)findViewById(R.id.imageDofusIHM);
        imageDofusIHM.setImageResource(imgDofusIds[laPosition]);

        nomDofusIHM = (TextView)findViewById(R.id.nomDofusIHM);
        levelDofusIHM = (TextView)findViewById(R.id.levelDofusIHM) ;
        descriptionDofusIHM = (TextView)findViewById(R.id.descriptionDofusIHM);
        effetsDofusIHM = (TextView)findViewById(R.id.effetsDofusIHM);
        descripEffetIHM = (TextView)findViewById(R.id.descripEffetDofusIHM);

        /**
         * SET d'affichage
         */
        nomDofusIHM.setText(lechoixdofus.getNomDofus());
        levelDofusIHM.setText("Disponible à partir du niveau  "+Integer.toString(lechoixdofus.getLevelDofus()));
        descriptionDofusIHM.setText("Description :\n"+lechoixdofus.getDescriptionDofus());
        effetsDofusIHM.setText("Effet(s) du dofus : "+lechoixdofus.getEffetDofus());
        descripEffetIHM.setText("Description des effets du dofus : "+lechoixdofus.getDescripEffet());

    }
}
