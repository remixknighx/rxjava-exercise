package com.bill.zk;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjf
 * @date 2019/10/4 0004.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkUtilTest {

    @Autowired
    private ZkUtil zkUtil;

    @Test
    public void testCreatePermanentNode() throws Exception {
        zkUtil.createPermanentNode("/new", "Carrie");
    }

    @Test
    public void testCreateTemporaryNode() throws Exception {
        zkUtil.createTemporaryNode("/new/temp", "new_temp");
        System.out.println(zkUtil.getNode("/new/temp"));
    }

    @Test
    public void testGetNode() throws Exception {
        System.out.println(zkUtil.getNode("/shendiao"));
    }

    @Test
    public void testUpdateNode() throws Exception {
        zkUtil.updateNode("/new", "Carrie Lim");
        System.out.println(zkUtil.getNode("/new"));
    }

    @Test
    public void testDeleteNode() throws Exception {
        zkUtil.deleteNode("/new");
    }

}