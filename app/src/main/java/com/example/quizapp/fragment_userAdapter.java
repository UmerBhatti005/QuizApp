package com.example.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.quizapp.Model.PersonUsersData;
import com.example.quizapp.databinding.RowLeaderboardsBinding;

import java.util.ArrayList;

public class fragment_userAdapter extends RecyclerView.Adapter<fragment_userAdapter.LeaderboardViewHolder> {

    Context context;
    ArrayList<PersonUsersData> users;

    public fragment_userAdapter(Context context, ArrayList<PersonUsersData> users) {
        this.context = context;
        this.users = users;
    }

    @NonNull
    @Override
    public LeaderboardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_leaderboards, parent, false);
        return new LeaderboardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardViewHolder holder, int position) {
        PersonUsersData user = users.get(position);

        holder.binding.name.setText(user.getUsername());
        holder.binding.coins.setText(String.valueOf(user.getMarks()));
        holder.binding.index.setText(String.format("#%d", position+1));

        Glide.with(context)
                .load(user.getProfile())
                .into(holder.binding.imageView7);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class LeaderboardViewHolder extends RecyclerView.ViewHolder {

        RowLeaderboardsBinding binding;
        public LeaderboardViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = RowLeaderboardsBinding.bind(itemView);
        }
    }
}
