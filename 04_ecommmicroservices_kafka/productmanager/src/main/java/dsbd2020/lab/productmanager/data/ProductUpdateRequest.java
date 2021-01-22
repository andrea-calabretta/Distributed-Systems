package dsbd2020.lab.productmanager.data;

import java.io.Serializable;

public class ProductUpdateRequest implements Serializable {
    private Integer productId;
    private Integer productQuantity;

    public Integer getProductId() {
        return productId;
    }

    public ProductUpdateRequest setProductId(Integer productId) {
        this.productId = productId;
        return this;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public ProductUpdateRequest setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
        return this;
    }
}
