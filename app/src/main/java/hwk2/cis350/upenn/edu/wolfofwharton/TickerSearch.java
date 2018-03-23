package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TickerSearch extends AppCompatActivity {
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
        tickerName = (TextView) findViewById(R.id.tickerSearch);
        String name = tickerName.getText().toString().toUpperCase();

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        FirebaseUser user = mAuth.getCurrentUser();
        String userID = user.getUid();
        myRef.child(userID).child(name).setValue("true");

        Intent intent = new Intent(this, StockInfoScreen.class);
        intent.putExtra("tickerName", name);
        startActivity(intent);
    }
}
