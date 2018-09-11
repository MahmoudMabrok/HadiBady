package com.mahmoudmabrok.hadibadi.Foramtion.Adapter;

import android.support.v7.widget.RecyclerView;
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
public class matchAdapter extends RecyclerView.Adapter<matchAdapter.Holder> {

    List<Match> list;

    public matchAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<Match> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public void addItem(Match item) {
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_item, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        Match value = list.get(position);
        holder.text1.setText(value.getFirstTeam());
        if (value.getSecondTeam() != null)
            holder.text2.setText(value.getSecondTeam());
        else {
            holder.text2.setText("تأهل للدور التالى ");
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;

        public Holder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.tvFirstTeam);
            text2 = itemView.findViewById(R.id.tvSecondTeam);
        }
    }

}
