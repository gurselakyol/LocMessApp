package grsl.com.locmessapp;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import grsl.com.locmessapp.Adapters.FollowersAdapter;
import grsl.com.locmessapp.Interface.OnButtonClick;
import grsl.com.locmessapp.Interface.OnItemClick;
import grsl.com.locmessapp.Models.Person;

public class FollowersActivity extends AppCompatActivity implements OnButtonClick, OnItemClick {

    private List<Person> followerList = new ArrayList<>();
    private ArrayList<String> followingList = new ArrayList<>();
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        ImageButton backButton = findViewById(R.id.header_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        TextView headerText = findViewById(R.id.header_text);
        headerText.setText(R.string.followers);

        RecyclerView followersRecycler = findViewById(R.id.followers_recycler);
        FollowersAdapter mAdapter = new FollowersAdapter(followerList, getApplicationContext(), this, followingList, this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        followersRecycler.setLayoutManager(mLayoutManager);
        followersRecycler.setItemAnimator(new DefaultItemAnimator());
        followersRecycler.setAdapter(mAdapter);

        getFollowerList();
        getFollowingList();
    }

    private void getFollowerList(){
        Person person = new Person("John","Doe","johndoe","");
        followerList.add(person);

        person = new Person("Jane","Doe","janedoe","");
        followerList.add(person);
    }

    private void getFollowingList(){
        followingList.add("johndoe");
    }

    @Override
    public void onClickAdd(String value) {
        followingList.add(value);
    }

    @Override
    public void onClickRemove(String value) {
        followingList.remove(value);
    }

    @Override
    public void onClickRemoveFollower(int index) {
        showRemoveUserDialog(index);
    }

    @Override
    public void onItemClick(int index) {
        showUserInfoDialog(index);
    }

    public void showRemoveUserDialog(final int index){
        myDialog = new Dialog(this);

        myDialog.setContentView(R.layout.remove_user_dialog);

        TextView dialogUsername = myDialog.findViewById(R.id.remove_user_dialog_username);
        dialogUsername.setText(String.valueOf(index));

        TextView warningText = myDialog.findViewById(R.id.remove_user_dialog_warning);
        warningText.setText(R.string.remove_user_text);

        Button removeUser = myDialog.findViewById(R.id.remove_user_dialog_remove_button);
        removeUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                followerList.remove(index);
            }
        });

        Button cancel = myDialog.findViewById(R.id.remove_user_dialog_cancel_button);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });

        Objects.requireNonNull(myDialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setCancelable(false);
        myDialog.show();
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
