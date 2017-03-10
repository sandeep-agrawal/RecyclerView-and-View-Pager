package com.askme.askmeassignment.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.askme.askmeassignment.R;
import com.squareup.picasso.Picasso;

/**
 * Created by sandeeplabs108 on 28/05/16.
 */
public class ImageFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_IMAGE_URL = "image_url";
    private Context context;
    private String image_url;
    public ImageFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ImageFragment newInstance(String image) {
        ImageFragment fragment = new ImageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_IMAGE_URL, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            image_url = getArguments().getString(ARG_IMAGE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_image, container, false);
        ImageView image = (ImageView) rootView.findViewById(R.id.image);
        Log.e("Pager Item Image",""+image_url);
        if(!TextUtils.isEmpty(image_url))
        Picasso.with(context).load(image_url).into(image);
        return rootView;
    }
}
