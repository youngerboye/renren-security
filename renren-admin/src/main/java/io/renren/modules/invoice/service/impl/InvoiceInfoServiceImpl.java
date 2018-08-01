package io.renren.modules.invoice.service.impl;

import io.renren.common.utils.R;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.invoice.dao.InvoiceInfoDao;
import io.renren.modules.invoice.entity.InvoiceInfoEntity;
import io.renren.modules.invoice.service.InvoiceInfoService;


@Service("invoiceInfoService")
public class InvoiceInfoServiceImpl extends ServiceImpl<InvoiceInfoDao, InvoiceInfoEntity> implements InvoiceInfoService {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceInfoServiceImpl.class);

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InvoiceInfoEntity> page = this.selectPage(
                new Query<InvoiceInfoEntity>(params).getPage(),
                new EntityWrapper<InvoiceInfoEntity>()
        );

        return new PageUtils(page);
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

    /**
     * 发票核验
     * @param scanStr
     * @return
     */
    public R validateInvoice(String scanStr){
        R r = R.ok();
        try {
            r = InvoiceInfoByQRCode.getVatInfoByParam(scanStr);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
        }
        return r;
    }
}
