package hwk2.cis350.upenn.edu.wolfofwharton;

public class Stock {
    public String name;
    public int numShares;
    public double originalPrice;

    public Stock() {

    }

    public Stock(String name, int amount, double originalPrice) {
        this.name = name;
        this.numShares = amount;
        this.originalPrice = originalPrice;
    }

    public String getName() {
        return this.name;
    }

    public void setNumShares(int i) {
        this.numShares= i;
    }

    public int getNumShares() {
        return this.numShares;
    }

    public double getPrice() {
        return this.originalPrice;
    }

    public void setPrice(double price) { this.originalPrice = price; }
}
