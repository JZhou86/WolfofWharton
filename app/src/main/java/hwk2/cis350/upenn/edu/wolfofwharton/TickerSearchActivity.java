package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;
import java.util.*;


public class TickerSearchActivity extends AppCompatActivity {
    private TextView tickerName;
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticker_search);
    }

    public void stockInfoScreen(View view) {

        //prompt stockInfoScreen once a ticker is searched

        tickerName = (TextView) findViewById(R.id.tickerSearch);
        String name = tickerName.getText().toString().toUpperCase();

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        //attach to user account
        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();

        Stock stock = new Stock(name, 3, 44);
        List<Stock> stockList = new ArrayList<Stock>();
        stockList.add(stock);
        User newUser = new User(1000, stockList);
        myRef.child("users").child(userID).setValue(newUser);

        //go to stockInfoScreen activity
        Intent intent = new Intent(this, StockInfoScreenActivity.class);
        intent.putExtra("tickerName", name);
        startActivity(intent);
    }
}
