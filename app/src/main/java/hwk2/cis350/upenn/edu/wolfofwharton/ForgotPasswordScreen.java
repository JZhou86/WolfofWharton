package hwk2.cis350.upenn.edu.wolfofwharton;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_screen);

        this.mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
    }

    public void sendPasswordResetEmail(View view) {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        //final String emailAddress = "user@example.com";

        if(email.getText().toString().isEmpty()) {
            Toast.makeText(ForgotPasswordScreen.this, "Please enter an email", Toast.LENGTH_SHORT).show();

        }

        System.out.println("email:" + email.getText().toString());
        mAuth.sendPasswordResetEmail(email.getText().toString())
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            //Log.d(TAG, "Email sent.");
                            Toast.makeText(ForgotPasswordScreen.this,
                                    "Password Reset Email Sent to " + email.getText().toString(), Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(ForgotPasswordScreen.this,
                                    "Password Reset Email Sent to " + email.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


    }

}
