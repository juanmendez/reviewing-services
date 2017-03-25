package info.juanmendez.android.reviewservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(view -> {
            String value = editText.getText().toString();
            presenter.doFibonacci( Integer.valueOf(value));
        });
    }


    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }


}
