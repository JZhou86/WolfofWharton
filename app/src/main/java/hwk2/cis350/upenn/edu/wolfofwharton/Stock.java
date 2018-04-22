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
    //get name
    public String getName() {
        return this.name;
    }

    //get num of stocks
    public int getAmount() {
        return this.amount;
    }

    //set num of stocks
    public void setAmount(int i) {
        this.amount = i;
    }

    public int getNumShares() {
        return this.amount;
    }

    public double getPrice() {
        return this.originalPrice;
    }

    public void setPrice(double price) { this.originalPrice = price; }
}
