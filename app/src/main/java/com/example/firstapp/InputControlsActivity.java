package com.example.firstapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstapp.databinding.ActivityInputControlsBinding;

public class InputControlsActivity extends AppCompatActivity {

    private ActivityInputControlsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Initialize view binding
        binding = ActivityInputControlsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply window insets for safe area
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set title for the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Input Controls Demo");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setupInputControls();
    }

    private void setupInputControls() {

        // 1. EditText - show text as user types
        binding.editTextMessage.setOnClickListener(v -> {
            String text = binding.editTextMessage.getText().toString();
            if (!text.isEmpty()) {
                Toast.makeText(this, "Message: " + text, Toast.LENGTH_SHORT).show();
            }
        });

        // 2. SeekBar - show progress value
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textViewSeekBarValue.setText("SeekBar Value: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Optional: handle start tracking
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(InputControlsActivity.this, "SeekBar stopped at: " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

        // 3. CheckBox - show checked state
        binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.textViewCheckBoxStatus.setText("CheckBox: " + (isChecked ? "Checked ✓" : "Unchecked"));
            if (isChecked) {
                Toast.makeText(this, "CheckBox is now checked!", Toast.LENGTH_SHORT).show();
            }
        });

        // 4. RadioButton Group - show selected option
        binding.radioGroup.setOnCheckedChangeListener((group, checkedId) -> {
            String selectedOption = "";
            if (checkedId == R.id.radioButtonOption1) {
                selectedOption = "Option 1";
            } else if (checkedId == R.id.radioButtonOption2) {
                selectedOption = "Option 2";
            }
            binding.textViewRadioStatus.setText("Selected: " + selectedOption);
            Toast.makeText(this, "Radio selected: " + selectedOption, Toast.LENGTH_SHORT).show();
        });

        // 5. Switch - show on/off state
        binding.switchToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            binding.textViewSwitchStatus.setText("Switch: " + (isChecked ? "ON" : "OFF"));
            String message = "Switch is " + (isChecked ? "ON" : "OFF");
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        // 6. Spinner - setup dropdown options
        String[] phoneTypes = {"Home", "Work", "Mobile", "Other"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, phoneTypes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedType = phoneTypes[position];
                binding.textViewSpinnerStatus.setText("Phone Type: " + selectedType);
                Toast.makeText(InputControlsActivity.this, "Selected: " + selectedType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}