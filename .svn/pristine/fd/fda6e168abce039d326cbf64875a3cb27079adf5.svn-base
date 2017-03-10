package com.youjuke.optimalmaterialtreasure.app.shopping_cart.DialogFragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.youjuke.library.base.BaseActivity;
import com.youjuke.library.utils.L;
import com.youjuke.optimalmaterialtreasure.R;
import com.youjuke.optimalmaterialtreasure.weights.PwdInputView;

import java.util.zip.Inflater;

/**
 * 描述: 支付密码
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-14 16:21
 */
@SuppressLint("ValidFragment")
public class PaymentCodeDialogFragment extends DialogFragment {

    private Dialog dialog;
    private Context context;
    private PwdInputView pwdInputView;
    private View view;
    private OnClickListener mOnClickListener;
    private String num;
    private void assignViews() {
        pwdInputView= (PwdInputView) view.findViewById(R.id.piv_password);
        ((TextView)view.findViewById(R.id.tv_num)).setText(num);
    }


    public PaymentCodeDialogFragment(Context context,String num) {
        super();
        this.num=num;
        this.context = context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        view = LayoutInflater.from(context).inflate(R.layout.dialog_payment_code, null);
        assignViews();
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        dialog = new Dialog(getActivity(), R.style.BottomDialog) {
            @Override
            public boolean onTouchEvent(MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (!(event.getX() >= -10 && event.getY() >= -10)) {//如果点击位置在当前View外部则销毁当前视图,其中10与20为微调距离
                        InputMethodManager inputmanger = (InputMethodManager) context.getSystemService(context.INPUT_METHOD_SERVICE);
                        if (inputmanger.isActive()) {
                            inputmanger.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
                        } else {
                            L.d("隐藏");
                            //dialog.dismiss();
                        }
                    }
                }

                return super.onTouchEvent(event);
            }
        };
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        final Window window = dialog.getWindow();

        assert window != null;
        window.setWindowAnimations(R.style.AnimBottom);

        final WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        //lp.height = getActivity().getWindowManager().getDefaultDisplay().getHeight() * 3 / 5;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        mOnClickListener.setOnClick(pwdInputView);
        return dialog;
    }

    public interface OnClickListener<T>{
        void setOnClick(PwdInputView pwdInputView);
    }
    public void setOnClickListener(OnClickListener mOnClickListener){
        this.mOnClickListener=mOnClickListener;
    }
}
