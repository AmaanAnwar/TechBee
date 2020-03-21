package com.example.techbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
Spinner spin;
String [] country= { "Select Device","Device 1", "Device 2 ", "Device 3 "};
ArrayAdapter aa;
Button button;

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    HashMap<String,String> hashMap= new HashMap<>();
    hashMap.put("waterquality","30");
    hashMap.put("waterflow","sampleflow");
    hashMap.put("waterlevel","samplelevel");
    // Write a message to the database
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Devices");
    DatabaseReference devRef;
    for (String str:country)
    {
        if(!str.equals("Select Device"))
        {
            myRef.child(str);
            devRef=myRef.child(str);
            devRef.setValue(hashMap);
        }
    }

       spin=findViewById(R.id.spinner);
       try {
            aa = new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, country);
       }
       catch (Exception e)
       {
           Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
       }
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);
        button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String device=spin.getSelectedItem().toString();
                if(!device.equals("Select Device"))
                {
                    Intent intent=new Intent(MainActivity.this,activity_2.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                  intent.putExtra("device name",device);
                startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid Device",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
