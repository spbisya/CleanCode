package okunev.com.cleancode.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import okunev.com.cleancode.views.ModelView;
import okunev.com.cleancode.R;
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
        itemViewHolder.modelView.setName(items.get(i).getName());
        itemViewHolder.modelView.setDescription(items.get(i).getDescription());
        itemViewHolder.modelView.setLink(items.get(i).getLink());
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        ModelView modelView;

        ItemViewHolder(View v) {
            super(v);
            modelView = (ModelView) itemView.findViewById(R.id.modelView);
        }
    }

}