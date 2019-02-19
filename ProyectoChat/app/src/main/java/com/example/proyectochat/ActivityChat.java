package com.example.proyectochat;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ActivityChat extends AppCompatActivity {
    List<String> addedDays;
    int lastIndex;

    FirebaseDatabase db;
    DatabaseReference currentRef;

    LinearLayout linearLayout;
    EditText msgText;
    Button btnSend;
    String usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        addedDays = new ArrayList<>();
        lastIndex = 0;

        db = FirebaseDatabase.getInstance();
        currentRef = db.getReference(GetDate());

        linearLayout = findViewById(R.id.linearLayout);
        msgText = findViewById(R.id.editText);
        btnSend = findViewById(R.id.buttonSend);
        usuario = getIntent().getStringExtra("usuario");

        if (usuario.isEmpty())
        {
            finish();
            System.exit(1);
        }
    }

    @Override
    public void onStart()
    {
        super.onStart();

        LoadMessages();
    }

    public void SendMessage(View view)
    {
        String msg = msgText.getText().toString();
        if (msg.isEmpty())
            return;

        btnSend.setEnabled(false);
        msg = BuildMessage(msg);
        SendToDatabase(msg);

        msgText.setText("");
    }

    private void SetupMessageListener() {
        ChildEventListener cListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                try {
                    AddNewDayToChat(dataSnapshot.getRef().getParent().getKey());

                    String msg = dataSnapshot.getValue(String.class);
                    AddMessageToChat(msg);

                    int index = Integer.valueOf(dataSnapshot.getKey());
                    if (index > lastIndex)
                        lastIndex = index;
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        currentRef.addChildEventListener(cListener);
    }

    private void LoadMessages()
    {
        DatabaseReference ref = db.getReference();
        ValueEventListener vListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot day : dataSnapshot.getChildren())
                {
                    if (day.getKey().equals(GetDate()))
                        continue;

                    AddNewDayToChat(day.getKey());
                    for (DataSnapshot msg : day.getChildren())
                        AddMessageToChat(msg.getValue(String.class));
                }

                SetupMessageListener();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        ref.orderByKey().addListenerForSingleValueEvent(vListener);
    }

    private void AddNewDayToChat(String day)
    {
        if (addedDays.indexOf(day) != -1)
            return;

        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.gravity = Gravity.BOTTOM;

        TextView newMsg = new TextView(this);
        newMsg.setLayoutParams(lparams);
        newMsg.setTextSize(25);
        newMsg.setText(day);
        linearLayout.addView(newMsg);

        addedDays.add(day);
    }

    private void AddMessageToChat(String msg)
    {
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.gravity = Gravity.BOTTOM;

        TextView newMsg = new TextView(this);
        newMsg.setLayoutParams(lparams);
        newMsg.setTextSize(20);
        newMsg.setText(msg);
        linearLayout.addView(newMsg);
    }

    private void SendToDatabase(String msg)
    {
        currentRef.child(String.valueOf(lastIndex + 1)).setValue(msg);
        btnSend.setEnabled(true);
    }

    private String BuildMessage(String msg)
    {
        String resultMsg = "";

        resultMsg += GetTime() + " - ";
        resultMsg += usuario + ": ";
        resultMsg += msg;

        return resultMsg;
    }

    private String GetDate()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatted = new SimpleDateFormat("dd-MM-yyyy", Locale.UK);
        return formatted.format(calendar.getTime());
    }

    private String GetTime()
    {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatted = new SimpleDateFormat("[HH:mm:ss]", Locale.UK);
        return formatted.format(calendar.getTime());
    }
}
