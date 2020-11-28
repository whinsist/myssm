package cn.com.cloudstar.rightcloud.framework.test.t003util.bloom;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

import org.apache.commons.codec.Charsets;

/**
 * @author Hong.Wu
 * @date: 15:38 2020/11/04
 */
public class BloomFilterTest {
    public static void main(String[] args) {
        // 总数量
        int total = 1000000;
        BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), total);
        //BloomFilter<String> bf = BloomFilter.create(Funnels.stringFunnel(Charsets. UTF_8), total, 0.0003);
        // 初始化 10000 条数据到过滤器中
        for (int i = 0; i < total; i++) {
            bf.put("" + i);
        }
        // 判断值是否存在过滤器中
        int count = 0;
        for (int i = 0; i < total + 10000; i++) {
            if (bf.mightContain("" + i)) {
                count++;
            }
        }
        //  1010000×（0.03/100）=303
        System.out.println("匹配数量 " + count);
    }
}
