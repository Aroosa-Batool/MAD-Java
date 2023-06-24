package com.example.messages;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    EditText et;
    ImageView refresh;
    ImageView send ;
    SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et = findViewById(R.id.etData);
        refresh = findViewById(R.id.ivrefresh);
        send = findViewById(R.id.ivsend);
        ImageView refresh = findViewById(R.id.ivrefresh);
        refresh.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                et.setText(" ");
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("************", "*************on Paused called");
// try{
// FileOutputStream fos = openFileOutput("mylocalfile.txt", MODE_PRIVATE);
// fos.write(et.getText().toString().getBytes());
// fos.close();
//
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }

        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Myowndata", et.getText().toString());
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();
        et = findViewById(R.id.etData);
// try{
// FileInputStream fin = openFileInput("mylocalfile.txt");
// BufferedReader br = new BufferedReader(new InputStreamReader(fin));
// String txt = br.readLine();
// et.setText(txt);
//
// }
// catch (Exception e)
// {
// e.printStackTrace();
// }

        pref = getSharedPreferences("Myowndata", MODE_PRIVATE);
        String txt = pref.getString("Myowndata", "nahi mila");
        et.setText(txt);
    }

//// ***************** shared preferences ************************

}