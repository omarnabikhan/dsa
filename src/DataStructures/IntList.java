package DataStructures;

public class IntList {

    private class IntNode {
        private int item;
        private IntNode next;

        public IntNode(int x, IntNode n) {
            item = x;
            next = n;
        }
    }

    private IntNode head = new IntNode(0, null);
    public int size = 0;


    public void add(int addMe) {
        IntNode addMeToo = new IntNode(addMe, null);
        IntNode endOfList = head;
        while (endOfList.next != null) {
            endOfList = endOfList.next;
        }
        endOfList.next = addMeToo;
        size += 1;
    }


    public void add(int addMe, int index) throws IndexOutOfBoundsException {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        IntNode addMeToo = new IntNode(addMe, null);
        IntNode addToMyEnd = head;
        for (int i = -1; i != index - 1; i += 1) {
            addToMyEnd = addToMyEnd.next;
        }

        IntNode rest = addToMyEnd.next;
        addToMyEnd.next = addMeToo;
        addMeToo.next = rest;
        size += 1;
    }

    public int get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        IntNode returnMe = head;
        for (int i = -1; i != index; i++) {
            returnMe = returnMe.next;
        }
        return returnMe.item;
    }

    @Override
    public String toString() {
        String result = "";
        String intermediate;
        for (IntNode n = head.next; n != null; n = n.next) {
            intermediate = Integer.toString(n.item);
            if (n.next != null) {
                intermediate += ", ";
            }
            result += intermediate;
        }
        return result;
    }

    public void print() {
        System.out.println(toString());
    }
}