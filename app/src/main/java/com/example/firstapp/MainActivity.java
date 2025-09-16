package com.example.firstapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private String[] activityNames;
    private String[] activityDescriptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        // Initialize view binding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set title for the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Demo Activities");
        }

        setupListView();
    }

    private void setupListView() {
        // Initialize arrays
        activityNames = new String[]{
            "View Event Listeners",
            "Input Controls"
        };

        activityDescriptions = new String[]{
            "Demo various event listeners like drag, touch, focus, etc.",
            "Demo input controls and validation"
        };

        // Setup ListView
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
            android.R.layout.simple_list_item_1, activityNames);
        binding.listViewActivities.setAdapter(adapter);

        // Set item click listener
        binding.listViewActivities.setOnItemClickListener((parent, view, position, id) -> {
            switch (position) {
                case 0:
                    startActivity(new Intent(MainActivity.this, ViewEventListenersActivity.class));
                    break;
                case 1:
                    startActivity(new Intent(MainActivity.this, InputControlsActivity.class));
                    break;
            }
        });

        // Set long click listener
        binding.listViewActivities.setOnItemLongClickListener((parent, view, position, id) -> {
            String description = activityDescriptions[position];
            Toast.makeText(MainActivity.this, description, Toast.LENGTH_LONG).show();
            return true;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            logout();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        // Navigate back to LoginActivity
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}