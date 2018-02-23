package hwk2.cis350.upenn.edu.wolfofwharton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.io.*;

/**
 * Created by Jeffrey on 2/22/2018.
 */

public class StockInfoScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_info);

        httpRequest();





    }

    void httpRequest() {
        try {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=MSFT&apikey=2OKOYNBSJ899XNY9&datatype=csv");
            URLConnection conn = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            in.readLine(); //Gets rid of header line
            List<String[]> stocksInfo = new ArrayList<>();
            while ((inputLine = in.readLine()) != null) {
                stocksInfo.add(inputLine.split(","));
            }

            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

            DailyInfo stock = new DailyInfo(date.parse(stocksInfo.get(0)[0]),
                    Double.valueOf(stocksInfo.get(0)[1]),
                    Double.valueOf(stocksInfo.get(0)[2]),
                    Double.valueOf(stocksInfo.get(0)[3]),
                    Double.valueOf(stocksInfo.get(0)[4]),
                    Double.valueOf(stocksInfo.get(0)[5]));
            System.out.println(stock.getOpen());
            System.out.println(stock.getDateTime());

            String tickerInput = getIntent().getStringExtra("tickerName");
            TextView ticker = (TextView) findViewById(R.id.textView11);
            ticker.setText(tickerInput);
            TextView open = (TextView) findViewById(R.id.textView12);
            open.setText(Double.toString(stock.getOpen()));
            TextView close = (TextView) findViewById(R.id.textView7);
            close.setText(Double.toString(stock.getClose()));
            TextView high = (TextView) findViewById(R.id.textView8);
            high.setText(Double.toString(stock.getHigh()));
            TextView low = (TextView) findViewById(R.id.textView10);
            low.setText(Double.toString(stock.getLow()));
            TextView volume = (TextView) findViewById(R.id.textView9);
            volume.setText(Double.toString(stock.getVolume()));

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
