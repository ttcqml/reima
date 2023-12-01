package com.micro.common.core.utils.sign;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

public class SignatureUtil {

    private static Long EXPIRE_TIME = 5*600*1000L;

    /**
     * 生成签名
     */
    public static String getSignature(Map<String,Object> params,String secretKey) {
        //params.put("timestamp", System.currentTimeMillis());
        //params.put("nonce",UUID.randomUUID()+"");
        //对map参数进行排序生成参数
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuilder temp = new StringBuilder();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = String.valueOf(value);
            }
            temp.append(valueString);
        }
        //根据参数生成签名
        System.out.println("temp:"+temp.toString());
        return DigestUtils.sha256Hex(temp.toString() + secretKey).toUpperCase();
    }

     /**
     * 校验签名有效性
     */
    public static boolean checkSignature(Map<String, Object> param, String secretKey) {
        //获取request中的json参数转成map
        /*Map<String, Object> param = new HashMap<>();
        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();
            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }
            ObjectMapper objectMapper = new ObjectMapper();
            param = objectMapper.readValue(responseStrBuilder.toString(), Map.class);
        } catch (IOException e) {
            return false;
        }*/
        String sign = (String) param.get("sign");
        Long start = Long.valueOf(param.get("timestamp")+"");
        long now = System.currentTimeMillis();
        //校验时间有效性
        if (start == null || now - start > EXPIRE_TIME || start - now > 0L) {
            return false;
        }
        //是否携带签名
        if (StringUtils.isBlank(sign)) {
            return false;
        }
        //获取除签名外的参数
        param.remove("sign");
        String signature = SignatureUtil.getSignature(param, secretKey);
        System.out.println(">>>sign:"+signature);
        //校验签名
        if (sign.equals(signature)) {
            return true;
        }
        return false;
    }

    /**
     * {"mobile":"19810660521","sign":"05C216C86EF530410BC46F7B2C75E7DDF68078E9FCBA4217A1629C50255AC4C3",
     * "ak":"opm6krm2xan3wfnk","nonce":"9e002070-854b-4137-966a-0d73de38608e","timestamp":1632444513467}]
     */
    public static void main(String[] args) {
//        OpenMobile openMobile = new OpenMobile();
//        openMobile.setMobile("19810660521");
//        openMobile.setAk("opm6krm2xan3wfnk");
//        openMobile.setNonce("9e002070-854b-4137-966a-0d73de38608e");
//        openMobile.setSign("05C216C86EF530410BC46F7B2C75E7DDF68078E9FCBA4217A1629C50255AC4C3");
//        openMobile.setTimestamp("1632444513467");
//        System.out.println(checkSignature(JSON.parseObject(JSON.toJSONString(openMobile), Map.class),"si91qzqvmf0303svmq04niycsh5ih4a5"));
    }
}
