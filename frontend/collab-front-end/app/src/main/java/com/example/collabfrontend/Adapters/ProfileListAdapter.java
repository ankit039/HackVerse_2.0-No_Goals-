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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.collabfrontend.R;
import com.example.collabfrontend.model.User;

import java.util.ArrayList;
import java.util.List;

public class ProfileListAdapter extends RecyclerView.Adapter<ProfileListAdapter.ProfileViewHolder> {

    List<User> data = new ArrayList<>();
    Context context;

    public ProfileListAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProfileListAdapter.ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_list_item, parent, false);
        return new ProfileViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileListAdapter.ProfileViewHolder holder, int position) {
        holder.name.setText(data.get(position).getFullName());

        if(data.get(position).getImage().equals("")){
            holder.logo.setImageResource(R.drawable.add_pic);
        }else{
            byte[] decodedString = Base64.decode(data.get(position).getImage(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.logo.setImageBitmap(decodedByte);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Will be implemented soon!!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ProfileViewHolder extends RecyclerView.ViewHolder {
        ImageView logo;
        TextView name;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);
            logo = itemView.findViewById(R.id.logo);
            name = itemView.findViewById(R.id.name);
        }
    }


    public void setData(List<User> par){
        this.data = par;
        notifyDataSetChanged();
    }

}
