package com.youjuke.optimalmaterialtreasure.entity;



import java.util.List;

/**
 * 描述: TODO
 * ------------------------------------------------------------------------
 * 工程:
 * #0000     tian xiao     创建日期: 2017-02-20 10:31
 */
public class OrderConfirmation {


    /**
     * totalInfo : {"totalCount":2,"totalPrice":31.2,"accountMoney":"0.00"}
     * materials : [{"materialName":"水管","brandName":"中财","materialId":"1","materialModel":"无","materialNorms":"DN32（3米）","materialPrice":"15.60","materialCount":"2","totalPrice":31.2}]
     */

    private TotalInfoBean totalInfo;
    private List<MaterialsBean> materials;

    public TotalInfoBean getTotalInfo() {
        return totalInfo;
    }

    public void setTotalInfo(TotalInfoBean totalInfo) {
        this.totalInfo = totalInfo;
    }

    public List<MaterialsBean> getMaterials() {
        return materials;
    }

    public void setMaterials(List<MaterialsBean> materials) {
        this.materials = materials;
    }

    public static class TotalInfoBean {
        /**
         * totalCount : 2
         * totalPrice : 31.2
         * accountMoney : 0.00
         */

        private String totalCount;
        private String totalPrice;
        private String accountMoney;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getAccountMoney() {
            return accountMoney;
        }

        public void setAccountMoney(String accountMoney) {
            this.accountMoney = accountMoney;
        }
    }


}
