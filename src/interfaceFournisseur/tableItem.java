package interfaceFournisseur;

public class tableItem {
    private String product;
    private int quantity;
    private boolean getit;
    public tableItem(String product,int quantity){
        this.product=product;
        this.quantity=quantity;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
