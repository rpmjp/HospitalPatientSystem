public class Main {
    public static void main(String[] args) {
        TriageQueue queue = new TriageQueue();

        // servedCounts[1]..servedCounts[5]
        int[] servedCounts = new int[6];

        queue.arrive("Alice", 2);
        queue.arrive("Bob", 5);
        queue.arrive("Charlie", 3);
        queue.arrive("Diana", 5);

        System.out.println("\nCurrent waiting list:");
        queue.display();

        System.out.println("\nCalling next patient...");
        Node served = queue.call_next();
        if (served != null) servedCounts[served.severity]++;

        queue.arrive("Ethan", 1);
        queue.remove_patient("Charlie");

        System.out.println("\nQueue after serving and removing:");
        queue.display();

        System.out.println("\nCalling next patient...");
        served = queue.call_next();
        if (served != null) servedCounts[served.severity]++;

        System.out.println("\nCalling next patient...");
        served = queue.call_next();
        if (served != null) servedCounts[served.severity]++;

        System.out.println("\nCalling next patient...");
        served = queue.call_next();
        if (served != null) servedCounts[served.severity]++;

        // ---- FINAL SUMMARY ----
        System.out.println("\nFinal Simulation Output Metrics");

        System.out.println("\nQueue Statistics: ");
        System.out.printf("%-25s %s%n", "Metric", "Value");
        System.out.printf("%-25s %d%n", "Maximum Queue Length", queue.getMaxCapacity());

        System.out.println("\nPatients Served by Severity: ");
        System.out.printf("%-15s %s%n", "Severity Level", "Patients Served");
        for (int level = 1; level <= 5; level++) {
            System.out.printf("%-15d %d%n", level, servedCounts[level]);
        }
    }
}