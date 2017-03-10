package com.youjuke.optimalmaterialtreasure.app.goods;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.youjuke.library.base.BaseRecyclerAdapter;
import com.youjuke.library.base.BaseRecyclerViewHolder;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.GoodsCallBack;
import com.youjuke.optimalmaterialtreasure.entity.GoodsList;
import com.youjuke.optimalmaterialtreasure.weights.AmountView;
import com.zhy.autolayout.utils.AutoUtils;

import java.util.List;

/**
 * 描述: 购物车订单适配
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-13 10:20
 */
public class GoodsAdapter extends BaseRecyclerAdapter<GoodsList.ShopcartInfoBean>  {

    private GoodsCallBack goodsCallBack;

    public GoodsAdapter(Context context, List<GoodsList.ShopcartInfoBean> datas) {
        super(context, datas);
        goodsCallBack=new GoodsCallBack();
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void showViewHolder(BaseRecyclerViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        GoodsList.ShopcartInfoBean shopcartInfoBean=mDatas.get(position);
        if ("1".equals(shopcartInfoBean.getIsChoose()+"")){
            viewHolder.ivSelectChoose.setImageResource(R.mipmap.btn_xzzt);
        }else if ("0".equals(shopcartInfoBean.getIsChoose()+"")){
            viewHolder.ivSelectChoose.setImageResource(R.mipmap.btn_wxzzt);
        }
        viewHolder.tvMaterialPrice.setText("￥"+shopcartInfoBean.getMaterialPrice().trim());
        viewHolder.tvBrandName.setText(shopcartInfoBean.getBrandName().trim()+"     颜色:   "
                +shopcartInfoBean.getMaterialColor());
        viewHolder.tvMaterialName.setText(shopcartInfoBean.getMaterialName().trim());
        viewHolder.tvMaterialModel.setText(shopcartInfoBean.getMaterialModel().trim());
        viewHolder.tvMaterialNorms.setText(shopcartInfoBean.getMaterialNorms().trim());

        viewHolder.AvCount.setAmount(shopcartInfoBean.getCount().trim());
        viewHolder.tvNum.setText("x"+shopcartInfoBean.getCount().trim());
        mOnItemClickListener.onItemClick(viewHolder,position,shopcartInfoBean);
    }

    @Override
    protected BaseRecyclerViewHolder createViewHOldeHolder(ViewGroup parent, int viewType) {
        View view = null;
        ViewHolder holder = null;
        view = mInflater.inflate(R.layout.recycler_view_item_shopping_cart, parent, false);
        holder = new ViewHolder(view);
        return holder;
    }



    class ViewHolder extends BaseRecyclerViewHolder  {

        private View view;
        private ImageView ivSelectChoose;
        private TextView tvMaterialName;
        private TextView tvBrandName;
        private TextView tvMaterialModel;
        private TextView tvMaterialNorms;
        private TextView tvMaterialPrice;
        private TextView tvCompile;
        private ImageView ivDeleteShopping;
        private TextView tvNum;
        private AmountView AvCount;

        private void assignViews() {
            ivSelectChoose = (ImageView)view.findViewById(R.id.iv_select_choose);
            tvMaterialName = (TextView) view.findViewById(R.id.tv_materialName);
            tvBrandName = (TextView) view.findViewById(R.id.tv_brandName);
            tvMaterialModel = (TextView)view. findViewById(R.id.tv_materialModel);
            tvMaterialNorms = (TextView) view.findViewById(R.id.tv_materialNorms);
            tvMaterialPrice = (TextView) view.findViewById(R.id.tv_materialPrice);
            tvCompile = (TextView) view.findViewById(R.id.tv_compile);
            ivDeleteShopping = (ImageView) view.findViewById(R.id.iv_delete_shopping);
            tvNum = (TextView) view.findViewById(R.id.tv_num);
            AvCount = (AmountView) view.findViewById(R.id.Av_count);
        }


        public ViewHolder(View mview) {
            super(mview);
            AutoUtils.autoSize(mview);
            view = mview;
            assignViews();
        }


    }
}
