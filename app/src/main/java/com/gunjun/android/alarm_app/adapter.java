package com.gunjun.android.alarm_app;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gunjun.android.alarm_app.models.Repo;

import java.util.ArrayList;

import io.realm.RealmResults;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public class adapter extends RecyclerView.Adapter<viewHolder>{
    private Context context;
    private RealmResults<Repo> mItems;

    public adapter(RealmResults<Repo> items, Context mContext){
        mItems  = items;
        context = mContext;
    }
    @Override
    public viewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        viewHolder holder = new viewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(viewHolder holder, int position) {
        holder.ownerHtmlUrl.setText(mItems.get(position).getOwner().getHtml_url());
        holder.ownerUrl.setText(mItems.get(position).getOwner().getUrl());
        holder.repoId.setText(Integer.toString(mItems.get(position).getId()));
        holder.repoName.setText(mItems.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
