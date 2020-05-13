package com.tangp.algo.pieces;

import java.util.HashMap;
import java.util.Map;

/**
 * network佣金计算
 */
public class CommissionCalculator {

    private static final Calculator[] CALCULATORS = {new NetworkCalculator(), new CategoryCalculator()};

    interface Calculator {
        double calculator(Product product, Network network, MerchantCommissionConfiguration configuration);
    }

    /**
     * 主程序；
     *
     * @param product
     * @param network
     * @param merchant
     * @return
     */
    public double commissionRate(Product product, Network network, Merchant merchant) {
        MerchantCommissionConfiguration configuration = getMerchantCommissionConfiguration(merchant);
        if (null == product || configuration == null) {
            throw new IllegalArgumentException("neither product nor configuration can be null.");
        }
        double commissionRate = 0.0D;
        for (Calculator calculator : CALCULATORS) {
            double rate;
            if ((rate = calculator.calculator(product, network, configuration)) > commissionRate) {
                commissionRate = rate;
            }
        }
        return commissionRate;
    }

    protected static class NetworkCalculator implements Calculator {

        @Override
        public double calculator(Product product, Network network, MerchantCommissionConfiguration configuration) {
            if (!isEmptyCollection(configuration.networkCommissionConfig) && null != network
                    && configuration.networkCommissionConfig.containsKey(network)) {
                // 商家对于network可以有佣金配置
                return configuration.networkCommissionConfig.get(network);
            }
            return 0.0D;
        }
    }

    protected static class CategoryCalculator implements Calculator {

        @Override
        public double calculator(Product product, Network network, MerchantCommissionConfiguration configuration) {
            if (!isEmptyCollection(configuration.categoryCommissionConfig) && product.categoryPath.length > 0) {
                long[] categoryPath = product.categoryPath;
                double categoryCommissionRate = 0.0D;
                for (int index = categoryPath.length - 1; index >= 0; index--) {
                    long categoryId = categoryPath[index];

                    if (configuration.categoryCommissionConfig.containsKey(categoryId)) {
                        categoryCommissionRate = configuration.categoryCommissionConfig.get(categoryId);
                        if (categoryCommissionRate > 0.0D) {
                            return categoryCommissionRate;
                        }
                    }
                }
            }
            return 0.0D;
        }
    }

    /**
     * 根据merchant获取佣金配置；
     *
     * @param merchant
     * @return
     */
    protected MerchantCommissionConfiguration getMerchantCommissionConfiguration(Merchant merchant) {
        return new MerchantCommissionConfiguration(merchant);
    }

    /**
     * 商品Object
     */
    public static class Product {

        /**
         * 类目ID层级，类目为树形结构
         * categoryPath[0]表示一级类目，categoryPath[1]表示二级类目，以此类推；
         */
        protected long[] categoryPath = new long[]{};

        /**
         * 单价；
         */
        protected Double amount;
    }

    protected static class Merchant {
        protected long merchantId;
    }

    /**
     * 商家佣金配置
     */
    public static class MerchantCommissionConfiguration {

        public MerchantCommissionConfiguration(Merchant merchant) {
            this.merchant = merchant;
        }

        protected final Merchant merchant;

        protected Map<Long, Double> categoryCommissionConfig = new HashMap<>();

        protected Map<Network, Double> networkCommissionConfig = new HashMap<>();
    }

    /**
     * 商家实体
     */
    public static class Network {

        protected String name;

        @Override
        public int hashCode() {
            return this.name == null ? 0 : this.name.hashCode();
        }
    }

    protected static final boolean isEmptyCollection(Map<?, ?> collection) {
        return null == collection || collection.isEmpty();
    }
}
