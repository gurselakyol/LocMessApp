package grsl.com.locmessapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import grsl.com.locmessapp.Interface.OnButtonClick;
import grsl.com.locmessapp.Interface.OnItemClick;
import grsl.com.locmessapp.Models.Person;
import grsl.com.locmessapp.R;

public class FollowersAdapter extends RecyclerView.Adapter<FollowersAdapter.MyViewHolder> {

    private List<Person> followerList;
    private ArrayList<String> followingList;
    private Context context;
    private OnButtonClick mCallback;
    private OnItemClick showUserInfoDialog;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname, username;
        ImageView photo;
        Button followButton, unfollowButton;
        ImageButton removeFollowersOption;
        RelativeLayout userListItem;

        MyViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.user_list_item_name);
            surname = view.findViewById(R.id.user_list_item_surname);
            username = view.findViewById(R.id.user_list_item_username);
            photo = view.findViewById(R.id.user_list_item_photo);
            followButton = view.findViewById(R.id.user_list_item_follow_button);
            unfollowButton = view.findViewById(R.id.user_list_item_unfollow_button);
            removeFollowersOption = view.findViewById(R.id.user_list_item_option_button);
            userListItem = view.findViewById(R.id.user_list_item_relative);
        }
    }

    public FollowersAdapter(List<Person> followerList, Context context, OnButtonClick listener, ArrayList<String> followingList, OnItemClick showUserInfoDialog){
        this.followerList = followerList;
        this.context = context;
        this.mCallback = listener;
        this.followingList = followingList;
        this.showUserInfoDialog = showUserInfoDialog;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Person person = followerList.get(position);
        holder.name.setText(person.getName());
        holder.surname.setText(person.getSurname());
        holder.username.setText(person.getUsername());
        Glide.with(context).load(person.getPhotoUrl()).placeholder(R.drawable.defaultprofilephoto).into(holder.photo);
        holder.removeFollowersOption.setVisibility(View.VISIBLE);

        if(!followingList.isEmpty()){
            if(followingList.contains(person.getUsername())){
                holder.followButton.setVisibility(View.GONE);
                holder.unfollowButton.setVisibility(View.VISIBLE);
            }else{
                holder.followButton.setVisibility(View.VISIBLE);
                holder.unfollowButton.setVisibility(View.GONE);
            }
        }

        holder.followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.followButton.setVisibility(View.GONE);
                holder.unfollowButton.setVisibility(View.VISIBLE);
                mCallback.onClickAdd(holder.username.getText().toString());
            }
        });

        holder.unfollowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.followButton.setVisibility(View.VISIBLE);
                holder.unfollowButton.setVisibility(View.GONE);
                mCallback.onClickRemove(holder.username.getText().toString());
            }
        });

        holder.removeFollowersOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onClickRemoveFollower(holder.getAdapterPosition());
            }
        });

        holder.userListItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUserInfoDialog.onItemClick(holder.getAdapterPosition());
            }
        });
    }



    @Override
    public int getItemCount() {
        return followerList.size();
    }
}
