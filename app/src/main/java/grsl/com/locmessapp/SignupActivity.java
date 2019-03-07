package grsl.com.locmessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    Button signupButton;
    EditText nameText, surnameText, usernameText, emailText, passwordText;
    TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupButton = findViewById(R.id.btn_signup);
        nameText = findViewById(R.id.input_name);
        surnameText = findViewById(R.id.input_surname);
        usernameText = findViewById(R.id.input_username);
        emailText = findViewById(R.id.input_email);
        passwordText = findViewById(R.id.input_password);
        loginText = findViewById(R.id.link_login);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        loginText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void signup(){
        if(!validate()) {
            onSignupFailed();
            return;
        }

        signupButton.setEnabled(false);

        //signup

    }

    private void onSignupSuccess(){
        Toast.makeText(getBaseContext(), "Created Successfully", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(SignupActivity.this, MapsActivity.class);
        startActivity(intent);
        finish();
    }

    private void onSignupFailed(){
        Toast.makeText(getBaseContext(), "Creating Account failed", Toast.LENGTH_LONG).show();
    }

    private boolean validate(){
        boolean valid = true;

        String name = nameText.getText().toString();
        String surname = surnameText.getText().toString();
        String username = usernameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(name.isEmpty() || name.length() < 3) {
            nameText.setError("at least 3 characters");
            valid = false;
        }else {
            nameText.setError(null);
        }

        if(surname.isEmpty() || surname.length() < 3) {
            surnameText.setError("at least 2 characters");
            valid = false;
        }else {
            surnameText.setError(null);
        }

        if(username.isEmpty() || username.length() < 3) {
            usernameText.setError("at least 3 characters");
            valid = false;
        }else {
            usernameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 8 ) {
            passwordText.setError("min. 8 characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}
