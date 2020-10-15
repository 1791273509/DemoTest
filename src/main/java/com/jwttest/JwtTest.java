package com.jwttest;

import io.jsonwebtoken.*;
import org.junit.Test;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wenbaoxie
 * @Date 2020/9/16
 */

public class JwtTest {

    /**
     * 这样就生成了一个固定的密钥：javastack
     *
     * @return
     */
    @Test
    public void generalKey() {
        Key KEY = new SecretKeySpec("javastack".getBytes(),
                SignatureAlgorithm.HS512.getJcaName());
        System.out.println(KEY);

        Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("type", "1");
        String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"2018-01-01 0:00:00\"}";
        String compactJws = Jwts.builder().setHeader(stringObjectMap)
                .setPayload(payload).signWith(SignatureAlgorithm.HS512, KEY).compact();

        System.out.println("jwt key:" + new String(KEY.getEncoded()));
        System.out.println("jwt payload:" + payload);
        System.out.println("jwt encoded:" + compactJws);
    }
    @Test
    public void test(){
        Key KEY = new SecretKeySpec("wenboaxie".getBytes(),SignatureAlgorithm.HS512.getJcaName());
        System.out.println(new String(KEY.getEncoded()));

        Map<String,Object> header = new HashMap<>();
        header.put("type",1);
        header.put("alg",SignatureAlgorithm.HS512.getValue());
        String payload = "{\"user_id\":\"1341137\", \"expire_time\":\"20188888888888-01-01 0:00:00\"}";

        String compact = Jwts.builder().setHeader(header).setPayload(payload).signWith(SignatureAlgorithm.HS512, KEY).compact();
        System.out.println(compact);


        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(KEY).parseClaimsJws(compact);
        JwsHeader headers = claimsJws.getHeader();
        Claims body = claimsJws.getBody();

        System.out.println("jwt header:" + headers);
        System.out.println("jwt body:" + body);
        System.out.println("jwt body user-id:" + body.get("user_id", String.class));


    }
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        add(0,nums,res,new ArrayList<>());
        return res;
    }
    public void add(int index,int nums[],List<List<Integer>> res, List<Integer> tem){
        if(index == nums.length ){
            res.add(new ArrayList<>(tem));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            int num = nums[i];
            tem.add(num);
            add(index + 1,nums,res,tem);
            tem.remove(tem.size() - 1);
        }
    }

}