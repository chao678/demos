package com.example.testapp;

/**
 * Created by Hm on 2018/4/24 0024.
 */

public class MyBean {


    /**
     * code : 200
     * msg : 成功
     * datas : {"order_id":"24756","order_sn":"1000000002510501","pay_sn":"370581100867035923","buyer_id":"16923","buyer_name":"13166790122","store_id":"5826","store_name":"测试李-卖家","store_phone":"18641682157","add_time":"2018-05-31 16:54:27","payment_code":"","payment_time":"","payment_state":"0","goods_amount":"10.00","order_amount":"10.00","order_state":"10"}
     */

    private String code;
    private String msg;
    private DatasBean datas;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * order_id : 24756
         * order_sn : 1000000002510501
         * pay_sn : 370581100867035923
         * buyer_id : 16923
         * buyer_name : 13166790122
         * store_id : 5826
         * store_name : 测试李-卖家
         * store_phone : 18641682157
         * add_time : 2018-05-31 16:54:27
         * payment_code :
         * payment_time :
         * payment_state : 0
         * goods_amount : 10.00
         * order_amount : 10.00
         * order_state : 10
         */

        private String order_id;
        private String order_sn;
        private String pay_sn;
        private String buyer_id;
        private String buyer_name;
        private String store_id;
        private String store_name;
        private String store_phone;
        private String add_time;
        private String payment_code;
        private String payment_time;
        private String payment_state;
        private String goods_amount;
        private String order_amount;
        private String order_state;

        public String getOrder_id() {
            return order_id;
        }

        public void setOrder_id(String order_id) {
            this.order_id = order_id;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getPay_sn() {
            return pay_sn;
        }

        public void setPay_sn(String pay_sn) {
            this.pay_sn = pay_sn;
        }

        public String getBuyer_id() {
            return buyer_id;
        }

        public void setBuyer_id(String buyer_id) {
            this.buyer_id = buyer_id;
        }

        public String getBuyer_name() {
            return buyer_name;
        }

        public void setBuyer_name(String buyer_name) {
            this.buyer_name = buyer_name;
        }

        public String getStore_id() {
            return store_id;
        }

        public void setStore_id(String store_id) {
            this.store_id = store_id;
        }

        public String getStore_name() {
            return store_name;
        }

        public void setStore_name(String store_name) {
            this.store_name = store_name;
        }

        public String getStore_phone() {
            return store_phone;
        }

        public void setStore_phone(String store_phone) {
            this.store_phone = store_phone;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getPayment_code() {
            return payment_code;
        }

        public void setPayment_code(String payment_code) {
            this.payment_code = payment_code;
        }

        public String getPayment_time() {
            return payment_time;
        }

        public void setPayment_time(String payment_time) {
            this.payment_time = payment_time;
        }

        public String getPayment_state() {
            return payment_state;
        }

        public void setPayment_state(String payment_state) {
            this.payment_state = payment_state;
        }

        public String getGoods_amount() {
            return goods_amount;
        }

        public void setGoods_amount(String goods_amount) {
            this.goods_amount = goods_amount;
        }

        public String getOrder_amount() {
            return order_amount;
        }

        public void setOrder_amount(String order_amount) {
            this.order_amount = order_amount;
        }

        public String getOrder_state() {
            return order_state;
        }

        public void setOrder_state(String order_state) {
            this.order_state = order_state;
        }
    }
}
