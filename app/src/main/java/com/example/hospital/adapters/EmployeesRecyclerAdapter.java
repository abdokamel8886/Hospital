package com.example.hospital.adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.hospital.R;
import com.example.hospital.models.ModelCall;
import com.example.hospital.models.ModelEmployee;

import java.util.ArrayList;
import java.util.Collection;

public class EmployeesRecyclerAdapter extends RecyclerView.Adapter<EmployeesRecyclerAdapter.Holder> implements Filterable {


    ArrayList<ModelEmployee.DataDTO> list = new ArrayList<>();
    ArrayList<ModelEmployee.DataDTO> ListAll = new ArrayList<>();;

    public void setList(ArrayList<ModelEmployee.DataDTO> list) {

        this.list = list;
        ListAll.clear();
        ListAll.addAll(list);
    }

    private EmployeesRecyclerAdapter.OnItemClick onItemClick ;

    public void setOnItemClick (EmployeesRecyclerAdapter.OnItemClick onItemClick){
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_emplyees, parent , false);

        return new EmployeesRecyclerAdapter.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

        holder.user.setText(list.get(position).getFirst_name());
        holder.sp.setText("Specialist - "+list.get(position).getType());

       // Glide.with(holder.itemView.getContext())
                //.load("http://api.instant-ss.com/public/avatar.png")
               // .into(holder.img);


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



        public Holder(@NonNull View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.name_txt);
            sp = itemView.findViewById(R.id.Specialist_txt);
            img = itemView.findViewById(R.id.img);

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
