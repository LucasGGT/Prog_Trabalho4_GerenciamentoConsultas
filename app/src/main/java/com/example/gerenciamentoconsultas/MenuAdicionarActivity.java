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

//        Button clickListar = findViewById(R.id.btnListar);
//        clickListar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getApplicationContext(), MenuListarActivity.class);
//                startActivity(i);
//            }
//        });
    }
}
