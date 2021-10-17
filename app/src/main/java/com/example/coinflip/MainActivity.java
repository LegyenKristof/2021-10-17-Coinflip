package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imgView1;
    private Button btn1, btn2;
    private TextView textView1, textView2, textView3;
    private Random rnd;
    private int dobasok, gyozelem, vereseg, animacioIndex;
    private boolean dobhatE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dobhatE){
                    dobhatE = false;
                    dobasAnimacio(true);
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dobhatE){
                    dobhatE = false;
                    dobasAnimacio(false);
                }
            }
        });
    }

    private void dobasAnimacio(boolean fejTipp){
        if (animacioIndex < 20){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (animacioIndex % 2 == 0){
                        imgView1.setImageResource(R.drawable.heads);
                    }
                    else{
                        imgView1.setImageResource(R.drawable.tails);
                    }
                    animacioIndex++;
                    dobasAnimacio(fejTipp);
                }
            }, 50);
        }
        else{
            animacioIndex = 0;
            dobas(fejTipp);
        }
    }

    private void dobas(boolean fejTipp){
        dobasok++;
        textView1.setText("Dobások: " + dobasok);
        boolean fej = rnd.nextBoolean();
        dobasEredmeny(fej);

        if (fej == fejTipp){
            gyozelem++;
            textView2.setText("Győzelem: " + gyozelem);
        }
        else{
            vereseg++;
            textView3.setText("Vereség: " + vereseg);
        }

        if (gyozelem >= 3 || vereseg >= 3){
            jatekVege();
        }
        else{
            dobhatE = true;
        }
    }

    private void dobasEredmeny(boolean fej){
        Context context = getApplicationContext();
        String dobasEredmeny;
        int duration = Toast.LENGTH_SHORT;

        if (fej){
            dobasEredmeny = "FEJ";
            imgView1.setImageResource(R.drawable.heads);
        }
        else{
            dobasEredmeny = "ÍRÁS";
            imgView1.setImageResource(R.drawable.tails);
        }
        Toast.makeText(context, dobasEredmeny, duration).show();
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
                ujJatek();
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

    private void ujJatek(){
        dobasok = 0;
        gyozelem = 0;
        vereseg = 0;
        textView1.setText("Dobások: 0");
        textView2.setText("Győzelem: 0");
        textView3.setText("Vereség: 0");
        imgView1.setImageResource(R.drawable.heads);
        dobhatE = true;
    }

    private void init(){
        imgView1 = findViewById(R.id.imgView1);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        rnd = new Random();
        dobhatE = true;
        animacioIndex = 0;
    }
}