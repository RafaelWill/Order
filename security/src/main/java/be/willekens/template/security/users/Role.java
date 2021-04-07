package be.willekens.template.security.users;


import org.assertj.core.util.Lists;

import java.util.List;

public enum Role {
    ADMIN(Feature.CREATE_ITEM, Feature.CHECK_STOCK, Feature.GET_ALL_CUSTOMERS, Feature.GET_CUSTOMER_BY_ID, Feature.GET_ORDERS_BY_SHIPPING_DATE, Feature.UPDATE_ITEM),
    CUSTOMER(Feature.PLACE_ORDER, Feature.REORDER, Feature.GET_HISTORY_OF_ORDERS);

    private final List<Feature> featureList;

    Role(Feature... featureList) {
        this.featureList = Lists.newArrayList(featureList);
    }

    public List<Feature> getFeatureList() {
        return featureList;
    }
}
