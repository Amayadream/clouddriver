package com.amayadream.clouddriver.common.util;

import org.apache.tomcat.util.buf.HexUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author :  Amayadream
 * @date :  2017.08.24 22:51
 */
public class FileMd5Utils {

    private static MessageDigest md5 = null;

    static {
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String md5(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        int len;
        byte[] bytes = new byte[4096];
        while ((len = fis.read(bytes)) != -1) {
            md5.update(bytes, 0, len);
        }
        return HexUtils.toHexString(md5.digest());
    }

}
