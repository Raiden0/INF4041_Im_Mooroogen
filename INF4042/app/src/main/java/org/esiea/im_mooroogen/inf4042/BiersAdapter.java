package org.esiea.im_mooroogen.inf4042;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;

/**
 * Created by Daren on 18/10/2015.
 */
public class BiersAdapter extends RecyclerView.Adapter<BiersAdapter.BierHolder>{
    private JSONArray biers;

    public BiersAdapter(JSONArray biers) {
        this.biers = biers;
    }


    @Override
    public BiersAdapter.BierHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BiersAdapter.BierHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BierHolder extends RecyclerView.ViewHolder {

        public BierHolder(View itemView) {
            super(itemView);
        }
    }
}


