package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.List;

public class PortfolioActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private DatabaseReference mDatabase;
    private User user;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //GRAPH PORTFOLIO
        GraphView graph = (GraphView) findViewById(R.id.portfolioGraph);
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(new DataPoint[] {
                new DataPoint(0, 1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);
        getUser();
/*
        // For displaying stock cards
        List<Data> data = fill_with_data();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // For animating stock cards
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);*/
    }

    public void fill_with_data(User u) {
        user = u;
        List<Data> data = new ArrayList<Data>();
        List<Stock> stocks;
        if (user.getStocks() == null) {
            stocks = new ArrayList<>();
        }
        else {
           stocks = user.getStocks();
        }

        for (Stock s : stocks) {
            data.add(new Data(s));
        }

        // For displaying stock cards
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        Recycler_View_Adapter adapter = new Recycler_View_Adapter(data, getApplication());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // For animating stock cards
        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);
    }

//    public List<Data> fill_with_data() {
//        List<Data> data = new ArrayList<>();
//        Stock s1 = new Stock("AAPL", 1, 160.90);
//        data.add(new Data(s1));
//        Stock s2 = new Stock("MSFT", 3, 92.10);
//        data.add(new Data(s2));
//        Stock s3 = new Stock("F", 100, 17.85);
//        data.add(new Data(s3));
//
//        return data;
//    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.portfolio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_watchlist) {
            Intent intent = new Intent(this, WatchListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(this, TransactionHistoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_logout) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_instructions) {
            Intent intent = new Intent(this, InstructionsActivity.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void goToTickerSearch(View view) {
        Intent intent = new Intent(this, TickerSearchActivity.class);
        startActivity(intent);
    }

    public void getUser() {
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
                fill_with_data(u);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
        });

    }
}
