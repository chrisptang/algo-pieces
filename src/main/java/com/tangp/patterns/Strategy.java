package com.tangp.patterns;

/**
 * 策略模式；
 * 可以用来解决过多的if-else嵌套，同时很好的遵守了"开放-闭合"原则；
 */
public class Strategy {

    /**
     * 表示一个收费方案；
     */
    interface BillingStrategy {

        double bill(double originBill);

        /**
         * 正常价格
         *
         * @return
         */
        static BillingStrategy normalStrategy() {
            return originBill -> originBill;
        }

        /**
         * 5折
         *
         * @return
         */
        static BillingStrategy happyHour() {
            return originBill -> 0.8 * originBill;
        }

        /**
         * 满100减5
         *
         * @return
         */
        static BillingStrategy fiveDollarOffOver10() {
            return originBill -> originBill >= 10 ? originBill - 5.0D : originBill;
        }
    }

    private BillingStrategy[] strategies = {BillingStrategy.happyHour(), BillingStrategy.fiveDollarOffOver10()};

    /**
     * 为顾客使用最经济划算的折扣方案；
     *
     * @param originBill
     * @return
     */
    public double billMe(double originBill) {
        double finalBill = strategies[0].bill(originBill);
        for (int i = 1; i < strategies.length; i++) {
            double strategyBill = strategies[i].bill(originBill);
            if (finalBill > strategyBill) {
                finalBill = strategyBill;
            }
        }
        return finalBill;
    }
}
