package io.renren.modules.invoice.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.invoice.dao.InvoCheckDao;
import io.renren.modules.invoice.entity.InvoCheckEntity;
import io.renren.modules.invoice.service.InvoCheckService;


@Service("invoCheckService")
public class InvoCheckServiceImpl extends ServiceImpl<InvoCheckDao, InvoCheckEntity> implements InvoCheckService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InvoCheckEntity> page = this.selectPage(
                new Query<InvoCheckEntity>(params).getPage(),
                new EntityWrapper<InvoCheckEntity>()
        );

        return new PageUtils(page);
    }

}
