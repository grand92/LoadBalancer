package com.loadbalancer;

import com.loadbalancer.balanceStrategy.BasicBalanceStrategy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BasicLoadBalancerShould {

    @Test
    void dispatch_request_sequentially_between_hosts() {

        // given
        ArrayList<Host> hostList = new ArrayList<>();
        hostList.add(new Host());
        hostList.add(new Host());

        LoadBalancer loadBalancer = new LoadBalancer(hostList, new BasicBalanceStrategy());

        // when
        for (int i = 0; i <= 50; i++) {
            loadBalancer.handleRequest(new Request(String.valueOf(i)));
        }

        // then

        hostShouldContainOnlyEvenRequests(hostList.get(0));
        hostShouldContainOnlyOddRequests(hostList.get(1));
    }

    private void hostShouldContainOnlyEvenRequests(Host host) {
        assertTrue(host.getReceivedRequests()
                .stream()
                .mapToInt(request -> Integer.parseInt(request.getMessage()))
                .allMatch(requestIndex -> requestIndex % 2 == 0));
    }

    private void hostShouldContainOnlyOddRequests(Host host) {
        assertTrue(host.getReceivedRequests()
                .stream()
                .mapToInt(request -> Integer.parseInt(request.getMessage()))
                .allMatch(requestIndex -> requestIndex % 2 == 1));
    }
}