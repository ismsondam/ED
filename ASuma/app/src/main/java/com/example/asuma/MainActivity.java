package com.example.asuma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText num1, num2;
    TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = findViewById(R.id.textNum1);
        num2 = findViewById(R.id.textNum2);
        textResultado = findViewById(R.id.textResultado);
    }

    public void sumar(View view) {
        num1.clearFocus();
        num2.clearFocus();

        float num1F;
        float num2F;
        try
        {
            num1F = Float.valueOf(num1.getText().toString());
            num2F = Float.valueOf(num2.getText().toString());
        }
        catch (Exception e)
        {
            Toast.makeText(this, "Debes introducir ambos números", Toast.LENGTH_SHORT).show();
            return;
        }

        float res = num1F + num2F;
        textResultado.setText(String.valueOf(res));
    }
}
