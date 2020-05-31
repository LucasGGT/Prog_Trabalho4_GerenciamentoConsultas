package com.example.gerenciamentoconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ListarPacienteActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvPacient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);

        lvPacient = findViewById(R.id.lvPacient);

        listarPacientes();

        lvPacient.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lvPacient.getChildAt(position);
                TextView tvListPacId = v.findViewById(R.id.tvListPacId);
                TextView tvListPacNome = v.findViewById(R.id.tvListPacNome);
                TextView tvListPacGrp = v.findViewById(R.id.tvListPacGrp);
                TextView tvListPacLogr = v.findViewById(R.id.tvListPacLogr);
                TextView tvListPacNum = v.findViewById(R.id.tvListPacNum);
                TextView tvListPacCid = v.findViewById(R.id.tvListPacCid);
                TextView tvListPacUf = v.findViewById(R.id.tvListPacUf);
                TextView tvListPacCel = v.findViewById(R.id.tvListPacCel);
                TextView tvListPacFixo = v.findViewById(R.id.tvListPacFixo);

                Intent i = new Intent(getApplicationContext(), EditarPacienteActivity.class);
                i.putExtra("id", tvListPacId.getText().toString());
                i.putExtra("nome", tvListPacNome.getText().toString());
                i.putExtra("grp", tvListPacGrp.getText().toString());
                i.putExtra("logr", tvListPacLogr.getText().toString());
                i.putExtra("num", tvListPacNum.getText().toString());
                i.putExtra("cid", tvListPacCid.getText().toString());
                i.putExtra("uf", tvListPacUf.getText().toString());
                i.putExtra("cel", tvListPacCel.getText().toString());
                i.putExtra("fixo", tvListPacFixo.getText().toString());

                startActivity(i);
            }
        });
    }

    private void listarPacientes () {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM paciente;");
        Cursor dados = db.rawQuery(sql.toString(), null);
        String[] from = {"_id", "nome", "grp_sanguineo", "logradouro", "numero", "cidade", "uf", "celular", "fixo"};
        int[] to = {R.id.tvListPacId, R.id.tvListPacNome, R.id.tvListPacGrp, R.id.tvListPacLogr, R.id.tvListPacNum,
                R.id.tvListPacCid, R.id.tvListPacUf, R.id.tvListPacCel, R.id.tvListPacFixo};

        SimpleCursorAdapter scAdapter =
                new SimpleCursorAdapter(getApplicationContext(), R.layout.dados_paciente, dados, from, to, 0);

        lvPacient.setAdapter(scAdapter);
        db.close();
    }
}
