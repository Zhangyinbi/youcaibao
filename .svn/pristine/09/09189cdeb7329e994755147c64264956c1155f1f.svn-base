package com.youjuke.optimalmaterialtreasure.app.site;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.SiteInfo;

import java.util.ArrayList;

/**
 * 描述：工地地址的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class SiteAdapter extends RecyclerView.Adapter<SiteAdapter.MyHolder> {
    private Context context;
    private ArrayList<SiteInfo> datas;

    public SiteAdapter(Context context, ArrayList<SiteInfo> datas) {
        this.context = context;
        this.datas = datas;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_site_adapter, parent, false));
        return myHolder;
    }
    @Override
    public void onBindViewHolder(final MyHolder holder, final int position) {
        holder.tv_name.setText(datas.get(position).getYezhu_name());
        holder.tv_address.setText(datas.get(position).getAddress());
        holder.tv_mobile.setText(datas.get(position).getYezhu_mobile());
        holder.tv_start_time.setText("开工时间："+datas.get(position).getStart_time());
        holder.tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.deleteClickListener(datas.get(position).getGd_id(),position);
                }
            }
        });
        holder.tv_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener!=null){
                    onClickListener.editClickListener(datas.get(position).getGd_id());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setdata(ArrayList<SiteInfo> datas) {
        this.datas = datas;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private TextView tv_mobile;
        private TextView tv_start_time;
        private TextView tv_address;
        private TextView tv_edit;
        private TextView tv_delete;
        public MyHolder(View itemView) {
            super(itemView);
            tv_name= (TextView) itemView.findViewById(R.id.tv_name);
            tv_mobile= (TextView) itemView.findViewById(R.id.tv_mobile);
            tv_start_time= (TextView) itemView.findViewById(R.id.tv_start_time);
            tv_address= (TextView) itemView.findViewById(R.id.tv_address);
            tv_edit= (TextView) itemView.findViewById(R.id.tv_edit);
            tv_delete= (TextView) itemView.findViewById(R.id.tv_delete);
        }
    }
    private OnClickListener onClickListener;
    public void setOnClickListener(OnClickListener onItemClickListener){
        this.onClickListener=onItemClickListener;
    }
    public interface OnClickListener{
        void deleteClickListener(int gd_id,int position);
        void editClickListener(int gd_id);
    }
}
