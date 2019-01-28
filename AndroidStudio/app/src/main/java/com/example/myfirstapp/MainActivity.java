package com.example.myfirstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText textNombre;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textNombre = findViewById(R.id.textNombre);
        textView = findViewById(R.id.TextView1);
    }

    public void saludar(View view) {
        textView.setText("Hola, " + textNombre.getText());
    }
}
