package com.example.techbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class activity_2 extends AppCompatActivity {
Button waterq,graph,waterflow,waterlevel,candsupp;
String deviceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        waterq=findViewById(R.id.button2);
        graph=findViewById(R.id.button3);
        waterflow=findViewById(R.id.button4);
        waterlevel=findViewById(R.id.button5);
        candsupp=findViewById(R.id.button6);
        Bundle extras = getIntent().getExtras();
        deviceName = extras.getString("device name");
        Toast.makeText(getApplicationContext(),deviceName,Toast.LENGTH_SHORT).show();
        waterq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_2.this,water_quality.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("water",deviceName);
                startActivity(intent);
            }
        });

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_2.this,graph.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("graph",deviceName);
                startActivity(intent);
            }
        });
        waterflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_2.this,water_flow.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("flow",deviceName);
                startActivity(intent);
            }
        });
        waterlevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_2.this,water_level.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("level",deviceName);
                startActivity(intent);

            }
        });
        candsupp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity_2.this,contact.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("contact",deviceName);
                startActivity(intent);
            }
        });
    }
}
