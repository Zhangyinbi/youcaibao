package com.youjuke.optimalmaterialtreasure.app.catalogue;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.MaterialsClassify;

import java.util.ArrayList;

/**
 * 描述：二级分类列表的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class BrandsAdapter extends RecyclerView.Adapter<BrandsAdapter.MyHolder> {
    private Context context;
    private ArrayList<MaterialsClassify> datas;

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    int cid=-1;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    int current;


    public BrandsAdapter(Context context, ArrayList<MaterialsClassify> datas,int cid) {
        this.context = context;
        this.datas = datas;
        this.cid=cid;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_brands, parent, false));
        return myHolder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.name.setText(datas.get(position).getName());
        if (cid==-1){
            if (current==position){
                holder.name.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.name.setTextColor(Color.parseColor("#ee0005"));
                if (onItemClickListener!=null) {
                    onItemClickListener.ItemClickListener(datas.get(position).getCid());
                }
            }else {
                holder.name.setBackgroundColor(Color.parseColor("#f3f6f6"));
                holder.name.setTextColor(Color.parseColor("#333333"));
            }
        }else {
            if (datas.get(position).getCid()==(cid)){
                holder.name.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.name.setTextColor(Color.parseColor("#ee0005"));
                if (onItemClickListener!=null) {
                    onItemClickListener.ItemClickListener(datas.get(position).getCid());
                }
            }else {
                holder.name.setBackgroundColor(Color.parseColor("#f3f6f6"));
                holder.name.setTextColor(Color.parseColor("#333333"));
            }
        }
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cid=datas.get(position).getCid();
                current=position;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setdata(ArrayList<MaterialsClassify> datas) {
        this.datas = datas;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public MyHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
        }
    }
    private OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
    public interface OnItemClickListener{
        void ItemClickListener(int  cid);
    }
}
