
package com.i2i.intern.pixcell.hazelcastoperation;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.i2i.intern.pixcell.hazelcastoperation.HazelcastOperation.NotFoundException;


public class Mainhz {
    
     public static void main(String[] args) {
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance();
        HazelcastOperation service = new HazelcastOperation(hazelcastInstance);

        service.setPartitionKey(166L, 1);
        try {
            int partitionKey = service.getPartitionKeyOrThrow(166L);
            System.out.println("Partition Key: " + partitionKey);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
          System.out.println("baris baris baris baris");
       // hazelcastInstance.shutdown();
    }
}
