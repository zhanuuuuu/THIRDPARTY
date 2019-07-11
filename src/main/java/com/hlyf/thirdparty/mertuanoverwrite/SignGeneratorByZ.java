package com.hlyf.thirdparty.mertuanoverwrite;

import com.sankuai.meituan.waimai.opensdk.constants.ErrorEnum;
import com.sankuai.meituan.waimai.opensdk.exception.ApiSysException;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SignGeneratorByZ {
    public SignGeneratorByZ() {
    }

    public static String genSig(String baseUrl) throws ApiSysException, UnsupportedEncodingException {
        String str = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            str = byte2hex(md.digest(baseUrl.getBytes("UTF-8")));
            return str;
        } catch (NoSuchAlgorithmException var3) {
            throw new ApiSysException(ErrorEnum.SYS_ERR);
        }
    }

    private static String byte2hex(byte[] b) {
        StringBuffer buf = new StringBuffer();

        for(int offset = 0; offset < b.length; ++offset) {
            int i = b[offset];
            if(i < 0) {
                i += 256;
            }

            if(i < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(i));
        }

        return buf.toString();
    }
}

