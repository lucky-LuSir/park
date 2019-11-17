package com.kfwy.park.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

/**
 * Created by luming on 2019/3/29.
 */
public class InputStreamToString {
    public static String showPng(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = -1;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        while ((len = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, len);
        }
        byte[] data = baos.toByteArray();
        baos.close();
        String encode = Base64.getEncoder().encodeToString(data);

        return encode;
    }
}
