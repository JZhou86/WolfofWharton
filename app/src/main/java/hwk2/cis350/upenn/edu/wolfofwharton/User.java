package hwk2.cis350.upenn.edu.wolfofwharton;
import java.util.*;

public class User {
    public double moneyLeft;
    public List<Stock> stocks;

    //TODO: new
    public List<String> transactionHistory;

    public User() {

    }

    public User(double moneyLeft, List<Stock> stocks, List<String> transactionHistory) {
        this.moneyLeft = moneyLeft;
        this.stocks = stocks;
        this.transactionHistory = transactionHistory;
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

    //TODO: new
    public List<String> getTransactionHistory() {
        return transactionHistory;
    }

    //TODO: new
    public void setTransactionHistory(List<String> s) {
        transactionHistory = s;
    }

}

