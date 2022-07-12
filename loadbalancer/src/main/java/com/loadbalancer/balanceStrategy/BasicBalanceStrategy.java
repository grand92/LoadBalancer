package com.loadbalancer.balanceStrategy;

import com.loadbalancer.Host;
import com.loadbalancer.Request;

import java.util.List;

public class BasicBalanceStrategy implements BalanceStrategy {

    private int currentHostIndex = 0;

    @Override
    public void balance(Request request, List<Host> hosts) {
        hosts.get(currentHostIndex).handleRequest(request);
        currentHostIndex = (currentHostIndex + 1) % hosts.size();
    }
}
