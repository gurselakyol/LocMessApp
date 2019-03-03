package grsl.com.locmessapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import grsl.com.locmessapp.Adapters.ChoosePeopleAdapter;
import grsl.com.locmessapp.Interface.GetUserList;
import grsl.com.locmessapp.Interface.OnButtonClick;
import grsl.com.locmessapp.Models.Person;

@SuppressLint("ValidFragment")
public class BottomDialogChoosePeople extends BottomSheetDialogFragment implements OnButtonClick {

    //followers list
    private List<Person> personList = new ArrayList<>();
    //empty selected people list for showing selected people
    private ArrayList<String> selectedPeople = new ArrayList<>();
    private GetUserList userList;
    //sending adapter for display select or remove button
    private ArrayList<String> selectedUserList;
    private TextView removeAll;

    public BottomDialogChoosePeople(GetUserList callback, ArrayList<String> selectedUserList) {
        this.userList = callback;
        this.selectedUserList = selectedUserList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_bottom_dialog_choose_people, container,
                false);

        //done button - when user select who can see the message, done button sending to writemessageactivity selected people list
        Button doneButton = view.findViewById(R.id.choose_people_done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userList.getUserListMethod(selectedPeople);
                dismiss();
            }
        });

        removeAll = view.findViewById(R.id.choose_people_remove_all);
        removeButton();

        ChoosePeopleAdapter mAdapter = new ChoosePeopleAdapter(personList, getContext(), this, selectedUserList);
        RecyclerView recyclerView = view.findViewById(R.id.choose_people_recycler);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        selectedPeople = selectedUserList;
        getPersonList();

        return view;
    }

    //remove all selected users from list
    private void removeButton(){
        if(!selectedUserList.isEmpty()){
            removeAll.setVisibility(View.VISIBLE);
        }
        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPeople.clear();
                selectedUserList.clear();
                removeAll.setVisibility(View.GONE);
                //remove selected people, set remove button hide and close fragment
                dismiss();
            }
        });
    }

    //dummy data for follower list
    private void getPersonList() {
        Person person = new Person("John","Doe","johndoe","");
        personList.add(person);

        person = new Person("Jane","Doe","janedoe","");
        personList.add(person);
    }

    //select button action
    //adding selected persons selectedPeople list
    @Override
    public void onClickAdd(String value) {
        selectedPeople.add(value);
        removeAll.setVisibility(View.VISIBLE);
    }

    //remove button action
    //removing selected persons selectedPeople list
    @Override
    public void onClickRemove(String value) {
        selectedPeople.remove(value);
        if(selectedPeople.isEmpty()){
            removeAll.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClickRemoveFollower(int index) {}

}
