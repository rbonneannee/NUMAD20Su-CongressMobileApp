package com.cs5520.numad20su_congressmobile.content;

import com.cs5520.numad20su_congressmobile.models.Bill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class DummyBillContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<Bill> BILLS = new ArrayList<Bill>();

    /**
     * A map of sample items, by ID.
     */
    public static final Map<String, Bill> ITEM_MAP = new HashMap<String, Bill>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createDummyItem(i));
        }
    }

    private static void addItem(Bill bill) {
        BILLS.add(bill);
        ITEM_MAP.put(bill.bill_id, bill);
    }

    private static Bill createDummyItem(int position) {
        return new Bill(String.valueOf(position), "EXAMPLE", "SUMMARY");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}