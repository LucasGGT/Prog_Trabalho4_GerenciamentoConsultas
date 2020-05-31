package com.example.gerenciamentoconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditarMedicoActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_editar_medico);

        etNome = findViewById(R.id.etEditMedNome);
        etCrm = findViewById(R.id.etEditMedCrm);
        etLogr = findViewById(R.id.etEditMedLogr);
        etNum = findViewById(R.id.etEditMedNum);
        etCid = findViewById(R.id.etEditMedCid);
        etCel = findViewById(R.id.etEditMedCel);
        etFixo = findViewById(R.id.etEditMedFixo);
        spUf = findViewById(R.id.spEditMedUf);

        String[] ufs = new String[] {
                "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO",
                "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI",
                "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"
        };

        ArrayAdapter<String> spArrayAdapter =
                new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, ufs);
        spUf.setAdapter(spArrayAdapter);

        Intent valores = getIntent();
        etNome.setText(valores.getStringExtra("nome"));
        etCrm.setText(valores.getStringExtra("crm"));
        etLogr.setText(valores.getStringExtra("logr"));
        etNum.setText(valores.getStringExtra("num"));
        etCid.setText(valores.getStringExtra("cid"));
        etCel.setText(valores.getStringExtra("cel"));
        etFixo.setText(valores.getStringExtra("fixo"));

        String ufExtra = valores.getStringExtra("uf");
        int aux = 0 ;
        for (String c : ufs) {
            if (c.equals(ufExtra)) {
                break;
            }
            aux ++;
        }
        spUf.setSelection(aux);

        final String id = valores.getStringExtra("id");

        Button clickEditar = findViewById(R.id.btnEditMed);
        clickEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD(id);
            }
        });

        Button clickExcluir = findViewById(R.id.btnDelMed);
        clickExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirBD(id);
            }
        });
    }

    private void salvarBD(String id) {
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
            String nomeUf = spUf.getSelectedItem().toString();
            sql.append("UPDATE medico SET ");
            sql.append("nome = '" + nome + "', ");
            sql.append("crm = '" + crm + "', ");
            sql.append("logradouro = '" + logr + "', ");
            sql.append("numero = " + num + ", ");
            sql.append("cidade = '" + cid + "', ");
            sql.append("uf = '" + nomeUf + "', ");
            sql.append("celular = '" + cel + "', ");
            sql.append("fixo = '" + nomeUf + "' ");
            sql.append("WHERE _id = " + id + ";");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Médico atualizado", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
            startActivity(i);
            db.close();
        }
    }

    private void excluirBD(String id) {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM medico ");
        sql.append("WHERE _id = " + id + ";");
        try {
            db.execSQL(sql.toString());
            Toast.makeText(getApplicationContext(), "Médico excluído", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
        startActivity(i);
        db.close();
    }
}
