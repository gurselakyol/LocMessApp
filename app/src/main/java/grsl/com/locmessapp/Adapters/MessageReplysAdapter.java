package grsl.com.locmessapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import grsl.com.locmessapp.Interface.OnItemClick;
import grsl.com.locmessapp.Models.Person;
import grsl.com.locmessapp.Models.Reply;
import grsl.com.locmessapp.R;

public class MessageReplysAdapter extends RecyclerView.Adapter<MessageReplysAdapter.MyViewHolder> {

    private List<Reply> replyList;
    private Context context;
    private OnItemClick replyClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView replyName, replySurname, replyDateTime, replyText;
        ImageView replyUserPhoto;

        MyViewHolder(View view){
            super(view);
            replyName = view.findViewById(R.id.reply_list_item_name);
            replySurname = view.findViewById(R.id.reply_list_item_surname);
            replyDateTime = view.findViewById(R.id.reply_list_item_date);
            replyText = view.findViewById(R.id.reply_list_item_text);
            replyUserPhoto = view.findViewById(R.id.reply_list_item_photo);
        }
    }

    public MessageReplysAdapter(List<Reply> replyList, Context context, OnItemClick replyClick){
        this.replyList = replyList;
        this.context = context;
        this.replyClick = replyClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reply_list_item, parent, false);



        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        Reply reply = replyList.get(position);

        holder.replyDateTime.setText(reply.getDateTime());
        holder.replyText.setText(reply.getReplyText());

        String userName,userSurname, userPhotoUrl;

        userName = "John";
        userSurname = "Doe";
        userPhotoUrl = "";

        holder.replyName.setText(userName);
        holder.replySurname.setText(userSurname);
        Glide.with(context).load(userPhotoUrl).placeholder(R.drawable.defaultprofilephoto).into(holder.replyUserPhoto);

        holder.replyUserPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replyClick.onItemClick(holder.getAdapterPosition());
            }
        });

    }

    @Override
    public int getItemCount() {
        return replyList.size();
    }
}
