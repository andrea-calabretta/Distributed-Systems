package dsbd2020.lab.ordermanager.controller;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class OrderRequest {

    @NotNull
    private Integer userId;
    // private String userEmail;  //alternativa


    @NotNull
    private Map<Integer, Integer> products; //products<productId, quantity>

    public Integer getUserId() {
        return userId;
    }

    public OrderRequest setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public Map<Integer, Integer> getProducts() {
        return products;
    }

    public OrderRequest setProducts(Map<Integer, Integer> products) {
        this.products = products;
        return this;
    }

    @Override
    public String toString() {
        return "OrderRequest{" +
                "userId=" + userId +
                ", products=" + products +
                '}';
    }
}
