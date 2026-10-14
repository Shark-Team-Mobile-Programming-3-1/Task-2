package com.example.task2;


import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PatientListFragment extends Fragment {

    private PatientViewModel viewModel;
    private PatientAdapter adapter;

    public PatientListFragment() {
        super(R.layout.fragment_patient_list);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Activity-scoped: the same instance MainActivity and PatientDetailFragment use
        viewModel = new ViewModelProvider(requireActivity()).get(PatientViewModel.class);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_patients);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.addItemDecoration(
                new DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL));

        adapter = new PatientAdapter(viewModel::select);
        recyclerView.setAdapter(adapter);

        viewModel.getPatients().observe(getViewLifecycleOwner(), adapter::submitList);

        viewModel.getSelectedPatient().observe(getViewLifecycleOwner(),
                patient -> adapter.setSelectedId(patient == null ? -1 : patient.getId()));
    }
}