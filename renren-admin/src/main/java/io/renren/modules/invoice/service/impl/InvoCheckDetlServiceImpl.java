package io.renren.modules.invoice.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.invoice.dao.InvoCheckDetlDao;
import io.renren.modules.invoice.entity.InvoCheckDetlEntity;
import io.renren.modules.invoice.service.InvoCheckDetlService;


@Service("invoCheckDetlService")
public class InvoCheckDetlServiceImpl extends ServiceImpl<InvoCheckDetlDao, InvoCheckDetlEntity> implements InvoCheckDetlService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<InvoCheckDetlEntity> page = this.selectPage(
                new Query<InvoCheckDetlEntity>(params).getPage(),
                new EntityWrapper<InvoCheckDetlEntity>()
        );

        return new PageUtils(page);
    }

}
