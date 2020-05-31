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

public class AdicionarMedicoActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etNome;
    EditText etCrm;
    EditText etLogr;
    EditText etNum;
    EditText etCid;
    EditText etCel;
    EditText etFixo;
    Spinner spUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_medico);

        etNome = findViewById(R.id.etNome);
        etCrm = findViewById(R.id.etCrm);
        etLogr = findViewById(R.id.etLogr);
        etNum = findViewById(R.id.etNum);
        etCid = findViewById(R.id.etCid);
        etCel = findViewById(R.id.etCel);
        etFixo = findViewById(R.id.etFixo);
        spUf = findViewById(R.id.spUf);

        String[] ufs = new String[] {
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
                "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
                "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };

        ArrayAdapter<String> spArrayAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ufs);
        spUf.setAdapter(spArrayAdapter);

        Button clickAdicionar = findViewById(R.id.btnAdicionarMedico);
        clickAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD();
            }
        });
    }

    private void salvarBD () {
        String nome = etNome.getText().toString().trim();
        String crm = etCrm.getText().toString().trim();
        String logr = etLogr.getText().toString().trim();
        String num = etNum.getText().toString().trim();
        String cid = etCid.getText().toString().trim();
        String cel = etCel.getText().toString().trim();
        String fixo = etFixo.getText().toString().trim();

        if(nome.equals("") || crm.equals("") || logr.equals("") || num.equals("") ||
           cid.equals("") || cel.equals("") || fixo.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe todos os dados corretamente!", Toast.LENGTH_LONG).show();
        } else {
            db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
            StringBuilder sql = new StringBuilder();
            String uf= spUf.getSelectedItem().toString();
            sql.append("INSERT INTO medico(nome, crm, logradouro, numero, cidade, uf, celular, fixo) VALUES (");
            sql.append("'" + nome + "', ");
            sql.append("'" + crm + "', ");
            sql.append("'" + logr + "', ");
            sql.append(num + ", ");
            sql.append("'" + cid + "', ");
            sql.append("'" + uf + "', ");
            sql.append("'" + cel + "', ");
            sql.append("'" + fixo + "'");
            sql.append(");");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Médico inserido", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            etNome.setText("");
            etCrm.setText("");
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
