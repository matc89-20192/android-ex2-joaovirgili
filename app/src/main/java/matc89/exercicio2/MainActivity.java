package matc89.exercicio2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView label;
    Button btnTrocar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.label = (TextView)findViewById(R.id.textView);
        this.btnTrocar = (Button)findViewById(R.id.btnTrocar);

        Log.d("create", "onCreate");

        this.btnTrocar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navegaParaOutraActivity();
            }
        });

        if (savedInstanceState != null) {
            checaPrefs();
        } else {
            this.clearPref();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        checaPrefs();
    }

    void navegaParaOutraActivity() {
        Intent intent = new Intent(this, OutraActivity.class);
        startActivity(intent);
    }

    void checaPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.contains("nome")) {
            this.label.setText("Oi, " + prefs.getString("nome", "")+ "!");
        } else {
            this.label.setText("Oi!");
        }
    }

    void clearPref() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().remove("nome").apply();
    }

}
