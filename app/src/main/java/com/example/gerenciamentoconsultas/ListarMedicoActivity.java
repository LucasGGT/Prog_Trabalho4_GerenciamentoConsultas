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

public class ListarMedicoActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvMedic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_medico);

        lvMedic = findViewById(R.id.lvMedic);

        listarMedicos();

        lvMedic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lvMedic.getChildAt(position);
                TextView tvListMedId = v.findViewById(R.id.tvListMedId);
                TextView tvListMedNome = v.findViewById(R.id.tvListMedNome);
                TextView tvListMedCrm = v.findViewById(R.id.tvListMedCrm);
                TextView tvListMedLogr = v.findViewById(R.id.tvListMedLogr);
                TextView tvListMedNum = v.findViewById(R.id.tvListMedNum);
                TextView tvListMedCid = v.findViewById(R.id.tvListMedCid);
                TextView tvListMedUf = v.findViewById(R.id.tvListMedUf);
                TextView tvListMedCel = v.findViewById(R.id.tvListMedCel);
                TextView tvListMedFixo = v.findViewById(R.id.tvListMedFixo);

                Intent i = new Intent(getApplicationContext(), EditarMedicoActivity.class);
                i.putExtra("id", tvListMedId.getText().toString());
                i.putExtra("nome", tvListMedNome.getText().toString());
                i.putExtra("idade", tvListMedCrm.getText().toString());
                i.putExtra("curso", tvListMedLogr.getText().toString());
                i.putExtra("id", tvListMedNum.getText().toString());
                i.putExtra("nome", tvListMedCid.getText().toString());
                i.putExtra("idade", tvListMedUf.getText().toString());
                i.putExtra("curso", tvListMedCel.getText().toString());
                i.putExtra("curso", tvListMedFixo.getText().toString());

                startActivity(i);
            }
        });
    }

    private void listarMedicos () {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM medico;");
        Cursor dados = db.rawQuery(sql.toString(), null);
        String[] from = {"_id", "nome", "crm", "logradouro", "numero", "cidade", "uf", "celular", "fixo"};
        int[] to = {R.id.tvListMedId, R.id.tvListMedNome, R.id.tvListMedCrm, R.id.tvListMedLogr, R.id.tvListMedNum,
                    R.id.tvListMedCid, R.id.tvListMedUf, R.id.tvListMedCel, R.id.tvListMedFixo};

        SimpleCursorAdapter scAdapter =
                new SimpleCursorAdapter(getApplicationContext(), R.layout.dados, dados, from, to, 0);

        lvMedic.setAdapter(scAdapter);
        db.close();
    }
}
