public class TriageQueue {

    // Front of the queue (next to be treated)
    private Node head;

    // End of the queue (where normal patients 1–4 are appended)
    private Node tail;

    // Current number of patients in the queue
    private int size;
    private int maxCapacity;

    public TriageQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.maxCapacity = 0;
    }

    public void arrive(String name, int severity) {
        Node newPatient = new Node(name, severity);

        // empty line
        if (head == null) {
            head = tail = newPatient;
            size++;
            updateMaxCapacity();
            System.out.println("Patient " + newPatient + " arrived and added to the queue.");
            return;
        }

        // severity 1–4: normal queue -> goes to the end
        if (severity < 5) {
            tail.next = newPatient;
            tail = newPatient;
            size++;
            updateMaxCapacity();
            System.out.println("Patient " + newPatient + " arrived and added to the end of the queue.");
            return;
        }

        // severity 5: critical patient
        // if the front isn't critical, this patient jumps to the front
        if (head.severity < 5) {
            newPatient.next = head;
            head = newPatient;
            size++;
            updateMaxCapacity();
            System.out.println("Critical patient " + newPatient + " arrived and added to the front of the queue.");
            return;
        }

        // otherwise, we already have critical patients at the front.
        // find where the block of 5s ends
        Node current = head;
        while (current.next != null && current.next.severity == 5) {
            current = current.next;
        }

        newPatient.next = current.next;
        current.next = newPatient;

        if (newPatient.next == null) {
            tail = newPatient;
        }

        size++;
        updateMaxCapacity();
        System.out.println("Critical patient " + newPatient + " arrived and added after the other critical patients.");
    }

    public Node call_next() {
        if (head == null) {
            System.out.println("No patients in queue.");
            return null;
        }

        Node served = head;
        head = head.next;
        size--;

        if (head == null) {
            tail = null;
        }

        System.out.println("Patient: " + served + " is being treated.");
        return served;
    }

    public void remove_patient(String name) {
        if (head == null) {
            System.out.println("No patients in line.");
            return;
        }

        // check the head first
        if (head.name.equals(name)) {
            head = head.next;
            if (head == null) tail = null;
            size--;
            System.out.println("Patient: " + name + " has left the waiting line.");
            return;
        }

        // search through the rest
        Node prev = head;
        for (Node curr = head.next; curr != null; curr = curr.next) {
            if (curr.name.equals(name)) {
                prev.next = curr.next;
                if (curr == tail) tail = prev;
                size--;
                System.out.println("Patient: " + name + " has left the waiting line.");
                return;
            }
            prev = curr;
        }

        System.out.println("Patient not found: " + name);
    }

    public void display() {
        if (head == null) {
            System.out.println("Waiting list is empty.");
            return;
        }

        System.out.print("Waiting list: ");
        for (Node n = head; n != null; n = n.next) {
            System.out.print(n);
            if (n.next != null) System.out.print(" -> ");
        }
        System.out.println();
    }

    private void updateMaxCapacity() {
        if (size > maxCapacity) {
            maxCapacity = size;
        }
    }
    public boolean isEmpty() {
        return head == null;   // or: return size == 0;
    }
    public int getSize() {
        return size;
    }
    public int getMaxCapacity() {
        return maxCapacity;
    }
}