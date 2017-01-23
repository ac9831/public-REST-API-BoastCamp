package com.gunjun.android.alarm_app;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gunjunLee on 2017-01-23.
 */

public class viewHolder extends RecyclerView.ViewHolder{
    @BindView(R.id.repo_id) TextView repoId;
    @BindView(R.id.repo_name) TextView repoName;
    @BindView(R.id.owner_url) TextView ownerUrl;
    @BindView(R.id.owner_html_url) TextView ownerHtmlUrl;

    public viewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
