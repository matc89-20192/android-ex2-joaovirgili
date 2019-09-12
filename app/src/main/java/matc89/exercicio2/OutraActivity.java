package matc89.exercicio2;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OutraActivity extends AppCompatActivity {

    EditText editText;
    Button btnConfirmar;
    Button btnCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outra);

        this.editText = (EditText) findViewById(R.id.editText);
        this.btnConfirmar = (Button)findViewById(R.id.btnConfirmar);
        this.btnCancelar = (Button)findViewById(R.id.btnCancelar);

            checaPrefs();

        this.btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmarClick();
                finish();
            }
        });
        this.btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limapPrefs();
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    void limapPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.edit().remove("nome").apply();
    }

    void checaPrefs() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if (prefs.contains("nome")) {
            this.editText.setText(prefs.getString("nome", ""));
        }
    }

    void confirmarClick() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        String nome = this.editText.getText().toString();
        if (nome.isEmpty()) {
            prefs.edit().remove("nome").apply();
            return;
        }
        prefs.edit().putString("nome", nome).apply();
    }
}
