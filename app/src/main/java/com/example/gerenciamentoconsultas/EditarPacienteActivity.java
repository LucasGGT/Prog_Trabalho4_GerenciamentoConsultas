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

public class EditarPacienteActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_editar_paciente);

        etNome = findViewById(R.id.etEditPacNome);
        spGrp = findViewById(R.id.spEditPacGrp);
        etLogr = findViewById(R.id.etEditPacLogr);
        etNum = findViewById(R.id.etEditPacNum);
        etCid = findViewById(R.id.etEditPacCid);
        etCel = findViewById(R.id.etEditPacCel);
        etFixo = findViewById(R.id.etEditPacFixo);
        spUf = findViewById(R.id.spEditPacUf);

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

        Intent valores = getIntent();
        etNome.setText(valores.getStringExtra("nome"));
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

        String grpExtra = valores.getStringExtra("grp");
        aux = 0 ;
        for (String c : grps) {
            if (c.equals(grpExtra)) {
                break;
            }
            aux ++;
        }
        spGrp.setSelection(aux);

        final String id = valores.getStringExtra("id");

        Button clickEditar = findViewById(R.id.btnEditPac);
        clickEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD(id);
            }
        });

        Button clickExcluir = findViewById(R.id.btnDelPac);
        clickExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirBD(id);
            }
        });
    }

    private void salvarBD(String id) {
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
            String nomeUf = spUf.getSelectedItem().toString();
            sql.append("UPDATE paciente SET ");
            sql.append("nome = '" + nome + "', ");
            sql.append("grp_sanguineo = '" + grp + "', ");
            sql.append("logradouro = '" + logr + "', ");
            sql.append("numero = " + num + ", ");
            sql.append("cidade = '" + cid + "', ");
            sql.append("uf = '" + nomeUf + "', ");
            sql.append("celular = '" + cel + "', ");
            sql.append("fixo = '" + fixo + "' ");
            sql.append("WHERE _id = " + id + ";");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Paciente atualizado", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(getApplicationContext(), ListarPacienteActivity.class);
            startActivity(i);
            db.close();
        }
    }

    private void excluirBD(String id) {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM paciente ");
        sql.append("WHERE _id = " + id + ";");
        try {
            db.execSQL(sql.toString());
            Toast.makeText(getApplicationContext(), "Paciente excluído", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), ListarPacienteActivity.class);
        startActivity(i);
        db.close();
    }
}
