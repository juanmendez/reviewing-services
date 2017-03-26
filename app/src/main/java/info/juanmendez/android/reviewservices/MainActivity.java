package info.juanmendez.android.reviewservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView testResult;

    MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        testResult = (TextView) findViewById(R.id.testResult);

        button.setOnClickListener(view -> {
            String value = editText.getText().toString();
            presenter.doFibonacci( Integer.valueOf(value));
        });

        presenter.onStart(savedInstanceState);
    }

    public void setResultValue( String s ){
        testResult.setText( s );
    }

    @Override
    public void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState(outState);
        presenter.onStop(outState);
    }
}
