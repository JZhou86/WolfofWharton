package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class TickerSearch extends AppCompatActivity {
    private String tickerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker_search);
    }


    public void stockInfoScreen(View view) {
        tickerName = view.findViewById(R.id.tickerSearch).toString();
        Intent intent = new Intent(this, StockInfoScreen.class);
        startActivity(intent);
    }
}
