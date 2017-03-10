package com.youjuke.optimalmaterialtreasure.weights;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.optimalmaterialtreasure.R;
import com.zhy.autolayout.AutoLinearLayout;

/**
 * 描述: 购物车数量控件
 * ------------------------------------------------------------------------
 * 工程: 配置自适性布局
 * #0000     tian xiao     创建日期: 2017-02-10 18:11
 */
public class AmountView extends AutoLinearLayout implements View.OnClickListener, TextWatcher {
    private static final String TAG = "AmountView";
    public int amount = 1; //购买数量
    private int goods_storage = 99999; //商品库存

    private OnAmountChangeListener mListener;

    private EditText etAmount;
    private Button btnDecrease;
    private Button btnIncrease;
    private View view;
    private Context context;

    public AmountView(Context context) {
        this(context, null);
        this.context = context;
    }


    public AmountView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLongClickable(false);

        this.context = context;
        view = LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = (EditText) findViewById(R.id.etAmount);
        btnDecrease = (Button) findViewById(R.id.btnDecrease);
        btnIncrease = (Button) findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        etAmount.setLongClickable(false);
        etAmount.setTextIsSelectable(false);

        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attrs, R.styleable.AmountView);
        int btnWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnWidth, LayoutParams.WRAP_CONTENT);
        int tvWidth = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvWidth, 80);
        int tvTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_tvTextSize, 0);
        int btnTextSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.AmountView_btnTextSize, 0);
        obtainStyledAttributes.recycle();

        LayoutParams btnParams = new LayoutParams(btnWidth, LayoutParams.MATCH_PARENT);
        btnDecrease.setLayoutParams(btnParams);
        btnIncrease.setLayoutParams(btnParams);
        if (btnTextSize != 0) {
            btnDecrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
            btnIncrease.setTextSize(TypedValue.COMPLEX_UNIT_PX, btnTextSize);
        }

        LayoutParams textParams = new LayoutParams(tvWidth, LayoutParams.MATCH_PARENT);
        etAmount.setLayoutParams(textParams);
        if (tvTextSize != 0) {
            etAmount.setTextSize(tvTextSize);
        }
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public String getNum() {
        return etAmount.getText().toString();
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnDecrease) {
            if (amount > 1) {
                amount--;
                etAmount.setCursorVisible(false);
                etAmount.setText(amount + "");
                closeKeyboard();
            }
        } else if (i == R.id.btnIncrease) {
            if (amount < goods_storage) {
                amount++;
                etAmount.setCursorVisible(false);

                etAmount.setText(amount + "");
                closeKeyboard();

            }
        } else if (i == R.id.etAmount) {
            etAmount.setCursorVisible(true);
            etAmount.setSelection(etAmount.getText().length());

        }

    }


    public void closeKeyboard() {
        InputMethodManager inputmanger = (InputMethodManager) (context).getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS)) {
            View mView = ((BaseActivity) context).getWindow().peekDecorView();
            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        etAmount.setSelection(s.length());
        if (!s.toString().isEmpty()) {
            amount = Integer.valueOf(s.toString());
            if (amount > goods_storage) {
                etAmount.setText(goods_storage + "");
                return;
            }
        }

        if (mListener != null) {
            Log.e("---------", "afterTextChanged: ");
            mListener.onAmountChange(this, amount);
        }
    }


    public void setAmount(String count) {
        etAmount.setText(count + "");

    }

    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }


}
