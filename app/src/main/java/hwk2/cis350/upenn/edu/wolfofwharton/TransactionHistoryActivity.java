package hwk2.cis350.upenn.edu.wolfofwharton;

import android.annotation.SuppressLint;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TransactionHistoryActivity extends AppCompatActivity {
    private String transaction;
    private TextView transactionDisplay;

    List<String> transactionHistory;

    ListView lv;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private DatabaseReference mDatabase;
    private User user;
    private String userID;

    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);


        lv = findViewById(R.id.lv);

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference("users");

        //attach to user account
        FirebaseUser u = mAuth.getCurrentUser();
        userID = u.getUid();

        mDatabase = FirebaseDatabase.getInstance().getReference();

        mDatabase.child("users").child(userID).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User u = dataSnapshot.getValue(User.class);
                Log.d("USER", Double.toString(u.getMoneyLeft()));
                setTransactionHistory(u);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });


    }

    public void setTransactionHistory(User u) {
        user = u;
        transactionHistory = user.getTransactionHistory();

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, transactionHistory);
        lv.setAdapter(adapter);

        //LISTENER
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(MainActivity.this, numbers.get(i), Toast.LENGTH_SHORT).show();
            }
        });
    }


}
