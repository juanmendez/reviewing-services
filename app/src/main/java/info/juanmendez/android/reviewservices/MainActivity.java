package info.juanmendez.android.reviewservices;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import info.juanmendez.android.reviewservices.services.FibonacciService;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(view -> {
            String value = editText.getText().toString();
            String buttonValue = button.getText().toString();

            Intent intent = new Intent(MainActivity.this, FibonacciService.class);

            if( buttonValue.equals( getString(R.string.start_service))){
                if( !value.isEmpty() ){
                    button.setText( getString(R.string.stop_service));
                    editText.setEnabled(false);

                    intent.putExtra("value", Integer.valueOf(value));
                    startService(intent);
                }
            }else{
                editText.setEnabled(true);
                button.setText( getString(R.string.start_service));

                stopService( intent );
            }
        });
    }
}
