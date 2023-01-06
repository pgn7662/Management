package people;

public class Requirement {
    private String productRequired;
    private int quantity;
    private double cost;

    public Requirement(String requiredItem, int quantity) {
        this.productRequired = requiredItem;
        this.quantity = quantity;
    }

    public String getProductRequired() {
        return productRequired;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost,Helper helper) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        String text = String.format("%30s", productRequired)+"| "+String.format("%3d",quantity)+"| "+String.format("%.2f",cost)+"| "+String.format("%.2f",quantity*cost);
        return super.toString();
    }
}
