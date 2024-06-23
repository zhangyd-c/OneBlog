<#assign po = table.classNameFirstLower>

${r"@"}RequiresPermissions("${po}s")
${r"@"}BussinessLog("进入${table.remark}页面")
${r"@"}GetMapping("/${po}")
public ModelAndView ${po}(Model model) {
    return ResultUtil.view("${po}/${po}");
}
