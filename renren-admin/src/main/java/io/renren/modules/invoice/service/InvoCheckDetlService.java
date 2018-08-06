package io.renren.modules.invoice.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.invoice.entity.InvoCheckDetlEntity;
import io.renren.modules.sys.entity.SysUserEntity;

import java.util.Map;

/**
 * 
 *
 * @author sunzh
 * @email kjustsun@gmail.com
 * @date 2018-08-04 16:54:52
 */
public interface InvoCheckDetlService extends IService<InvoCheckDetlEntity> {

    PageUtils queryPage(Map<String, Object> params);
    
    R validateInvoice(String scanStr, SysUserEntity user);

}

