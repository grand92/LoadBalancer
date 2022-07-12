package com.loadbalancer;

import com.loadbalancer.balanceStrategy.BalanceStrategy;
import com.loadbalancer.balanceStrategy.BasicBalanceStrategy;

import java.util.ArrayList;

public class LoadBalancer {

    private ArrayList<Host> hosts;
    private BalanceStrategy strategy;

    public LoadBalancer(ArrayList<Host> hosts, BalanceStrategy strategy) {
        this.hosts = hosts;
        this.strategy = strategy;
    }

    public void handleRequest(Request request) {
        strategy.balance(request, hosts);
    }
}
