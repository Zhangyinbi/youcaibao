package com.youjuke.optimalmaterialtreasure.app.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.youjuke.library.utils.DensityUtil;
import com.youjuke.library.utils.MoneySimpleFormat;
import com.youjuke.library.utils.NumUtil;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.app.catalogue.GoodsDetailActivity;
import com.youjuke.optimalmaterialtreasure.entity.Index;

import java.util.ArrayList;

/**
 * 描述：商品的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class GoodsAdapter extends RecyclerView.Adapter<GoodsAdapter.MyHolder> {
    private Context context;
    private ArrayList<Index.Goods> datas;

    public GoodsAdapter(Context context, ArrayList<Index.Goods> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_home_popularity, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        Glide.with(context).load(datas.get(position).getImgUrl())
                .placeholder(R.mipmap.btn_tp).into(holder.iv_pic);
        holder.tv_classify.setText(datas.get(position).getName());
        if (!NumUtil.isNumber(datas.get(position).getPrice())) {
            holder.tv_price.setText(datas.get(position).getPrice());
            /*if (datas.get(position).getPrice().contains("登录可见")){
                SPUtil.put(context, "isLoadingStatus", false);
            }*/
        } else {
//            SPUtil.put(context, "isLoadingStatus", true);
            String money = MoneySimpleFormat.getMoneyType(datas.get(position).getPrice());
            SpannableString span = new SpannableString(money);
            span.setSpan(new AbsoluteSizeSpan(DensityUtil.sp2px(context,18)), 1, money.lastIndexOf("."), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            holder.tv_price.setText(span);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoodsDetailActivity.class);
                intent.putExtra("material_config_id", datas.get(position).getMaterial_config_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setdata(ArrayList<Index.Goods> datas) {
        this.datas = datas;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private ImageView iv_pic;
        private TextView tv_classify;
        private TextView tv_price;
        private ImageView iv_cart;

        public MyHolder(View itemView) {
            super(itemView);
            iv_cart = (ImageView) itemView.findViewById(R.id.iv_cart);
            iv_pic = (ImageView) itemView.findViewById(R.id.iv_pic);
            tv_price = (TextView) itemView.findViewById(R.id.tv_price);
            tv_classify = (TextView) itemView.findViewById(R.id.tv_classify);
        }
    }
}
