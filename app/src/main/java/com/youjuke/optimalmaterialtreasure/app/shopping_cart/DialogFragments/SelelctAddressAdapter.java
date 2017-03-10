package com.youjuke.optimalmaterialtreasure.app.shopping_cart.DialogFragments;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.youjuke.library.base.BaseRecyclerAdapter;
import com.youjuke.library.base.BaseRecyclerViewHolder;
import com.youjuke.library.utils.L;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.SiteInfo;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * 描述: TODO
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-17 13:36
 */
public class SelelctAddressAdapter extends BaseRecyclerAdapter<SiteInfo>{


    public SelelctAddressAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        L.d("选择地址集合"+mDatas.size());
            viewHolder.tvName.setText(mDatas.get(position).getYezhu_name());
            viewHolder.tvAddress.setText(mDatas.get(position).getAddress());
            viewHolder.tvMobile.setText(mDatas.get(position).getYezhu_mobile());
            viewHolder.tvStartTime.setText("开工时间：" + mDatas.get(position).getStart_time());
            mOnItemClickListener.onItemClick(viewHolder, position, mDatas.get(position));
    }

    @Override
    protected BaseRecyclerViewHolder createViewHOldeHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder holder = null;
        view = mInflater.inflate(R.layout.recycler_view_item_address, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }



    class ViewHolder extends BaseRecyclerViewHolder {
        private View view;
        private AutoLinearLayout llItem;
        private TextView tvName;
        private TextView tvMobile;
        private TextView tvStartTime;
        private TextView tvAddress;

        private void assignViews() {
            llItem = (AutoLinearLayout)view.findViewById(R.id.ll_item);
            tvName = (TextView) view.findViewById(R.id.tv_name);
            tvMobile = (TextView) view.findViewById(R.id.tv_mobile);
            tvStartTime = (TextView)view.findViewById(R.id.tv_start_time);
            tvAddress = (TextView)view.findViewById(R.id.tv_address);
        }

        public ViewHolder(View mview) {
            super(mview);
            view=mview;
            assignViews();
        }
    }
}
