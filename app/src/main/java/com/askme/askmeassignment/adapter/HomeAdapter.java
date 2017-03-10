package com.askme.askmeassignment.adapter;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.askme.askmeassignment.R;
import com.askme.askmeassignment.entity.ProductEntity;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by sandeeplabs108 on 16/05/16.
 */
public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private List<ProductEntity> productEntities;
    private Context context;
    private FragmentManager fragmentManager;
    private OnItemClickListener mItemClickListener;
    private static final int TYPE_FIRST = 0;
    private static final int TYPE_SECOND = 1;
    private static final int TYPE_THIRD = 2;
    private int Id = 1000;
    private static final String TEMPLATE_FIRST ="product-template-1";
    private static final String TEMPLATE_SECOND ="product-template-2";
    private static final String TEMPLATE_THIRD ="product-template-3";
    public HomeAdapter(Context context , FragmentManager fragmentManager ,List<ProductEntity> productEntities) {
        this.productEntities = productEntities;
        this.context = context;
        this.fragmentManager = fragmentManager;
    }

    @Override
    public int getItemCount() {
        Log.e("Item count",""+productEntities.size());
        return productEntities.size();
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ImageViewHolder) {
            ImageViewHolder imageViewHolder = (ImageViewHolder) holder;
            ProductEntity productEntity = productEntities.get(position);
            Log.e("Template Item Image",""+productEntity.getItemEntities().get(0).getImage());
            Picasso.with(context).load(productEntity.getItemEntities().get(0).getImage()).into(imageViewHolder.imageView);
        } else if(holder instanceof PagerViewHolder) {
            PagerViewHolder pagerViewHolder = (PagerViewHolder) holder;
            ProductEntity productEntity = productEntities.get(position);
            setUiPageViewController(pagerViewHolder,productEntity.getItemEntities().size());
            pagerViewHolder.viewPager.setAdapter(new PagerAdapter(fragmentManager,productEntity.getItemEntities()));
        } else if (holder instanceof HorizontalViewHolder) {
            HorizontalViewHolder horizontalViewHolder = (HorizontalViewHolder) holder;
            ProductEntity productEntity = productEntities.get(position);
            horizontalViewHolder.title.setText(productEntity.getLabel());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            horizontalViewHolder.recyclerView.setLayoutManager(linearLayoutManager);
            horizontalViewHolder.recyclerView.setAdapter(new SliderAdapter(context,productEntity.getItemEntities()));
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
         if(viewType == TYPE_FIRST) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.content_image, parent, false);
            return new ImageViewHolder (v);
        } else if(viewType == TYPE_SECOND) {
            View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.content_horizontal, parent, false);
            return new HorizontalViewHolder (v);
        } else {
             View v = LayoutInflater.from (parent.getContext ()).inflate (R.layout.content_pager, parent, false);
             return new PagerViewHolder(v);
         }
    }

    @Override
    public int getItemViewType (int position) {
        if(productEntities.get(position).getTemplate().equalsIgnoreCase(TEMPLATE_FIRST)) {
            Log.e("Item type",""+TYPE_FIRST);
            return TYPE_FIRST;
        } else if(productEntities.get(position).getTemplate().equalsIgnoreCase(TEMPLATE_SECOND)) {
            Log.e("Item type",""+TYPE_SECOND);
            return TYPE_SECOND;
        } else {
            Log.e("Item type",""+TYPE_THIRD);
            return TYPE_THIRD;
        }
    }

    class PagerViewHolder extends RecyclerView.ViewHolder {
        protected ViewPager viewPager;
        protected LinearLayout viewPagerCountDots;
        public PagerViewHolder (View itemView) {
            super (itemView);
            this.viewPagerCountDots = (LinearLayout)itemView.findViewById(R.id.viewPagerCountDots);
            this.viewPager = (ViewPager) itemView.findViewById (R.id.viewpager);
            this.viewPager.setId(Id++);
        }
    }

    class ImageViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;

        public ImageViewHolder (View itemView) {
            super (itemView);
            this.imageView = (ImageView) itemView.findViewById (R.id.image);
        }
    }

    public  class HorizontalViewHolder extends RecyclerView.ViewHolder {

        protected RecyclerView recyclerView;
        protected TextView title;
        public HorizontalViewHolder(View v) {
            super(v);
            recyclerView = (RecyclerView)v.findViewById(R.id.horizontallist);
            title = (TextView)v.findViewById(R.id.title);
        }
    }

    private void setUiPageViewController(PagerViewHolder pagerViewHolder,final int count) {

        final ImageView[] dots = new ImageView[count];

        for (int i = 0; i < count; i++) {
            dots[i] = new ImageView(context);
            dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(4, 0, 4, 0);

            pagerViewHolder.viewPagerCountDots.addView(dots[i], params);
        }

        dots[0].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));

        pagerViewHolder.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < count; i++) {
                    dots[i].setImageDrawable(context.getResources().getDrawable(R.drawable.nonselecteditem_dot));
                }

                dots[position].setImageDrawable(context.getResources().getDrawable(R.drawable.selecteditem_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setProduct(List<ProductEntity> data) {
        productEntities.clear();
        productEntities.addAll(data);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
