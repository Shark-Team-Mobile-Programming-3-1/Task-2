package com.example.task2;


import java.util.List;

public class Patient {

    private final int id;
    private final String name;
    private final int age;
    private final String gender;
    private final String district;
    private final String summary;
    private final List<MedicalRecord> history;

    public Patient(int id, String name, int age, String gender, String district,
                   String summary, List<MedicalRecord> history) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.district = district;
        this.summary = summary;
        this.history = history;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getDistrict() { return district; }
    public String getSummary() { return summary; }
    public List<MedicalRecord> getHistory() { return history; }

    /** One entry in a patient's medical history. */
    public static class MedicalRecord {
        private final String date;
        private final String condition;
        private final String treatment;

        public MedicalRecord(String date, String condition, String treatment) {
            this.date = date;
            this.condition = condition;
            this.treatment = treatment;
        }

        public String getDate() { return date; }
        public String getCondition() { return condition; }
        public String getTreatment() { return treatment; }
    }
}
