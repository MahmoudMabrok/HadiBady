package com.mahmoudmabrok.hadibadi.features.Foramtion.Adapter;

import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = "matchAdapter";

    List<MatchGame> list;
    List<Integer> indexesOfHeader = new ArrayList<>();

    public matchAdapter() {
        list = new ArrayList<>();
    }

    public void setList(List<MatchGame> list) {

        this.list = new ArrayList<>(list);
        notifyDataSetChanged();
    }

    public void addItem(MatchGame item) {
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
        MatchGame value = list.get(position);
        String oldValue;

        Log.d(TAG, position + "onBindViewHolder: " + value.getFirstTeam() + "#" + value.getSecondTeam() + "#" + value.getMatchNum());
        //case header
        if (value.getFirstTeam() == null && value.getSecondTeam() == null) {
            holder.header.setVisibility(View.VISIBLE);
            holder.header.setText("stage " + value.getMatchNum());

            holder.text1.setVisibility(View.GONE);
            holder.vs.setVisibility(View.GONE);
            holder.text2.setVisibility(View.GONE);

        } else {
            holder.text1.setVisibility(View.VISIBLE);
            holder.vs.setVisibility(View.VISIBLE);
            holder.text2.setVisibility(View.VISIBLE);

            //case match row
            holder.text1.setText(value.getFirstTeam());
            if (value.getSecondTeam() != null)
                holder.text2.setText(value.getSecondTeam());
            else {
                holder.vs.setVisibility(View.GONE);
                oldValue = holder.text1.getText().toString();

                holder.text1.setText(oldValue + " تأهل ");
                holder.text2.setVisibility(View.GONE);
            }

            holder.header.setVisibility(View.GONE);
        }

        holder.num.setText("" + value.getMatchNum());

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        TextView text1;
        TextView text2;
        TextView vs;
        TextView num;

        TextView header;

        public Holder(View itemView) {
            super(itemView);
            text1 = itemView.findViewById(R.id.tvFirstTeam);
            text2 = itemView.findViewById(R.id.tvSecondTeam);
            vs = itemView.findViewById(R.id.vs);
            num = itemView.findViewById(R.id.tvCount);
            header = itemView.findViewById(R.id.headerName);
        }
    }

}
