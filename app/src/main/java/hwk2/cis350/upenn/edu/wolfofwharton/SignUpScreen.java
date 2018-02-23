package hwk2.cis350.upenn.edu.wolfofwharton;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;


public class SignUpScreen extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText email;
    private EditText password;
    private EditText reenterpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        this.mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        reenterpassword = (EditText) findViewById(R.id.reenterpassword);

    }

    public void onStart() {
        super.onStart();
        //check if user is signed in (non-null) and update UI
        //accordingly

        if (mAuth.getCurrentUser() != null) {
            FirebaseUser currentUser = mAuth.getCurrentUser();
            updateUI(currentUser);
        }

    }

    private void updateUI(FirebaseUser user) {
        //TODO
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
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                           Exception e = task.getException();
                            Toast.makeText(SignUpScreen.this, "Failed Registration: "+e.getMessage(), Toast.LENGTH_LONG).show();
                            updateUI(null);
                        }

                    }
                });
    }

    public void executeSignUp(View view) {
        //check passwords match
        if(!password.getText().toString().equals(reenterpassword.getText().toString())) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        createAccount(email.getText().toString(), password.getText().toString());
    }
}

