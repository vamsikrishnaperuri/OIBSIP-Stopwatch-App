package com.example.stopwatch;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextView timee;
    CardView crdst,crdsp,crdrs,exitcdview;
    private int sec=0;
    private  boolean running;
    private boolean pastrunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exitcdview=findViewById(R.id.exitcd);

        if(savedInstanceState!=null){
            sec = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            pastrunning=savedInstanceState.getBoolean("wasrunning");
        }
        runTimer();

        exitcdview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog= new AlertDialog.Builder(MainActivity.this);
                alertDialog.setTitle("Exit App");
                alertDialog.setMessage("Do you want to exit from the app");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                });
                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }

    private void runTimer() {
        final TextView timee = findViewById(R.id.textview1);
        final Handler handle = new Handler();
        handle.post(new Runnable() {
            @Override
            public void run() {
                int seconds = sec%60;
                int min = (sec%3600)/60;
                int hrs = sec/3600;

                String timmertimme = String.format(Locale.getDefault(),"%02d:%02d:%02d",hrs,min,seconds);
                timee.setText(timmertimme);
                if(running){sec++;}
                handle.postDelayed(this,1000);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        pastrunning=running;
        running=false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pastrunning){running=true;}
    }

    public void stoptimmer(View view) {
        running=false;
    }

    public void resettimmer(View view) {
        running=false;
        sec=0;

    }

    public void starttimmer(View view) {
        running=true;
    }
}