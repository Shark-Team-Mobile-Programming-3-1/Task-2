package com.example.task2;


import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PatientAdapter extends RecyclerView.Adapter<PatientAdapter.PatientViewHolder> {

    public interface OnPatientClickListener {
        void onPatientClick(Patient patient);
    }

    private final List<Patient> patients = new ArrayList<>();
    private final OnPatientClickListener listener;
    private int selectedId = -1;

    public PatientAdapter(OnPatientClickListener listener) {
        this.listener = listener;
    }

    public void submitList(List<Patient> newList) {
        patients.clear();
        if (newList != null) {
            patients.addAll(newList);
        }
        notifyDataSetChanged();
    }

    public void setSelectedId(int id) {
        selectedId = id;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PatientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_patient, parent, false);
        return new PatientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PatientViewHolder holder, int position) {
        Patient patient = patients.get(position);

        holder.name.setText(patient.getName());
        holder.meta.setText(patient.getAge() + " yrs  •  "
                + patient.getGender() + "  •  " + patient.getDistrict());
        holder.summary.setText(patient.getSummary());

        // Highlight the selected row (useful in the tablet two-pane layout)
        holder.itemView.setBackgroundColor(
                patient.getId() == selectedId ? 0xFFE3F2FD : Color.TRANSPARENT);

        holder.itemView.setOnClickListener(v -> listener.onPatientClick(patient));
    }

    @Override
    public int getItemCount() {
        return patients.size();
    }

    public static class PatientViewHolder extends RecyclerView.ViewHolder {
        final TextView name;
        final TextView meta;
        final TextView summary;

        PatientViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.text_name);
            meta = itemView.findViewById(R.id.text_meta);
            summary = itemView.findViewById(R.id.text_summary);
        }
    }
}