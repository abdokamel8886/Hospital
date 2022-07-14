package com.example.hospital.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;
import com.example.hospital.models.ModelCall;

import java.util.ArrayList;

public class CallsRecyclerAdapter extends RecyclerView.Adapter<CallsRecyclerAdapter.Holder> {


    ArrayList<ModelCall.DataDTO> list = new ArrayList<>();

    public void setList(ArrayList<ModelCall.DataDTO> list) {
        this.list = list;
    }

    private CallsRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (CallsRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calls, parent , false);

        return new CallsRecyclerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.user.setText(list.get(position).getPatient_name());
        holder.date.setText(list.get(position).getCreated_at());

        String s = list.get(position).getStatus();

        int resId1 = holder.itemView.getContext().getResources().getIdentifier("accept", "drawable", holder.itemView.getContext().getPackageName());
        int resId2 = holder.itemView.getContext().getResources().getIdentifier("reject", "drawable", holder.itemView.getContext().getPackageName());
        int resId3 =holder.itemView.getContext().getResources().getIdentifier("process", "drawable", holder.itemView.getContext().getPackageName());

        if (s.equals("accept_doctor") || s.equals("logout")){
            holder.state.setImageResource(resId1);

        }
        else if (s.equals("reject")){
            holder.state.setImageResource(resId2);

        }
        else{
            holder.state.setImageResource(resId3);

        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    class Holder extends RecyclerView.ViewHolder{

        TextView user;
        TextView date;
        ImageView state;


        public Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.name_txt);
            date = itemView.findViewById(R.id.date_txt);
            state  = itemView.findViewById(R.id.call_status_img);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){

                        onItemClick.OnClick(list.get(getLayoutPosition()).getId());
                    }
                }
            });

        }
    }

    public interface OnItemClick{

        void OnClick(int id);

    }
}
