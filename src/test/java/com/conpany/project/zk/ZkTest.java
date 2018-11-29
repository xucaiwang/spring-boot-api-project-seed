package com.conpany.project.zk;

import com.conpany.project.Tester;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.IZkStateListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.Watcher;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * ZkClient解决了watcher的一次性注册问题，将znode的事件重新定义为子节点的变化、数据的变化、连接状态的变化三类
 *
 * 1.持久节点
 * 2.临时节点
 * 3.持久顺序节点
 * 4.临时顺序节点
 *
 *
 */
@Slf4j
public class ZkTest extends Tester {

    private ZkClient zkClient;

    @Before
    public void init(){
        zkClient = new ZkClient("127.0.0.1:2181");
        zkClient.subscribeChildChanges("/root", new IZkChildListener() {

            @Override
            public void handleChildChange(String arg0, List<String> arg1) throws Exception {
                System.out.println("子节点的变化");
            }
        });
        zkClient.subscribeDataChanges("/root/child", new IZkDataListener() {

            @Override
            public void handleDataDeleted(String arg0) throws Exception {
                System.out.println("数据被删除");
            }

            @Override
            public void handleDataChange(String arg0, Object arg1) throws Exception {
                System.out.println("数据被更改");
            }
        });
        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState keeperState) throws Exception {
                System.out.println("数据状态改变");
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println("当session expire异常重新连接时，由于原来的所有的watcher和EPHEMERAL节点都已失效，可以在这里进行相应的容错处理");
            }

            @Override
            public void handleSessionEstablishmentError(Throwable throwable) throws Exception {

            }
        });
    }

    @Test
    public void testCreate(){
        zkClient.create("/root", "root", CreateMode.PERSISTENT);
        //创建子节点
        zkClient.create("/root/child", "child", CreateMode.PERSISTENT);
    }

    @Test
    public void testReadChild(){
        List<String> children = zkClient.getChildren("/root");
        for(String item : children){
            log.info("/root所有子节点:"+item);
        }
    }

    @Test
    public void testWriteData(){
        zkClient.writeData("/root/child","哈哈");
    }

    @Test
    public void testReadData(){
        Object object = zkClient.readData("/root/child");
        log.info("/root/child节点数据:"+object);
    }

    @Test
    public void testDel(){
        zkClient.delete("/root/child");
    }

}
