package io.renren.modules.invoice.service.impl;

import io.renren.common.utils.HttpRequestUtils;
import io.renren.common.utils.JacksonUtils;
import io.renren.common.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 根据发票二维码字符串进行查验
 *
 * @author 支持场景：扫描枪，微信公众号，小程序，支付宝第三方平台，自己app中扫描功能
 * 好处：无需填写参数，只需识别工具识别就行了
 * 缺点：需要识别工具
 */
public class InvoiceInfoByQRCode {



    private static final Logger log = LoggerFactory.getLogger(InvoiceInfoByQRCode.class);


}
