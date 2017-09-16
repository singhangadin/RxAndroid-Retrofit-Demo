package com.github.angads25.rxjava_retrofitexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.angads25.rxjava_retrofitexample.model.GithubData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <p>
 * Created by Angad Singh on 16/9/17.
 * </p>
 */

public class GithubListAdapter extends RecyclerView.Adapter<GithubListAdapter.GithubViewHolder>{
    private ArrayList<GithubData> listData = new ArrayList<>();
    private Context context;

    public GithubListAdapter(Context context) {
        this.context = context;
    }

    @Override
    public GithubViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_repository, parent, false);
        return new GithubViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GithubViewHolder holder, int position) {
        holder.repoName.setText(listData.get(position).getRepositoryName());
        holder.starCount.setText(listData.get(position).getStargazers());
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public void addAll(ArrayList<GithubData> data) {
        int size = listData.size();
        listData.clear();
        notifyItemRangeRemoved(0, size);
        listData.addAll(data);
        notifyItemRangeInserted(0, listData.size());
    }

    class GithubViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tc_repo_name)
        TextView repoName;

        @BindView(R.id.tv_stars)
        TextView starCount;

        public GithubViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
