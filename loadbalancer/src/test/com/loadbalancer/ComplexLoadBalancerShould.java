package com.loadbalancer;

import com.loadbalancer.balanceStrategy.ComplexBalanceStrategy;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

public class ComplexLoadBalancerShould {

    @Test
    void dispatch_request_to_most_efficient_host() {
        // given
        ArrayList<Host> hostList = new ArrayList<>();

        Host host1 = createHostWithLoad(90);
        Host host2 = createHostWithLoad(80);

        hostList.add(host1);
        hostList.add(host2);
        LoadBalancer loadBalancer = new LoadBalancer(hostList, new ComplexBalanceStrategy());

        // when
        loadBalancer.handleRequest(new Request("Message"));

        // then
        assertThat(host2.getReceivedRequests()).hasSize(81);
    }


    @Test
    void dispatch_request_to_free_host() {
        // given
        ArrayList<Host> hostList = new ArrayList<>();

        Host busyHost1 = createHostWithLoad(90);
        Host busyHost2 = createHostWithLoad(71);
        Host freeHost = createHostWithLoad(70);

        hostList.add(busyHost1);
        hostList.add(busyHost2);
        hostList.add(freeHost);
        LoadBalancer loadBalancer = new LoadBalancer(hostList, new ComplexBalanceStrategy());

        // when
        loadBalancer.handleRequest(new Request("Message"));

        // then
        assertThat(freeHost.getReceivedRequests()).hasSize(71);
    }

    private Host createHostWithLoad(int load) {
        Host host = new Host();
        host.getReceivedRequests().addAll(Collections.nCopies(load, new Request(RandomStringUtils.randomAlphanumeric(5))));
        return host;
    }
}
