package com.example.hospital.adapters;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hospital.R;
import com.example.hospital.models.ModelEmployee;
import com.example.hospital.utils.SharedModel;

import java.util.ArrayList;
import java.util.Collection;

public class doctorsRecyclerAdapter extends RecyclerView.Adapter<doctorsRecyclerAdapter.Holder> implements Filterable {


    ArrayList<ModelEmployee.DataDTO> list = new ArrayList<>();
    ArrayList<ModelEmployee.DataDTO> ListAll = new ArrayList<>();

    int row_index = -1;

    public void setList(ArrayList<ModelEmployee.DataDTO> list) {

        this.list = list;
        ListAll.clear();
        ListAll.addAll(list);
    }

    private doctorsRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (doctorsRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doctors, parent , false);

        return new doctorsRecyclerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, @SuppressLint("RecyclerView") int position) {

        holder.user.setText(list.get(position).getFirst_name());
        holder.sp.setText("Specialist - "+list.get(position).getType());

       // Glide.with(holder.itemView.getContext())
                //.load("http://api.instant-ss.com/public/avatar.png")
               // .into(holder.img);

        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index = position;
                notifyDataSetChanged();
            }
        });

        if(row_index == position){
            holder.card.setCardBackgroundColor(holder.itemView.getResources().getColor(R.color.color1));
            SharedModel.setSelected_dr_id(list.get(position).getId());
            SharedModel.setSelected_dr_name(list.get(position).getFirst_name());
        }
        else{
            holder.card.setCardBackgroundColor(Color.WHITE);
        }


    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public Filter getFilter() {
        return myFilter;
    }

    Filter myFilter = new Filter() {

        //Automatic on background thread
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {

            ArrayList<ModelEmployee.DataDTO> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(ListAll);
            } else {
                for (ModelEmployee.DataDTO d : ListAll) {
                        if (d.getFirst_name().toLowerCase().contains(charSequence.toString().toLowerCase())) {
                            filteredList.add(d);
                        }

                }

            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        //Automatic on UI thread
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((Collection<? extends ModelEmployee.DataDTO>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class Holder extends RecyclerView.ViewHolder{

        TextView user;
        TextView sp;
        ImageView img;

        CardView card;



        public Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.name_txt);
            sp = itemView.findViewById(R.id.Specialist_txt);
            img = itemView.findViewById(R.id.img);
            card = itemView.findViewById(R.id.card);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClick != null){
                        onItemClick.OnClick(list.get(getLayoutPosition()).getId() , list.get(getLayoutPosition()).getFirst_name());
                    }
                }
            });

        }
    }

    public interface OnItemClick{
        void OnClick(int id , String name);

    }
}
