package com.loadbalancer;

import com.loadbalancer.balanceStrategy.BalanceStrategy;

import java.util.ArrayList;

public class LoadBalancer {

    private final ArrayList<Host> hosts;
    private final BalanceStrategy strategy;

    public LoadBalancer(ArrayList<Host> hosts, BalanceStrategy strategy) {
        this.hosts = hosts;
        this.strategy = strategy;
    }

    public void handleRequest(Request request) {
        strategy.balance(request, hosts);
    }
}
