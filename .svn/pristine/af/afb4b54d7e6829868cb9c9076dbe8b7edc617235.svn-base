package com.youjuke.optimalmaterialtreasure.app.catalogue;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.MaterialGoodsDetails;

import java.util.ArrayList;

/**
 * 描述：商品列表的三级分类的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MyHolder> {
    private Context context;
    private ArrayList<MaterialGoodsDetails> datas;

    public MaterialAdapter(Context context, ArrayList<MaterialGoodsDetails> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_material, parent, false));
        return myHolder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.name.setText(datas.get(position).getMaterial_name());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null)
                    onItemClickListener.ItemClickListener(datas.get(position).getMaterial_config_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setdata(ArrayList<MaterialGoodsDetails> datas) {
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
        void ItemClickListener(int material_config_id);
    }
}
