package hwk2.cis350.upenn.edu.wolfofwharton;
import java.util.*;

public class User {
    public double moneyLeft;
    public List<Stock> stocks;

    public User() {

    }

    public User(double moneyLeft, List<Stock> stocks) {
        this.moneyLeft = moneyLeft;
        this.stocks = stocks;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public double getMoneyLeft() {
        return moneyLeft;
    }

    public void setStocks(List<Stock> s) {
        stocks = s;
    }

    public void setMoneyLeft(double m) {
        moneyLeft = m;
    }
}
