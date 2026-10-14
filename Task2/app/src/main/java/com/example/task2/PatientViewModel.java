package com.example.task2;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class PatientViewModel extends ViewModel {

    private final MutableLiveData<List<Patient>> patients =
            new MutableLiveData<>(PatientRepository.getPatients());

    private final MutableLiveData<Patient> selectedPatient = new MutableLiveData<>();

    public LiveData<List<Patient>> getPatients() {
        return patients;
    }

    public LiveData<Patient> getSelectedPatient() {
        return selectedPatient;
    }

    public void select(Patient patient) {
        selectedPatient.setValue(patient);
    }

    public void clearSelection() {
        selectedPatient.setValue(null);
    }
}