package com.youjuke.optimalmaterialtreasure.app.order;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.youjuke.library.MyPickView.CustomListener;
import com.youjuke.library.MyPickView.OptionsPickerView;
import com.youjuke.library.MyPickView.WheelView;
import com.youjuke.library.utils.DensityUtil;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.entity.CardBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/24.
 */

public class ReasonDialog {
    private ArrayList<CardBean> cardItem = new ArrayList<>();
    private static ReasonDialog reasonDialog;
    private Context context;
    OptionsPickerView pvCustomOptions;

    public ReasonDialog(Context context) {
        this.context = context;
    }

    public ReasonDialog setData(ArrayList<CardBean> cardItem) {
        this.cardItem = cardItem;
        return this;
    }

    public static ReasonDialog getInstance(Context context) {
        reasonDialog = new ReasonDialog(context);
        return reasonDialog;
    }

    public void show() {
        initCustomOptionPicker();
    }

    private void initCustomOptionPicker() {//条件选择器初始化，自定义布局
        // 注意：自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针
        // 具体可参考demo 里面的两个自定义布局
        if (pvCustomOptions == null) {
            pvCustomOptions = new OptionsPickerView.Builder(context, new OptionsPickerView.OnOptionsSelectListener() {
                @Override
                public void onOptionsSelect(int options1, int option2, int options3, View v) {
                    //返回的分别是三个级别的选中位置
                    if (onCancelListener != null) {
                        onCancelListener.CancelListener(cardItem.get(options1).getId());
                        reasonDialog=null;
                    }

                }
            }).setLayoutRes(R.layout.pickerview_custom_options, new CustomListener() {
                @Override
                public void customLayout(View v) {
                    final TextView tvFinish = (TextView) v.findViewById(R.id.tv_finish);
                    tvFinish.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            pvCustomOptions.returnData();
                        }
                    });
                }
            }).isDialog(true).setTextColorOut(Color.parseColor("#808080"))
                    .setTextColorCenter(Color.parseColor("#222222")).setContentTextSize(DensityUtil.sp2px(context, 11))
                    .setSelectOptions(0)
                    .setDividerType(WheelView.DividerType.FILL)
                    .setDividerColor(Color.parseColor("#00508cee"))
                    .setBgColor(Color.parseColor("#ffffffff"))
                    .build();
            pvCustomOptions.setPicker(cardItem);
        }
        pvCustomOptions.show();

    }

    OnCancelListener onCancelListener;

    public ReasonDialog setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
        return this;
    }

}
