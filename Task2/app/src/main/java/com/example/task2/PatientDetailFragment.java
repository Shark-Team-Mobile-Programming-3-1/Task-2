package com.example.task2;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class PatientDetailFragment extends Fragment {

    public PatientDetailFragment() {
        super(R.layout.fragment_patient_detail);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        PatientViewModel viewModel =
                new ViewModelProvider(requireActivity()).get(PatientViewModel.class);

        TextView placeholder = view.findViewById(R.id.text_placeholder);
        View content = view.findViewById(R.id.detail_content);
        TextView name = view.findViewById(R.id.detail_name);
        TextView meta = view.findViewById(R.id.detail_meta);
        TextView summary = view.findViewById(R.id.detail_summary);
        TextView history = view.findViewById(R.id.detail_history);

        viewModel.getSelectedPatient().observe(getViewLifecycleOwner(), patient -> {
            if (patient == null) {
                placeholder.setVisibility(View.VISIBLE);
                content.setVisibility(View.GONE);
                return;
            }

            placeholder.setVisibility(View.GONE);
            content.setVisibility(View.VISIBLE);

            name.setText(patient.getName());
            meta.setText(patient.getAge() + " yrs  •  "
                    + patient.getGender() + "  •  " + patient.getDistrict());
            summary.setText(patient.getSummary());

            StringBuilder sb = new StringBuilder();
            for (Patient.MedicalRecord record : patient.getHistory()) {
                sb.append("• ").append(record.getDate())
                        .append(" – ").append(record.getCondition()).append('\n')
                        .append("   Treatment: ").append(record.getTreatment())
                        .append("\n\n");
            }
            history.setText(sb.toString().trim());
        });
    }
}