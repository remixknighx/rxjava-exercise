package com.bill.sensestudio;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author wangjianfeng
 */
@Component
public class AuthGenerator {

    private final String API_KEY = "4a1b613417484e099755962d0eb9c4c6";
    private final String API_SECRET = "4c2d1579635d4f7583f7315df30f6f13";

    public String generateToken(){
        String timestamp = Long.toString(System.currentTimeMillis());
        String nonce = RandomStringUtils.randomAlphanumeric(16);
        String genOriString = genOriString(timestamp, nonce);
        String encryptedString = Hashing.hmacSha256(API_SECRET.getBytes()).hashString(genOriString, Charsets.UTF_8).toString();

        return String.format("key=%s,timestamp=%s,nonce=%s,signature=%s", API_KEY, timestamp, nonce, encryptedString);
    }

    private String genOriString(String timestamp, String nonce){

        ArrayList<String> beforeSort = new ArrayList<>();
        beforeSort.add(API_KEY);
        beforeSort.add(timestamp);
        beforeSort.add(nonce);

        Collections.sort(beforeSort, (o1, o2) -> {
            try{
                String s1 = new String(o1.toString().getBytes(Charsets.UTF_8.name()), Charsets.ISO_8859_1.name());
                String s2 = new String(o2.toString().getBytes(Charsets.UTF_8.name()), Charsets.ISO_8859_1.name());
                return s1.compareTo(s2);
            }catch (Exception e){
                e.printStackTrace();
            }
            return 0;
        });
        StringBuilder afterSort = new StringBuilder();
        beforeSort.forEach(afterSort::append);

        return afterSort.toString();
    }

}

