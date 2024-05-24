
package doublelylinkedlist;


public class DoublelyLinkedList {
    Node head, tail;
//head tham chiếu đến node đầu tiên
//tail tham chiếu đến node cuối cùng
    DoublelyLinkedList() {
        head = tail = null;
    }
    void addFirst(int x) {
        Node p = new Node(x, null, head);
        if (head != null)
            head.prev = p;
        head = p;
        if (tail == null) 
            tail = p;
    }

    void addLast(int x) {
        Node p = new Node(x, tail, null);
        if (tail != null) 
            tail.next = p;
        tail = p;
        if (head == null)
            head = p;
    }

    void addAtPosition(int x, int position) {
        Node p = new Node(x, null, null);//node p với giá trị x
        if (head == null) {
            head = tail = p;
        } else {
            Node q = head;
            for (int i = 0; i < position - 1; i++) {
                q = q.next;
            }
            p.next = q.next;
            p.prev = q;
            if (q.next != null) 
                q.next.prev = p;
            q.next = p;
            if (p.next == null) 
                tail = p;
        }
    }
    void deleteFirst() {
        if (head == null) 
            return;
        head = head.next;
        if (head == null)
            tail = null;
        else 
            head.prev = null;
    }

    void deleteLast() {
        if (tail == null) 
            return;
        tail = tail.prev;
        if (tail == null)
            head = null;
        else 
            tail.next = null;
    }

    void deleteAtPosition(int pos) {
        if (head == null)
            return;
        Node p = head;
        for (int i = 0; i < pos; i++) {
            if (p.next == null)
                return;
            p = p.next;
        }
        if (p.prev != null)
            p.prev.next = p.next;
        if (p.next != null) 
            p.next.prev = p.prev;
        if (p == head) 
            head = head.next;
        if (p == tail) 
            tail = tail.prev;
    }

    Node search(int x) {
        Node p = head;
        while (p != null) {
            if (p.info == x) 
                return p;
            p = p.next;
        }
        return null;
    }

    void traverse() {
        Node p = head;
        while (p != null) {
            System.out.print(p.info + " ");//show thông tin
            p = p.next;
        }
        System.out.println();
    }

    void sort() {
        for (Node p = head; p != null; p = p.next) {
            for (Node q = p.next; q != null; q = q.next) {
                if (p.info > q.info) {
                    int temp = p.info;
                    p.info = q.info;
                    q.info = temp;
                }
            }
        }
    }

    void insertElement(int x) {
        addLast(x);
        sort();
    }

    void deleteElement(int x) {
        Node p = search(x);//tìm p bằng search
        if (p == null) return;
        if (p.prev != null)
            p.prev.next = p.next;
        if (p.next != null) 
            p.next.prev = p.prev;
        if (p == head)
            head = head.next;
        if (p == tail)
            tail = tail.prev;

    }
}
    
