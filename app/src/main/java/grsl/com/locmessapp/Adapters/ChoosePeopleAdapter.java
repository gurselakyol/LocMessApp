package grsl.com.locmessapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import grsl.com.locmessapp.Interface.OnButtonClick;
import grsl.com.locmessapp.Models.Person;
import grsl.com.locmessapp.R;

public class ChoosePeopleAdapter extends RecyclerView.Adapter<ChoosePeopleAdapter.MyViewHolder> {

    //followersList comes from fragment via adapter
    private List<Person> personList;
    //get Context
    private Context context;
    //add or remove button onClick action
    private OnButtonClick mCallback;
    //selected users list; comes from fragment via adapter for showing select or remove buttons
    private ArrayList<String> selectedUserList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, surname, username;
        ImageView photo;
        Button selectButton, removeButton;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.user_list_item_name);
            surname = view.findViewById(R.id.user_list_item_surname);
            username = view.findViewById(R.id.user_list_item_username);
            photo = view.findViewById(R.id.user_list_item_photo);
            selectButton = view.findViewById(R.id.user_list_item_select_button);
            removeButton = view.findViewById(R.id.user_list_item_remove_button);
        }
    }

    public ChoosePeopleAdapter(List<Person> personList, Context context, OnButtonClick listener, ArrayList<String> selectedUserList) {
        this.personList = personList;
        this.context = context;
        this.mCallback = listener;
        this.selectedUserList = selectedUserList;
    }

    @Override
    @NonNull
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_list_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Person person = personList.get(position);
        holder.name.setText(person.getName());
        holder.surname.setText(person.getSurname());
        holder.username.setText(person.getUsername());
        Glide.with(context).load(person.getPhotoUrl()).placeholder(R.drawable.defaultprofilephoto).into(holder.photo);
        holder.selectButton.setVisibility(View.VISIBLE);

        //if selected user list (comes from fragment) empty set default => select button visible, remove button hide
        if(!selectedUserList.isEmpty()){
            if(selectedUserList.contains(person.getUsername())){
                holder.selectButton.setVisibility(View.GONE);
                holder.removeButton.setVisibility(View.VISIBLE);
            }else{
                holder.selectButton.setVisibility(View.VISIBLE);
                holder.removeButton.setVisibility(View.GONE);
            }
        }

        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.removeButton.setVisibility(View.GONE);
                holder.selectButton.setVisibility(View.VISIBLE);
                mCallback.onClickRemove(holder.username.getText().toString());
            }
        });
        holder.selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.selectButton.setVisibility(View.GONE);
                holder.removeButton.setVisibility(View.VISIBLE);
                mCallback.onClickAdd(holder.username.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
}
