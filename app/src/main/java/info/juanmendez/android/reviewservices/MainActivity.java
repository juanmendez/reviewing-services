package info.juanmendez.android.reviewservices;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import icepick.Icepick;
import icepick.State;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;
    private TextView testResult;
    @State String fibString;

    MainPresenter presenter = new MainPresenter(this);

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Icepick.restoreInstanceState( this, savedInstanceState );
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);
        testResult = (TextView) findViewById(R.id.testResult);

        setResultValue(fibString);
        button.setOnClickListener(view -> {
            String value = editText.getText().toString();
            presenter.doFibonacci( Integer.valueOf(value));
        });
    }

    public void setResultValue( String s ){
        testResult.setText( fibString = s );
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }


}
