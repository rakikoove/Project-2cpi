package basicClasses;

        import Connector.ConnectionClass;
        import com.jfoenix.controls.JFXCheckBox;
        import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
        import javafx.beans.property.*;

        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;

public class product extends RecursiveTreeObject<product> {
    private SimpleStringProperty productName;
    private SimpleStringProperty barcode;
    private SimpleFloatProperty buyPrice, sellPrice,stockPercentage;
    private SimpleIntegerProperty quantity;
    private SimpleBooleanProperty IfWasSent;
    private SimpleStringProperty expirationDate;
    private Integer NeededQuantity;
    boolean IfWasAdded;
    private SimpleStringProperty productType;
    private JFXCheckBox checkbox;
    //Dashboard and sells attributes
    private Integer initialQuantity;
    private int numberOfSells;

    public product(String productName, String barcode, float buyPrice, float sellPrice, int quantity, String expirationDate) {
        this.productName = new SimpleStringProperty(productName);
        this.barcode=  new SimpleStringProperty(barcode);
        this.buyPrice = new SimpleFloatProperty(buyPrice);
        this.sellPrice = new SimpleFloatProperty(sellPrice);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.expirationDate = new SimpleStringProperty(expirationDate);
        NeededQuantity=0;
        stockPercentage=new SimpleFloatProperty(100);
        IfWasSent=new SimpleBooleanProperty(false);
        this.checkbox = new JFXCheckBox();
        //Dashboard attributes init
        initialQuantity = quantity;
        numberOfSells = 0;
        productType= new SimpleStringProperty();
    }

    /*public product(String text, String barcode, float sellPrice, float buyPrice, int quanity, String expirationdateString){
        this.productName = new SimpleStringProperty("");
        this.barcode = new SimpleStringProperty("0");
        this.buyPrice = new SimpleFloatProperty(0);
        this.sellPrice = new SimpleFloatProperty(0);
        this.quantity = new SimpleIntegerProperty(0);
        this.expirationDate = new SimpleStringProperty("");
        NeededQuantity = 0;
        numberOfSells = 0;
        productType= new SimpleStringProperty();
    }*/

    public product (String ProductName, String barCode, float BuyPrice, int Quantity, float StockPercentage, boolean ifWasSent, int InitialQuantity) {
        this.productName = new SimpleStringProperty(ProductName);
        this.barcode = new SimpleStringProperty(barCode);
        this.buyPrice = new SimpleFloatProperty(BuyPrice);
        this.stockPercentage = new SimpleFloatProperty(StockPercentage);
        this.quantity = new SimpleIntegerProperty(Quantity);
        IfWasSent =new SimpleBooleanProperty( ifWasSent);
        this.initialQuantity = InitialQuantity;
        NeededQuantity=0;
        numberOfSells = 0;
        productType= new SimpleStringProperty();
    }

    /*
    public product (String ProductName, int barCode, int Quantity, float StockPercentage, boolean ifWasSent, Integer neededQuantity) {
        this.productName = new SimpleStringProperty(ProductName);
        this.barcode = new SimpleIntegerProperty(barCode);
        this.stockPercentage= new SimpleFloatProperty(StockPercentage);
        this.stockPercentage = new SimpleFloatProperty(StockPercentage);
        this.quantity = new SimpleIntegerProperty(Quantity);
        IfWasSent =new SimpleBooleanProperty( ifWasSent);
        NeededQuantity = neededQuantity;
    }


 */

    public product (String ProductName, String Barcode, Integer neededQuantity, boolean ifWasAdded) {
        this.productName=new SimpleStringProperty(ProductName);
        this.barcode =new SimpleStringProperty(Barcode);
        NeededQuantity = neededQuantity;
        IfWasAdded = ifWasAdded;
        numberOfSells = 0;
        productType= new SimpleStringProperty();
    }

    public product(String productName, String barcode, float buyPrice, float sellPrice, int quantity, String expirationDate, int numberOfSells) {
        this.productName = new SimpleStringProperty(productName);
        this.barcode = new SimpleStringProperty(barcode);
        this.buyPrice = new SimpleFloatProperty(buyPrice);
        this.sellPrice = new SimpleFloatProperty(sellPrice);
        this.quantity = new SimpleIntegerProperty(quantity);
        this.expirationDate = new SimpleStringProperty(expirationDate);
        NeededQuantity=0;

        IfWasSent=new SimpleBooleanProperty(false);
        this.checkbox = new JFXCheckBox();
        //Dashboard attributes init
        initialQuantity = quantity;
        this.numberOfSells = numberOfSells;
        productType= new SimpleStringProperty();
    }

    public product(String barcode, float sellPrice, float profit, int quantity) {
        this.barcode = new SimpleStringProperty(barcode);
        this.sellPrice = new SimpleFloatProperty(sellPrice);
        this.buyPrice = new SimpleFloatProperty(sellPrice - profit);
        this.quantity = new SimpleIntegerProperty(quantity);
        productType= new SimpleStringProperty();
        //Complete the other vars from database
        try {
            Connection connection = ConnectionClass.getConnection();
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM stock WHERE barcode=" + barcode;
            ResultSet rs = statement.executeQuery(query);
            if (rs.next()) {
                this.productName = new SimpleStringProperty(rs.getString("name"));
                this.expirationDate = new SimpleStringProperty(rs.getString("expirationDate"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public product() {
    }

    public Integer getNeededQuantity () {
        return NeededQuantity;
    }

    public void setNeededQuantity (Integer neededQuantity) {
        NeededQuantity  =neededQuantity;
    }



    public float getStockPercentage () {
        return stockPercentage.get();
    }

    public SimpleFloatProperty stockPercentageProperty () {
        return stockPercentage;
    }

    public void setStockPercentage (float stockPercentage) {
        this.stockPercentage.set(stockPercentage);
    }

    public boolean getIfWasSent () {
        return IfWasSent.get();
    }

    public SimpleBooleanProperty ifWasSentProperty () {
        return IfWasSent;
    }

    public void setIfWasSent (boolean ifWasSent) {
        this.IfWasSent.set(ifWasSent);
    }

    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getBarcode() {
        return barcode.getValue();
    }

    public SimpleStringProperty barcodeProperty() {
        return new SimpleStringProperty(barcode.get());
    }

    public void setBarcode(String barcode) {
        this.barcode.set(String.valueOf(barcode));
    }

    public float getBuyPrice() {
        return buyPrice.get();
    }

    public SimpleFloatProperty buyPriceProperty() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice.set(buyPrice);
    }

    public float getSellPrice() {
        return sellPrice.get();
    }

    public SimpleFloatProperty sellPriceProperty() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice.set(sellPrice);
    }

    public Integer getQuantity() {
        return quantity.get();
    }

    public SimpleIntegerProperty quantityProperty() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity.set(quantity);
    }

    public String getExpirationDate() {
        return expirationDate.get();
    }

    public SimpleStringProperty expirationDateProperty() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate.set(expirationDate);
    }

    public JFXCheckBox getCheckbox() {
        return checkbox;
    }

    public void setCheckbox(JFXCheckBox checkbox) {
        this.checkbox = checkbox;
    }

    public Integer getInitialQuantity() { return initialQuantity; }

    public void setInitialQuantity(Integer initialQuantity) { this.initialQuantity = initialQuantity; }

    public int getNumberOfSells() { return numberOfSells; }

    public void setNumberOfSells(int numberOfSells) { this.numberOfSells = numberOfSells; }





    public boolean getIfWasAdded () {
        return IfWasAdded;
    }

    public void setIfWasAdded (boolean ifWasAdded) {
        IfWasAdded = ifWasAdded;
    }

    public String getProductType() {
        return productType.get();
    }

    public SimpleStringProperty productTypeProperty() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType.set(productType);
    }
}

