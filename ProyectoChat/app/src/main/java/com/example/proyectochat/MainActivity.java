package com.example.proyectochat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText textUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textUsuario = findViewById(R.id.textUsuario);
    }

    public void entrarChat(View view) {
        String usuario = textUsuario.getText().toString();
        if (usuario.isEmpty())
        {
            ShowToast("El nombre de usuario no puede estar vacío");
            return;
        }

        if (usuario.length() < 3)
        {
            ShowToast("El usuario debe tener un mínimo de 3 caracteres");
            return;
        }

        if (usuario.contains(" "))
        {
            ShowToast("El usuario no puede contener espacios");
            return;
        }

        if (!Character.isUpperCase(usuario.charAt(0)))
        {
            char[] arr = usuario.toCharArray();
            arr[0] = Character.toUpperCase(usuario.charAt(0));
            usuario = String.valueOf(arr);
        }

        Intent intent = new Intent(this, ActivityChat.class);
        intent.putExtra("usuario", usuario);
        startActivity(intent);
    }

    private void ShowToast(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
