package app.cave.mathgame;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnItemSelectedListener {
    TextView decTV, binTV, hexTV, octTV;
    EditText numEdt;
    String numFormate[] = new String[4];
    Spinner formatNumSpineer;

    String num;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.hide();
        }

        decTV = findViewById(R.id.decNumTxt);
        binTV = findViewById(R.id.binNumTxt);
        octTV = findViewById(R.id.octNumTxt);
        hexTV = findViewById(R.id.hexNumTxt);
        numEdt = findViewById(R.id.numberEDId);
        formatNumSpineer = findViewById(R.id.formatNumID);


        numFormate = getResources().getStringArray(R.array.numberFormat);
        ArrayAdapter a = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, numFormate);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        formatNumSpineer.setAdapter(a);

        formatNumSpineer.setOnItemSelectedListener(this);


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        num = numEdt.getText().toString().trim();
        if(num.isEmpty())
        {
            numEdt.setError("Enter number first");
            numEdt.requestFocus();
        }
        if (position == 1) {

            num = numEdt.getText().toString().trim();
            if(num.isEmpty())
            {
                numEdt.setError("Enter number first");
                numEdt.requestFocus();
            }
            Integer i = Integer.parseInt(num, 2);
            binTV.setText(num);
            octTV.setText(Integer.toOctalString(i));
            decTV.setText(Integer.toString(i));
            hexTV.setText(Integer.toHexString(i));

            //Toast.makeText(this, "binary", Toast.LENGTH_SHORT).show();
        }
        if (position == 2) {
            num = numEdt.getText().toString().trim();
            if(num.isEmpty())
            {
                numEdt.setError("Enter number first");
                numEdt.requestFocus();
            }
            Integer i = Integer.parseInt(num);
            binTV.setText(Integer.toBinaryString(i));
            octTV.setText(Integer.toOctalString(i));
            decTV.setText(num);
            hexTV.setText(Integer.toHexString(i));

            //Toast.makeText(this, "decimal", Toast.LENGTH_SHORT).show();
        }
        if (position == 3) {
            num = numEdt.getText().toString().trim();
            if(num.isEmpty())
            {
                numEdt.setError("Enter number first");
                numEdt.requestFocus();
            }
            Integer i = Integer.parseInt(num, 8);
            binTV.setText(Integer.toBinaryString(i));
            octTV.setText(num);
            decTV.setText(Integer.toString(i));
            hexTV.setText(Integer.toHexString(i));

            //Toast.makeText(this, "octal", Toast.LENGTH_SHORT).show();
        }
        if (position == 4) {
            num = numEdt.getText().toString().trim();
            if(num.isEmpty())
            {
                numEdt.setError("Enter number first");
                numEdt.requestFocus();
            }
            Integer i = Integer.parseInt(num, 16);
            binTV.setText(Integer.toBinaryString(i));
            octTV.setText(Integer.toOctalString(i));
            decTV.setText(Integer.toString(i));
            hexTV.setText(num);

            //Toast.makeText(this, "hex", Toast.LENGTH_SHORT).show();
        }

        /*// On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
*/
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}