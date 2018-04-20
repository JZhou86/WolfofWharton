package hwk2.cis350.upenn.edu.wolfofwharton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TransactionHistoryActivity extends AppCompatActivity {
    private String transaction;
    private TextView transactionDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        transactionDisplay = (TextView) findViewById(R.id.transaction1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            transaction = extras.getString("transaction");
            transactionDisplay.setText(transaction);
        }
    }



}
