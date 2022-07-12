package com.loadbalancer.balanceStrategy;

import com.loadbalancer.Host;
import com.loadbalancer.Request;

import java.util.List;

public interface BalanceStrategy {
    void balance(Request request, List<Host> hosts);
}
