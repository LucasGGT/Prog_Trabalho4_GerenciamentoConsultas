package com.example.gerenciamentoconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionarPacienteActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etNome;
    EditText etLogr;
    EditText etNum;
    EditText etCid;
    EditText etCel;
    EditText etFixo;
    Spinner spGrp;
    Spinner spUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_paciente);

        etNome = findViewById(R.id.etAddPacNome);
        spGrp = findViewById(R.id.spAddPacGrp);
        etLogr = findViewById(R.id.etAddPacLogr);
        etNum = findViewById(R.id.etAddPacNum);
        etCid = findViewById(R.id.etAddPacCid);
        etCel = findViewById(R.id.etAddPacCel);
        etFixo = findViewById(R.id.etAddPacFixo);
        spUf = findViewById(R.id.spAddPacUf);

        String[] ufs = new String[] {
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
                "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
                "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };

        ArrayAdapter<String> spArrayAdapterUf =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ufs);
        spUf.setAdapter(spArrayAdapterUf);

        String[] grps = new String[] {
                "A", "B", "AB", "O"
        };

        ArrayAdapter<String> spArrayAdapterGrp =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, grps);
        spGrp.setAdapter(spArrayAdapterGrp);

        Button clickAdicionar = findViewById(R.id.btnAdicionarPaciente);
        clickAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD();
            }
        });
    }

    private void salvarBD () {
        String nome = etNome.getText().toString().trim();
        String logr = etLogr.getText().toString().trim();
        String num = etNum.getText().toString().trim();
        String cid = etCid.getText().toString().trim();
        String cel = etCel.getText().toString().trim();
        String fixo = etFixo.getText().toString().trim();

        if(nome.equals("") || logr.equals("") || num.equals("") ||
                cid.equals("") || cel.equals("") || fixo.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe todos os dados corretamente!", Toast.LENGTH_LONG).show();
        } else {
            db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
            StringBuilder sql = new StringBuilder();
            String grp = spGrp.getSelectedItem().toString();
            String uf= spUf.getSelectedItem().toString();
            sql.append("INSERT INTO paciente(nome, grp_sanguineo, logradouro, numero, cidade, uf, celular, fixo) VALUES (");
            sql.append("'" + nome + "', ");
            sql.append("'" + grp + "', ");
            sql.append("'" + logr + "', ");
            sql.append(num + ", ");
            sql.append("'" + cid + "', ");
            sql.append("'" + uf + "', ");
            sql.append("'" + cel + "', ");
            sql.append("'" + fixo + "'");
            sql.append(");");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Paciente inserido", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            etNome.setText("");
            spGrp.setSelection(0);
            etLogr.setText("");
            etNum.setText("");
            etCid.setText("");
            etCel.setText("");
            etFixo.setText("");
            spUf.setSelection(0);

            db.close();
        }
    }
}
