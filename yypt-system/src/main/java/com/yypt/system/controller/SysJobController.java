package com.yypt.system.controller;

import com.yypt.common.annotation.Limit;
import com.yypt.common.domain.YyptResponse;
import com.yypt.system.domain.SysJob;
import com.yypt.system.service.SysJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @创建人 zhk
 * @创建时间 2019-05-15
 * @描述
 */
@RestController
@RequestMapping("job")
public class SysJobController {

	@Autowired
	SysJobService jobService;

	@GetMapping("list")
	public YyptResponse list() throws Exception{
		return YyptResponse.success(jobService.getAllJob());
	}

	@RequestMapping("/resume")
	public  YyptResponse resume(@RequestBody SysJob sysJob) throws Exception{
		jobService.resume(sysJob);
		return YyptResponse.success("恢复成功");
	}

	@RequestMapping("/add")
	public YyptResponse add(@RequestBody SysJob sysJob) throws  Exception{
		jobService.add(sysJob);
		return YyptResponse.success("添加成功");
	}

	@RequestMapping("/remove")
	public  YyptResponse remove(@RequestBody SysJob sysJob) throws  Exception{
		jobService.remove(sysJob);
		return YyptResponse.success("移除成功");
	}

	@RequestMapping("/pause")
	public YyptResponse pause(@RequestBody SysJob sysJob) throws Exception{
		jobService.pause(sysJob);
		return YyptResponse.success("停止成功");
	}

	@RequestMapping("/edit")
	public  YyptResponse edit(@RequestBody SysJob sysJob) throws Exception{
		jobService.edit(sysJob);
		return YyptResponse.success("修改成功");
	}


}
