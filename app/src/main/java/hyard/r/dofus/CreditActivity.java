package hyard.r.dofus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

/**
 * Created by admin on 31/05/2018.
 */

public class CreditActivity extends AppCompatActivity {

    TextView urlDofus;
    TextView urlDev;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        urlDofus = (TextView)findViewById(R.id.urlDofus);
        urlDev = (TextView)findViewById(R.id.urlDev);
        urlDofus.setText("https://www.dofus.com/fr");
        urlDev.setText("https://jenniferhyard.wordpress.com/");

    }
}
