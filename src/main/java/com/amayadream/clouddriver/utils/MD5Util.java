package com.amayadream.clouddriver.utils;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Test;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author :  Amayadream
 * @date :  2016.09.30 16:41
 */
public class MD5Util {

    private static MessageDigest MD5 = null;

    static {
        try {
            MD5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }

    public static String MD5(File file) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            return MD5(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (fileInputStream != null)
                    fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String MD5(InputStream is) {
        try {
            byte[] buffer = new byte[8192];
            int length;
            while ((length = is.read(buffer)) != -1) {
                MD5.update(buffer, 0, length);
            }
            return new String(Hex.encodeHex(MD5.digest()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String MD5(String target) {
        return DigestUtils.md5Hex(target);
    }

    @Test
    public void test(){
        long begin = System.currentTimeMillis();
        File file = new File("E:\\Apache_OpenOffice_4.1.2_Win_x86_install_zh-CN.exe");
        String md5 = MD5(file);
        long end = System.currentTimeMillis();
        System.out.println("MD5:"+md5+"\n time:"+((end-begin)/1000)+"s");
    }

}
