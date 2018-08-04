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

import io.renren.modules.invoice.entity.InvoCheckEntity;
import io.renren.modules.invoice.service.InvoCheckService;
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
@RequestMapping("invoice/invocheck")
public class InvoCheckController {
    @Autowired
    private InvoCheckService invoCheckService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("invoice:invocheck:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = invoCheckService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("invoice:invocheck:info")
    public R info(@PathVariable("id") Integer id){
        InvoCheckEntity invoCheck = invoCheckService.selectById(id);

        return R.ok().put("invoCheck", invoCheck);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("invoice:invocheck:save")
    public R save(@RequestBody InvoCheckEntity invoCheck){
        invoCheckService.insert(invoCheck);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("invoice:invocheck:update")
    public R update(@RequestBody InvoCheckEntity invoCheck){
        ValidatorUtils.validateEntity(invoCheck);
        invoCheckService.updateAllColumnById(invoCheck);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("invoice:invocheck:delete")
    public R delete(@RequestBody Integer[] ids){
        invoCheckService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
