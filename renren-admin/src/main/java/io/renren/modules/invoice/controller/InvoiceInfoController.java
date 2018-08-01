package io.renren.modules.invoice.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.common.validator.ValidatorUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(InvoiceInfoController.class);

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

    /**
     * 校验发票真伪
     */
    @RequestMapping("/validate")
    @RequiresPermissions("invoice:invoiceinfo:validate")
    public R validateInvoice(@RequestParam String scanStr){
        //本地解析扫描出的字符串
        R analyResult = invoiceInfoService.analyScanStr(scanStr);
        //本地校验成功后,调取接口进行发票核验
        if (!"500".equals(analyResult.get("code"))){
            return analyResult;
        }
        //R validateResult = invoiceInfoService.validateInvoice(scanStr);

        return R.ok();
    }

}
