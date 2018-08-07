package io.renren.modules.invoice.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.*;
import io.renren.modules.invoice.dao.InvoCheckDetlDao;
import io.renren.modules.invoice.entity.InvoCheckDetlEntity;
import io.renren.modules.invoice.entity.InvoCheckEntity;
import io.renren.modules.invoice.entity.InvoInfoEntity;
import io.renren.modules.invoice.service.InvoCheckDetlService;
import io.renren.modules.invoice.service.InvoCheckService;
import io.renren.modules.invoice.service.InvoInfoService;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysConfigService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("invoCheckDetlService")
@Transactional
public class InvoCheckDetlServiceImpl extends ServiceImpl<InvoCheckDetlDao, InvoCheckDetlEntity> implements InvoCheckDetlService {

    private static final Logger logger = LoggerFactory.getLogger(InvoCheckDetlServiceImpl.class);

    @Autowired
    private InvoInfoService invoInfoService;
    @Autowired
    private InvoCheckService invoCheckService;
    @Autowired
    private InvoCheckDetlService invoCheckDetlService;
    @Autowired
    private SysConfigService sysConfigService;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String name = (String) params.get("name");

        Page<InvoCheckDetlEntity> page = this.selectPage(
                new Query<InvoCheckDetlEntity>(params).getPage(),
                new EntityWrapper<InvoCheckDetlEntity>()
                        .like(StringUtils.isNotBlank(name), "invoice_number", name)
                        .or().like(StringUtils.isNotBlank(name), "crt_usr", name)
                        .or().like(StringUtils.isNotBlank(name), "crt_dt", name)
        );

        return new PageUtils(page);
    }

    /**
     * 校验发票真伪并落库
     *
     * @param scanStr
     * @return
     */
    public R validateInvoice(String scanStr, SysUserEntity user) {
        R result = R.ok();
        try {
            //1.本地解析扫描出的字符串
            result = this.analyScanStr(scanStr);
            if ((int) result.get("code") == 500) {//字符串错误，直接返回
                return result;
            }

            //2.查询本地数据库是否存在该发票且通过核验
            result = this.invoiceIsExist(scanStr);
            if ((int) result.get("code") == 500) {//之前已存信息或发生异常
                return result;
            }

            //3.本地校验成功后,调取接口进行发票核验
            result = this.getVatInfoByParam(scanStr, user);

        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return R.error("校验发票时异常,请联系管理员");
        }
        return result;
    }

    /**
     * 导出Excel表格
     *
     * @param params
     * @return
     */
    public R exportExcel(HttpServletResponse response, Map<String, Object> params) {
        String name = (String) params.get("name");
        Page<InvoCheckDetlEntity> page = this.selectPage(
                new Query<InvoCheckDetlEntity>(params).getPage(),
                new EntityWrapper<InvoCheckDetlEntity>()
                        .like(StringUtils.isNotBlank(name), "invoice_number", name)
                        .or().like(StringUtils.isNotBlank(name), "crt_usr", name)
                        .or().like(StringUtils.isNotBlank(name), "crt_dt", name)
        );

        try {
            List<InvoCheckDetlEntity> list = page.getRecords();
            String fileName = "反馈明细" + System.currentTimeMillis() + ".xls"; //文件名
            String sheetName = "发票明细";//sheet名
            String[] title = new String[]{"发票代码", "发票号码", "开票日期", "录入日期", "录入人"};//标题

            String[][] values = new String[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                values[i] = new String[title.length];
                //将对象内容转换成string
                InvoCheckDetlEntity obj = list.get(i);
                values[i][0] = obj.getInvoiceDataCode();
                values[i][1] = obj.getInvoiceNumber();
                values[i][2] = obj.getBillingTime();
                values[i][3] = obj.getCrtDt();
                values[i][4] = obj.getCrtUsr();
            }

            HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, values, null);
            //将文件存到指定位置

            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (Exception ex) {

        }
        return R.ok();
    }

    /**
     * 设置response头
     */
    public void setResponseHeader(HttpServletResponse response, String fileName) throws UnsupportedEncodingException, Exception {
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * 本地解析扫描出的字符串
     *
     * @param scanStr
     * @return
     */
    public R analyScanStr(String scanStr) {
        logger.info("扫描的字符串为:{}", scanStr);
        scanStr = scanStr.replace("，", ",");
        try {
            if (StringUtils.isBlank(scanStr)) {
                return R.error("扫描出的字符串为空,请检查!");
            }

            String[] strs = scanStr.split(",");
            if (strs == null || strs.length < 7) {
                return R.error("扫描出的字符串格式错误,请检查!");
            }

            if (!(strs[2].length() == 10 || strs[2].length() == 12)) {
                return R.error("发票代码错误,请检查!");
            }

            if (strs[3].length() != 8) {
                return R.error("发票号码错误,请检查!");
            }

            if (StringUtils.isBlank(strs[4])) {
                return R.error("开具金额错误,请检查!");
            }

            if (strs[5].length() != 8) {
                return R.error("开票时间错误,请检查!");
            }

            if (strs[6].length() < 7) {
                return R.error("校验码错误,请检查!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error("未知异常,异常信息为:" + e.getStackTrace().toString());
        }
        return R.ok();
    }

    public R invoiceIsExist(String scanStr) {
        Map<String, Object> params = new HashMap<>();
        try {
            scanStr = scanStr.replace("，", ",");
            String[] strs = scanStr.split(",");
            params.put("invoiceCode", strs[2]);
            params.put("invoiceNumber", strs[3]);
            PageUtils pageUtils = invoInfoService.queryPage(params);
            if (pageUtils != null && pageUtils.getTotalCount() == 1) {// 之前已落库
                List list = pageUtils.getList();
                InvoInfoEntity invoInfo = (InvoInfoEntity) list.get(0);
                if (Constant.NO.equals(invoInfo.getIsChecked())) {//数据库中发票未通过校验
                    return R.ok();
                } else {
                    return R.error("该发票已核验过,请通过查询功能查询!");
                }
            } else {//之前未落库，是新增发票
                return R.ok();
            }
        } catch (Exception e) {
            logger.error("出现未知错误,错误信息为:" + e.getMessage());
            return R.error("出现未知错误,错误信息为:" + e.getMessage());
        }
    }

    /**
     * 调取乐税网发票核验接口
     *
     * @param scanStr Demo:"01,04,1100174320,40184419,329.25,20180512,82190984023325258452,8E15,"
     *                my01:"01,10,033001800111,25672225,76.72,20180610,82076771121721427121,CB32,"
     *                my02:"01,10,031001700111,91076602,187.18,20170710,61052145051968576568,3977"
     *                01,10,033001800111,25672225,76.72,20180610,82076771121721427121,CB32,
     *                01,10,033001800111,25672225,76.72,20180610,82076771121721427121,CB, //天猫供应
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    public R getVatInfoByParam(String scanStr, SysUserEntity user) throws JsonGenerationException, JsonMappingException, IOException {
        scanStr = scanStr.replace("，", ",");
        Map<String, Object> parameter = new HashMap();
        //获取发票查验企业接口请求参数报文
        parameter.put("scanStr", scanStr);
        //查询配置文件
        String appKey = sysConfigService.getValue(ConfigConstant.APP_KEY);
        String appSecret = sysConfigService.getValue(ConfigConstant.APP_SECRET);
        String token = getToken(appKey, appSecret);
        String url = ConfigConstant.OPEN_URL + "/api/invoiceInfoByQRCode";
        if (!"".equals(token)) {
            parameter.put("token", token);
            String requestJson = JacksonUtils.getJsonFromMap(parameter);
            logger.info("调取发票核验接口请求报文为:{}", requestJson);
            String result = HttpRequestUtils.sendPost(url, requestJson);
            logger.info("调取发票核验接口返回报文为:{}", result);
            if (StringUtils.isNotEmpty(result)) {
                Map resultMap = JacksonUtils.getMapFromJson(result);
                if (null != resultMap) {
                    //需要重新获取新的token
                    if (resultMap.containsKey("error")) {
                        String error = (String) resultMap.get("error");
                        if ("token error".equals(error)) {
                            //这个错误是token失效
                            logger.error("此token:{},已失效!", token);
                            return R.error("此token,已失效!" + token);
                        }
                    } else {
                        //返回发票code
                        String resultCode = (String) resultMap.get("resultCode");
                        //成功返回发票信息
                        if ("1000".equals(resultCode)) {
                            String invoiceResult = (String) resultMap.get("invoiceResult");
                            logger.info("成功返回发票信息,信息为:{}", invoiceResult);
                            //处理返回成功发票
                            this.getAndSaveInviceInfo(scanStr, token, resultMap, invoiceResult, user);
                            return R.ok("发票校验通过,详细信息可进行搜索查看!").put("invoiceResult", invoiceResult);
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
                            if ("201".equals(invoicefalseCode)) {
                                return R.error("发票不存在(第二天查询发票还未上传或者发票代码，发票号码输入有误)");
                            } else if ("220".equals(invoicefalseCode)) {
                                return R.error("发票不一致 （请检查发票日期或者校验码[发票金额] 输入有误  ，注意金额是不含税金额）");
                            } else if ("210".equals(invoicefalseCode)) {
                                return R.error("代码或号码格式有误（金额有误，校验码有误。。）");
                            } else if ("211".equals(invoicefalseCode)) {
                                return R.error("您输入的发票信息不完全");
                            } else if ("230".equals(invoicefalseCode)) {
                                return R.error("您输入的发票日期格式不正确，请重新输入（格式2016-01-01）");
                            } else if ("213".equals(invoicefalseCode)) {
                                return R.error("查询失败，服务忙 ");
                            } else if ("218".equals(invoicefalseCode)) {
                                return R.error("税局查验服务暂时不可用，请稍后再试 ");
                            } else if ("221".equals(invoicefalseCode)) {
                                return R.error("您输入的发票正在查询中，请不要重复提交请求");
                            } else if ("202".equals(invoicefalseCode)) {
                                return R.error("查验失败：失败原因，超过该张发票的单日查验次数（5次），请于24小时之后再进行查验");
                            } else if ("216".equals(invoicefalseCode)) {
                                return R.error("您查询的发票是当日开具的，请于次日查询");
                            } else if ("217".equals(invoicefalseCode)) {
                                return R.error("过了查票期 （只支持一年发票查询）");
                            } else if ("219".equals(invoicefalseCode)) {
                                return R.error("您输入的发票暂时不支持查询");
                            } else if ("240".equals(invoicefalseCode)) {
                                return R.error("余额不足，请充值");
                            } else if ("241".equals(invoicefalseCode)) {
                                return R.error("发票查询失败，请联系管理员");
                            }
                        }
                    }
                } else {
                    return R.error("调取发票核验接口返回为空");
                }
            } else {
                return R.error("调取发票核验接口返回为空");
            }
        } else {
            logger.error("token为空,请检查");
            return R.error("token为空,请检查");
        }
        return R.error();
    }

    /**
     * 处理返回成功发票
     *
     * @param invoiceResult
     * @throws JsonParseException
     * @throws JsonMappingException
     * @throws IOException
     */
    public void getAndSaveInviceInfo(String scanStr, String token, Map resultMap, String invoiceResult, SysUserEntity user) throws JsonParseException, JsonMappingException, IOException {
        //落表三个对象
        InvoInfoEntity invoInfoEntity = new InvoInfoEntity();
        InvoCheckEntity invoCheckEntity = new InvoCheckEntity();
        InvoCheckDetlEntity invoCheckDetlEntity = new InvoCheckDetlEntity();
        //一个用于生成seq，一个作为创建时间
        String now = DateUtils.format(new Date(), "yyyyMMddHHmmss");
        String time = DateUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");

        //返回报文头
        int random = (int) (Math.random() * 100);
        String invoiceSeq = now + random;
        //RtnCode 00：成功，99：失败
        String rtnCode = (String) resultMap.get("RtnCode");
        //发票状态码 1000：查询到票的信息，2001：没有查询到票的信息
        String resultCode = (String) resultMap.get("resultCode");
        //提示信息，resultCode为1000返回：查验结果成功，resultCode为2001返回对应invoicefalseCode的错误信息
        String resultMsg = (String) resultMap.get("resultMsg");
        //发票名称
        String invoiceName = (String) resultMap.get("invoiceName");
        //查询是否免费，Y：是，N：否
        String isFree = (String) resultMap.get("isFree");

        //这边是发票数据解析（返回报文体）
        Object invoiceResultData = JacksonUtils.getMapFromJson(invoiceResult);
        if (invoiceResultData instanceof Map) {
            Map invoiceResultMap = (Map) invoiceResultData;
            //发票类型
            String invoiceTypeCode = (String) invoiceResultMap.get("invoiceTypeCode");
            //发票代码
            String invoiceDataCode = (String) invoiceResultMap.get("invoiceDataCode");
            //发票号码
            String invoiceNumber = (String) invoiceResultMap.get("invoiceNumber");
            //发票类型名称
            String invoiceTypeName = (String) invoiceResultMap.get("invoiceTypeName");
            //开票时间
            String billingTime = (String) invoiceResultMap.get("billingTime");
            //查询日期
            String checkDate = (String) invoiceResultMap.get("checkDate");
            //查询次数
            String checkNum = (String) invoiceResultMap.get("checkNum");
            //校验码
            String checkCode = (String) invoiceResultMap.get("checkCode");
            //机器码
            String taxDiskCode = (String) invoiceResultMap.get("taxDiskCode");
            //购方名称
            String purchaserName = (String) invoiceResultMap.get("purchaserName");
            //购方纳税人识别号
            String taxpayerNumber = (String) invoiceResultMap.get("taxpayerNumber");
            //购方银行账号
            String taxpayerBankAccount = (String) invoiceResultMap.get("taxpayerBankAccount");
            //购方地址，电话
            String taxpayerAddressOrId = (String) invoiceResultMap.get("taxpayerAddressOrId");
            //销方名称
            String salesName = (String) invoiceResultMap.get("salesName");
            //销方纳税人识别号
            String salesTaxpayerNum = (String) invoiceResultMap.get("salesTaxpayerNum");
            //销方银行，账号
            String salesTaxpayerBankAccount = (String) invoiceResultMap.get("salesTaxpayerBankAccount");
            //销方地址，电话
            String salesTaxpayerAddress = (String) invoiceResultMap.get("salesTaxpayerAddress");
            //价税合计
            String totalTaxSum = (String) invoiceResultMap.get("totalTaxSum");
            //税额
            String totalTaxNum = (String) invoiceResultMap.get("totalTaxNum");
            //不含税价（金额）
            String totalAmount = (String) invoiceResultMap.get("totalAmount");
            //备注
            String invoiceRemarks = (String) invoiceResultMap.get("invoiceRemarks");
            //是否为清单票，Y：是，N：否
            String isBillMark = (String) invoiceResultMap.get("isBillMark");
            //作废标志，0：正常，1：作废
            String voidMark = (String) invoiceResultMap.get("voidMark");
            //收货员（卷式发票新增字段，其他票可以不用）
            String goodsClerk = (String) invoiceResultMap.get("goodsClerk");
            //支持票种：普通发票，专用发票，卷式发票，普通增值税（通行费），普通增值税（折叠费）
            String tollSign = (String) invoiceResultMap.get("tollSign");
            //收费标志名称（没有为空）
            String tollSignName = (String) invoiceResultMap.get("tollSignName");

            invoInfoEntity.setInvoiceSeq(invoiceSeq);
            invoInfoEntity.setInvoiceCode(invoiceDataCode);
            invoInfoEntity.setInvoiceNumber(invoiceNumber);
            invoInfoEntity.setBillTime(billingTime);
            invoInfoEntity.setCheckCode(checkCode);
            invoInfoEntity.setInvoiceAmount(totalTaxSum);//价税合计
            invoInfoEntity.setIsChecked(Constant.YES);
            invoInfoEntity.setCrtDt(time);
            invoInfoEntity.setCrtUsr(user.getUsername());
            invoInfoEntity.setLastChgDt(time);
            invoInfoService.insert(invoInfoEntity);

            invoCheckEntity.setInvoiceSeq(invoiceSeq);
            invoCheckEntity.setScanStr(scanStr);
            invoCheckEntity.setToken(token);
            invoCheckEntity.setRtnCode(rtnCode);
            invoCheckEntity.setResultCode(resultCode);//TODO
            invoCheckEntity.setResultMsg(resultMsg);
            invoCheckEntity.setInvoiceName(invoiceName);
            invoCheckEntity.setInvoiceResult(invoiceResult);
            invoCheckEntity.setIsFree(isFree);
            invoCheckEntity.setCrtDt(time);
            invoCheckEntity.setCrtUsr(user.getUsername());
            invoCheckEntity.setLastChgDt(time);
            invoCheckService.insert(invoCheckEntity);

            invoCheckDetlEntity.setInvoiceSeq(invoiceSeq);
            invoCheckDetlEntity.setInvoiceDataCode(invoiceDataCode);
            invoCheckDetlEntity.setInvoiceNumber(invoiceNumber);
            invoCheckDetlEntity.setInvoiceTypeName(invoiceTypeName);
            invoCheckDetlEntity.setInvoiceTypeCode(invoiceTypeCode);
            invoCheckDetlEntity.setBillingTime(billingTime);
            invoCheckDetlEntity.setCheckDate(checkDate);
            invoCheckDetlEntity.setCheckNum(checkNum);
            invoCheckDetlEntity.setCheckCode(checkCode);
            invoCheckDetlEntity.setTaxDiskCode(taxDiskCode);
            invoCheckDetlEntity.setPurchaserName(purchaserName);
            invoCheckDetlEntity.setTaxpayerNumber(taxpayerNumber);
            invoCheckDetlEntity.setTaxpayerBankAccount(taxpayerBankAccount);
            invoCheckDetlEntity.setTaxpayerAddressOrId(taxpayerAddressOrId);
            invoCheckDetlEntity.setSalesName(salesName);
            invoCheckDetlEntity.setSalesTaxpayerNum(salesTaxpayerNum);
            invoCheckDetlEntity.setSalesTaxpayerBankAccount(salesTaxpayerBankAccount);
            invoCheckDetlEntity.setSalesTaxpayerAddress(salesTaxpayerAddress);
            invoCheckDetlEntity.setTotalTaxSum(totalTaxSum);
            invoCheckDetlEntity.setTotalTaxNum(totalTaxNum);
            invoCheckDetlEntity.setTotalAmount(totalAmount);
            invoCheckDetlEntity.setInvoiceRemarks(invoiceRemarks);
            invoCheckDetlEntity.setIsBillMark(isBillMark);
            invoCheckDetlEntity.setVoidMark(voidMark);
            invoCheckDetlEntity.setGoodsClerk(goodsClerk);
            invoCheckDetlEntity.setTollSign(tollSign);
            invoCheckDetlEntity.setTollSignName(tollSignName);
            invoCheckDetlEntity.setInvoiceDetailData("");//TODO
            invoCheckDetlEntity.setCrtDt(time);
            invoCheckDetlEntity.setCrtUsr(user.getUsername());
            invoCheckDetlEntity.setLastChgDt(time);
            invoCheckDetlService.insert(invoCheckDetlEntity);

            //TODO (不同发票处理不同数据模型) 以下先不做
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
    public static String getToken(String appKey, String appSecret) throws JsonParseException, JsonMappingException, IOException {
        String token = "";
        String tokenRest = HttpRequestUtils.sendGet(ConfigConstant.OPEN_URL + "/getToken?appKey=" + appKey + "&appSecret=" + appSecret);
        Map<String, Object> tokenMap = JacksonUtils.getMapFromJson(tokenRest);
        if (tokenMap != null && tokenMap.get("token") != null) {
            token = tokenMap.get("token").toString();
            logger.info("获取token值为:{}", token);
        } else {
            //没拿到token原因，1）检查账号是否开通  2）是否修改appSecret
            logger.error("获取token失败,请检查!");
        }
        return token;
    }


}
