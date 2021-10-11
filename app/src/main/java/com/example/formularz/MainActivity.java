package com.example.formularz;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void formCheck(View view) {
        EditText nameObj = findViewById(R.id.name),
                mailObj = findViewById(R.id.mail),
                phoneObj = findViewById(R.id.phone);

        String name = nameObj.getText().toString(),
                mail = mailObj.getText().toString(),
                phone = phoneObj.getText().toString();

        TextView tempObj = findViewById(R.id.nameError);

        boolean nameLenError = false;
        boolean mailLenError = false;
        boolean phoneLenError = false;

        if (name.length() == 0) {
            tempObj.setText("Wymagane");
            nameLenError = true;
        } else {
            tempObj.setText("");
        }

        tempObj = findViewById(R.id.mailError);

        if (mail.length() == 0) {
            tempObj.setText("Wymagane");
            mailLenError = true;
        } else {
            tempObj.setText("");
        }

        tempObj = findViewById(R.id.phoneError);

        if (phone.length() == 0) {
            tempObj.setText("Wymagane");
            phoneLenError = true;
        } else {
            tempObj.setText("");
        }

        Pattern namePattern = Pattern.compile("[A-Z][a-z]+"),
                mailPattern = Pattern.compile("[A-Za-z._]{2,}@.*\\.[a-z0-9]{2,3}"),
                phonePattern = Pattern.compile("\\+((\\d{2}-\\d{2}-\\d{7})|(\\d{2}-\\d{9}))");

        Matcher nameMatcher = namePattern.matcher(name),
                mailMatcher = mailPattern.matcher(mail),
                phoneMatcher = phonePattern.matcher(phone);

        boolean nameMatch = nameMatcher.matches(),
                mailMatch = mailMatcher.matches(),
                phoneMatch = phoneMatcher.matches();

        if (!nameMatch && !nameLenError) {
            tempObj = findViewById(R.id.nameError);
            tempObj.setText("Błędne dane!");
        }

        if (!mailMatch && !mailLenError) {
            tempObj = findViewById(R.id.mailError);
            tempObj.setText("Błędne dane!");
        }

        if (!phoneMatch && !phoneLenError) {
            tempObj = findViewById(R.id.phoneError);
            tempObj.setText("Błędne dane!");
        }

        if (nameMatch && mailMatch && phoneMatch) {
            Toast.makeText(getApplicationContext(), "Formularz wysłany!", Toast.LENGTH_SHORT).show();
        }
    }
}