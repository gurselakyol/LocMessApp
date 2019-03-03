package grsl.com.locmessapp;

import android.content.Context;
import android.support.design.widget.BottomSheetDialog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import grsl.com.locmessapp.Interface.GetUserList;

public class WriteMessageActivity extends AppCompatActivity implements View.OnClickListener, GetUserList {

    private BottomSheetDialog bottomSheetDialog;
    private TextView whoSeeThisText;
    private Spinner peopleCountSpinner;
    private EditText writeMessage;
    public GetUserList userList;
    private ArrayList<String> selectedUserList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_message);

        //who see this message selection bottom dialog
        setBottomDialog();

        //userlist - coming from choose people fragment
        userList = this;

        peopleCountSpinner = findViewById(R.id.count_spinner);
        Button sendButton = findViewById(R.id.send_message_button);
        RelativeLayout whoCanSee = findViewById(R.id.person_relative);
        whoSeeThisText = findViewById(R.id.who_see_this_text);
        writeMessage = findViewById(R.id.leave_message_edittext);

        whoCanSee.setOnClickListener(this);
        sendButton.setOnClickListener(this);

    }

    private void setBottomDialog(){
        //bottom sheet dialog
        bottomSheetDialog = new BottomSheetDialog(WriteMessageActivity.this);
        View bottomSheetDialogView = getLayoutInflater()
                .inflate(R.layout.bottom_dialog_who_see, null);
        bottomSheetDialog.setContentView(bottomSheetDialogView);

        //bottom sheet dialog buttons
        View bottomEveryone = bottomSheetDialogView.findViewById(R.id.bottom_everyone);
        View bottomFollowers = bottomSheetDialogView.findViewById(R.id.bottom_followers);
        View bottomChoosePeople = bottomSheetDialogView.findViewById(R.id.bottom_choose_people);
        View bottomFirstXPeople = bottomSheetDialogView.findViewById(R.id.bottom_first_x_people);

        //bottom sheet dialog buttons onClick
        bottomEveryone.setOnClickListener(this);
        bottomFollowers.setOnClickListener(this);
        bottomChoosePeople.setOnClickListener(this);
        bottomFirstXPeople.setOnClickListener(this);
    }

    private void setSpinner(){
        //first X people spinner
        peopleCountSpinner.setVisibility(View.VISIBLE);
        peopleCountSpinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        String peopleCount = String.valueOf(peopleCountSpinner.getSelectedItem());
        Toast.makeText(WriteMessageActivity.this, peopleCount, Toast.LENGTH_SHORT).show();
    }

    private void checkSpinnerVisible(){
        //check first x people option number of people spinner is visible
        //if first x people option selected, show spinner, otherwise hide it
        if(peopleCountSpinner.getVisibility() == View.VISIBLE){
            peopleCountSpinner.setVisibility(View.GONE);
        }
    }

    private void sendMessage(){
        //send message button
        //hide soft keyboard and close WriteMessageActivity
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(writeMessage.getWindowToken(), 0);
        }
        ActivityCompat. finishAfterTransition(this);
    }

    //Activity onClicks
    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            //Who See This Button
            case R.id.person_relative:
                bottomSheetDialog.show();
                break;
            //sen message button
            case R.id.send_message_button:
                sendMessage();
                break;
            //everyone can see this message button
            case R.id.bottom_everyone:
                //close bottom sheet dialog
                bottomSheetDialog.dismiss();
                whoSeeThisText.setText(R.string.everyone_text);
                checkSpinnerVisible();
                break;
            //followers can see this message button
            case R.id.bottom_followers:
                bottomSheetDialog.dismiss();
                whoSeeThisText.setText(R.string.followers);
                checkSpinnerVisible();
                break;
            //choose people who can see this button
            case R.id.bottom_choose_people:
                bottomSheetDialog.dismiss();
                whoSeeThisText.setText(R.string.who_see_this);
                checkSpinnerVisible();
                //choose people fragment initial
                //userList is coming from choose people fragment through interface (selected users)
                BottomDialogChoosePeople bottomDialogChoosePeople = new BottomDialogChoosePeople(userList, selectedUserList);
                bottomDialogChoosePeople.show(getSupportFragmentManager(), bottomDialogChoosePeople.getTag());
                break;
            case R.id.bottom_first_x_people:
                bottomSheetDialog.dismiss();
                whoSeeThisText.setText(R.string.first_x_people);
                setSpinner();
                break;
        }
    }

    @Override
    public void getUserListMethod(ArrayList<String> value) {
        if(!value.isEmpty()) {
            selectedUserList = value;
            whoSeeThisText.setText(TextUtils.join(",", selectedUserList));
        }else{
            whoSeeThisText.setText(R.string.who_see_this);
        }
    }
}
