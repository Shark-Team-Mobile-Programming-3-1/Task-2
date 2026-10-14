package com.example.task2;


import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_LIST = "patient_list";
    private static final String TAG_DETAIL = "patient_detail";

    private PatientViewModel viewModel;
    private boolean twoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Only the sw600dp (tablet) layout contains detail_container
        twoPane = findViewById(R.id.detail_container) != null;

        viewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        FragmentManager fm = getSupportFragmentManager();

        // Add fragments only on first launch; after rotation they are restored automatically
        if (savedInstanceState == null) {
            FragmentTransaction ft = fm.beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.list_container, new PatientListFragment(), TAG_LIST);
            if (twoPane) {
                ft.replace(R.id.detail_container, new PatientDetailFragment(), TAG_DETAIL);
            }
            ft.commit();
        }

        fm.addOnBackStackChangedListener(this::onBackStackChanged);

        // Phone only: when a patient is selected, replace the list with the detail.
        // On tablets the detail fragment is already visible and updates itself.
        viewModel.getSelectedPatient().observe(this, patient -> {
            if (twoPane) {
                return;
            }
            if (patient == null) {
                setTitle(R.string.app_name);
            } else {
                setTitle(patient.getName());
                showDetailOnPhone();
            }
        });

        updateUpButton();
    }

    private void showDetailOnPhone() {
        FragmentManager fm = getSupportFragmentManager();

        // Already showing (e.g. after screen rotation)? Do nothing.
        if (fm.findFragmentByTag(TAG_DETAIL) != null) {
            return;
        }

        fm.beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.list_container, new PatientDetailFragment(), TAG_DETAIL)
                .addToBackStack(null)   // so the Back button returns to the list
                .commit();
    }

    private void onBackStackChanged() {
        updateUpButton();
        // Back on the list (phone): clear the selection so rotation doesn't reopen the detail
        if (!twoPane && getSupportFragmentManager().getBackStackEntryCount() == 0) {
            viewModel.clearSelection();
        }
    }

    private void updateUpButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            boolean showUp = !twoPane
                    && getSupportFragmentManager().getBackStackEntryCount() > 0;
            actionBar.setDisplayHomeAsUpEnabled(showUp);
        }
    }

    // The toolbar's "up" arrow behaves like Back
    @Override
    public boolean onSupportNavigateUp() {
        getSupportFragmentManager().popBackStack();
        return true;
    }
}