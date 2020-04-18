package com.example.instagramtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText pPower, pSpeed, kPower, kSpeed, fName;
    private Button btnSubmit, btnGetAllMMA, btnTransition;
    private TextView txtGetData;
    private String allMMAFighters;

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
        txtGetData = findViewById(R.id.txtGetData);
        btnGetAllMMA = findViewById(R.id.btnGetAllMMA);
        btnTransition = findViewById(R.id.btnNextActivity);

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("MMA");
                parseQuery.getInBackground("ZLqWDRoW07", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object != null && e == null) {
                            txtGetData.setText(object.get("fName") + "" + " Punch Power: " + object.get("punchPower"));
                        }
                    }
                });
            }
        });

        btnGetAllMMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allMMAFighters = "";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("MMA");
                // where punching power is greater than 20
                queryAll.whereGreaterThan("punchPower",20);
                //queryAll.whereGreaterThanOrEqualTo("punchPower",8);
                //queryAll.setLimit(1)
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if (e == null) {
                            if (objects.size() > 0) { // IOW: we have at least 1 item
                                // this for loop simply loops through the entire list of objects!
                                for (ParseObject mmaFighter : objects) {
                                    allMMAFighters = allMMAFighters + mmaFighter.get("fName") + ":" + mmaFighter.get("punchPower") + "\n";
                                }
                               // FancyToast.makeText(MainActivity.this, "Success Brother!", FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                               FancyToast.makeText(MainActivity.this, allMMAFighters, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();

                            } else {
                                FancyToast.makeText(MainActivity.this, e + " is has an issue!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                            }
                        }
                    }

                });
            }
        });
        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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

    @Override
    public void onClick(View v) {
        try {
            final ParseObject fighter = new ParseObject("MMA");
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
                        FancyToast.makeText(MainActivity.this, e + " is has an issue!", FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
                        // Toast.makeText(MainActivity.this,e.getMessage() + " os your error!",Toast.LENGTH_LONG).show();
                        //Log.i("Instagram",e + " Is the ERRor!!");
                    }
                }
            });
        } catch (Exception e) {
            FancyToast.makeText(MainActivity.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();

        }
    }
}
