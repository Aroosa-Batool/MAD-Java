package com.example.lab5storages;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    char [] d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        d = new char[20480*1024];
        for( int i=0; i<d.length; i++)
        {
            d[i] = 'M';
        }
        String data = new String(d);

        Button internal, external, cache;
        internal = findViewById(R.id.internalBtn);
        external = findViewById(R.id.externalBtn);
        cache = findViewById(R.id.cacheBtn);

        external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    File dir =

                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);

                    File myphoto = new File(dir, "myselfie.txt");
                    FileWriter fw = new FileWriter(myphoto);
                    fw.write(data);
                    fw.close();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        cache.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    File dir = getCacheDir();
                    File f = new File(dir,"mycachefile.txt");
                    FileWriter fw = new FileWriter(f);
                    for(int i=0; i<5; i++)
                        fw.write(data);
                    fw.close();
                }
                catch (Exception e)
                {

                    e.printStackTrace();
                }

            }
        });

        internal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {

                            File dir = getFilesDir();
                            File internalFile = new File(dir,

                                    "myinternalfile.txt");

                            FileWriter fw = new FileWriter(internalFile);
                            for(int i=0; i<10; i++)
                                fw.write(data);
                            fw.close();
                        }
                        catch(Exception e)
                        {
                            e.printStackTrace();
                        }

                    }
                });
                t.start();

            }
        });

    }
}