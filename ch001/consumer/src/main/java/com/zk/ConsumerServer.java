package com.zk;

import com.google.gson.Gson;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author jayying
 * @since 2021/1/16
 */
public class ConsumerServer implements Watcher {
    private static final ConsumerServer instance = new ConsumerServer();

    private final HashMap<String, Status> map = new HashMap<>();
    private ZooKeeper zooKeeper;

    private ConsumerServer() {
        System.out.println("init construct function");
        try {
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 5000, this);
            List<String> children = zooKeeper.getChildren("/user", false);
            //持续监听该节点
            zooKeeper.addWatch("/user", this, AddWatchMode.PERSISTENT);

            for(String s : children) {
                System.out.println(s);
                byte[] bytes = zooKeeper.getData("/user/" + s, this, new Stat());
                Gson gson = new Gson();
                Status status = gson.fromJson(new String(bytes), Status.class);
                map.put("/user/" + s, status);
            }
        } catch (IOException | InterruptedException | KeeperException e) {
            e.printStackTrace();
        }
    }

    public static ConsumerServer getInstance() {
        return instance;
    }

    public String getUrl() {
        Random random = new Random();
        int n = map.size();
        int t = random.nextInt(n);
        Set<String> set = map.keySet();
        for(String s : set) {
            if(t == n - 1) {
                Status status = map.get(s);
                return "http://" + status.getIP() + ":" + status.getPort() + status.getPath();
            }
            t++;
        }
        return "";
    }

    @Override
    public void process(WatchedEvent event) {
        //Watch是一次性触发器，如果得到了一个watch事件，而希望在以后发生变更时继续得到通知，应该再设置一个watch
        //通过设置addWatch方法，可持久化监听
        if(event.getType() == Event.EventType.NodeDeleted) {
            map.remove(event.getPath());
            System.out.println("发现子节点被删除");
        } else if(event.getType() == Event.EventType.NodeChildrenChanged) {
            System.out.println("子节点有变化");
            try {
                List<String> children = zooKeeper.getChildren("/user", false);

                for(String s : children) {
                    if(!map.containsKey("/user/" + s)) {
                        byte[] bytes = zooKeeper.getData("/user/" + s, this, new Stat());
                        Gson gson = new Gson();
                        Status status = gson.fromJson(new String(bytes), Status.class);
                        map.put("/user/" + s, status);
                    }
                }
            } catch (KeeperException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
