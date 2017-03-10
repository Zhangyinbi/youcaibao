package com.youjuke.optimalmaterialtreasure.app.catalogue;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youjuke.library.utils.MoneySimpleFormat;
import com.youjuke.library.utils.NumUtil;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.MaterialDetails;

import java.util.ArrayList;

/**
 * 描述：选择商品规格的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class GoodsDetailAdapter extends RecyclerView.Adapter<GoodsDetailAdapter.MyHolder> {
    private Context context;
    private ArrayList<MaterialDetails> datas;

    public GoodsDetailAdapter(Context context, ArrayList<MaterialDetails> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_goods_detail_adapter, parent, false));
        return myHolder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv_brand.setText(datas.get(position).getBrand());
        holder.tv_material_color.setText(datas.get(position).getMaterial_color());
        holder.tv_material_model.setText(datas.get(position).getMaterial_model());
        holder.tv_material_norms.setText(datas.get(position).getMaterial_norms());
        holder.tv_title.setText(datas.get(position).getTitle());
        if (!NumUtil.isNumber(datas.get(position).getMaterial_price())){
            holder.tv_price.setText(datas.get(position).getMaterial_price());
        }else{
            String money = MoneySimpleFormat.getMoneyType(datas.get(position).getMaterial_price());
            SpannableString span = new SpannableString(money);
            span.setSpan(new AbsoluteSizeSpan(36), 1, money.lastIndexOf("."), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.tv_price.setText(span);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.ItemClickListener(datas.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setdata(ArrayList<MaterialDetails> datas) {
        this.datas = datas;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv_price;
        private TextView tv_material_norms;
        private TextView tv_material_model;
        private TextView tv_material_color;
        private TextView tv_brand;
        private TextView tv_title;
        public MyHolder(View itemView) {
            super(itemView);
            tv_price= (TextView) itemView.findViewById(R.id.tv_price);
            tv_material_norms= (TextView) itemView.findViewById(R.id.tv_material_norms);
            tv_material_model= (TextView) itemView.findViewById(R.id.tv_material_model);
            tv_material_color= (TextView) itemView.findViewById(R.id.tv_material_color);
            tv_brand= (TextView) itemView.findViewById(R.id.tv_brand);
            tv_title= (TextView) itemView.findViewById(R.id.tv_title);

        }
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void ItemClickListener(MaterialDetails gooddetail);
    }
}
