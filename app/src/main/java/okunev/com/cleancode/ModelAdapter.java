package okunev.com.cleancode;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.List;

import okunev.com.cleancode.models.CustomModel;

/**
 * Project CleanCode. Created by gwa on 9/23/16.
 */

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.ItemViewHolder> {
    private List<CustomModel> items;

    public ModelAdapter(List<CustomModel> items) {
        this.items = items;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.model_item, viewGroup, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder itemViewHolder, int i) {
        final int position =i;
        itemViewHolder.name.setText(items.get(position).getName());
        itemViewHolder.description.setText(items.get(position).getDescription());
        itemViewHolder.link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse(items.get(position).getLink())));
            }
        });
        itemViewHolder.link.setText(items.get(position).getLink());
        itemViewHolder.link.setTextColor(Color.BLUE);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView link;
        TextView description;

        ItemViewHolder(View v) {
            super(v);
            name = (TextView) itemView.findViewById(R.id.modelName);
            link = (TextView) itemView.findViewById(R.id.modelLink);
            description = (TextView) itemView.findViewById(R.id.modelDescription);
        }
    }

}