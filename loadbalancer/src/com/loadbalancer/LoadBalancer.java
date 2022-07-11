package com.loadbalancer;

import com.loadbalancer.balanceStrategy.BasicBalanceStrategy;

import java.util.ArrayList;

public class LoadBalancer {


    private ArrayList<String> hosts;
    private BasicBalanceStrategy strategy;

    public LoadBalancer(ArrayList<String> hosts, BasicBalanceStrategy strategy) {
        this.hosts = hosts;
        this.strategy = strategy;
    }
}
