package com.youjuke.optimalmaterialtreasure.app.order;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youjuke.library.base.BaseRecyclerAdapter;
import com.youjuke.library.base.BaseRecyclerViewHolder;
import com.youjuke.library.utils.L;
import com.youjuke.library.utils.MoneySimpleFormat;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.MaterialsBean;
import com.youjuke.optimalmaterialtreasure.entity.OrderDetail;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * 描述: 适配器
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-03-27 18:14
 */
public class ExamineOrderDetailsAdapter extends BaseRecyclerAdapter {
    private MaterialsBean materialsBean;
    public ExamineOrderDetailsAdapter(Context context, List datas) {
        super(context, datas);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) {
        L.d("加载");
            materialsBean= (MaterialsBean) mDatas.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        if (materialsBean.getTip()==null||"".equals(materialsBean.getTip().trim())){
            viewHolder.ll_LeaveMessage.setVisibility(View.GONE);
        }else {
            viewHolder.ll_LeaveMessage.setVisibility(View.VISIBLE);
            viewHolder.mTvLeaveMessage.setText(materialsBean.getTip().trim());
        }
        viewHolder.mTvBrandName.setText(materialsBean.getBrandName().trim());
        viewHolder.mTvMaterialCount.setText(materialsBean.getMaterialCount().trim());
        viewHolder.mTvMaterialModel.setText(materialsBean.getMaterialModel().trim());
        viewHolder.mTvMaterialName.setText(materialsBean.getMaterialName().trim());
        viewHolder.mTvMaterialNorms.setText(materialsBean.getMaterialNorms().trim());
        viewHolder.mTvMaterialPrice.setText(MoneySimpleFormat.getMoneyType(materialsBean.getMaterialPrice()));
    }       

    @Override
    protected BaseRecyclerViewHolder createViewHOldeHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder holder = null;
        view = mInflater.inflate(R.layout.recycler_view_item_examine_order, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }


    static class ViewHolder extends BaseRecyclerViewHolder {
        private View view;
        private TextView mTvMaterialName;
        private TextView mTvBrandName;
        private TextView mTvMaterialModel;
        private TextView mTvMaterialNorms;
        private TextView mTvMaterialPrice;
        private TextView mTvMaterialCount;
        private TextView mTvLeaveMessage;
        private AutoLinearLayout ll_LeaveMessage;

        public ViewHolder(View mview) {
            super(mview);
            this.view = mview;
            this.ll_LeaveMessage = (AutoLinearLayout) view.findViewById(R.id.ll_LeaveMessage);
            this.mTvMaterialName = (TextView) view.findViewById(R.id.tv_materialName);
            this.mTvBrandName = (TextView) view.findViewById(R.id.tv_brandName);
            this.mTvMaterialModel = (TextView) view.findViewById(R.id.tv_materialModel);
            this.mTvMaterialNorms = (TextView) view.findViewById(R.id.tv_materialNorms);
            this.mTvMaterialPrice = (TextView) view.findViewById(R.id.tv_materialPrice);
            this.mTvMaterialCount = (TextView) view.findViewById(R.id.tv_material_count);
            this.mTvLeaveMessage = (TextView) view.findViewById(R.id.tv_leave_message);
        }
    }
}
