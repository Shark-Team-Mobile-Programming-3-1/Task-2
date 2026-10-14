package com.example.task2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class PatientRepository {

    private PatientRepository() { }

    public static List<Patient> getPatients() {
        List<Patient> list = new ArrayList<>();

        list.add(new Patient(1, "Namukasa Sarah", 28, "Female", "Mukono",
                "Antenatal care, 32 weeks. Mild anaemia under treatment.",
                Arrays.asList(
                        rec("12 Aug 2026", "Antenatal visit 3", "Iron and folic acid; advised on nutrition"),
                        rec("10 Jun 2026", "Antenatal visit 2", "Malaria prophylaxis (IPTp) given"),
                        rec("15 Apr 2026", "Antenatal visit 1", "Routine tests; tetanus vaccine"))));

        list.add(new Patient(2, "Okello James", 45, "Male", "Gulu",
                "Hypertension, on daily medication. Blood pressure mostly controlled.",
                Arrays.asList(
                        rec("02 Sep 2026", "Hypertension follow-up", "Continue amlodipine 5mg daily"),
                        rec("01 Jun 2026", "Hypertension diagnosis", "Started amlodipine; lifestyle counselling"))));

        list.add(new Patient(3, "Atim Grace", 6, "Female", "Lira",
                "Recovering from uncomplicated malaria. Fully immunised for age.",
                Arrays.asList(
                        rec("28 Sep 2026", "Uncomplicated malaria", "Artemether-Lumefantrine, 3-day course"),
                        rec("14 Feb 2026", "Routine immunisation", "Measles-Rubella booster"))));

        list.add(new Patient(4, "Mugisha Robert", 52, "Male", "Mbarara",
                "Type 2 diabetes. Needs regular blood sugar monitoring.",
                Arrays.asList(
                        rec("20 Sep 2026", "Diabetes review", "Metformin dose adjusted; diet advice"),
                        rec("18 Jun 2026", "Diabetes review", "Fasting glucose high; started metformin"),
                        rec("05 Mar 2026", "Diabetes diagnosis", "Education and baseline tests"))));

        list.add(new Patient(5, "Nakato Esther", 34, "Female", "Jinja",
                "Treated for typhoid. Advised on safe water and hygiene.",
                Arrays.asList(
                        rec("07 Oct 2026", "Typhoid fever", "Ciprofloxacin 7 days; oral rehydration"),
                        rec("11 Jul 2026", "Minor wound", "Cleaned and dressed; tetanus checked"))));

        list.add(new Patient(6, "Opio Daniel", 8, "Male", "Arua",
                "Asthma, mild persistent. Uses inhaler when needed.",
                Arrays.asList(
                        rec("30 Sep 2026", "Asthma follow-up", "Salbutamol inhaler; avoid dust triggers"),
                        rec("22 May 2026", "Asthma attack", "Nebulisation at HC IV, then discharged"))));

        list.add(new Patient(7, "Tumusiime Patience", 61, "Female", "Masaka",
                "Osteoarthritis (knees). Pain managed with medication.",
                Arrays.asList(
                        rec("15 Sep 2026", "Knee pain review", "Paracetamol; physiotherapy exercises"),
                        rec("03 Apr 2026", "Osteoarthritis diagnosis", "X-ray at regional referral hospital"))));

        list.add(new Patient(8, "Kato Joseph", 19, "Male", "Mbale",
                "Treated for respiratory infection. Fully recovered.",
                Arrays.asList(
                        rec("19 Aug 2026", "Chest infection", "Amoxicillin 5 days; rest and fluids"),
                        rec("09 Jan 2026", "Routine check-up", "No issues found"))));

        return list;
    }

    private static Patient.MedicalRecord rec(String date, String condition, String treatment) {
        return new Patient.MedicalRecord(date, condition, treatment);
    }
}