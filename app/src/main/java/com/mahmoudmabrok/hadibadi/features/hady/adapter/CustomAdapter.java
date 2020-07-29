package com.mahmoudmabrok.hadibadi.features.hady.adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mahmoudmabrok.hadibadi.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahmoud on 9/10/2018.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder> {
    List<String> list;

    public CustomAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<String> listNew) {
        list.clear();
        list.addAll(listNew);
        notifyDataSetChanged();
    }


    public void addItem(String item) {
        list.add(item);
        notifyItemInserted(list.size() - 1);
        notifyItemRangeChanged(list.size() - 1, 1);
    }

    public void removeItem(int pos) {
        list.remove(pos);
        notifyItemRemoved(pos);
        notifyItemRangeChanged(pos, list.size());
    }


    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new Holder(view);
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        String value = list.get(position);
        holder.text.setText(value);
    }

    public String drawOut(int rnd) {
        String name = list.remove(rnd);
        notifyItemRemoved(rnd);
        notifyItemRangeChanged(rnd,list.size());
        return name;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class Holder extends RecyclerView.ViewHolder {

        TextView text;

        public Holder(View itemView) {
            super(itemView);
            text = itemView.findViewById(R.id.textView);
        }
    }

}
