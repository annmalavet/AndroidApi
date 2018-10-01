package com.marvel.malavet.marvel;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.marvel.malavet.marvel.Models.CharacterObject.Data0;
import com.marvel.malavet.marvel.Models.CharacterObject.MarvelInitCharObject;
import com.marvel.malavet.marvel.Models.CharacterObject.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CharacterActivity extends AppCompatActivity {
    ProgressDialog progress;
    Context context;
    ArrayList<Result> r = new ArrayList<Result>();
    RecyclerView recyclerView;
    Button button1;
    @Override
    protected  void onCreate(Bundle savedInstanceState){


        super.onCreate(savedInstanceState);
        setContentView(R.layout.characters_recycler);
        recyclerView = findViewById(R.id.recycler_view);

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);

        context = this;
        progress = new ProgressDialog(this);
        progress.setMessage("Loading....");
        progress.show();

        APIHash apiHash = new APIHash();
        ClientDefinition cd = new ClientDefinition(context);
        APICalls service = cd.getRetrofitInstance().create(APICalls.class);
        HashMap<String, String> hm = apiHash.makeApiParam();
        String hash = hm.get("hash");
        Log.d("hash", hm.get("hash"));

            Call<MarvelInitCharObject> call = service.getCharacter("characters","lady", apiHash.makeApiParam());
            call.enqueue(new Callback<MarvelInitCharObject>() {
                @Override
                public void onResponse(Call<MarvelInitCharObject> call, Response<MarvelInitCharObject> response) {
                    progress.dismiss();
                     Data0 data = response.body().getData();
                    //Data rNew = response.body().getData();
                    List<Result> r = data.getResults();
                    CharacterAdapter adapter = new CharacterAdapter(r, context);
                    recyclerView.setAdapter(adapter);

                }

                @Override
                public void onFailure(Call<MarvelInitCharObject> call, Throwable t) {
                    progress.dismiss();
                    Toast.makeText(CharacterActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        }

}
