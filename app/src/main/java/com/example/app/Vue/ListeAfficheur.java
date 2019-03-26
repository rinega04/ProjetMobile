package com.example.app.Vue;

        import com.example.app.Controller.MyAdapter;
        import com.example.app.Model.APIRest;
        import com.example.app.Model.Constants;
        import com.example.app.Model.Pokemon;
        import com.example.app.R;
        import com.example.app.Model.RestPokemon;
        import com.google.gson.Gson;
        import com.google.gson.GsonBuilder;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.View;
        import android.widget.AdapterView;

        import java.util.List;

        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.Retrofit;
        import retrofit2.converter.gson.GsonConverterFactory;


public class ListeAfficheur extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_afficheur);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        APIRest pokemonRestApi = retrofit.create(APIRest.class);


        Call<RestPokemon> call = pokemonRestApi.getListPokemon();
        call.enqueue(new Callback<RestPokemon>() {
            @Override
            public void onResponse(Call<RestPokemon> call, Response<RestPokemon> response) {
                RestPokemon restPokemon = response.body();
                List<Pokemon> listPokemon = restPokemon.getResults();
                showList(listPokemon);
            }

            @Override
            public void onFailure(Call<RestPokemon> call, Throwable t) {
                Log.d("Erreur", "API KO");
            }
        });
    }

    public void showList(List<Pokemon> list) {
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        // use this setting to
        // improve performance if you know that changes
        // in content do not change the layout size
        // of the RecyclerView
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // define an adapter
        mAdapter = new MyAdapter(list, getListener());
        recyclerView.setAdapter(mAdapter);
    }

    private MyAdapter.OnItemClickListener getListener() {
        return new MyAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }

            public void onItemClick(Pokemon item) {
                Gson gson = new Gson();

                Intent intent = new Intent(getBaseContext(), AttributAfficheur.class);
                intent.putExtra(Constants.KEY, gson.toJson(item));
                startActivity(intent);
            }
        };
    }
}
