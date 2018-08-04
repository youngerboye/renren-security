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

import io.renren.modules.invoice.entity.InvoCheckDetlEntity;
import io.renren.modules.invoice.service.InvoCheckDetlService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;



/**
 * 
 *
 * @author sunzh
 * @email kjustsun@gmail.com
 * @date 2018-08-04 16:54:52
 */
@RestController
@RequestMapping("invoice/invocheckdetl")
public class InvoCheckDetlController {
    @Autowired
    private InvoCheckDetlService invoCheckDetlService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("invoice:invocheckdetl:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = invoCheckDetlService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("invoice:invocheckdetl:info")
    public R info(@PathVariable("id") Integer id){
        InvoCheckDetlEntity invoCheckDetl = invoCheckDetlService.selectById(id);

        return R.ok().put("invoCheckDetl", invoCheckDetl);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("invoice:invocheckdetl:save")
    public R save(@RequestBody InvoCheckDetlEntity invoCheckDetl){
        invoCheckDetlService.insert(invoCheckDetl);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("invoice:invocheckdetl:update")
    public R update(@RequestBody InvoCheckDetlEntity invoCheckDetl){
        ValidatorUtils.validateEntity(invoCheckDetl);
        invoCheckDetlService.updateAllColumnById(invoCheckDetl);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("invoice:invocheckdetl:delete")
    public R delete(@RequestBody Integer[] ids){
        invoCheckDetlService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
