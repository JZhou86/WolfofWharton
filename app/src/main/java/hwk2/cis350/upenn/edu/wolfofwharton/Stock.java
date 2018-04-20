package hwk2.cis350.upenn.edu.wolfofwharton;

public class Stock {
    public String name;
    public int amount;
    public double originalPrice;

    public Stock() {

    }

    public Stock(String name, int amount, double originalPrice) {
        this.name = name;
        this.amount = amount;
        this.originalPrice = originalPrice;
    }

    public String getName() {
        return this.name;
    }

    public int getNumShares() {
        return this.amount;
    }

    public double getPrice() {
        return this.originalPrice;
    }
}
