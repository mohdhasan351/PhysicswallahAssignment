package com.example.physicswallah.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.physicswallah.Models.FacultyModel;
import com.example.physicswallah.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.ViewHolder> {
    Context ctx;
    ArrayList<FacultyModel> facultyModelArrayList;

    public HomeRecyclerViewAdapter(Context ctx, ArrayList<FacultyModel> facultyModelArrayList) {
        this.ctx = ctx;
        this.facultyModelArrayList = facultyModelArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.itemrecycler,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(facultyModelArrayList.get(position).getName());
        holder.degree.setText(facultyModelArrayList.get(position).getQualification().get(0));
        holder.subject.setText(facultyModelArrayList.get(position).getSubjects().get(0)+"  ●  ");
        Picasso.get().load(facultyModelArrayList.get(position).getProfileurl()).into(holder.imageView);
        Log.i("profileURL",facultyModelArrayList.get(position).getProfileurl());
    }

    @Override
    public int getItemCount() {
        return facultyModelArrayList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name,subject,degree;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameid);
            subject = itemView.findViewById(R.id.subjectone);
            degree = itemView.findViewById(R.id.degree);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }
}
