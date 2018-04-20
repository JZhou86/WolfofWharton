package hwk2.cis350.upenn.edu.wolfofwharton;
import java.util.*;

public class User {
    public double moneyLeft;
    public List<Stock> stocks;
    public List<String> transactionHistory;

    public User() {

    }

    public User(double moneyLeft, List<Stock> stocks, List<String> transactionHistory) {
        this.moneyLeft = moneyLeft;
        this.stocks = stocks;
        this.transactionHistory = transactionHistory;
    }

    public List<Stock> getStocks() {
        if (stocks == null) {
            return new ArrayList<Stock>();
        }
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

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    public void setTransactionHistory(List<String> s) {
        transactionHistory = s;
    }

}

