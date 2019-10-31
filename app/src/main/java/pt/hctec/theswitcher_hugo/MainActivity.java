package pt.hctec.theswitcher_hugo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pt.hctec.theswitcher_hugo.adapters.DivisionRecycleAdapter;
import pt.hctec.theswitcher_hugo.models.Division;
import pt.hctec.theswitcher_hugo.viewmodels.DivisionViewModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DivisionRecycleAdapter adapter;

    FloatingActionButton buttonAddDivision;

    public static final int ADD_DIVISION_REQUEST = 1;
    public static final int VIEW_DIVISION_REQUEST = 2;
    public static final int UPDATE_DIVISION_REQUEST = 3;

    private DivisionViewModel divisionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Action bar theme TODO

        /*getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.abs_layout);
        */

        recyclerView = findViewById(R.id.recycler_view);
        // DIVIDER
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        // final NoteRecycleAdapter adapter = new NoteRecycleAdapter();
        adapter = new DivisionRecycleAdapter();
        recyclerView.setAdapter(adapter);


        divisionViewModel = ViewModelProviders.of(this).get(DivisionViewModel.class);
        divisionViewModel.getAllDivisions().observe(this, new Observer<List<Division>>() {  // alldivisions Observer
            @Override
            public void onChanged(@Nullable List<Division> divisions) { // actvity has to be in foreground
                // update Recycler view
                adapter.setDivisions(divisions);

                Log.i("MAIN WINDOW", "MyClass — onChanged " );

                 adapter.notifyDataSetChanged();
            }


        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }


            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                divisionViewModel.delete(adapter.getDivisionAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Division deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new DivisionRecycleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Division division) {

                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_ID, division.getId());
                intent.putExtra(DetailActivity.EXTRA_TITLE, division.getTitle());
                intent.putExtra(DetailActivity.EXTRA_STATE, division.getState());

                startActivityForResult(intent, VIEW_DIVISION_REQUEST);

            }
        });



        buttonAddDivision = findViewById(R.id.floatingActionButton);
        buttonAddDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                startActivityForResult(intent, ADD_DIVISION_REQUEST); // For multiple activities
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_DIVISION_REQUEST && resultCode == RESULT_OK) {
            String title = data.getStringExtra(DetailActivity.EXTRA_TITLE);
            int status = data.getIntExtra(DetailActivity.EXTRA_STATE, 0);

            Division  division = new Division(title, status);
            divisionViewModel.insert(division);

            Toast.makeText(this, "Division SAVED ", Toast.LENGTH_SHORT).show();

        }
        else if (requestCode == UPDATE_DIVISION_REQUEST) {


        }  else {
            Toast.makeText(this, "Division NOT Saved", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete_all_divisions:
                divisionViewModel.deleteAlDivisions();
                Toast.makeText(this, "All Divisions deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
