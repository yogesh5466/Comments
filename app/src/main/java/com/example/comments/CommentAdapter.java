package com.example.comments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.MyViewHolder> {

    private List<comments> commentsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, date, comment;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            date = (TextView) view.findViewById(R.id.date);
            comment = (TextView) view.findViewById(R.id.comment);
        }
    }


    public CommentAdapter(List<comments> commentsList) {
        this.commentsList = commentsList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        comments com = commentsList.get(position);
        holder.name.setText(com.getName());
        holder.comment.setText(com.getComment());
        holder.date.setText(com.getDate());
    }

    @Override
    public int getItemCount() {
        return commentsList.size();
    }
}