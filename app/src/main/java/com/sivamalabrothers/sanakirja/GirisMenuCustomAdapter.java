package com.sivamalabrothers.sanakirja;


import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class GirisMenuCustomAdapter extends RecyclerView.Adapter<GirisMenuCustomAdapter.MyViewHolder> {


    ArrayList<GirisMenuItem> mDataList;
    LayoutInflater inflater;
    int pos;
    Context context;

    public GirisMenuCustomAdapter(Context context, ArrayList<GirisMenuItem> data){

        this.context = context;
        //inflater = (Layoutiflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater = LayoutInflater.from(context);
        this.mDataList = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = inflater.inflate(R.layout.giris_list_item,parent,false);
        MyViewHolder holder = new MyViewHolder(v);

        return holder;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        GirisMenuItem tiklananItem = mDataList.get(position);
        holder.setData(tiklananItem,position);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public void tiklananMenuCagir(int pos){
        GirisSayfasi girisSayfasi;
        girisSayfasi = (GirisSayfasi) context;
        girisSayfasi.tiklananMenuItem(pos);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mText;
        ImageView mImg;
        int tiklananPos;
        LinearLayout list_layout;




        public MyViewHolder(View itemView) {
            super(itemView);


            list_layout = itemView.findViewById(R.id.list_layout);
            mImg = itemView.findViewById(R.id.list_img);
            mText = itemView.findViewById(R.id.list_text);
            mText.setTextColor(context.getResources().getColor(R.color.kizildereli));

            list_layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiklananMenuCagir(tiklananPos);
                }
            });
            mText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiklananMenuCagir(tiklananPos);
                }
            });

            mImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    tiklananMenuCagir(tiklananPos);
                }
            });

        }

        public void setData(GirisMenuItem tiklananItem, int position) {
            this.mImg.setImageResource(tiklananItem.getImgId());
            Typeface font = Typeface.createFromAsset(context.getAssets(),"fonts/fofbb_reg.ttf");
            mText.setTextSize(16);
            mText.setTypeface(font, Typeface.BOLD);
            this.mText.setText(tiklananItem.getAdi());
            this.tiklananPos = position;
        }



    }
}
