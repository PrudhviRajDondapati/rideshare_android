package com.example.rideshare;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class RideAdapter extends FirebaseRecyclerAdapter<RideModel,RideAdapter.myviewholder> {


    public RideAdapter(@NonNull FirebaseRecyclerOptions<RideModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull  RideAdapter.myviewholder holder, final int position, @NonNull final RideModel model) {
        holder.ride_desc.setText(model.getRide_desc());
        holder.from.setText(model.getFrom());
        holder.to.setText(model.getTo());
        holder.seats.setText(model.getSeats());

        holder.price.setText(model.getPrice());



        holder.ride_desc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity=(AppCompatActivity)v.getContext();
                Intent intent=new Intent(activity,SingleListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("ride_desc",model.getRide_desc());
                intent.putExtra("from",model.getFrom());
                intent.putExtra("to",model.getTo());
                intent.putExtra("seats",model.getSeats());
                intent.putExtra("rating",model.getRating());
                intent.putExtra("price",model.getPrice());

                v.getContext().startActivity(intent);

            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ridelist_layout,parent,false);
        return new myviewholder(view);
    }



    class myviewholder extends RecyclerView.ViewHolder {
        TextView ride_desc,from,to,rating,price,seats;
        RelativeLayout relative;

        public myviewholder(@NonNull View itemView) {

            super(itemView);

            ride_desc = (TextView)itemView.findViewById(R.id.ride_desc);
            from = (TextView)itemView.findViewById(R.id.from);
            to = (TextView)itemView.findViewById(R.id.to);
            seats = (TextView)itemView.findViewById(R.id.seats);
            price = (TextView)itemView.findViewById(R.id.price);

        }
    }
}