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

import io.renren.modules.invoice.entity.InvoInfoEntity;
import io.renren.modules.invoice.service.InvoInfoService;
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
@RequestMapping("invoice/invoinfo")
public class InvoInfoController {
    @Autowired
    private InvoInfoService invoInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("invoice:invoinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = invoInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("invoice:invoinfo:info")
    public R info(@PathVariable("id") Integer id){
        InvoInfoEntity invoInfo = invoInfoService.selectById(id);

        return R.ok().put("invoInfo", invoInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("invoice:invoinfo:save")
    public R save(@RequestBody InvoInfoEntity invoInfo){
        invoInfoService.insert(invoInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("invoice:invoinfo:update")
    public R update(@RequestBody InvoInfoEntity invoInfo){
        ValidatorUtils.validateEntity(invoInfo);
        invoInfoService.updateAllColumnById(invoInfo);//全部更新
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("invoice:invoinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        invoInfoService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
