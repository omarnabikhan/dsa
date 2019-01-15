package Tests;
import DataStructures.IntList;
import org.junit.Test;
import org.junit.Assert;

public class IntListTest {

    @Test
    public void sanityTest() {
        IntList list = new IntList();
        Assert.assertEquals(0, list.size);
        for (int i = 0; i != 10; i++) {
            list.add(i);
        }
        Assert.assertEquals(10, list.size);
        Assert.assertEquals("0, 1, 2, 3, 4, 5, 6, 7, 8, 9", list.toString());


        // multiple lists should be able to exist at the same time
        IntList list2 = new IntList();
        Assert.assertEquals(0, list2.size);
        Assert.assertEquals(10, list.size);
        Assert.assertEquals("", list2.toString());
    }

    @Test
    public void testIndexAdds() {
        IntList list = new IntList();

        for (int i = 0; i != 10; i++) {
            list.add(i);
        }

        list.add(13, 3);
        Assert.assertEquals("0, 1, 2, 13, 3, 4, 5, 6, 7, 8, 9", list.toString());
        Assert.assertEquals(11, list.size);

        list.add(-1, 0);
        Assert.assertEquals("-1, 0, 1, 2, 13, 3, 4, 5, 6, 7, 8, 9", list.toString());
        Assert.assertEquals(12, list.size);

        list.add(10, 12);
        Assert.assertEquals("-1, 0, 1, 2, 13, 3, 4, 5, 6, 7, 8, 9, 10", list.toString());
        Assert.assertEquals(13, list.size);


        try {
            list.add(0, 100);
            Assert.assertTrue(false); // should never get to this line
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            list.add(0, -1);
            Assert.assertTrue(false); // also shouldn't get here
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }

    @Test
    public void testGet() {
        IntList list = new IntList();
        for (int i = 0; i != 100; i ++) {
            list.add(i);
        }

        for (int i = 0; i != 100; i++) {
            Assert.assertEquals(i, list.get(i));
        }

        try {
            list.get(101);
            Assert.assertTrue(false); // should never get to this line
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            list.get(-1);
            Assert.assertTrue(false); // also shouldn't get here
        } catch (IndexOutOfBoundsException e) {
            return;
        }
    }


}
