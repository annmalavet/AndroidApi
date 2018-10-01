package com.marvel.malavet.marvel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.malavet.marvel.Models.CharacterObject.Result;
import com.marvel.malavet.marvel.Models.CharacterObject.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.List;

public  class CharacterAdapter extends RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder> {

    List<Result>resultList;
    Context context;

    public CharacterAdapter(List<Result>results, Context c){
        resultList = results;
        context = c;
    }

    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_item,viewGroup, false);
        CharacterViewHolder cvh = new CharacterViewHolder(view);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder cvh, int i) {
        cvh.txt.setText(resultList.get(i).getName());

        ImageView iv = cvh.img;
        Thumbnail t = resultList.get(i).getThumbnail();
        String img = t.getPath() + "." + t.getExtension();
        String ii = img;
        Picasso.get()
                .load(ii)
                .resize(300, 300)
                .onlyScaleDown()
                .into(iv);

    }

    @Override
    public int getItemCount() {
        //count of elements received
        return resultList.size();
    }

    public static  class CharacterViewHolder extends RecyclerView.ViewHolder{

        CardView cv;
        ImageView img;
        TextView txt;
        CharacterViewHolder(View view){
            super(view);
            cv = view.findViewById(R.id.card);
            img = view.findViewById(R.id.image);
            txt  = view.findViewById(R.id.title);

        }

    }


}
