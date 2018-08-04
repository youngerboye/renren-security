package io.renren.modules.invoice.service.impl;

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
        Page<InvoInfoEntity> page = this.selectPage(
                new Query<InvoInfoEntity>(params).getPage(),
                new EntityWrapper<InvoInfoEntity>()
        );

        return new PageUtils(page);
    }

}
