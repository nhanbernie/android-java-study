package com.example.firstapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.DragEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.firstapp.databinding.ActivityViewEventListenersBinding;

public class ViewEventListenersActivity extends AppCompatActivity {

    private ActivityViewEventListenersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Enable edge-to-edge display
        EdgeToEdge.enable(this);

        // Initialize view binding
        binding = ActivityViewEventListenersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Apply window insets for safe area
        ViewCompat.setOnApplyWindowInsetsListener(binding.main, (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Set title for the action bar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Event Listeners Demo");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setupEventListeners();
    }    @SuppressLint("ClickableViewAccessibility")
    private void setupEventListeners() {

        // 1. setOnDragListener
        binding.textViewDragTarget.setOnDragListener((v, event) -> {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    binding.textViewDragStatus.setText("Drag started");
                    binding.textViewDragTarget.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    binding.textViewDragStatus.setText("Drag entered target");
                    binding.textViewDragTarget.setBackgroundColor(getColor(android.R.color.holo_green_light));
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    binding.textViewDragStatus.setText("Drag exited target");
                    binding.textViewDragTarget.setBackgroundColor(getColor(android.R.color.holo_blue_light));
                    return true;
                case DragEvent.ACTION_DROP:
                    binding.textViewDragStatus.setText("Dropped!");
                    binding.textViewDragTarget.setBackgroundColor(getColor(android.R.color.holo_orange_light));
                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    binding.textViewDragStatus.setText("Drag ended");
                    binding.textViewDragTarget.setBackgroundColor(getColor(android.R.color.background_light));
                    return true;
                default:
                    return false;
            }
        });

        // Make drag source draggable
        binding.textViewDragSource.setOnLongClickListener(v -> {
            View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
            v.startDragAndDrop(null, shadowBuilder, v, 0);
            return true;
        });

        // 2. setOnLongClickListener
        binding.buttonLongClick.setOnLongClickListener(v -> {
            binding.textViewLongClickStatus.setText("Long click detected! Time: " + System.currentTimeMillis());
            Toast.makeText(this, "Button was long clicked!", Toast.LENGTH_SHORT).show();
            return true;
        });

        // Regular click for comparison
        binding.buttonLongClick.setOnClickListener(v -> {
            binding.textViewLongClickStatus.setText("Regular click detected");
        });

        // 3. setOnFocusChangeListener
        binding.editTextFocus.setOnFocusChangeListener((v, hasFocus) -> {
            if (hasFocus) {
                binding.textViewFocusStatus.setText("EditText has focus");
                binding.editTextFocus.setBackgroundColor(getColor(android.R.color.holo_green_light));
            } else {
                binding.textViewFocusStatus.setText("EditText lost focus");
                binding.editTextFocus.setBackgroundColor(getColor(android.R.color.background_light));
            }
        });

        // 4. setOnTouchListener
        binding.viewTouchArea.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    binding.textViewTouchStatus.setText("Touch DOWN at (" + event.getX() + ", " + event.getY() + ")");
                    binding.viewTouchArea.setBackgroundColor(getColor(android.R.color.holo_red_light));
                    break;
                case MotionEvent.ACTION_MOVE:
                    binding.textViewTouchStatus.setText("Touch MOVE at (" + event.getX() + ", " + event.getY() + ")");
                    break;
                case MotionEvent.ACTION_UP:
                    binding.textViewTouchStatus.setText("Touch UP at (" + event.getX() + ", " + event.getY() + ")");
                    binding.viewTouchArea.setBackgroundColor(getColor(android.R.color.background_light));
                    break;
            }
            return true;
        });

        // 5. addTextChangedListener
        binding.editTextWatcher.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not used
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.textViewTextChangeStatus.setText("Text changed: \"" + s.toString() + "\" (Length: " + s.length() + ")");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not used
            }
        });

        // 6. setOnEditorActionListener
        binding.editTextEditor.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                String text = binding.editTextEditor.getText().toString();
                binding.textViewEditorStatus.setText("Editor action: \"" + text + "\"");
                Toast.makeText(this, "Done pressed: " + text, Toast.LENGTH_SHORT).show();
                return true;
            }
            return false;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}