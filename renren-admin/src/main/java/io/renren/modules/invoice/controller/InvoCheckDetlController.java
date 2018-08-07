package io.renren.modules.invoice.controller;

import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import io.renren.common.validator.ValidatorUtils;
import io.renren.modules.invoice.entity.InvoCheckDetlEntity;
import io.renren.modules.invoice.service.InvoCheckDetlService;
import io.renren.modules.sys.controller.AbstractController;
import io.renren.modules.sys.entity.SysUserEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;


/**
 * @author sunzh
 * @email kjustsun@gmail.com
 * @date 2018-08-04 16:54:52
 */
@RestController
@RequestMapping("invoice/invocheckdetl")
public class InvoCheckDetlController extends AbstractController {
    @Autowired
    private InvoCheckDetlService invoCheckDetlService;

    /**
     * 列表(根据查询内容进行查询)
     */
    @RequestMapping("/list")
    @RequiresPermissions("invoice:invocheckdetl:list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = invoCheckDetlService.queryPage(params);

        return R.ok().put("page", page);
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("invoice:invocheckdetl:info")
    public R info(@PathVariable("id") Integer id) {
        InvoCheckDetlEntity invoCheckDetl = invoCheckDetlService.selectById(id);

        return R.ok().put("invoCheckDetl", invoCheckDetl);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("invoice:invocheckdetl:save")
    public R save(@RequestBody InvoCheckDetlEntity invoCheckDetl) {
        invoCheckDetlService.insert(invoCheckDetl);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("invoice:invocheckdetl:update")
    public R update(@RequestBody InvoCheckDetlEntity invoCheckDetl) {
        ValidatorUtils.validateEntity(invoCheckDetl);
        invoCheckDetlService.updateAllColumnById(invoCheckDetl);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("invoice:invocheckdetl:delete")
    public R delete(@RequestBody Integer[] ids) {
        invoCheckDetlService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 校验发票真伪并落库
     */
    @RequestMapping("/scan")
    @RequiresPermissions("invoice:invocheckdetl:scan") //权限管理
    public R validateInvoice(@RequestParam String scanStr) {
        if (StringUtils.isBlank(scanStr)) {
            return R.error("请扫描后,再进行查验!");
        }
        SysUserEntity user = getUser();

        return invoCheckDetlService.validateInvoice(scanStr, user);
    }

    /**
     * 导出Excel表格
     */
    @RequestMapping("/exportExcel")
    @RequiresPermissions("invoice:invocheckdetl:exportExcel")
    public R exportExcel(HttpServletResponse response, @RequestParam Map<String, Object> params) throws IOException {


        return invoCheckDetlService.exportExcel(response, params);
    }

}
