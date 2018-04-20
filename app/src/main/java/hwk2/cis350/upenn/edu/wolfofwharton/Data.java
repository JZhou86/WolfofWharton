package hwk2.cis350.upenn.edu.wolfofwharton;

import android.os.StrictMode;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rahul on 4/19/18.
 */

public class Data {
    public String stockName;
    public int numShares;
    private double origPrice;
    private double currPrice;
    public double priceChange;

    public Data(Stock stock) {
        this.stockName = stock.getName();
        this.numShares = stock.getNumShares();
        // CHANGE PRICE_CHANGE
        this.origPrice = stock.getPrice();
        this.currPrice = httpRequest();
        this.priceChange = Math.round(((this.currPrice - this.origPrice) / this.origPrice) * 100);
    }

    private double httpRequest() {
        List<String[]> stockInfo = null;
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //url for the specific ticker
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockName + "&apikey=2OKOYNBSJ899XNY9&datatype=csv");
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            in.readLine(); //Gets rid of header line
            stockInfo = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                stockInfo.add(inputLine.split(","));
            }
            in.close();
        } catch (MalformedURLException e) {
            Log.e("tag", "error");
        } catch (IOException e) {
            Log.e("tag", "error");
        }

        return Double.valueOf(stockInfo.get(0)[4]);
    }
}
