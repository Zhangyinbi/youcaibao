package com.youjuke.optimalmaterialtreasure.app.shopping_cart;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youjuke.library.base.BaseRecyclerAdapter;
import com.youjuke.library.base.BaseRecyclerViewHolder;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.MaterialsBean;

import java.util.List;

/**
 * 描述: 确认订单列表
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-20 10:43
 */
public class OrderConfirmationAdapter extends BaseRecyclerAdapter<MaterialsBean> {

    private MaterialsBean materialsBean;

    public OrderConfirmationAdapter(Context context, List<MaterialsBean> datas) {
        super(context, datas);
    }

    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, int position) {
        materialsBean=mDatas.get(position);
        ViewHolder viewHolder= (ViewHolder) holder;
        viewHolder.tvBrandName.setText(materialsBean.getBrandName().trim()+"    颜色:  "+
        materialsBean.getMaterialColor());
        viewHolder.tvMaterialModel.setText(materialsBean.getMaterialModel().trim());
        viewHolder.tvMaterialCount.setText(materialsBean.getMaterialCount().trim());
        viewHolder.tvMaterialName.setText(materialsBean.getMaterialName());
        viewHolder.tvMaterialPrice.setText("￥"+materialsBean.getMaterialPrice());
        viewHolder.tvMaterialNorms.setText(materialsBean.getMaterialNorms());
    }

    @Override
    protected BaseRecyclerViewHolder createViewHOldeHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder holder = null;
        view = mInflater.inflate(R.layout.recycler_view_item_order_confirmation, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }


    class ViewHolder extends BaseRecyclerViewHolder {
        private View view;
        private TextView tvMaterialName;
        private TextView tvBrandName;
        private TextView tvMaterialModel;
        private TextView tvMaterialNorms;
        private TextView tvMaterialPrice;
        private TextView tvMaterialCount;

        private void assignViews() {
            tvMaterialName = (TextView) view.findViewById(R.id.tv_materialName);
            tvBrandName = (TextView) view.findViewById(R.id.tv_brandName);
            tvMaterialModel = (TextView) view.findViewById(R.id.tv_materialModel);
            tvMaterialNorms = (TextView) view.findViewById(R.id.tv_materialNorms);
            tvMaterialPrice = (TextView) view.findViewById(R.id.tv_materialPrice);
            tvMaterialCount = (TextView) view.findViewById(R.id.tv_material_count);
        }

        public ViewHolder(View mview) {
            super(mview);
            view = mview;
            assignViews();
        }
    }
}
