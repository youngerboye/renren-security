package io.renren.modules.invoice.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.modules.invoice.entity.InvoiceInfoEntity;
import io.renren.modules.invoice.service.InvoiceInfoService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author chenshun
 * @email kjustsun@gmail.com
 * @date 2018-07-31 17:01:04
 */
@RestController
@RequestMapping("invoice/invoiceinfo")
public class InvoiceInfoController {
    @Autowired
    private InvoiceInfoService invoiceInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("invoice:invoiceinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = invoiceInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("invoice:invoiceinfo:info")
    public R info(@PathVariable("id") Integer id){
        InvoiceInfoEntity invoiceInfo = invoiceInfoService.selectById(id);

        return R.ok().put("invoiceInfo", invoiceInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("invoice:invoiceinfo:save")
    public R save(@RequestBody InvoiceInfoEntity invoiceInfo){
        invoiceInfoService.insert(invoiceInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("invoice:invoiceinfo:update")
    public R update(@RequestBody InvoiceInfoEntity invoiceInfo){
        ValidatorUtils.validateEntity(invoiceInfo);
        invoiceInfoService.updateAllColumnById(invoiceInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("invoice:invoiceinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        invoiceInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
