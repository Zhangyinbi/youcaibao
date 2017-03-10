package com.youjuke.optimalmaterialtreasure.utils;

import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

/**
 * Created by Administrator on 2017/2/22.
 * 限制只能输入两位小数
 * @author zengchao
 */

public class InputMoney implements InputFilter {
    EditText money;

    public InputMoney(EditText money) {
        this.money = money;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end,
                               Spanned dest, int dstart, int dend) {
        // TODO Auto-generated method stub
        if (source.toString().equals(".") && dstart == 0 && dend == 0) {//判断小数点是否在第一位
            return "0.";
        }
        if (source.toString().equals("0") && dstart == 0 && dend == 0) {//判断零是否在第一位
            money.setText( "0." );//给小数点前面加0
            money.setSelection(2);//设置光标
        }
        if (dest.toString().indexOf(".") != -1 && (dest.length() - dest.toString().indexOf(".")) > 2) {//判断小数点是否存在并且小数点后面是否已有两个字符
            if ((dest.length() - dstart) < 3) {//判断现在输入的字符是不是在小数点后面
                return "";//过滤当前输入的字符
            }
        }
        return null;
    }

}