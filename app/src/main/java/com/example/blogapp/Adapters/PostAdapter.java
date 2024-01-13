package com.example.blogapp.Adapters;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.blogapp.Constant;
import com.example.blogapp.Model.Post;
import com.example.blogapp.R;
import com.google.android.material.transition.Hold;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private Context context;
    private ArrayList<Post> list;

    public PostAdapter(Context context, ArrayList<Post> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_post,parent,false);
        return new PostHolder(view);//khoi tao view holder moi
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        Post post = list.get(position);
        Log.d(TAG, "Adapter" +Constant.URL+"storage/profiles/"+post.getPhoto());
        Picasso.get().load(post.getUser().getPhoto()).into(holder.imgProfile);
        Picasso.get().load(post.getPhoto()).into(holder.imgPost);
        holder.txtName.setText(post.getUser().getUserName());
        holder.txtComments.setText("Xem tất cả "+ post.getComments()+" bình luận");
        holder.txtLikes.setText(post.getLikes()+"likes");
        holder.txtTime.setText(post.getTime());
        holder.txtDesc.setText(post.getDesc());


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class PostHolder extends RecyclerView.ViewHolder{
        private TextView txtName, txtTime, txtDesc, txtLikes, txtComments;
        private CircleImageView imgProfile;
        private ImageView imgPost;
        ImageButton btnPostOptions, btnLike,btnComment;


        public PostHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtPostNameUser);
            txtTime =  itemView.findViewById(R.id.txtPostTime);
            txtDesc = itemView.findViewById(R.id.txtPostDesc);
            txtLikes = itemView.findViewById(R.id.txtPostLike);
            txtComments = itemView.findViewById(R.id.txtPostComment);
            imgProfile = itemView.findViewById(R.id.imgPostProfile);
            imgPost = itemView.findViewById(R.id.imgPost);
            btnPostOptions = itemView.findViewById(R.id.btnOptionPost);
            btnLike = itemView.findViewById(R.id.btnLike);
            btnComment = itemView.findViewById(R.id.btnComment);

        }
    }
}
