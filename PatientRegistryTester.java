import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class PatientRegistryTester {

    public static void main(String[] args) {
        // Create an instance of the PatientRegistry
        PatientRegistry patientRegistry = new PatientRegistry();
        Scanner scanner = new Scanner(System.in);

        try {
            // Continuously display the menu until the user chooses to exit
            while (true) {
                // Display the menu options
                printMenu();

                int choice;
                try {
                    // Read the user's choice
                    choice = scanner.nextInt();
                } catch (InputMismatchException e) {
                    // Handle invalid input (non-integer)
                    System.err.println("Invalid input. Please enter a number.");
                    scanner.nextLine();  // Consume the invalid input
                    continue;
                }

                scanner.nextLine();  // Consume the newline character

                // Process the user's choice
                switch (choice) {
                    case 1:
                        addPatient(patientRegistry, scanner);
                        break;

                    case 2:
                        deletePatient(patientRegistry, scanner);
                        break;

                    case 3:
                        viewAllPatients(patientRegistry);
                        break;

                    case 4:
                        checkIfPatientRegistered(patientRegistry, scanner);
                        break;

                    case 5:
                        displayTotalPatients(patientRegistry);
                        break;

                    case 6:
                        exitProgram();
                        break;

                    default:
                        // Invalid choice
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                        break;
                }
            }
        } catch (Exception e) {
            // Handle unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
        } finally {
            // Close the Scanner using try-with-resources
            scanner.close();
        }
    }

    // Display the menu options
    private static void printMenu() {
        System.out.println("\n ********** Patient Management System ********** ");
        System.out.println("1. Add Patient Record");
        System.out.println("2. Delete Patient Record");
        System.out.println("3. View All Patients");
        System.out.println("4. Check if Patient is Registered");
        System.out.println("5. Display Total Number of Registered Patients");
        System.out.println("6. Exit");
        System.out.print("Select an option (1-6): ");
    }

    private static void addPatient(PatientRegistry patientRegistry, Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        patientRegistry.addPatientRecord(id, name, age);
        System.out.println("Patient Record Added Successfully!");
    }

    private static void deletePatient(PatientRegistry patientRegistry, Scanner scanner) {
        System.out.print("Enter Patient ID to Delete: ");
        String deleteId = scanner.nextLine();
        patientRegistry.deletePatientRecord(deleteId);
    }

    private static void viewAllPatients(PatientRegistry patientRegistry) {
        Set<PatientRegistry.Patient> patients = patientRegistry.getPatients();
        System.out.println("All Patients:");
        if (patients.isEmpty()) {
            System.out.println("No patients registered.");
        } else {
            for (PatientRegistry.Patient patient : patients) {
                System.out.println(patient);
            }
        }
    }

    private static void checkIfPatientRegistered(PatientRegistry patientRegistry, Scanner scanner) {
        System.out.print("Enter Patient ID to Check: ");
        String patientId = scanner.nextLine();
        boolean isRegistered = patientRegistry.isRegistered(new PatientRegistry.Patient(patientId, "", 0));

        if (isRegistered) {
            System.out.println("Patient with ID '" + patientId + "' is registered.");
        } else {
            System.out.println("Patient with ID '" + patientId + "' is not registered.");
        }
    }

    private static void displayTotalPatients(PatientRegistry patientRegistry) {
        int totalPatients = patientRegistry.numberRegistered();
        System.out.println("Total Number of Registered Patients: " + totalPatients);
    }

    private static void exitProgram() {
        System.out.println("Exiting Program. Goodbye!");
        System.exit(0);
    }
}
