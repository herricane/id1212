package se.kth.task5extra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private EditText editEmail;
    private EditText editPassword;
    private TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editTextNumber);
        editEmail = (EditText) findViewById(R.id.editTextEmail);
        editPassword = (EditText) findViewById(R.id.editTextPassword);

        textContent = (TextView) findViewById(R.id.textContent);
        textContent.setMovementMethod(ScrollingMovementMethod.getInstance());
        Button button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new FetchButtonListener());
    }

    private class FetchButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            String index = editText.getText().toString();
            String email = editEmail.getText().toString();
            String password = editPassword.getText().toString();
            new FetchTask().execute(email, password, index);
        }
    }

    private class FetchTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String user = strings[0];
            String password = strings[1];
            int index = Integer.parseInt(strings[2]);

            return MailFetcher.fetchMail(user, password, index);
        }

        @Override
        protected void onPostExecute(String mail) {
            textContent.setText(mail);
        }
    }
}