/*
 * Copyright 2011 Midokura KK 
 */

package com.midokura.midolman.state;

import java.util.Set;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;

public interface Directory {

    String add(String relativePath, byte[] data, CreateMode mode)
            throws KeeperException, InterruptedException;

    void update(String relativePath, byte[] data)
            throws KeeperException, InterruptedException;

    byte[] get(String relativePath, Runnable watcher)
            throws KeeperException, InterruptedException;

    Set<String> getChildren(String relativePath, Runnable watcher)
            throws KeeperException, InterruptedException;

    boolean has(String relativePath)
            throws KeeperException, InterruptedException;

    void delete(String relativePath)
            throws KeeperException, InterruptedException;

    Directory getSubDirectory(String relativePath) throws KeeperException;
}
