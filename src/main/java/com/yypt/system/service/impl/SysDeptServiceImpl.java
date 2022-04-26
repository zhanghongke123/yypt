package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.utils.Condition;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.domain.SysTenant;
import com.yypt.system.mapper.SysDeptMapper;
import com.yypt.system.domain.SysDept;
import com.yypt.system.service.SysDeptService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @创建人 zhk
 * @创建时间 2019-07-12
 * @描述
 */
@Service
public class SysDeptServiceImpl extends ServiceImpl<SysDeptMapper,SysDept> implements SysDeptService {

    List<SysDept> childdept ;


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
        if(deptId == 0){
            throw  new Exception("最高级部门不可删除");
        }
        childdept = new ArrayList<>();
        List<SysDept> sysDeptList = this.list();
        List<SysDept> childrenList = this.getAllChildren(sysDeptList,deptId);
        List<Long> childrenIds = childrenList.stream().map(dept -> dept.getDeptId()).collect(Collectors.toList());
        childrenIds.add(deptId);
        this.baseMapper.deleteBatchIds(childrenIds);
    }

    @Override
    public Page<Map> pageMap(QueryRequest<SysDept> queryRequest) {
        SysDept querylist = queryRequest.getQuerylist();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (querylist != null) {
            queryWrapper = Condition.getQueryWrapper(queryRequest.getQuerylist(),false);
        }
        Page page = new Page();
        SortUtil.handlePageSort(queryRequest,page,"deptId", YyptConstant.ORDER_DESC,true);
        return this.baseMapper.pageMap(page , queryWrapper);
    }


    /**
     * 获取当前部门下面的所有子部门
     * @param depts
     * @param parentid
     * @return
     */
    public List<SysDept> getAllChildren(List<SysDept> depts, Long parentid){
        for (SysDept sysdept:depts) {
            if(sysdept.getParentid() == parentid){
                getAllChildren(depts,sysdept.getDeptId());
                childdept.add(sysdept);
            }
        }
        return childdept;
    }




}
