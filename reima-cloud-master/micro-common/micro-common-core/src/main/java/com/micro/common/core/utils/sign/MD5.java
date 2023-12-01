package com.micro.common.core.utils.sign;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.micro.common.core.utils.IdUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.crypto.Digest;
import org.bouncycastle.crypto.digests.SHA1Digest;
import org.bouncycastle.util.encoders.Hex;

public class MD5 {

  public static void main(String[] args) {
//    String ss = MD5.encryp("10012843443");
////    System.err.println(ss);
//    // 80a75acb0e45ebac8f3efc496ee64559feb4f39b
////    System.out.println(sha1("d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN"));
    String requestId = IdUtils.simpleUUID();
    System.out.println(requestId);
    String timestamp = IdUtils.timestamp();
    System.out.println(timestamp);
//    String password = "d52RFqSrmjUn5KnJZCVztlbb0i0GT7zN";
    String requestBody = "{\"bu_content\":{\"create_date_to\":\"2022-01-17T15:42:05+08:00\",\"asc\":\"\",\"member_code\":\"18501751176\",\"order_by\":\"\",\"page_no\":1,\"create_date_from\":\"2021-03-19T15:42:05+08:00\",\"page_size\":20}}";
    String signString = requestId+"&"+timestamp+"&"+requestBody+"&";
//    // 8f97c49c812f403c9b17e3b37e46f84a&1642068202723&{"bu_content":{"create_date_from":1616139725000,"create_date_to":1641800525000,"member_code":"18262299398","page_no":1,"page_size":20}}&,token:eyJhbGciOiJIUzUxMiJ9.eyJ1c2VyX2lkIjotMSwidXNlcl9rZXkiOiJjZWZhNGVmYS0zMjhlLTQ3OTEtOTQ2Zi04YjU5MWQ0MGNlOTEiLCJ1c2VybmFtZSI6Ilh0V1Z5ZkFvNVhETmxyUEQyVklhUUlsa3Q3MDRaZ3VnODBhNzVhY2IwZTQ1ZWJhYzhmM2VmYzQ5NmVlNjQ1NTlmZWI0ZjM5YiJ9.yEBVtX-TCA_LE3WG1cFT32b4NlL7VrFvIe6yWFEH3edtZbfYyrBmOr5Ecy588Cby3Q4IaPzWRhUonbSW0aHxdA
//    System.out.println("signString:"+signString);
//    System.out.println(MD5.encryp(signString));

    String str = "8a43ac8c-5a20-4cd7-9040-1b5f14abb0ff&1642125135091&{\"bu_content\":{\"mobile\":\"18262299398\"}}&";
    System.out.println(DigestUtils.md5Hex(signString));
//    System.out.println(DigestUtils.sha1Hex(str));

  }

}
