package io.renren.modules.invoice.service;

import com.baomidou.mybatisplus.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.modules.invoice.entity.InvoiceInfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenshun
 * @email kjustsun@gmail.com
 * @date 2018-07-31 17:01:04
 */
public interface InvoiceInfoService extends IService<InvoiceInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);

    R analyScanStr(String scanStr);

    R validateInvoice(String scanStr);
}

