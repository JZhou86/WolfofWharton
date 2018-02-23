package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class TickerSearch extends AppCompatActivity {
    private TextView tickerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker_search);
    }

    public void stockInfoScreen(View view) {
        tickerName = (TextView) findViewById(R.id.tickerSearch);
        String name = tickerName.getText().toString().toUpperCase();
        Intent intent = new Intent(this, StockInfoScreen.class);
        intent.putExtra("tickerName", name);
        startActivity(intent);
    }
}
