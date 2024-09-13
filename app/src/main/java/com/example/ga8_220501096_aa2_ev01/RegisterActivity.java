package com.example.ga8_220501096_aa2_ev01;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import OpenHelper.SQLite_OpenHelper;

public class RegisterActivity extends AppCompatActivity {

    TextView textViewRegister;
    Button buttonIngresar;

    SQLite_OpenHelper helper= new SQLite_OpenHelper(this, "usuarios.db", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
        textViewRegister=(TextView)findViewById(R.id.textView6);

        textViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, UserRegisterActivity2.class);
                startActivity(intent);
            }
        });

        buttonIngresar=(Button) findViewById(R.id.button2);
        buttonIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editTextTextEmailAddress = (EditText) findViewById(R.id.editTextTextEmailAddress);
                EditText editTextTextPassword = (EditText) findViewById(R.id.editTextTextPassword);

                try {
                    Cursor cursor = helper.validarUsuario(editTextTextEmailAddress.getText().toString(), editTextTextPassword.getText().toString());
                    if (cursor.getCount() > 0) {
                        Intent intent = new Intent(RegisterActivity.this, Principal.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), "Usuario o contraseña incorrectos", Toast.LENGTH_LONG).show();
                    }
                    editTextTextEmailAddress.setText("");
                    editTextTextPassword.setText("");
                    editTextTextEmailAddress.findFocus();

                } catch (SQLException e) {
                    e.printStackTrace();
                }


            }
        });



    }
}