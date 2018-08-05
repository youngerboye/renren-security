package io.renren.modules.invoice.service.impl;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.invoice.dao.InvoInfoDao;
import io.renren.modules.invoice.entity.InvoInfoEntity;
import io.renren.modules.invoice.service.InvoInfoService;


@Service("invoInfoService")
public class InvoInfoServiceImpl extends ServiceImpl<InvoInfoDao, InvoInfoEntity> implements InvoInfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String invoiceCode = (String) params.get("invoiceCode");
        String invoiceNumber = (String) params.get("invoiceNumber");

        Page<InvoInfoEntity> page = this.selectPage(
                new Query<InvoInfoEntity>(params).getPage(),
                new EntityWrapper<InvoInfoEntity>()
                .eq(StringUtils.isNotBlank(invoiceCode), "invoice_code", invoiceCode)
                .eq(StringUtils.isNotBlank(invoiceNumber), "invoice_number", invoiceNumber)
        );

        return new PageUtils(page);
    }

}
