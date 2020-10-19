package com.test;

import com.google.protobuf.InvalidProtocolBufferException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.junit.Test;

/**
 * @Author wenbaoxie
 * @Date 2020/10/15
 */
public class TestProtoBuf {

    public static void main(String[] args) {
        System.out.println("===== 构建一个GPS模型开始 =====");

        GpsDataProto.gps_data.Builder gps_builder = GpsDataProto.gps_data.newBuilder();
        gps_builder.setAltitude(1);
        gps_builder.setDataTime("2017-12-17 16:21:44");
        gps_builder.setGpsStatus(1);
        gps_builder.setLat(39.123);
        gps_builder.setLon(120.112);
        gps_builder.setDirection(30.2F);
        gps_builder.setId(100L);

        GpsDataProto.gps_data gps_data = gps_builder.build();
        System.out.println(gps_data.toString());
        System.out.println("===== 构建GPS模型结束 =====");

        System.out.println("===== gps Byte 开始=====");
        for (byte b : gps_data.toByteArray()) {
            System.out.print(b);
        }
        System.out.println("\n" + "bytes长度" + gps_data.toByteString().size());
        System.out.println("===== gps Byte 结束 =====");

        System.out.println("===== 使用gps 反序列化生成对象开始 =====");
        GpsDataProto.gps_data gd = null;
        try {
            gd = GpsDataProto.gps_data.parseFrom(gps_data.toByteArray());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
        System.out.print(gd.toString());
        System.out.println("===== 使用gps 反序列化生成对象结束 =====");
    }

    @Test
    public void tet() {
        String regEx = "count(\\d+)(df)";
        String s = "count000dfdfsdffaaaa1";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(s);
        System.out.println(mat.find());
        System.out.println(mat.groupCount());
        for (int i = 0; i < mat.groupCount(); i++) {
            System.out.println(mat.group(i));
        }
        System.out.println(mat.group(2));
    }

    @Test
    public void test() {
    /*
        打印结果
        true
        1
        2312321321
         +dfd*/
        Pattern p = Pattern.compile("(.)(\\d+) (\\+dfd)");
        Matcher dfd = p.matcher("12312321321 +dfd");
        System.out.println(dfd.find());
        System.out.println(dfd.group(1));
        System.out.println(dfd.group(2));
        System.out.println(dfd.group(3));

    }

}
