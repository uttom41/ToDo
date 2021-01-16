package com.uttom41mitra.todo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uttom41mitra.todo.R;
import com.uttom41mitra.todo.db.ModelValue;
import com.uttom41mitra.todo.db.OnClickEvent;

import java.util.ArrayList;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.DailyViewHolder>{
    private Context context;
    private ArrayList<ModelValue> dailyList;
    private OnClickEvent onClickEvent;

    public DailyAdapter(Context context, ArrayList<ModelValue> dailyList,OnClickEvent onClickEvent) {
        this.context = context;
        this.dailyList = dailyList;
        this.onClickEvent = onClickEvent;
    }

    @NonNull
    @Override
    public DailyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.row_value,parent,false);

        return new DailyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DailyViewHolder holder, int position) {

        holder.textTV.setText(dailyList.get(position).getText());
        holder.timeTV.setText(dailyList.get(position).getTime());
        holder.dateTV.setText(dailyList.get(position).getDate());
        holder.Onclick(dailyList.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return dailyList.size();
    }

    public class DailyViewHolder extends RecyclerView.ViewHolder {
        TextView textTV,dateTV,timeTV,deleteTV;
        public DailyViewHolder(@NonNull View itemView) {
            super(itemView);
            textTV = itemView.findViewById(R.id.textrow);
            dateTV = itemView.findViewById(R.id.daterow);
            timeTV = itemView.findViewById(R.id.timeRow);
            deleteTV = itemView.findViewById(R.id.deleterow);

        }

        public void Onclick(int position) {
            deleteTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickEvent.ClickEvent(position);
                }
            });
        }
    }
}
