package com.cs5520.numad20su_congressmobile.content;


import com.cs5520.numad20su_congressmobile.models.Bill;
import com.cs5520.numad20su_congressmobile.network.NetworkFragment;

import java.util.List;

public class BillsViewContent extends AbstractViewContent<Bill> {


    private NetworkFragment networkFragment;

    public BillsViewContent(NetworkFragment networkFragment) {
        this.networkFragment = networkFragment;
    }

    public BillsViewContent() {
        this(new NetworkFragment());
    }

    @Override
    List<Bill> getItems() {
        // TODO Use this.networkFragment to make HTTP requests to the ProPublica API
        // TODO     https://developer.android.com/training/basics/network-ops/connecting#HeadlessFragment
        return DummyBillContent.BILLS;
    }
}
