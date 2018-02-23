package hwk2.cis350.upenn.edu.wolfofwharton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jeffrey on 2/22/2018.
 */

public class StockInfoScreen extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String tickerInput = getIntent().getStringExtra("tickerName");
        TextView ticker = (TextView) findViewById(R.id.textView11);
        ticker.setText(tickerInput);
        TextView open = (TextView) findViewById(R.id.textView12);
        open.setText();
        TextView close = (TextView) findViewById(R.id.textView7);
        close.setText();
        TextView high = (TextView) findViewById(R.id.textView8);
        high.setText();
        TextView low = (TextView) findViewById(R.id.textView10);
        low.setText();
        TextView volume = (TextView) findViewById(R.id.textView9);
        volume.setText();
    }
}
