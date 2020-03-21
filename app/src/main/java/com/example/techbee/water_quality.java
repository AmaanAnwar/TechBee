package com.example.techbee;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class water_quality extends AppCompatActivity {
    SeekBar seekBar;
    TextView textView;
    ImageView human,agri;
    Button refresh;
    String deviceName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_quality);
        seekBar=findViewById(R.id.seekBar);
        textView=findViewById(R.id.waterqualityindex);
        human=findViewById(R.id.imageView2);
        agri=findViewById(R.id.imageView3);
        refresh=findViewById(R.id.refresh);
        seekBar.setEnabled(false);
        Bundle extras = getIntent().getExtras();
          deviceName = extras.getString("water");
     //   Toast.makeText(getApplicationContext(),deviceName,Toast.LENGTH_SHORT).show();
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("Devices").child(deviceName).child("waterquality");
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String data=dataSnapshot.getValue().toString();
               //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_SHORT).show();
                int value=Integer.valueOf(data);
                seekBar.setProgress(value);
                textView.setText(data);

                if(value<70)
                {
                    human.setImageResource(R.drawable.notsuitable);
                }
                if(value<45)
                {
                    agri.setImageResource(R.drawable.notsuitable);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),databaseError.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),water_quality.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("water",deviceName);
                startActivity(intent);
            }
        });
    }
}
