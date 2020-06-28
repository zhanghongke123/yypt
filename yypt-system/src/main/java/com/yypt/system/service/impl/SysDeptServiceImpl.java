package com.yypt.system.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.system.dao.SysDeptMapper;
import com.yypt.system.domain.SysDept;
import com.yypt.system.service.SysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper,SysDept> implements SysDeptService {
    @Override
    public List<SysDept> getDeptTreeView(SysDept sysDept) {
        List<SysDept> sysDepts = this.baseMapper.selectList(null);
        List<SysDept> topDepts = new ArrayList<SysDept>();
        sysDepts.forEach(dept -> {
            if(dept.getDeptId() == 0L){
                topDepts.add(dept);
                return;
            }

            Long parentId = dept.getParentid();
            if (parentId == null || 0L == parentId) {
                topDepts.add(dept);
                return;
            }
            for (SysDept parent : sysDepts) {
                Long deptId = parent.getDeptId();
                if (deptId != null  &&  deptId == parentId) {
                    if (parent.getChildren() == null){
                        parent.initChildren();
                    }
                    parent.getChildren().add(dept);
                    return;
                }
            }
        });
        return topDepts;
    }

    @Override
    @Transactional
    public SysDept updateOrAdd(SysDept sysDept) {
        //有ID的话就是修改，没有ID的话就是保存
        Long deptId = sysDept.getDeptId();
        if(deptId != null){
            sysDept.setModifyDate(new Date());
            this.baseMapper.updateById(sysDept);
        }else{
            sysDept.setModifyDate(new Date());
            sysDept.setCreateDate(new Date());
            this.baseMapper.insert(sysDept);
        }
        return sysDept;
    }

    @Override
    public void delte(Long deptId) throws Exception{
        //递归删除部门下面的部门
        this.baseMapper.deleteBy(deptId);
    }




}
