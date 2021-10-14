package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class FoodToOrderListTest {
    private FoodToOrderList testList;
    private Food testFoodA;
    private Food testFoodB;
    private Food testFoodC;

    @BeforeEach
    public void setUp() {
        testList = new FoodToOrderList();
        testFoodA = new Food("Onion Rings", 6.36);
        testFoodB = new Food("Hash Browns", 3);
        testFoodC = new Food("Tuna Salad", 8.89);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, testList.size());
    }

    @Test
    public void testAddFood() {
        assertEquals(0, testList.size());

        testList.addFood(testFoodA);
        assertEquals(1, testList.size());
        assertTrue(testList.contains(testFoodA));

        testList.addFood(testFoodB);
        assertEquals(2, testList.size());
        assertTrue(testList.contains(testFoodA));
        assertTrue(testList.contains(testFoodB));

        testList.addFood(testFoodC);
        testList.addFood(testFoodC);
        testList.addFood(testFoodC);
        assertEquals(5, testList.size());
        assertTrue(testList.contains(testFoodA));
        assertTrue(testList.contains(testFoodB));
        assertTrue(testList.contains(testFoodC));

        assertEquals(5, testList.getFoodToOrderList().size());
    }

    @Test
    public void testRemoveFood() {
        assertFalse(testList.contains(testFoodA));
        assertFalse(testList.contains(testFoodB));
        assertFalse(testList.contains(testFoodC));

        testList.addFood(testFoodA);
        testList.addFood(testFoodB);
        assertEquals(2, testList.size());

        testList.removeFood(testFoodA);
        assertFalse(testList.contains(testFoodA));
        assertEquals(1, testList.size());

        testList.removeFood(testFoodC);
        assertEquals(1, testList.size());

        testList.removeFood(testFoodB);
        assertEquals(0, testList.size());
    }

    @Test
    public void testGetTotalPrice() {
        assertEquals(0, testList.getTotalPrice());

        testList.addFood(testFoodA);
        assertEquals(6.36, testList.getTotalPrice());

        testList.addFood(testFoodC);
        assertEquals(6.36 + 8.89, testList.getTotalPrice());
    }

    @Test
    public void testGetTotalFoodNum() {
        assertEquals(0, testList.getTotalFoodNum());

        testList.addFood(testFoodA);
        assertEquals(1, testList.getTotalFoodNum());

        testList.addFood(testFoodB);
        testList.addFood(testFoodC);
        assertEquals(3, testList.getTotalFoodNum());
    }

    @Test
    public void testSetDeliveredTime() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String time0 = "00:00";
        String time1 = "17:45";
        String invalidTime1 = "";
        String invalidTime2 = "NoNoNoNo";
        String invalidTime3 = "1235";

        testList.setDeliveredTime(time0);
        assertEquals(sdf.parse(time0), testList.getDeliveredTime());

        testList.setDeliveredTime(time1);
        assertEquals(sdf.parse(time1), testList.getDeliveredTime());

        testList.setDeliveredTime(invalidTime1);
        assertEquals(sdf.parse(time0), testList.getDeliveredTime());

        testList.setDeliveredTime(invalidTime2);
        assertEquals(sdf.parse(time0), testList.getDeliveredTime());

        testList.setDeliveredTime(invalidTime3);
        assertEquals(sdf.parse(time0), testList.getDeliveredTime());
    }

    @Test
    public void testIsValidTime() {
        String time = "21:36";
        assertTrue(testList.isValidTime(time));

        String invalidTime = "YesYesYes";
        assertFalse(testList.isValidTime(invalidTime));
    }
}
