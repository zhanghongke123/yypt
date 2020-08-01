package com.yypt.system.service;


import com.yypt.system.domain.SysJob;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-05-15
 * @描述
 */
public interface SysJobService {
    List<SysJob>  getAllJob() throws Exception;
    void resume(SysJob sysJob) throws Exception;
    void add(SysJob sysJob) throws Exception;
    void remove(SysJob sysJob) throws Exception;
    void pause(SysJob sysJob) throws Exception;
    void edit(SysJob sysJob) throws Exception;
}
