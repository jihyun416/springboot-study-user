package com.jessy.user.context;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MasterSlaveRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DatabaseContextHolder.getEnvironment();
    }
}
