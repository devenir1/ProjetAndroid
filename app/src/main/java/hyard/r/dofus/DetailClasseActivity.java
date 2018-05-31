package hyard.r.dofus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;

/**
 * Created by admin on 28/05/2018.
 */

public class DetailClasseActivity extends AppCompatActivity {

    /**
     * Variables images
     */
    private Integer[] imgClasseIds = {
            R.drawable.cra,
            R.drawable.ecaflip,
            R.drawable.eliotrope,
            R.drawable.eniripsa,
            R.drawable.enutrof,
            R.drawable.feca,
            R.drawable.huppermage,
            R.drawable.iop,
            R.drawable.osamodas,
            R.drawable.ouginak,
            R.drawable.pandawa,
            R.drawable.roublard,
            R.drawable.sacrieur,
            R.drawable.sadida,
            R.drawable.sram,
            R.drawable.steamer,
            R.drawable.xelor,
            R.drawable.zobal,
    };

    /**
     * Variables interface
     */
    ImageView imageClasseIHM;
    TextView nomClasseIHM;
    TextView difficulteClasseIHM;
    TextView descriptionClasseIHM;
    TextView dieuClasseIHM;

    ArrayList<String> lesNoms = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailclasse);
        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesNoms = sgbd.donneLesClasses();
        Intent intent = getIntent();
        int laPosition = intent.getIntExtra("leChoix", 0);
        //Toast.makeText(this, " le choix : "+laPosition, Toast.LENGTH_LONG).show();
        Log.i("detail","la position : "+laPosition);
        String classeChoisi =lesNoms.get(laPosition);

        ClasseD lechoix = sgbd.donneUneClasse(classeChoisi);
        imageClasseIHM = (ImageView)findViewById(R.id.imageClasseIHM);
        imageClasseIHM.setImageResource(imgClasseIds[laPosition]);

        nomClasseIHM = (TextView)findViewById(R.id.nomClasseIHM);
        difficulteClasseIHM = (TextView)findViewById(R.id.difficulteClasseIHM) ;
        descriptionClasseIHM = (TextView)findViewById(R.id.descriptionClasseIHM);
        dieuClasseIHM = (TextView)findViewById(R.id.dieuClasseIHM);

        /**
         * SET d'affichage
         */
        nomClasseIHM.setText(lechoix.getNom());
        difficulteClasseIHM.setText("Difficulté : "+Integer.toString(lechoix.getDifficulte())+" étoile(s)");
        descriptionClasseIHM.setText("Description :\n"+lechoix.getDescription());
        dieuClasseIHM.setText("Dieu prié : "+lechoix.getDieu());

    }
}

