package com.askme.askmeassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.askme.askmeassignment.R;
import com.askme.askmeassignment.entity.ItemEntity;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by sandeeplabs108 on 16/05/16.
 */
public class SliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ItemEntity> itemEntities;
    private Context context;
    private OnItemClickListener mItemClickListener;
    public SliderAdapter(Context context , List<ItemEntity> notesEntities) {
        this.itemEntities = notesEntities;
        this.context = context;
    }

    @Override
    public int getItemCount() {
        return itemEntities.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            ItemEntity itemEntity = itemEntities.get(position);
            itemViewHolder.productName.setText(itemEntity.getLabel());
            Log.e("Item Image",""+itemEntity.getImage());
            if(!TextUtils.isEmpty(itemEntity.getImage()))
            Picasso.with(context).load(itemEntity.getImage()).into(itemViewHolder.productImage);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.recycler_card_layout, parent, false);
            return new ItemViewHolder (v);

    }

    public  class ItemViewHolder extends RecyclerView.ViewHolder {

        protected TextView productName;
        protected ImageView productImage;

        public ItemViewHolder(View v) {
            super(v);
            productName =  (TextView) v.findViewById(R.id.product_name);
            productImage = (ImageView)  v.findViewById(R.id.product_image);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null)
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                }
            });

        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

}
