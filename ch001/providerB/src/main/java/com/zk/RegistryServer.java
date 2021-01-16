package com.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @author jayying
 * @since 2021/1/15
 */
public class RegistryServer implements Watcher {
    private static final RegistryServer instance = new RegistryServer();
    private ZooKeeper zooKeeper;

    private RegistryServer() {
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, this);

            String node = zooKeeper.create("/user", null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            System.out.println("初始化父节点成功 :" + node);
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public static RegistryServer getInstance() {
        return instance;
    }

    public void register(String serverName) {
        try {
            String node = zooKeeper.create("/user/service", serverName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            System.out.println("创建Node节点成功 :" + node);
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void process(WatchedEvent event) {

    }
}
