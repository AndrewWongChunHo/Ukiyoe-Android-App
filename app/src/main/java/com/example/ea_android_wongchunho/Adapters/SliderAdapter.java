package com.example.ea_android_wongchunho.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.ea_android_wongchunho.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>{

    //The array of images
    int[] images;

    //Constructor
    public SliderAdapter(int[] images){

        this.images = images;

    }

    //Link slider_item to RecyclerView
    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.slider_item,parent,false);
        return new Holder(view);
    }

    //Return the position of the images
    @Override
    public void onBindViewHolder(Holder viewHolder, int position) {

        viewHolder.imageView.setImageResource(images[position]);

    }

    //Count the length of the array
    @Override
    public int getCount() {
        return images.length;
    }

    //View Holder
    public class Holder extends SliderViewAdapter.ViewHolder{

        ImageView imageView;

        public Holder(View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);

        }
    }

}
