package grsl.com.locmessapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.card.MaterialCardView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import grsl.com.locmessapp.Adapters.MessageReplysAdapter;
import grsl.com.locmessapp.Interface.OnItemClick;
import grsl.com.locmessapp.Models.Messages;
import grsl.com.locmessapp.Models.Reply;

public class MessageDetailsActivity extends AppCompatActivity implements OnItemClick {

    private TextView locationText, messageText, seenCountText, replyCountText, messageDateTime;
    private ImageView whoCanSeeIcon;
    private List<Messages> messagesList = new ArrayList<>();
    private List<Reply> replyList = new ArrayList<>();
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        locationText = findViewById(R.id.message_list_location_text);
        messageText = findViewById(R.id.message_list_message_text);
        seenCountText = findViewById(R.id.message_list_seen_count_text);
        replyCountText = findViewById(R.id.message_list_reply_count_text);
        whoCanSeeIcon = findViewById(R.id.message_list_who_can_see_icon);
        messageDateTime = findViewById(R.id.message_list_item_date);

        RecyclerView replyRecycler = findViewById(R.id.message_details_recycler);
        MessageReplysAdapter mAdapter = new MessageReplysAdapter(replyList, getApplicationContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        replyRecycler.setLayoutManager(mLayoutManager);
        replyRecycler.setItemAnimator(new DefaultItemAnimator());
        replyRecycler.setAdapter(mAdapter);



        getMessage();
        getReply();
    }

    private void getMessage(){
        Intent intent = getIntent();
        int messageId = intent.getIntExtra("messageId",0);

        Messages messages;

        if(messageId == 0){
            messages = new Messages("Beyoğlu Caddesi","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir","ic_person_black_36dp","Today",5,2);
            messagesList.add(messages);
        }else {
            messages = new Messages("İstiklal Caddesi","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir","ic_public_black_36dp","2 days ago",50,12);
            messagesList.add(messages);
        }

        int id = getResources().getIdentifier("grsl.com.locmessapp:drawable/" + messages.getWhoCanSee(), null, null);
        whoCanSeeIcon.setImageResource(id);

        locationText.setText(messages.getLocation());
        messageText.setText(messages.getMessage());
        messageDateTime.setText(messages.getDateTime());
        seenCountText.setText(String.valueOf(messages.getSeenCount()));
        replyCountText.setText(String.valueOf(messages.getReplyCount()));

    }

    private void getReply(){
        Reply reply = new Reply("123","Today","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir");
        replyList.add(reply);

        reply = new Reply("1234","Yesterday","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir");
        replyList.add(reply);
    }

    @Override
    public void onItemClick(int index) {
        showUserInfoDialog(index);
    }

    public void showUserInfoDialog(int index){
        myDialog = new Dialog(this);

        myDialog.setContentView(R.layout.show_user_info_dialog);

        TextView userInfoDialogName = myDialog.findViewById(R.id.user_info_dialog_name);
        userInfoDialogName.setText(R.string.name_text);

        TextView userInfoDialogSurname = myDialog.findViewById(R.id.user_info_dialog_surname);
        userInfoDialogSurname.setText(R.string.surname_text);

        TextView userInfoDialogUsername = myDialog.findViewById(R.id.user_info_dialog_username);
        userInfoDialogUsername.setText(String.valueOf(index));

        ImageView closeDialog = myDialog.findViewById(R.id.user_info_dialog_close);
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setCancelable(false);
        myDialog.show();
    }
}
