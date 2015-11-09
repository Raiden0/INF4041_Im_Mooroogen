package org.esiea.im_mooroogen.inf4042;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcel;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.lang.Object;

public class MainActivity extends AppCompatActivity {

    DatePickerFragment dpd;
    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        TextView tv_hw = (TextView) findViewById(R.id.tv_hello_world);
        String str = getString(R.string.hello_world);
        tv_hw.setText(str);
        String now = DateUtils.formatDateTime(getApplicationContext(), (new Date()).getTime(), DateFormat.FULL);
        tv_hw.setText(now);
        tv_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog(v);
            }
        });

        TextView btn_hw = (TextView) findViewById(R.id.btn_hello_world);
        btn_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Une biscotte !", Toast.LENGTH_LONG).show();
                notification_text();
            }
        });

        dpd = new DatePickerFragment();

        GetBiersServices.startActionBiers(this);

        IntentFilter intentfilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new BierUpdate(), intentfilter);

        RecyclerView rv = (RecyclerView)findViewById(R.id.rv_biere);
        rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        TextView mn_hw = (TextView) findViewById(R.id.toast_menu_button);

        mn_hw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Une biscotte royale !", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), SecondeActivity.class);
                startActivity(intent);
            }
        });

        return super.onOptionsItemSelected(item);
    }

    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getFragmentManager(), "datePicker");
    }

    public void notification_text() {
        NotificationCompat.Builder nBuilder = new NotificationCompat.Builder(this);
        nBuilder.setSmallIcon(R.drawable.unknown).setContentTitle("Notification").setContentText("Yo World !");

        NotificationManager nManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.notify(444, nBuilder.build());
    }

    public class BierUpdate extends BroadcastReceiver {
        private static final String TAG = "lll";

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG, getIntent().getAction()); // prévoir une action de notification ici//
        }
    }

    public JSONArray getBiersFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir() + "/" + "bieres.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer, "UTF-8"));
        }catch (IOException e){
            e.printStackTrace();
            return new JSONArray();
        }catch(JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }
}



