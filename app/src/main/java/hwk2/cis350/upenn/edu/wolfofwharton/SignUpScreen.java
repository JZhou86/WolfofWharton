package hwk2.cis350.upenn.edu.wolfofwharton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class SignUpScreen extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private EditText reenterpassword;
    private TextView msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_screen);

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        reenterpassword = (EditText) findViewById(R.id.reenterpassword);
        msg = (TextView) findViewById(R.id.simpleTextView);

    }

    public void executeSignUp(View view) {
        //check passwords match
        if(!password.getText().toString().equals(reenterpassword.getText().toString())) {
            msg.setText("Passwords do not match");
            msg.setVisibility(View.VISIBLE);
            return;
        }

        //TODO: call WelcomeScreen.java method 'createAccount()' and pass in fields
        //WelcomeScreen.createAccount(email, password, reenterpassword);
    }
}

