package com.example.instagramtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText pPower,pSpeed,kPower,kSpeed,fName;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pPower = findViewById(R.id.pPower);
        pSpeed = findViewById(R.id.pSpeed);
        kPower = findViewById(R.id.kPower);
        kSpeed = findViewById(R.id.kSpeed);
        fName = findViewById(R.id.fName);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(MainActivity.this);



    }
//   public void helloWorldTapped(View view) {
//        ParseObject boxer = new ParseObject("Boxer");
//        boxer.put("punchspeed", 200);
//        boxer.put("Power", 55);
//        boxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null) {
//                  //  Toast.makeText(MainActivity.this,"Good boy!",Toast.LENGTH_LONG).show();
//                } else {
//                    Log.i("Instagram",e + " Is the ERRor!!");
//                }
//            }
//        });
//        final ParseObject kickBoxer = new ParseObject("KickBoxer");
//        kickBoxer.put("name","Mikeo");
//        kickBoxer.put("punchSpeed", 6718);
//        kickBoxer.put("kickSpeed", 81988);
//        kickBoxer.put("kickPower", 1988);
//
//        kickBoxer.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null) {
//                    Toast.makeText(MainActivity.this,kickBoxer.get("name") + " saved to Server!",Toast.LENGTH_LONG).show();
//                } else {
//                    Log.i("Instagram",e + " Is the ERRor!!");
//                }
//            }
//        });
//
//    }

    @Override
    public void onClick(View v) {

        // Log.i("TST",pPower.getText() + " heu diude!");
        try {
            final ParseObject fighter = new ParseObject("Fighter");
            fighter.put("fName", fName.getText().toString());
            fighter.put("punchSpeed", Integer.parseInt(pSpeed.getText().toString()));
            fighter.put("punchPower", Integer.parseInt(pPower.getText().toString()));
            fighter.put("kickPower", Integer.parseInt(kSpeed.getText().toString()));
            fighter.put("kickSpeed", Integer.parseInt(kPower.getText().toString()));

            fighter.saveInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {

                    if (e == null) {
                        FancyToast.makeText(MainActivity.this, fighter.get("fName") + " is saved to Server!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        //Toast.makeText(MainActivity.this,fighter.get("firstName") + " saved to Server!",Toast.LENGTH_LONG).show();
                    } else {
                        FancyToast.makeText(MainActivity.this, fighter.get("fName") + " is saved to Server!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        // Toast.makeText(MainActivity.this,e.getMessage() + " os your error!",Toast.LENGTH_LONG).show();
                        //Log.i("Instagram",e + " Is the ERRor!!");
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }

//    public void submitUserData(View v) {
//        Log.i("TST",fName.getText() + "  is the text");
//        final ParseObject fighter = new ParseObject("Fighter");
//        fighter.put("FirstName",fName.getText().toString());
//
//        fighter.saveInBackground(new SaveCallback() {
//            @Override
//            public void done(ParseException e) {
//
//                if (e == null) {
//                    Toast.makeText(MainActivity.this,fighter.get("name") + " saved to Server!",Toast.LENGTH_LONG).show();
//                } else {
//                    Log.i("Instagram",e + " Is the ERRor!!");
//                }
//            }
//        });
//
//        }

}
