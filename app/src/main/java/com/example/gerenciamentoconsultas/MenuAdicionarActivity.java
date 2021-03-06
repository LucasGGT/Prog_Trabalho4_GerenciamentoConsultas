package com.example.gerenciamentoconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuAdicionarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_adicionar);

        Button clickAdicionarMed = findViewById(R.id.btnAddMedico);
        clickAdicionarMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdicionarMedicoActivity.class);
                startActivity(i);
            }
        });

        Button clickAdicionarCon = findViewById(R.id.btnAddConsulta);
        clickAdicionarCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdicionarConsultaActivity.class);
                startActivity(i);
            }
        });

        Button clickAdicionarPac = findViewById(R.id.btnAddPaciente);
        clickAdicionarPac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AdicionarPacienteActivity.class);
                startActivity(i);
            }
        });
    }
}
