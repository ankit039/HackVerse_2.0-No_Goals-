package com.example.collabfrontend.Adapters;

import androidx.recyclerview.widget.DiffUtil;

import com.example.collabfrontend.model.User;

import java.util.List;

public class CardStackCallback extends DiffUtil.Callback {
    private List<User> old, baru;

    public CardStackCallback(List<User> old, List<User> baru) {
        this.old = old;
        this.baru = baru;
    }

    @Override
    public int getOldListSize() {
        return old.size();
    }

    @Override
    public int getNewListSize() {
        return baru.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition).getImage() == baru.get(newItemPosition).getImage();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return old.get(oldItemPosition) == baru.get(newItemPosition);
    }


}
