package hyard.r.dofus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    Button boutonClasse;
    Button boutonMetier;
    Button boutonDofus;
    Button boutonCredit;
    GestionBD sgbd = new GestionBD(this);
    ArrayList<ClasseD> lesClasses = new ArrayList<ClasseD>();
    TraitementJSONClasseD traitJSONClasseD = new TraitementJSONClasseD(this);
    TraitementJSONMetier traitJSONMetier = new TraitementJSONMetier(this);
    TraitementJSONDofus traitJSONDofus = new TraitementJSONDofus(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sgbd.open();
        sgbd.supprimeClasse(); //supprime la classe si elle existait déjà
        sgbd.supprimeMetier();//supprime les métiers déjà affichés
        sgbd.supprimeDofus(); //supprime les dofus
        traitJSONClasseD.execute("https://projete4.000webhostapp.com/fichierJSONClasseD.json");
        traitJSONMetier.execute("https://projete4.000webhostapp.com/fichierJSONMetier.json");
        traitJSONDofus.execute("https://projete4.000webhostapp.com/fichierJSONDofus.json");
        sgbd.close();

        /*Lien vers la liste classe*/
        boutonClasse = (Button)findViewById(R.id.boutonClasse);
        boutonClasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allerListeClasse = new Intent(getApplicationContext(), ListeClasseActivity.class);
                startActivity(allerListeClasse);
            }
        });

        /*Lien vers la liste metier*/
        boutonMetier = (Button)findViewById(R.id.boutonMetier);
        boutonMetier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allerListeMetier = new Intent(getApplicationContext(), ListeMetierActivity.class);
                startActivity(allerListeMetier);
            }
        });

        /*Lien vers la liste dofus*/
        boutonDofus = (Button)findViewById(R.id.boutonDofus);
        boutonDofus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allerListeDofus = new Intent(getApplicationContext(), ListeDofusActivity.class);
                startActivity(allerListeDofus);
            }
        });

        /*Lien vers le Credit*/
        boutonCredit = (Button)findViewById(R.id.boutonCredit);
        boutonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allerCredit = new Intent(getApplicationContext(), CreditActivity.class);
                startActivity(allerCredit);
            }
        });

    }
}
