package ge.gamp.learningandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import ge.gamp.learningandroid.adapters.ProgrammersListAdapter;
import ge.gamp.learningandroid.data.model.Programmer;
import ge.gamp.learningandroid.data.repository.CombinedRepository;
import ge.gamp.learningandroid.data.repository.Repository;

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

        refreshProgrammers();
    }

    void refreshProgrammers(){
        // ToDO: Change Progress bar visibility here
        this.programmers.clear();

        // Without notifyDataSetChanged - Listview won't know that there's been update to it's data
        this.programmersListAdapter.notifyDataSetChanged();

        repository.getProgrammers(programmers -> {
            this.programmers.clear();
            this.programmers.addAll(programmers);
            this.programmersListAdapter.notifyDataSetChanged();
        });
    }


}
