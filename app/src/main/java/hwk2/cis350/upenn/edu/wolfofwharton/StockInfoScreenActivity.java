package hwk2.cis350.upenn.edu.wolfofwharton;

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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.io.*;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Jeffrey on 2/22/2018.
 *
 * modified by alice 04/05/2018
 */



public class StockInfoScreenActivity extends AppCompatActivity{

    private EditText quantity; //NUMBER OF STOCKS SELECTED
    private TextView dollarAmount; //VALUE OF STOCKS
    private String priceClose; //STOCK PRICE USED IN CALCULATIONS
    private String tickerInput;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private DatabaseReference mDatabase;
    private User user;

    private double newAmount;
    private int amount;
    private String userID;
    private double originalPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_info);

        httpRequest(); //ticker info should immediately be displayed on the screen upon creation
        quantity = (EditText) findViewById(R.id.numberOfStocks);
        dollarAmount = (TextView) findViewById(R.id.dollar_amount);

         quantity.addTextChangedListener(new TextWatcher() {
             @Override
             public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 //DO NOTHING
             }

             @Override
             public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 //DO NOTHING
             }

             @Override
             //UPDATE DOLLAR VALUE DEPENDING ON QUANTITY OF STOCKS
             //UPDATES REAL TIME AS YOU MODIFY TEXT FIELD
             public void afterTextChanged(Editable editable) {
                 if (!quantity.getText().toString().equals("")) {
                     double newAmount = 0;
                     newAmount = Double.parseDouble(quantity.getText().toString()) *
                             Double.parseDouble(priceClose);

                     //CALCULATE THE PRICE AMOUNT (EITHER SPEND WHEN BUYING OR
                     //GAIN FROM SELLING)
                     newAmount = Math.round(newAmount * 100.0);
                     newAmount = newAmount / 100.0;
                     dollarAmount.setText("$" + newAmount);

                 } else {
                     //IF THERE IS NO QUANTITY SPECIFIED
                     dollarAmount.setText("S0.00");
                 }
             }
         });


    }

    //GET TICKER BY SENDING IN AN HTTPREQUEST
    void httpRequest() {
        try {
            //pass in ticker input to find the ticker
            tickerInput = getIntent().getStringExtra("tickerName");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            //url for the specific ticker
            URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + tickerInput + "&apikey=2OKOYNBSJ899XNY9&datatype=csv");
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
            TextView ticker = (TextView) findViewById(R.id.stockName);
            ticker.setText(tickerInput);
            TextView open = (TextView) findViewById(R.id.openData);
            open.setText(Double.toString(stock.getOpen()));
            TextView close = (TextView) findViewById(R.id.closeData);
            priceClose = Double.toString(stock.getClose());
            close.setText(Double.toString(stock.getClose()));
            TextView high = (TextView) findViewById(R.id.highData);
            high.setText(Double.toString(stock.getHigh()));
            TextView low = (TextView) findViewById(R.id.lowData);
            low.setText(Double.toString(stock.getLow()));
            TextView volume = (TextView) findViewById(R.id.volumeData);
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

    //USER PRESSES BUY
    public void buyOption(View view) {

        /*
        Intent intent = new Intent( this, TransactionHistoryActivity.class );
        intent.putExtra( "transaction", dollarAmount.getText().toString() );
        startActivity( intent );

        newAmount = 0;
        newAmount = Double.parseDouble(quantity.getText().toString()) *
                Double.parseDouble(priceClose);

        originalPrice = Double.parseDouble(priceClose);
        amount = Integer.parseInt(quantity.getText().toString());

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");

        //attach to user account
        FirebaseUser u = mAuth.getCurrentUser();
        userID = u.getUid();
        Log.d("UID", userID);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                Log.d("USER", Double.toString(u.getMoneyLeft()));
                setUser(u);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

        /*ValueEventListener stockListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    User u = dataSnapshot.getValue(User.class);
                    setUser(u);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Failed to read value
            }
        };

        myRef.addValueEventListener(stockListener);*/


        /*Stock stock = new Stock(tickerInput, amount, originalPrice);
        List<Stock> stockList = user.getStocks();
        stockList.add(stock);
        Double moneyLeft = user.getMoneyLeft() - newAmount;
        user.setMoneyLeft(moneyLeft);
        user.setStocks(stockList);
        myRef.child("users").child(userID).setValue(user);*/
    }

    private void setUser(User u) {
        user = u;

        Stock stock = new Stock(tickerInput, amount, originalPrice);
        List<Stock> stockList = user.getStocks();
        if(stockList == null) {
            stockList = new ArrayList<>();
        }
        stockList.add(stock);
        Double moneyLeft = user.getMoneyLeft() - newAmount;
        user.setMoneyLeft(moneyLeft);
        user.setStocks(stockList);
        mDatabase.child("users").child(userID).setValue(user);
    }
}
