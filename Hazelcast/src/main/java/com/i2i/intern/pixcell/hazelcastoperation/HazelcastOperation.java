package com.i2i.intern.pixcell.hazelcastoperation;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import java.util.Optional;

public class HazelcastOperation {

    private IMap<Long, Integer> msisdnMap;

    private static final ClientConfig config = Configuration.getConfig();
   // private static final HazelcastInstance hazelcast = HazelcastClient.newHazelcastClient(config);
    
   
    public HazelcastOperation(HazelcastInstance hazelcastInstance) {
        this.msisdnMap = hazelcastInstance.getMap("MsisdnMap");
    }

    public Optional<Integer> getPartitionKey(Long msisdn) {
        Integer partitionKey = msisdnMap.get(msisdn);
        return Optional.ofNullable(partitionKey);
    }

    public void setPartitionKey(Long msisdn, int partitionKey) {
        msisdnMap.put(msisdn, partitionKey);
    }

    public int getPartitionKeyOrThrow(Long msisdn) throws NotFoundException {
        Optional<Integer> partitionKeyOptional = getPartitionKey(msisdn);
        return partitionKeyOptional.orElseThrow(() -> new NotFoundException("Partition key not found for this MSISDN: " + msisdn));
    }

    public void removePartitionKey(Long msisdn) {
        Optional<Integer> partitionKeyOptional = getPartitionKey(msisdn);
        if (partitionKeyOptional.isPresent()) {
            msisdnMap.remove(msisdn);
        } else {
            throw new NotFoundException("Partition key not found for this MSISDN: " + msisdn);
        }
    }

    public static class NotFoundException extends RuntimeException {

        public NotFoundException(String message) {
            super(message);
        }
    }

   
}
