package css.roomdb_heartrate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    // variables linked to UI widgets in XML layout file
    EditText editTextPulse;
    EditText editTextAge;
    EditText editTextDisplay;
    Button buttonInsert, buttonUpdate;

    MainViewModel mainViewModel;        // ViewModel with ties to Room database

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Link variables to XML layout file
        editTextAge = findViewById(R.id.editTextAge);
        editTextPulse = findViewById(R.id.editTextPulse);
        editTextDisplay = findViewById(R.id.editTextDisplay);
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        // Set up ViewModel so that is aware of the application context
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // Set up the button listeners
        setupInsertButton();
        setupUpdateButton();

    }

    /**
     *  Set up the OnClickListener for the Insert Button
     */
    private void setupInsertButton() {
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer pulse = Integer.parseInt(editTextPulse.getText().toString());
                Integer age = Integer.parseInt(editTextAge.getText().toString());
                mainViewModel.insert(pulse, age);

            }
        });
    }
    /**
     *  Set up the OnClickListener for the Update Button
     */
    private void setupUpdateButton() {
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strRates = mainViewModel.getHeartratesAsString();
                editTextDisplay.setText(strRates);
            }
        });
    }

}  // end of class