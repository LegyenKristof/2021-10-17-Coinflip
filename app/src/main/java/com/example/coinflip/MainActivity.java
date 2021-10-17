package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imgView1;
    Button btn1, btn2;
    TextView textView1, textView2, textView3;
    Random rnd;
    int dobasok, gyozelem, vereseg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobasok++;
                boolean fej = rnd.nextBoolean();
                Context context = getApplicationContext();
                String dobasEredmeny = "";
                int duration = Toast.LENGTH_SHORT;
                textView1.setText("Dobások: " + dobasok);

                if (fej){
                    gyozelem++;
                    imgView1.setImageResource(R.drawable.heads);
                    dobasEredmeny = "FEJ";
                    Toast.makeText(context, dobasEredmeny, duration).show();
                    textView2.setText("Győzelem: " + gyozelem);
                }
                else{
                    vereseg++;
                    imgView1.setImageResource(R.drawable.tails);
                    dobasEredmeny = "ÍRÁS";
                    Toast.makeText(context, dobasEredmeny, duration).show();
                    textView3.setText("Vereség: " + vereseg);
                }

                if (dobasok == 5){
                    jatekVege();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dobasok++;
                boolean fej = rnd.nextBoolean();
                Context context = getApplicationContext();
                String dobasEredmeny = "";
                int duration = Toast.LENGTH_SHORT;
                textView1.setText("Dobások: " + dobasok);

                if (fej){
                    vereseg++;
                    imgView1.setImageResource(R.drawable.heads);
                    dobasEredmeny = "FEJ";
                    Toast.makeText(context, dobasEredmeny, duration).show();
                    textView3.setText("Vereség: " + vereseg);
                }
                else{
                    gyozelem++;
                    imgView1.setImageResource(R.drawable.tails);
                    dobasEredmeny = "ÍRÁS";
                    Toast.makeText(context, dobasEredmeny, duration).show();
                    textView2.setText("Győzelem: " + gyozelem);
                }

                if (dobasok == 5){
                    jatekVege();
                }
            }
        });
    }

    private void jatekVege(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        if (gyozelem > vereseg){
            builder.setTitle("Győzelem");
        }
        else{
            builder.setTitle("Vereség");
        }
        builder.setMessage("Szeretne új játékot játszani?");
        builder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                init();
            }
        });
        builder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                System.exit(1);
            }
        });
        builder.setCancelable(false);
        builder.create().show();
    }

    private void init(){
        imgView1 = findViewById(R.id.imgView1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        rnd = new Random();
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
        textView1.setText("Dobások: 0");
        textView2.setText("Győzelem: 0");
        textView3.setText("Vereség: 0");
        imgView1.setImageResource(R.drawable.heads);
    }
}