package ge.gamp.learningandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ge.gamp.learningandroid.adapters.ProgrammersListAdapter;
import ge.gamp.learningandroid.data.model.Programmer;
import ge.gamp.learningandroid.data.repository.CombinedRepository;
import ge.gamp.learningandroid.data.repository.Repository;
import ge.gamp.learningandroid.dialogs.CreateProgrammerFragment;

public class ListActivity extends AppCompatActivity {
    private Repository repository;

    private final List<Programmer> programmers = new ArrayList<>();

    private ListView listView;
    private ProgressBar progressBar;

    private ProgrammersListAdapter programmersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        progressBar = findViewById(R.id.progressBar);

        progressBar.setVisibility(View.INVISIBLE);
        repository = CombinedRepository.getInstance();

        // It's important not to change reference of the list provided here. That's why it's final!
        programmersListAdapter = new ProgrammersListAdapter(this, programmers);
        listView.setAdapter(programmersListAdapter);

        // create a listener for each item in the listView
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override

            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Programmer programmerToShow = programmers.get(i);
                String programmerName = programmerToShow.getName();

                Toast.makeText(getApplicationContext(), programmerName,
                        Toast.LENGTH_SHORT).show();

//                repository.getProgrammer();
//                repository.getProgrammer(2, null);
            }
        });

        refreshProgrammers();

        findViewById(R.id.btn_refresh).setOnClickListener(event -> {
            refreshProgrammers();
        });

        findViewById(R.id.btn_back).setOnClickListener(event -> {
            goBackHome();
        });

        findViewById(R.id.btn_add_programmer).setOnClickListener(event -> {
            openModalToAddProgrammer();
        });
    }

    void refreshProgrammers() {
        // TODO: Change Progress bar visibility here

        progressBar.setVisibility(View.VISIBLE);
        this.programmers.clear();

        // Without notifyDataSetChanged - Listview won't know that there's been update to it's data
        this.programmersListAdapter.notifyDataSetChanged();

        repository.getProgrammers(programmers -> {
            this.programmers.clear();
            this.programmers.addAll(programmers);
            this.programmersListAdapter.notifyDataSetChanged();
            progressBar.setVisibility(View.INVISIBLE);
        });
    }

    void openModalToAddProgrammer() {
        CreateProgrammerFragment dialog = new CreateProgrammerFragment();
        dialog.show(getSupportFragmentManager(), "Programmer details");
    }

    void goBackHome() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    void getProgrammer() {
//        repository.getProgrammer();
    }
}
