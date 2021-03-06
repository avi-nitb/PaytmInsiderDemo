package com.etamine.paytminsiderdemo.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.etamine.paytminsiderdemo.R;
import com.etamine.paytminsiderdemo.models.MasterList;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodRecyclerViewAdapter extends RecyclerView.Adapter<FoodRecyclerViewAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<MasterList> masterList;

    public FoodRecyclerViewAdapter(Context context, ArrayList<MasterList> masterList){
        this.context = context;
        this.masterList = masterList;
    }


    @NonNull
    @Override
    public FoodRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.itemviewevents, parent, false);
        return (new MyViewHolder(itemView));
    }

    @Override
    public void onBindViewHolder(@NonNull FoodRecyclerViewAdapter.MyViewHolder holder, int position) {
        MasterList currentData = masterList.get(position);

        if (currentData.getGroup_id().getName().equalsIgnoreCase("food")) {
            Picasso.with(context).load(currentData.getHorizontal_cover_image()).fit().into(holder.imgView_thumbnail);
            holder.txtView_title.setText(currentData.getName());
            holder.txtView_date.setText(currentData.getVenue_date_string());
        }
    }

    @Override
    public int getItemCount() {
        return masterList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgView_thumbnail;
        TextView txtView_title,txtView_date;
        ImageButton favImageButton;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imgView_thumbnail = itemView.findViewById(R.id.imgView_thumbnail);
            txtView_title = itemView.findViewById(R.id.txtView_title);
            txtView_date = itemView.findViewById(R.id.txtView_date);
            favImageButton = itemView.findViewById(R.id.favImageButton);
            favImageButton.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (v == favImageButton){
                if (!masterList.get(getAdapterPosition()).isFavorite()){
                    masterList.get(getAdapterPosition()).setFavorite(true);
                    favImageButton.setImageResource(R.drawable.ic_iconfinder_heart_299063);
                    Toast.makeText(context, "Added to favorites.", Toast.LENGTH_SHORT).show();
                } else {
                    masterList.get(getAdapterPosition()).setFavorite(false);
                    favImageButton.setImageResource(R.drawable.ic_iconfinder_weheartit_2308138);
                    Toast.makeText(context, "Removed from favorites.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
