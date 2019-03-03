package grsl.com.locmessapp;

import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import grsl.com.locmessapp.Adapters.MessagesAdapter;
import grsl.com.locmessapp.Interface.OnItemClickTransition;
import grsl.com.locmessapp.Models.Messages;

public class MessagesActivity extends AppCompatActivity implements OnItemClickTransition {

    private List<Messages> messagesList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        ImageButton backButton = findViewById(R.id.header_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView headerText = findViewById(R.id.header_text);
        headerText.setText(R.string.messages_text);

        RecyclerView messagesRecycler = findViewById(R.id.messages_recycler);
        MessagesAdapter mAdapter = new MessagesAdapter(messagesList, getApplicationContext(), this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        messagesRecycler.setLayoutManager(mLayoutManager);
        messagesRecycler.setItemAnimator(new DefaultItemAnimator());
        messagesRecycler.setAdapter(mAdapter);
        getMessageList();
    }
    private void getMessageList(){
        Messages messages = new Messages("Beyoğlu Caddesi","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir","ic_person_black_36dp","Today",5,2);
        messagesList.add(messages);

        messages = new Messages("İstiklal Caddesi","Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir. Lorem Ipsum, dizgi ve baskı endüstrisinde kullanılan mıgır metinlerdir","ic_public_black_36dp","2 days ago",50,12);
        messagesList.add(messages);
    }

    @Override
    public void onItemClick(int index, View cardView) {
        Intent intent = new Intent(MessagesActivity.this, MessageDetailsActivity.class);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                .makeSceneTransitionAnimation(
                        MessagesActivity.this, cardView,
                        Objects.requireNonNull(ViewCompat.getTransitionName(cardView))
                );
        intent.putExtra("messageId", index);
        startActivity(intent, optionsCompat.toBundle());
    }
}
