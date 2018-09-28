package com.marvel.malavet.marvel;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.marvel.malavet.marvel.Models.ComicBookObject.Data;
import com.marvel.malavet.marvel.Models.ComicBookObject.MarvelInitialObject;
import com.marvel.malavet.marvel.Models.ComicBookObject.Result;
import com.marvel.malavet.marvel.Models.ComicBookObject.Thumbnail;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Main extends AppCompatActivity {
    ProgressDialog progress;
    //one comic
    int comicID = 29749;
    Context context;
    ArrayList<Result> r = new ArrayList<Result>();
    LinearLayoutManager lm;
    Button button;
    String bookInfo = "";
    private TextView txtview;
    //button click
    private View.OnClickListener handleClick = new View.OnClickListener() {
        public void onClick(View arg0) {
            if (bookInfo.isEmpty()) {
                button.setText("No Info Available");
            } else {
                Toast.makeText(Main.this, bookInfo, Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //check if internet is available
        if (!isNetworkAvailable()) {
            Toast.makeText(Main.this, "You must be connected to the internet for this app to function.", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.comic);
        context = this;
        progress = new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.show();
        //make hash for api call
        APIHash apiHash = new APIHash();
        //make client object - call API to get comic book
        ClientDefinition cd = new ClientDefinition(context);
        APICalls service = cd.getRetrofitInstance().create(APICalls.class);
        HashMap<String, String> hm = apiHash.makeApiParam();
        String hash = hm.get("hash");
        Log.d("hash", hm.get("hash"));
        Call<MarvelInitialObject> call = service.getComic(comicID, apiHash.makeApiParam());
        call.enqueue(new Callback<MarvelInitialObject>() {
            @Override
            public void onResponse(Call<MarvelInitialObject> call, Response<MarvelInitialObject> response) {
                progress.dismiss();
                context = context;
                Data rNew = response.body().getData();
                Result r = rNew.getResults().get(0);
                placeResults(r);
            }

            @Override
            public void onFailure(Call<MarvelInitialObject> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(Main.this, "error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    //place API results in layout
    private void placeResults(Result r) {
        ImageView iv = findViewById(R.id.image);
        Thumbnail t = r.getThumbnail();
        String img = t.getPath() + "." + t.getExtension();
        String ii = img;
        bookInfo = "Price is $" + r.getPrices().get(0).getPrice();
        txtview = findViewById(R.id.title);
        button = findViewById(R.id.button);
        button.setOnClickListener(handleClick);
        txtview.setText(r.getTitle());
        Animation slide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.move);
        txtview.startAnimation(fade);
        Picasso.get()
                .load(ii).into(iv);
        iv.startAnimation(slide);
        button.startAnimation(slide);
        Picasso.get().setLoggingEnabled(true);
    }

    @Override
    public void onStop() {
        super.onStop();
        progress.dismiss();
    }

    //check internet boolean
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}
