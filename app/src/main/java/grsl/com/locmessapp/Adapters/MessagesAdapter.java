package grsl.com.locmessapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.card.MaterialCardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import grsl.com.locmessapp.Interface.OnItemClickTransition;
import grsl.com.locmessapp.Models.Messages;
import grsl.com.locmessapp.R;

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MyViewHolder> {

    private List<Messages> messagesList;
    private Context context;
    private OnItemClickTransition messageClick;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView locationText, messageText, seenCountText, replyCountText, messageDateTime;
        ImageView whoCanSeeIcon;
        MaterialCardView messageCard;

        MyViewHolder(View view){
            super(view);
            locationText = view.findViewById(R.id.message_list_location_text);
            messageText = view.findViewById(R.id.message_list_message_text);
            seenCountText = view.findViewById(R.id.message_list_seen_count_text);
            replyCountText = view.findViewById(R.id.message_list_reply_count_text);
            whoCanSeeIcon = view.findViewById(R.id.message_list_who_can_see_icon);
            messageCard = view.findViewById(R.id.message_list_item_card);
            messageDateTime = view.findViewById(R.id.message_list_item_date);
        }
    }

    public MessagesAdapter(List<Messages> messagesList, Context context, OnItemClickTransition messageClick){
        this.messagesList = messagesList;
        this.context = context;
        this.messageClick = messageClick;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.messages_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Messages messages = messagesList.get(position);

        int id = context.getResources().getIdentifier("grsl.com.locmessapp:drawable/" + messages.getWhoCanSee(), null, null);
        holder.whoCanSeeIcon.setImageResource(id);

        holder.locationText.setText(messages.getLocation());
        holder.messageDateTime.setText(messages.getDateTime());
        holder.messageText.setText(messages.getMessage());
        holder.messageText.setMaxLines(5);
        holder.seenCountText.setText(String.valueOf(messages.getSeenCount()));
        holder.replyCountText.setText(String.valueOf(messages.getReplyCount()));

        holder.messageCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageClick.onItemClick(holder.getAdapterPosition(), holder.messageCard);
            }
        });

    }

    public static void viewMoreText(){

    }

    @Override
    public int getItemCount() {
        return messagesList.size();
    }
}
