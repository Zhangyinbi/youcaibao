package com.youjuke.optimalmaterialtreasure.app.order;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.youjuke.library.utils.MoneySimpleFormat;
import com.youjuke.library.utils.NumUtil;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.OrderInfo;

import java.util.ArrayList;

/**
 * 描述：详情界面的adapter
 * author：zyb
 * Created by Administrator on 2017/2/10.
 */

public class Orderdapter extends RecyclerView.Adapter<Orderdapter.MyHolder> {
    private Context context;
    private ArrayList<OrderInfo> datas;

    public Orderdapter(Context context, ArrayList<OrderInfo> datas) {
        this.context = context;
        this.datas = datas;
    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        MyHolder myHolder = new MyHolder(inflater.inflate(R.layout.item_order_list, parent, false));
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {
        holder.tv_no.setText("订单编号：" + datas.get(position).getNo());
        holder.tv_status.setText("状态：" + datas.get(position).getOrder_status());
        if ("待付款".equals(datas.get(position).getOrder_status())) {
            holder.tv_status.setTextColor(Color.parseColor("#ee0005"));
            holder.btn_hide_or_show.setVisibility(View.VISIBLE);
        } else if ("待收货".equals(datas.get(position).getOrder_status())) {
            holder.tv_status.setTextColor(Color.parseColor("#ee0005"));
            holder.btn_hide_or_show.setVisibility(View.GONE);
        } else {
            holder.tv_status.setTextColor(Color.parseColor("#808080"));
            holder.btn_hide_or_show.setVisibility(View.GONE);
        }
        holder.tv_goods.setText("共计" + datas.get(position).getAmount() + "件商品，合计：" +
                MoneySimpleFormat.getMoneyType(datas.get(position).getPayable()));
        holder.btn_hide_or_show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPayClickListener != null)
                    onPayClickListener.onPayClickListener(datas.get(position).getOrder_id());
            }
        });
        holder.linearLayout.removeAllViews();
        for (int i = 0; i < datas.get(position).getMaterials_list().size(); i++) {
            holder.linearLayout.addView(addView(datas.get(position).getMaterials_list().get(i)));
        }
    }

    private OnPayClickListener onPayClickListener;

    public void setOnPayClickListener(OnPayClickListener onPayClickListener) {
        this.onPayClickListener = onPayClickListener;
    }

    public interface OnPayClickListener {
        void onPayClickListener(String Order_id);
    }

    private View addView(OrderInfo.GoodsInfo goodsInfo) {
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_order_detail, null);
        TextView tv_name = (TextView) view.findViewById(R.id.tv_name);
        TextView tv_band = (TextView) view.findViewById(R.id.tv_band);
        TextView tv_norms = (TextView) view.findViewById(R.id.tv_norms);
        TextView tv_model = (TextView) view.findViewById(R.id.tv_model);
        TextView tv_price = (TextView) view.findViewById(R.id.tv_price);
        TextView tv_count = (TextView) view.findViewById(R.id.tv_count);
        tv_name.setText(goodsInfo.getMat_name());
        tv_band.setText("品牌：" + goodsInfo.getMat_brand()+"    颜色："+goodsInfo.getMat_color());
        tv_norms.setText("型号：" + goodsInfo.getMat_norms());
        tv_model.setText("规格：" + goodsInfo.getMat_model());
        if (!NumUtil.isNumber(goodsInfo.getMat_price())) {
            tv_price.setText(goodsInfo.getMat_price());
        } else {
            String money = MoneySimpleFormat.getMoneyType(goodsInfo.getMat_price());
            SpannableString span = new SpannableString(money);
            span.setSpan(new AbsoluteSizeSpan(34), 1, money.lastIndexOf("."), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            tv_price.setText(span);
        }

        tv_count.setText("数量：" + goodsInfo.getCount());

        view.setLayoutParams(lp);
        return view;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setdata(ArrayList<OrderInfo> datas) {
        this.datas = datas;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        private TextView tv_no;//订单编号
        private TextView tv_status;//状态
        private TextView tv_goods;//总计数量和金额
        private Button btn_hide_or_show;//付款按钮
        private LinearLayout linearLayout;

        public MyHolder(View itemView) {
            super(itemView);
            tv_no = (TextView) itemView.findViewById(R.id.tv_no);
            tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_goods = (TextView) itemView.findViewById(R.id.tv_goods);
            btn_hide_or_show = (Button) itemView.findViewById(R.id.btn_hide_or_show);
            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
