package hyard.r.dofus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import org.w3c.dom.Text;
import java.util.ArrayList;

/**
 * Created by admin on 29/05/2018.
 */

public class DetailMetierActivity extends AppCompatActivity {


    /**
     * Variables images
     */
    private Integer[] imgMetierIds = {
            R.drawable.alchimiste,
            R.drawable.bijoutier,
            R.drawable.bricoleur,
            R.drawable.bucheron,
            R.drawable.chasseur,
            R.drawable.cordomage,
            R.drawable.cordonnier,
            R.drawable.costumage,
            R.drawable.facomage,
            R.drawable.faconneur,
            R.drawable.forgemage,
            R.drawable.forgeron,
            R.drawable.joaillomage,
            R.drawable.mineur,
            R.drawable.paysan,
            R.drawable.pecheur,
            R.drawable.sculptemage,
            R.drawable.sculpteur,
            R.drawable.tailleur,

    };

    ImageView imageMetierIHM;
    TextView nomMetierIHM;
    TextView descriptionMetierIHM;
    TextView levelMetierIHM;
    ArrayList<String> lesMetiers = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailmetier);
        GestionBD sgbd = new GestionBD(this);
        sgbd.open();
        lesMetiers = sgbd.donneLesMetiers();
        Intent intent = getIntent();
        int laPosition = intent.getIntExtra("leChoix", 0);
        //Toast.makeText(this, " le choix : "+laPosition, Toast.LENGTH_LONG).show();
        Log.i("detail","la position : "+laPosition);
        String metierChoisi =lesMetiers.get(laPosition);

        Metier lechoixmetier = sgbd.donneUnMetier(metierChoisi);
        imageMetierIHM = (ImageView)findViewById(R.id.imageMetierIHM);
        imageMetierIHM.setImageResource(imgMetierIds[laPosition]);

        nomMetierIHM = (TextView)findViewById(R.id.nomMetierIHM);
        descriptionMetierIHM = (TextView)findViewById(R.id.descriptionMetierIHM);
        levelMetierIHM = (TextView)findViewById(R.id.levelMetierIHM);

        /**
         * SET d'affichage
         */
        nomMetierIHM.setText(lechoixmetier.getNomMetier());
        descriptionMetierIHM.setText("Description :\n"+lechoixmetier.getDescriptionMetier());
        levelMetierIHM.setText("Disponible à partir du niveau "+lechoixmetier.getLevel());
    }
}
