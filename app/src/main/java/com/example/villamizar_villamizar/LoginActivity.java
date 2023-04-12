package com.example.villamizar_villamizar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail, etPassword;
    private SharedPreferences misPreferencias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        referenciar();
        misPreferencias = getSharedPreferences("tienda_app", MODE_PRIVATE);
        //verificar sí está logueado
        if(misPreferencias.getBoolean("logueado", false)){
            Intent myIntent  = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            finish();
    }}
    private  void referenciar(){
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
    }
    public void clickIniciarSesion (View view){
        String PASS = "123456";
        String USER = "Camila";
        String passUser = etPassword.getText().toString();
        String userUSer = etEmail.getText().toString();
        if (PASS.equals(passUser)&&USER.equals(userUSer)){
            SharedPreferences.Editor myEditor =  misPreferencias.edit();
            Intent myIntent  = new Intent(this, MainActivity.class);
            startActivity(myIntent);
            finish();
            myEditor.putBoolean("logueado", true);
            myEditor.apply();
        }else{
            Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show();
        }
    }
}