package org.esiea.im_mooroogen.application;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class HistoryActivity extends AppCompatActivity {

    public static final String BIERS_UPDATE = "com.octip.cours.inf4042_11.BIERS_UPDATE";
    private static final String TAG = "TAG";

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private JSONArray mJsonArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        android.support.v7.app.ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        final Context context = getApplicationContext();
        TextView ddl_btn = (TextView) findViewById(R.id.telechargement);
        ddl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetTransaction.startActionGet(context);
            }
        });

        IntentFilter intentFilter = new IntentFilter(BIERS_UPDATE);
        LocalBroadcastManager.getInstance(this).registerReceiver(new TransactionUpdate(), intentFilter);

        mJsonArray = getTransactionFromFile();
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyAdapter(mJsonArray);
        mRecyclerView.setAdapter(mAdapter);
    }

    public class TransactionUpdate extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.d(TAG,getIntent().getAction());
            MyAdapter myAdapter = (MyAdapter) mRecyclerView.getAdapter();
            myAdapter.setNewTransaction(getTransactionFromFile());
        }
    }

    public JSONArray getTransactionFromFile() {
        try {
            InputStream is = new FileInputStream(getCacheDir()+"/"+"transaction.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new JSONArray(new String(buffer,"UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
            return new JSONArray();
        } catch (JSONException e) {
            e.printStackTrace();
            return new JSONArray();
        }
    }

    private class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

        private JSONArray jsonArray;



        public MyAdapter(JSONArray jsonArray) {
            this.jsonArray = jsonArray;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{
            private final TextView mTextView;
            public ViewHolder(View textView) {
                super(textView);
                mTextView = (TextView) textView.findViewById(R.id.rv_transaction_element_name);
            }
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_transaction_element, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            JSONObject jsonObject;
            try {
                jsonObject = (JSONObject) jsonArray.get(position);
                holder.mTextView.setText(jsonObject.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return jsonArray.length();
        }

        public void setNewTransaction (JSONArray update) {
            this.jsonArray = update;
            this.notifyDataSetChanged();
        }
    }


}

