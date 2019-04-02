package com.example.app.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.Model.Pokemon;
import com.example.app.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Pokemon> values;
    Context context;

    private final OnItemClickListener listener;

    public interface OnItemClickListener extends AdapterView.OnItemClickListener {
        void onItemClick(Pokemon item);
    }


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView txtHeader;
        public ImageView img;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = v.findViewById(R.id.firstLine);
            img = v.findViewById(R.id.icon);
        }

    }



    // Provide a suitable constructor (depends on the kind of dataset)

    public MyAdapter(List<Pokemon> values, AdapterView.OnItemClickListener listener, Context context) {
        this.values = values;
        this.listener = (OnItemClickListener) listener;
        this.context = context;
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        final Pokemon pokemon = values.get(position);
        holder.txtHeader.setText(pokemon.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onItemClick(pokemon);
            }
        });

        holder.txtHeader.setText(pokemon.getName());

        String[] values = pokemon.getUrl().split("/");

        Glide.with(context)
                .load("https://www.pokebip.com/pokedex-images/artworks/"+values[values.length - 1]+".png")
                .into(holder.img);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() { return values.size(); }

}