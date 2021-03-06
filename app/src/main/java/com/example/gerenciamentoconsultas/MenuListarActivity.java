package com.example.gerenciamentoconsultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuListarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_listar);

        Button clickListMed = findViewById(R.id.btnListMedico);
        clickListMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
                startActivity(i);
            }
        });

        Button clickListPac = findViewById(R.id.btnListPaciente);
        clickListPac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ListarPacienteActivity.class);
                startActivity(i);
            }
        });
    }
}
