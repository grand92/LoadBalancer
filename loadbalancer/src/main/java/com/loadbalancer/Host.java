package com.loadbalancer;

import java.util.ArrayList;

public class Host {

    public static final int INITIAL_CAPACITY = 100;
    private final ArrayList<Request> receivedRequests = new ArrayList<>(INITIAL_CAPACITY);

    public Host() {
    }

    public float getLoad() {
        return  ((float)receivedRequests.size() / (float)INITIAL_CAPACITY) * 100;
    }

    public void handleRequest(Request request) {
        receivedRequests.add(request);
    }

    public ArrayList<Request> getReceivedRequests() {
        return receivedRequests;
    }
}


