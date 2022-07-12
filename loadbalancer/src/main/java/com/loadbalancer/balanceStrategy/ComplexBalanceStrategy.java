package com.loadbalancer.balanceStrategy;

import com.loadbalancer.Host;
import com.loadbalancer.Request;

import java.util.List;
import java.util.stream.Collectors;

public class ComplexBalanceStrategy implements BalanceStrategy {

    @Override
    public void balance(Request request, List<Host> hosts) {
        List<Host> allHostsWithAcceptableLoad = findAllHostsWithAcceptableLoad(hosts);
        allHostsWithAcceptableLoad.stream()
                .min((host1, host2) -> Float.compare(host1.getLoad(), host2.getLoad()))
                .orElse(findHostWithLowestLoad(hosts)).handleRequest(request);
    }

    private Host findHostWithLowestLoad(List<Host> hosts) {
        Host hostWithMinimumLoad = hosts.get(0);
        for (Host host : hosts){
            if(host.getLoad() < hostWithMinimumLoad.getLoad()){
                hostWithMinimumLoad = host;
            }
        }
        return hostWithMinimumLoad;
    }

    private List<Host> findAllHostsWithAcceptableLoad(List<Host> hosts) {
        return hosts.stream().filter(host -> host.getLoad() < 75).collect(Collectors.toList());
    }

}
