package com.example.ga8_220501096_aa2_ev01;

import android.content.Intent;
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

public class UserRegisterActivity2 extends AppCompatActivity {

    Button buttonRegister;
    EditText txtUser, txtPassword;
    TextView textViewLogin;

    SQLite_OpenHelper helper = new SQLite_OpenHelper(this, "usuarios", null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_user_register2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        buttonRegister = (Button) findViewById(R.id.button3);
        txtUser = (EditText) findViewById(R.id.editTextTextEmailAddress2);
        txtPassword = (EditText) findViewById(R.id.editTextTextPassword2);

        textViewLogin=(TextView)findViewById(R.id.textView7);

        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserRegisterActivity2.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            helper.abrir();
            helper.insertar(txtUser.getText().toString(), txtPassword.getText().toString());
            helper.cerrar();

            Toast.makeText(getApplicationContext(), "Registro almacenado con éxito", Toast.LENGTH_LONG).show();

            Intent intent = new Intent(UserRegisterActivity2.this, RegisterActivity.class);
            startActivity(intent);

            }
        });
        }
    }
