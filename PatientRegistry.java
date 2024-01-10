import java.util.HashSet;
import java.util.Set;

public class PatientRegistry {
    // Types
    public static class Patient {
        private String id;
        private String name;
        private int age;

        public Patient(String id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Patient patient = (Patient) obj;
            return id.equals(patient.id);
        }

        @Override
        public int hashCode() {
            return id.hashCode();
        }

        @Override
        public String toString() {
            return "Patient{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }

    // Values
    private static final int LIMIT = 200;

    public static int getLimit() {
        return LIMIT;
    }

    public static class RegistryFullException extends RuntimeException {
        public RegistryFullException(String message) {
            super(message);
        }
    }

    // State
    private Set<Patient> reg = new HashSet<>();

    // Operations
    public void addPatientRecord(String id, String name, int age) {
        Patient patient = new Patient(id, name, age);
        addPatient(patient);
    }

    public void deletePatientRecord(String id) {
        Patient patientToRemove = null;
        for (Patient patient : reg) {
            if (patient.id.equals(id)) {
                patientToRemove = patient;
                break;
            }
        }

        if (patientToRemove != null) {
            removePatient(patientToRemove);
            System.out.println("Patient Record Deleted Successfully!");
        } else {
            System.out.println("No such patient with ID '" + id + "' exists.");
        }
    }

    public void addPatient(Patient patientIn) {
        if (reg.size() < LIMIT) {
            if (!reg.contains(patientIn)) {
                reg.add(patientIn);
            } else {
                System.err.println("Error: Patient with ID " + patientIn.id + " is already registered.");
            }
        } else {
            throw new RegistryFullException("Patient registry is full. Cannot add more patients.");
        }
    }

    public void removePatient(Patient patientIn) {
        reg.remove(patientIn);
    }

    public Set<Patient> getPatients() {
        return new HashSet<>(reg);
    }

    public boolean isRegistered(Patient patientIn) {
        return reg.contains(patientIn);
    }

    public int numberRegistered() {
        return reg.size();
    }
}
