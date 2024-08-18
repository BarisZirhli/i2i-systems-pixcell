package com.i2i.intern.pixcell.hazelcastoperation;

import com.hazelcast.client.config.ClientConfig;

public class Configuration {

    public static ClientConfig getConfig() {

        ClientConfig config = new ClientConfig();
        config.getNetworkConfig().addAddress(StringConstants.hazelcastUrl);
        return config;

    }
}
