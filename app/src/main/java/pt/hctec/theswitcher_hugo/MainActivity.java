package pt.hctec.theswitcher_hugo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pt.hctec.theswitcher_hugo.adapters.DivisionRecycleAdapter;
import pt.hctec.theswitcher_hugo.models.Division;
import pt.hctec.theswitcher_hugo.viewmodels.DivisionViewModel;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    DivisionRecycleAdapter adapter;

    FloatingActionButton buttonAddNote;

    private DivisionViewModel divisionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                // adapter.notifyDataSetChanged();
            }


        });

    }
}
