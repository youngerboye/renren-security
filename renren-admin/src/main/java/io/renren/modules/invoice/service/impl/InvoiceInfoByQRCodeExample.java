package io.renren.modules.invoice.service.impl;

import io.renren.common.utils.HttpRequestUtils;
import io.renren.common.utils.JacksonUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据发票二维码字符串进行查验
 *
 * @author wuche
 * 支持场景：扫描枪，微信公众号，小程序，支付宝第三方平台，自己app中扫描功能
 * 好处：无需填写参数，只需识别工具识别就行了
 * 缺点：需要识别工具
 */
public class InvoiceInfoByQRCodeExample {
    private static String openUrl = "https://open.leshui365.com";

    public static void main(String[] args) throws JsonParseException,
            JsonMappingException, IOException {
        getVatInfoByParam();
    }

    public static void getVatInfoByParam() throws JsonGenerationException, JsonMappingException, IOException {
        String token = getToken("ce15074eb4dd425aa935719fc74f4dd1", "127d2900-fdaf-4565-b964-a349d449f484");
        String url = openUrl + "/api/invoiceInfoByQRCode";
        if (!"".equals(token)) {
            //TODO 不同请求
            Map<String, Object> parameter = getInvoiceInfoByQRCode();
            parameter.put("token", token);
            String requestJson = JacksonUtils.getJsonFromMap(parameter);
            String result = HttpRequestUtils.sendPost(url, requestJson);
            if (StringUtils.isNotEmpty(result)) {
                Map resultMap = JacksonUtils.getMapFromJson(result);
                if (null != resultMap) {
                    //需要重新获取新的token
                    if (resultMap.containsKey("error")) {
                        String error = (String) resultMap.get("error");
                        if ("token error".equals(error)) {
                            //TODO 这个错误是token失效
                        }
                    } else {
                        //返回发票code
                        String resultCode = (String) resultMap.get("resultCode");
                        //成功返回发票信息
                        if ("1000".equals(resultCode)) {
                            String invoiceResult = (String) resultMap.get("invoiceResult");
                            System.out.println(invoiceResult);
                            //处理返回成功发票
                            getInviceInfo(invoiceResult);
                        } else if ("2001".equals(resultCode)) {
                            //错误码
                            String invoicefalseCode = (String) resultMap.get("invoicefalseCode");
                            /***
                             * 第一种：可以查询，需要检查数据是否正确（扣费的）
                             *        201：发票不存在(第二天查询发票还未上传或者发票代码，发票号码输入有误)
                             *        220：发票不一致 （请检查发票日期或者校验码[发票金额] 输入有误  ，注意金额是不含税金额）
                             *
                             * 第二种：可以查询，需要检查数据是否正确（不扣费）
                             *        210 ：代码或号码格式有误（金额有误，校验码有误。。）
                             *        211：您输入的发票信息不完全
                             *        230：您输入的发票日期格式不正确，请重新输入（格式2016-01-01）
                             *
                             * 第三种：可以在次查询（不扣费）
                             *       213：查询失败，服务忙      （税局服务不稳定导致的）
                             *       218：税局查验服务暂时不可用，请稍后再试     （税局服务宕机）
                             *       221：您输入的发票正在查询中，请不要重复提交请求
                             *
                             * 第四种：不能查询 ，需要第二天查的（不扣费）
                             *       202：    查验失败：失败原因，超过该张发票的单日查验次数（5次），请于24小时之后再进行查验
                             *       216：您查询的发票是当日开具的，请于次日查询
                             *
                             * 第五种：不能查询  （不扣费）
                             *       217：过了查票期 （只支持一年发票查询）
                             *       219：您输入的发票暂时不支持查询
                             *
                             * 第六种：不能查询 ，需要充值（不扣费）
                             *      240，余额不足，请充值
                             *
                             * 第七种：不能查询 需要联系我们（不扣费）
                             *       241，发票查询失败，请联系管理员（我们服务器处理数据异常导致的）
                             *
                             */
                            //TODO 需要您处理不同情况

                        }
                    }
                }
            }
        } else {
            System.out.println("token为空");
        }
    }

    public static void getInviceInfo(String invoiceResult) throws JsonParseException, JsonMappingException, IOException {
        //这边是发票数据解析
        Object invoiceResultData = JacksonUtils.getMapFromJson(invoiceResult);
        if (invoiceResultData instanceof Map) {
            Map invoiceResultMap = (Map) invoiceResultData;
            //发票类型
            String invoiceTypeCode = (String) invoiceResultMap.get("invoiceTypeCode");
            //TODO (不同发票处理不同数据模型)
            Object invoiceDetailData = invoiceResultMap.get("invoiceDetailData");
            if (invoiceDetailData instanceof List) {
                List invoiceDetailDataList = (List) invoiceDetailData;
                for (int i = 0; i < invoiceDetailDataList.size(); i++) {
                    Object invoiceDetail = invoiceDetailDataList.get(i);
                    if (invoiceDetail instanceof Map) {
                        //这边需要注意（如果是清单票或折扣清单票行号是做特殊处理的，是否存储可以质询业务的，正常票行号都是从0开始的）
                        Map invoiceDetailMap = (Map) invoiceDetail;
                        //TODO(有两种格式，请查考文档进行处理)
                    }

                }
            }
        }
    }

    /**
     * 获取token
     *
     * @param appKey
     * @param appSecret
     * @return
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    public static String getToken(String appKey, String appSecret)
            throws JsonParseException, JsonMappingException, IOException {
        String token = "";
        String tokenRest = HttpRequestUtils.sendGet(openUrl + "/getToken?appKey=" + appKey
                + "&appSecret=" + appSecret);
        Map<String, Object> tokenMap = JacksonUtils.getMapFromJson(tokenRest);
        if (tokenMap != null && tokenMap.get("token") != null) {
            token = tokenMap.get("token").toString();
        } else {
            //TODO 这边没拿到token原因，1）检查账号是否开通  2）是否修改appSecret
            System.out.println(tokenRest);
        }
        return token;
    }


    /**
     * 获取发票查验企业接口请求参数报文
     *
     * @return
     */
    public static Map<String, Object> getInvoiceInfoByQRCode() {
        Map<String, Object> rtnMap = new HashMap<String, Object>();
        rtnMap.put("scanStr", "01,04,1100174320,40184419,329.25,20180512,82190984023325258452,8E15,");
        return rtnMap;
    }
}