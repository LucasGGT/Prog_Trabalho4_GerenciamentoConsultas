package com.example.gerenciamentoconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AdicionarConsultaActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText spPac;
    EditText spMed;
    EditText etDatIni;
    EditText etDatFim;
    EditText etObs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_consulta);

        etDatIni = findViewById(R.id.etAddConDatIni);
        etDatFim = findViewById(R.id.etAddConDatFim);
        etObs = findViewById(R.id.etAddConObs);
        spPac = findViewById(R.id.etAddConPac);
        spMed = findViewById(R.id.etAddConMed);

        Button clickAdicionar = findViewById(R.id.btnAdicionarConsulta);
        clickAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD();
            }
        });
    }

    private void salvarBD () {
        String datIni = etDatIni.getText().toString().trim();
        String datFim = etDatFim.getText().toString().trim();
        String obs = etObs.getText().toString().trim();
        String pac = spPac.getText().toString().trim();
        String med = spMed.getText().toString().trim();

        if(datIni.equals("") || datFim.equals("") || obs.equals("") || pac.equals("") ||
                med.equals("")) {
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
