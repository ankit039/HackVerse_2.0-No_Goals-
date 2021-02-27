package com.example.collabfrontend.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collabfrontend.R;
import com.example.collabfrontend.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{

    List<User> data;
    Context context;

    public UserAdapter(Context context, List<User> data){
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.useritem, parent, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        holder.name.setText(data.get(position).getFullName());

        if(data.get(position).getImage().equals("")){
            holder.profile_pic.setImageResource(R.drawable.add_pic);
        }else{
            byte[] decodedString = Base64.decode(data.get(position).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.profile_pic.setImageBitmap(decodedByte);
        }

        List<String> skills = new ArrayList<>();
        skills.add("JavaScript");
        skills.add("Django");
        skills.add("React");
        skills.add("C++");
        skills.add("JAVA");
        skills.add("Kotlin");
        skills.add("Python");
        skills.add("Machine Learning");
        skills.add("Digital Marketing");
        skills.add("UI Design");

        String skillstxt = "";
        String curruserskills = data.get(position).getSkills();
        for(int i = 0; i < curruserskills.length(); i++){
            if(curruserskills.charAt(i) != ','){
                skillstxt += String.valueOf(skills.get(curruserskills.charAt(i) - '0'));
                skillstxt += "\n";
            }
        }

        holder.skills.setText(skillstxt);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_pic;
        TextView name, skills;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_pic = itemView.findViewById(R.id.logo);
            name = itemView.findViewById(R.id.name);
            skills = itemView.findViewById(R.id.skills);
        }
    }

    public void setData(List<User> par){
        this.data = par;
        notifyDataSetChanged();
    }

    public List<User> getData() {
        return data;
    }
}
