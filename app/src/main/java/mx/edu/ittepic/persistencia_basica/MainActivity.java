package mx.edu.ittepic.persistencia_basica;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    final String EMAIL = "EMAIL";
    final String GENDER = "GENDER";
    final String CODE = "CODE";
    final String WRITE = "WRITE";
    final String JOGGE = "JOGGE";
    final String ZODIAC = "ZODIAC";

    Spinner sp;
    EditText edtEmail;
    Button btnSaveMe, btnGetMe;
    RadioButton rdoMale, rdoFemale;
    CheckBox chkCoding, chkWritting, chkjogging;
    String signo = "Sagitario";
    String [] signos = {"Piscis", "Cancer", "Leo", "Geminis", "Sagitario", "Capricornio", "Acuario",
            "Aries", "Tauro", "Escorpio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtEmail = findViewById(R.id.edtEmail);
        btnSaveMe = findViewById(R.id.btnSaveMe);
        btnGetMe = findViewById(R.id.btnGetMe);
        rdoMale = findViewById(R.id.rdoMale);
        rdoFemale = findViewById(R.id.rdoFemale);
        chkCoding = findViewById(R.id.chkCoding);
        chkWritting = findViewById(R.id.chkWritting);
        chkjogging = findViewById(R.id.chkJogging);

        sp = findViewById(R.id.spinner);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, signos));
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                signo = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    public void saveMe(View view) {
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(EMAIL, edtEmail.getText().toString());
        editor.putBoolean(GENDER, rdoMale.isChecked()?true:false);
        editor.putBoolean(CODE, chkCoding.isChecked()?true:false);
        editor.putBoolean(WRITE, chkWritting.isChecked()?true:false);
        editor.putBoolean(JOGGE, chkjogging.isChecked()?true:false);
        editor.putInt(ZODIAC, sp.getSelectedItemPosition());
        editor.commit();
    }

    public void getMe(View view){
        SharedPreferences editor = getPreferences(MODE_PRIVATE);
        edtEmail.setText(editor.getString(EMAIL,null));
        chkCoding.setChecked(editor.getBoolean(CODE, false));
        chkWritting.setChecked(editor.getBoolean(WRITE, false));
        chkjogging.setChecked(editor.getBoolean(JOGGE, false));
        rdoMale.setChecked(editor.getBoolean(GENDER, false));
        int i = editor.getInt(ZODIAC, 1);
        sp.setSelection(i);
    }
}
