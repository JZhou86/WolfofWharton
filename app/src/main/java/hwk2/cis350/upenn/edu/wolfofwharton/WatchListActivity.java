package hwk2.cis350.upenn.edu.wolfofwharton;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.*;



public class WatchListActivity extends AppCompatActivity {

    String tickerInputXMI;
    String tickerInputSPY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);
        tickerInputXMI = "XMI";
        tickerInputSPY = "SPY";
        httpRequestXMI();
        httpRequestSPY();
    }

    //GET TICKER BY SENDING IN AN HTTPREQUEST
    void httpRequestXMI() {
        try {
            //pass in ticker input to find the ticker
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //url for the specific ticker
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + tickerInputXMI + "&apikey=2OKOYNBSJ899XNY9&datatype=csv");
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            in.readLine(); //Gets rid of header line
            List<String[]> stocksInfo = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                stocksInfo.add(inputLine.split(","));
            }
            //format dates for US
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

            //parse daily info for the stock
            DailyInfoActivity stock = new DailyInfoActivity(date.parse(stocksInfo.get(0)[0]),
                    Double.valueOf(stocksInfo.get(0)[1]),
                    Double.valueOf(stocksInfo.get(0)[2]),
                    Double.valueOf(stocksInfo.get(0)[3]),
                    Double.valueOf(stocksInfo.get(0)[4]),
                    Double.valueOf(stocksInfo.get(0)[5]));
            System.out.println(stock.getOpen());
            System.out.println(stock.getDateTime());

            //DISPLAY TICKER AND TICKER INFO
            TextView NYSEcurr = (TextView) findViewById(R.id.NYSEcurr);
            NYSEcurr.setText(Double.toString(stock.getOpen()));

            in.close();


        } catch (NumberFormatException e) {
            System.out.println("Date is wrong");
        } catch (ParseException e) {
            System.out.println("Date is wrong");
        } catch (MalformedURLException e) {
            Log.e("tag", "error");
        } catch (IOException e) {
            Log.e("tag", "error");
        }
    }

    void httpRequestSPY() {
        try {
            //pass in ticker input to find the ticker
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //url for the specific ticker
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + tickerInputSPY + "&apikey=2OKOYNBSJ899XNY9&datatype=csv");
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            in.readLine(); //Gets rid of header line
            List<String[]> stocksInfo = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                stocksInfo.add(inputLine.split(","));
            }
            //format dates for US
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

            //parse daily info for the stock
            DailyInfoActivity stock = new DailyInfoActivity(date.parse(stocksInfo.get(0)[0]),
                    Double.valueOf(stocksInfo.get(0)[1]),
                    Double.valueOf(stocksInfo.get(0)[2]),
                    Double.valueOf(stocksInfo.get(0)[3]),
                    Double.valueOf(stocksInfo.get(0)[4]),
                    Double.valueOf(stocksInfo.get(0)[5]));
            System.out.println(stock.getOpen());
            System.out.println(stock.getDateTime());

            //DISPLAY TICKER AND TICKER INFO
            TextView SP500 = (TextView) findViewById(R.id.SandPcurr);
            SP500.setText(Double.toString(stock.getOpen()));

            in.close();


        } catch (NumberFormatException e) {
            System.out.println("Date is wrong");
        } catch (ParseException e) {
            System.out.println("Date is wrong");
        } catch (MalformedURLException e) {
            Log.e("tag", "error");
        } catch (IOException e) {
            Log.e("tag", "error");
        }
    }
}
