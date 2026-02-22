public class Node {
    String name;
    int severity;
    Node next;

    public Node(String name, int severity) {
        this.name = name;
        this.severity = severity;
        this.next = null;
    }

    @Override
    public String toString() {
        return name + "( severity " + severity + ")";
    }
}