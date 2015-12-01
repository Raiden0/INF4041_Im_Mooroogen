package org.esiea.im_mooroogen.application;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ObjectifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_objectif);
        //setTitle(R.string.app_name);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "DancingScript-Regular.ttf");
        TextView budget = (TextView) findViewById(R.id.textView);
        budget.setTypeface(typeface);

        TextView hist_btn = (TextView) findViewById(R.id.history_button);
        hist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHist = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intentHist);
            }
        });

        TextView addpaid_btn = (TextView) findViewById(R.id.add_button);
        addpaid_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHist = new Intent(getApplicationContext(), AddPaidActivity.class);
                startActivity(intentHist);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_objectif, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        /*TextView hist = (TextView) findViewById(R.id.history_button);
        hist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHist = new Intent(getApplicationContext(), HistoryActivity.class);
                startActivity(intentHist);
            }
        });*/

        return super.onOptionsItemSelected(item);
    }
}
