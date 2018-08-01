package io.renren.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * 输出自己发票格式(key[官方键名称]:value[输出方字段名称])
 *
 * @author wuche
 */
public class InvoiceUtils {
    /**
     * 增值税通用发票数据（增值税普票，增值税专票，电子普通发票,卷式）
     */
    public static Map<String, Object> invoiceBaseVatMap = new LinkedHashMap<String, Object>();
    /**
     * 增值税通用发票数据（增值税普票，增值税专票，电子普通发票）
     */
    public static Map<String, Object> invoiceVatMap = new LinkedHashMap<String, Object>();
    /**
     * 机动车统一发票数据
     */
    public static Map<String, Object> vehicleVatMap = new LinkedHashMap<String, Object>();
    /**
     * 增值税卷式普通发票
     */
    public static Map<String, Object> rollVatMap = new LinkedHashMap<String, Object>();
    /**
     * 电子普通[通行费]发票
     */
    public static Map<String, Object> tollVatMap = new LinkedHashMap<String, Object>();
    /**
     * 二手车销售统一发票
     */
    public static Map<String, Object> usedCarVatMap = new LinkedHashMap<String, Object>();

    static {
        getInvoiceBaseVatMap(invoiceBaseVatMap);
        getInvoiceVatMap(invoiceVatMap);
        getVehicleVatMap(vehicleVatMap);
        getRollVatMap(rollVatMap);
        getTollVatMap(tollVatMap);
        getUsedCarVatMap(usedCarVatMap);

    }

    /**
     * 通用的数据
     *
     * @param invoiceVatMap
     */
    private static void getInvoiceBaseVatMap(Map<String, Object> invoiceVatMap) {
        invoiceVatMap.put("invoiceTypeName", "invoiceTypeName");//发票名称
        invoiceVatMap.put("invoiceTypeCode", "invoiceTypeCode");//发票类型
        invoiceVatMap.put("invoiceDataCode", "invoiceDataCode");//发票代码
        invoiceVatMap.put("checkCode", "checkCode");//校验码
        invoiceVatMap.put("invoiceNumber", "invoiceNumber");//发票号码
        invoiceVatMap.put("billingTime", "billingTime");//开票日期
        invoiceVatMap.put("purchaserName", "purchaserName");//购买方
        invoiceVatMap.put("taxpayerNumber", "taxpayerNumber");//购买方税号
        invoiceVatMap.put("taxpayerAddressOrId", "taxpayerAddressOrId");//购买方地址
        invoiceVatMap.put("taxpayerBankAccount", "taxpayerBankAccount");//购买方银行信息
        invoiceVatMap.put("salesName", "salesName");//销售方名称
        invoiceVatMap.put("salesTaxpayerNum", "salesTaxpayerNum");//销售方税号
        invoiceVatMap.put("salesTaxpayerAddress", "salesTaxpayerAddress");//销售方地址
        invoiceVatMap.put("salesTaxpayerBankAccount", "salesTaxpayerBankAccount");//销售方银行信息
        invoiceVatMap.put("totalAmount", "totalAmount");//金额
        invoiceVatMap.put("totalTaxNum", "totalTaxNum");//税额
        invoiceVatMap.put("totalTaxSum", "totalTaxSum");//价税合计
        invoiceVatMap.put("voidMark", "voidMark");//是否作废 0：正常，1：作废
        invoiceVatMap.put("invoiceRemarks", "invoiceRemarks");//备注
        invoiceVatMap.put("taxDiskCode", "taxDiskCode");//机器编号
        invoiceVatMap.put("isBillMark", "isBillMark");//是否为清单票  Y：是，N：否//可以根据该字段展示清单票和正常票
        invoiceVatMap.put("tollSign", "tollSign");//收费标志字段（06：可抵扣通行费 07：不可抵扣通行费，08：成品油）
        invoiceVatMap.put("tollSignName", "tollSignName");//收费标志名称

    }

    /**
     * 获取增值税通用发票数据（增值税普票，增值税专票，电子普通发票）
     *
     * @param invoiceVatMap
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void getInvoiceVatMap(Map<String, Object> invoiceVatMap) {
        invoiceVatMap.putAll(invoiceBaseVatMap);
        Map detailMap = new LinkedHashMap();
        detailMap.put("lineNum", "lineNum");//行号
        detailMap.put("goodserviceName", "goodserviceName");//货劳务名称
        detailMap.put("model", "model");//型号
        detailMap.put("unit", "unit");//单位
        detailMap.put("number", "number");//数量
        detailMap.put("price", "price");//单价
        detailMap.put("sum", "sum");//金额
        detailMap.put("taxRate", "taxRate");//税率
        detailMap.put("tax", "tax");//税额
        detailMap.put("isBillLine", "isBillLine");//是否为清单行
        detailMap.put("zeroTaxRateSign", "zeroTaxRateSign");//零税率标志字段（空:非零税率， 1:税率栏位显示“免税”， 2:税率栏位显示“不征收”， 3:零税率）
        detailMap.put("zeroTaxRateSignName", "zeroTaxRateSignName");//零税率标志名称
        invoiceVatMap.put("invoiceDetailData", detailMap);//发票类型
    }

    /**
     * 机动车统一发票数据
     *
     * @param vehicleVatMap
     */
    private static void getVehicleVatMap(Map<String, Object> vehicleVatMap) {
        vehicleVatMap.put("invoiceTypeName", "invoiceTypeName");//发票名称
        vehicleVatMap.put("invoiceTypeCode", "invoiceTypeCode");//发票类型
        vehicleVatMap.put("invoiceDataCode", "invoiceDataCode");//发票代码
        vehicleVatMap.put("invoiceNumber", "invoiceNumber");//发票号码
        vehicleVatMap.put("taxDiskCode", "taxDiskCode");//机器码
        vehicleVatMap.put("checkCode", "checkCode");//校验码
        vehicleVatMap.put("billingTime", "billingTime");//开票日期
        vehicleVatMap.put("purchaserName", "purchaserName");//购买方
        vehicleVatMap.put("taxpayerIdOrOrginCode", "taxpayerIdOrOrginCode");//购货单位（人）及身份证号码／组织机构代码
        vehicleVatMap.put("taxpayerNumber", "taxpayerNumber");//纳税人识别号
        vehicleVatMap.put("vehicleType", "vehicleType");//车辆类型
        vehicleVatMap.put("brandType", "brandType");//厂牌型号
        vehicleVatMap.put("producingArea", "producingArea");//产地
        vehicleVatMap.put("certifNumber", "certifNumber");//合格证号
        vehicleVatMap.put("inspectionOrder", "inspectionOrder");//商检单号
        vehicleVatMap.put("engineNumber", "engineNumber");//发动机号码
        vehicleVatMap.put("frameNumbr", "frameNumbr");//车辆识别代码／车架号码
        vehicleVatMap.put("importCertif", "importCertif");//进口证名书号
        vehicleVatMap.put("salesName", "salesName");//销货单位名称
        vehicleVatMap.put("salesTaxpayerTel", "salesTaxpayerTel");//电话
        vehicleVatMap.put("salesTaxpayerNum", "salesTaxpayerNum");//纳税人识别号
        vehicleVatMap.put("salesTaxpayerAccount", "salesTaxpayerAccount");//账号
        vehicleVatMap.put("salesTaxpayerAddress", "salesTaxpayerAddress");//地址
        vehicleVatMap.put("salesTaxpayerBank", "salesTaxpayerBank");//开户银行
        vehicleVatMap.put("totalAmount", "totalAmount");//不含税价
        vehicleVatMap.put("taxRate", "taxRate");//增值税税率或征收率
        vehicleVatMap.put("totalTaxNum", "totalTaxNum");//增值税税额
        vehicleVatMap.put("totalTaxSum", "totalTaxSum");//价税合计
        vehicleVatMap.put("taxReceiptCode", "taxReceiptCode");//完税凭证号码
        vehicleVatMap.put("tonnage", "tonnage");//吨位
        vehicleVatMap.put("limitNum", "limitNum");//限乘人数
        vehicleVatMap.put("voidMark", "voidMark");//是否作废
        vehicleVatMap.put("taxOfficeName", "taxOfficeName");//主管税务机关及代码
        vehicleVatMap.put("machineCode", "machineCode");//机打代码
        vehicleVatMap.put("machineNumber", "machineNumber");//机打号码
    }

    /**
     * 增值税卷式普通发票
     *
     * @param rollVatMap
     */
    private static void getRollVatMap(Map<String, Object> rollVatMap) {
        rollVatMap.putAll(invoiceVatMap);
        rollVatMap.put("goodsClerk", "goodsClerk");//开票员（与上面普通，电子，专用不同，其他字段都一样）
    }

    /**
     * 电子普通[通行费]发票
     *
     * @param tollVatMap
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    private static void getTollVatMap(Map<String, Object> tollVatMap) {
        tollVatMap.putAll(invoiceBaseVatMap);
        Map detailMap = new LinkedHashMap();
        detailMap.put("lineNum", "lineNum");//行号
        detailMap.put("goodserviceName", "goodserviceName");//货劳务名称
        detailMap.put("plateNo", "plateNo");//车牌号
        detailMap.put("passStartDate", "passStartDate");//通行日期起
        detailMap.put("passEndDate", "passEndDate");//通行日期止
        detailMap.put("sum", "sum");//金额
        detailMap.put("model", "model");//型号
        detailMap.put("taxRate", "taxRate");//税率
        detailMap.put("tax", "tax");//税额
        detailMap.put("isBillLine", "isBillLine");//是否为清单行 Y：是，N：否（是的时候，货物名称为“请详见货物清单”，“折扣额合计”，“原价合计”，可以不用做账）
        detailMap.put("zeroTaxRateSign", "zeroTaxRateSign");//行号
        detailMap.put("zeroTaxRateSignName", "zeroTaxRateSignName");//行号
        tollVatMap.put("invoiceDetailData", detailMap);//发票类型
    }

    /**
     * 二手车销售统一发票
     *
     * @param usedCarVatMap
     */
    private static void getUsedCarVatMap(Map<String, Object> usedCarVatMap) {
        usedCarVatMap.put("invoiceTypeName", "invoiceTypeName");//发票名称
        usedCarVatMap.put("invoiceTypeCode", "invoiceTypeCode");//发票类型
        usedCarVatMap.put("invoiceDataCode", "invoiceDataCode");//发票代码
        usedCarVatMap.put("invoiceNumber", "invoiceNumber");//发票号码
        usedCarVatMap.put("taxDiskCode", "taxDiskCode");//机器码
        usedCarVatMap.put("checkCode", "checkCode");//校验码
        usedCarVatMap.put("billingTime", "billingTime");//开票日期
        usedCarVatMap.put("purchaserName", "purchaserName");//购买方
        usedCarVatMap.put("taxpayerIdOrOrginCode", "taxpayerIdOrOrginCode");//买方单位代码/身份证号码
        usedCarVatMap.put("taxpayerAddress", "taxpayerAddress");//买方单位/个人住址
        usedCarVatMap.put("taxpayerTel", "taxpayerTel");//（买方）电话
        usedCarVatMap.put("plateNo", "plateNo");//车牌号
        usedCarVatMap.put("registNo", "registNo");//登记证号
        usedCarVatMap.put("vehicleType", "vehicleType");//车辆类型
        usedCarVatMap.put("totalTaxSum", "totalTaxSum");//车价合计
        usedCarVatMap.put("frameNumbr", "frameNumbr");//车辆识别代号/车架号码
        usedCarVatMap.put("brandType", "brandType");//厂牌型号
        usedCarVatMap.put("vehicleOfficeName", "vehicleOfficeName");//转入地车辆管理所名称
        usedCarVatMap.put("salesName", "salesName");//卖方单位/个人
        usedCarVatMap.put("salesTaxpayerNum", "salesTaxpayerNum");//卖方单位代码/身份证号码
        usedCarVatMap.put("salesTaxpayerAddress", "salesTaxpayerAddress");//卖方单位/个人住址
        usedCarVatMap.put("salesTaxpayerTel", "salesTaxpayerTel");//（卖方）电话
        usedCarVatMap.put("auctionUnitName", "auctionUnitName");//经营、拍卖单位
        usedCarVatMap.put("auctionUnitAddress", "auctionUnitAddress");//经营、拍卖单位地址
        usedCarVatMap.put("auctionUnitNo", "auctionUnitNo");//（经营、拍卖单位）纳税人识别号
        usedCarVatMap.put("auctionUnitBankAccount", "auctionUnitBankAccount");//（经营、拍卖单位）开户银行、账号
        usedCarVatMap.put("auctionUnitTel", "auctionUnitTel");//（经营、拍卖单位）电话
        usedCarVatMap.put("secondHandMarket", "secondHandMarket");//二手车市场
        usedCarVatMap.put("secondHandMarketNo", "secondHandMarketNo");//（二手车市场）纳税人识别号
        usedCarVatMap.put("marketAddress", "marketAddress");//（二手车市场）地址
        usedCarVatMap.put("marketBankAccount", "marketBankAccount");//（二手车市场）开户银行、账号
        usedCarVatMap.put("marketTel", "marketTel");//（二手车市场）电话
        usedCarVatMap.put("voidMark", "voidMark");//是否作废 1：是 0：否
        usedCarVatMap.put("invoiceRemarks", "invoiceRemarks");//备注
    }

    /**
     * 解析发票结果（这边可以对特殊字段输出自己想要格式）
     *
     * @param isNeedOcrResult
     * @param resMap
     * @return
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    public static Map getInoivceInfo(Map resMap, String invoiceTypeCode) {
        Map resultMap = new HashMap();
        Map vatMap = new HashMap();
        if ("01".equals(invoiceTypeCode) || "04".equals(invoiceTypeCode) || "10".equals(invoiceTypeCode)) {
            vatMap.putAll(invoiceVatMap);
        } else if ("03".equals(invoiceTypeCode)) {
            vatMap.putAll(vehicleVatMap);
        } else if ("11".equals(invoiceTypeCode)) {
            vatMap.putAll(rollVatMap);
        } else if ("14".equals(invoiceTypeCode)) {
            vatMap.putAll(tollVatMap);
        } else if ("15".equals(invoiceTypeCode)) {
            vatMap.putAll(usedCarVatMap);
        }
        // 需要票面信息
        Iterator<String> iterator = vatMap.keySet().iterator();
        List invoiceDetailDataList = new ArrayList();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = vatMap.get(key);
            if (value instanceof String) {
                String str = (String) resMap.get(key);
                //TODO
                resultMap.put(value, str);
            } else if (value instanceof Map) {
                Map detailParamMap = (Map) value;
                Object detail = resMap.get(key);
                if (detail instanceof List) {
                    List detailList = (List) detail;
                    for (int i = 0; i < detailList.size(); i++) {
                        Map invoiceDetailDataMap = new HashMap();
                        Map datailMap = (Map) detailList.get(i);
                        Iterator<String> iterator2 = detailParamMap.keySet().iterator();
                        while (iterator2.hasNext()) {
                            String key2 = iterator2.next();
                            Object value2 = detailParamMap.get(key2);
                            invoiceDetailDataMap.put(value2, datailMap.get(key2));
                        }
                        invoiceDetailDataList.add(invoiceDetailDataMap);
                    }
                    resultMap.put("invoiceDetailData", invoiceDetailDataList);
                }

            }

        }
        return resultMap;

    }

}
