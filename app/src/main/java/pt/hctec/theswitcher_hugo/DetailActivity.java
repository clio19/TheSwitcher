package pt.hctec.theswitcher_hugo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "pt.hctec.theswitcher_hugomende.EXTRA_ID";

    public static final String EXTRA_TITLE =
            "pt.hctec.theswitcher_hugomendes.EXTRA_TITLE";
    public static final String EXTRA_STATE =
            "pt.hctec.theswitcher_hugomendes.EXTRA_PRIORITY";

    private EditText editTextTitle;
    private Switch toggleSwitch;
    ImageView img;
    private TextView textDivision;
    private  TextView statusDivision;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        editTextTitle = findViewById(R.id.edit_text_title);
        toggleSwitch = findViewById(R.id.switchInitial);
        img= (ImageView) findViewById(R.id.headerImage);

        textDivision =  findViewById(R.id.textDivision);
        statusDivision = findViewById(R.id.statusDivision);

        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle(intent.getStringExtra(EXTRA_TITLE));
            editTextTitle.setVisibility(View.GONE);
            textDivision.setText( " Your " + intent.getStringExtra(EXTRA_TITLE) + " light is");
            statusDivision.setText( intent.getIntExtra(EXTRA_STATE,1) == 1 ? " ON " : "OFF" );
            toggleSwitch.setVisibility(View.GONE);
            img.setImageResource( intent.getIntExtra(EXTRA_STATE,1) == 1 ? R.drawable.light_on : R.drawable.light_off);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_chevron_left_black_24dp);

        } else {
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);
            setTitle("Please add Division");
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_division_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id == android.R.id.home){
            saveDivision();
        } else if (id == R.id.save_division){
            saveDivision();
        }
        return super.onOptionsItemSelected(item);
    }


    private void saveDivision() {
        String title = editTextTitle.getText().toString();
        int state = toggleSwitch.isChecked() ? 1 : 0 ;

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_STATE, state);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
            setResult(RESULT_CANCELED, data);
        } else {

            setResult(RESULT_OK, data);
        }

        finish();
    }

}
