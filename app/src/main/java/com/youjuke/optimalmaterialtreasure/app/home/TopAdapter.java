package com.youjuke.optimalmaterialtreasure.app.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.catalogue.GoodsListActivity;

/**
 * 描述：首页顶部的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class TopAdapter extends RecyclerView.Adapter<TopAdapter.MyHolder> {
    private Context context;

    public TopAdapter(Context context) {
        this.context = context;
    }

    private int[] img = {R.mipmap.btn_shui, R.mipmap.btn_dian, R.mipmap.btn_ni, R.mipmap.btn_mu, R.mipmap.btn_you, R.mipmap.btn_wujin,};
    private String[] title = {"水", "电", "泥", "木", "油", "五金",};

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_home_top, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.ivPic.setImageResource(img[position]);
        holder.tvName.setText(title[position]);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsListActivity.class);
                intent.putExtra("fid",position+5);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }
    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView ivPic;
        private TextView tvName;

        public MyHolder(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tvName = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
}
