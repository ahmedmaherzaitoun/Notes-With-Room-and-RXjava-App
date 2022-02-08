package com.example.posts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostListAdaptor extends RecyclerView.Adapter<PostListAdaptor.PostViewHolder> {
    private List<Post> arrPost = new ArrayList<>();

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PostViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_item, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        holder.tvTitle.setText(arrPost.get(position).getTitle());
        holder.tvBody.setText(arrPost.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return arrPost.size();
    }

    public void setArrPost (List<Post> arrPost) {
        this.arrPost = arrPost;
        notifyDataSetChanged();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle , tvBody ;
        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.item_title_tv);
            tvBody = itemView.findViewById(R.id.item_body_tv);
        }
    }
}
