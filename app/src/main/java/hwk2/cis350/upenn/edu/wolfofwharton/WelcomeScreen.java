package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class WelcomeScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_welcome_screen);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        //check if user is signed in (non-null) and update UI
        //accordingly

        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser user) {
        //TO DO
    }

    //helper function to validate that the form has been filled out
    private boolean validateForm(String email, String password) {
        boolean validForm = true;
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Context context = getApplicationContext();
            CharSequence text = "Email and password fields required";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            validForm = false;
        }
        return validForm;
    }

    //CREATE NEW USER
    //called when new account is created by filling out the form in
    //the sign up activity
    public void createAccount(String email, String password) {
        //validate email and password
        if (!validateForm(email, password)) {
            return;
        }

        //creates new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) { //sign in successful
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        }
                    }
                });
    }

    //LOG IN EXISTING USERS
    public void logIn(String email, String password) {
        //validate email and password
        if (!validateForm(email, password)) {
            return;
        }

        //log in
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Toast.makeText(WelcomeScreen.this,
                                    "Log in failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }




    //transition to sign up screen when user presses sign up button
    public void signUpScreen(View view) {
        Intent intent = new Intent(this, SignUpScreen.class);
        startActivity(intent);
    }
}
