package grsl.com.locmessapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class SettingsActivity extends AppCompatActivity {

    EditText settingsName, settingsSurname, settingsUsername, settingsEmail;
    ImageButton backButton, saveButton;
    TextView changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        backButton = findViewById(R.id.header_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton = findViewById(R.id.header_save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserSettings();
            }
        });

        settingsName = findViewById(R.id.settings_name_input);
        settingsSurname = findViewById(R.id.settings_surname_input);
        settingsUsername = findViewById(R.id.settings_username_input);
        settingsEmail = findViewById(R.id.settings_email_input);
        changePassword = findViewById(R.id.settings_change_password_text);

        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void saveUserSettings(){
        finish();
    }
}
