package io.renren.modules.invoice.service.impl;

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

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InvoiceInfoEntity> page = this.selectPage(
                new Query<InvoiceInfoEntity>(params).getPage(),
                new EntityWrapper<InvoiceInfoEntity>()
        );

        return new PageUtils(page);
    }

}
