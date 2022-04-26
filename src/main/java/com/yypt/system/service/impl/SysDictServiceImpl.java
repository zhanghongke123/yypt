package com.yypt.system.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yypt.common.domain.QueryRequest;
import com.yypt.common.properties.YyptConstant;
import com.yypt.common.service.RedisService;
import com.yypt.common.utils.SortUtil;
import com.yypt.system.domain.SysDict;
import com.yypt.system.dao.SysDictMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yypt.system.domain.SysDictDtl;
import com.yypt.system.domain.SysLog;
import com.yypt.system.domain.SysLoginLog;
import com.yypt.system.service.SysDictDtlService;
import com.yypt.system.service.SysDictService;
import com.yypt.system.vo.DictVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ZHK
 */
@Service
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {


    private final static String DICT_SUFFIX="dict:";

    @Autowired
    SysDictDtlService sysDictDtlService;

    @Autowired
    RedisService redisService;

    @Autowired
    ObjectMapper objectMapper;


    @Override
    public IPage<SysDict> list(QueryRequest<SysDict> request) {
        try {
            Page<SysDict> page = new Page<>();
            SortUtil.handlePageSort(request, page, "dictId", YyptConstant.ORDER_DESC,true);
            return this.baseMapper.list(page,request.getQuerylist());
        } catch (Exception e) {
            log.error("查询登录历史异常", e);
            return null;
        }

    }


    @Override
    public List<DictVO> codeInfo(String dictCode)  {
        //先从redis中取，没有的话从数据库取
        try {
            String dictStr = redisService.get(DICT_SUFFIX + dictCode);
            if (StringUtils.isBlank(dictStr)) {
                throw new Exception("redis不存在");
            }
            List<DictVO> dictVOS = objectMapper.readValue(dictStr, new TypeReference<List<DictVO>>() {
            });

            if(CollectionUtils.isNotEmpty(dictVOS)){
                return dictVOS;
            }

        }catch (Exception e){

        }

        SysDict sysDict = this.getOne(Wrappers.<SysDict>lambdaQuery()
                .eq(SysDict::getDictName, dictCode)
                .last(" limit 1")
                .select(SysDict::getDictId));
        if (sysDict == null) {
            throw new RuntimeException("未找到【"+dictCode+"】对应的字典信息");
        }
        List<SysDictDtl> sysDictDtls = sysDictDtlService.list(Wrappers.<SysDictDtl>lambdaQuery().eq(SysDictDtl::getDictId, sysDict.getDictId()));
        List<DictVO> dictVOS = new ArrayList<>();
        dictVOS.add(new DictVO("",""));
        for (SysDictDtl sysDictDtl : sysDictDtls) {
            String label = sysDictDtl.getLabel();
            String value = sysDictDtl.getValue();
            dictVOS.add(new DictVO(label,value));
        }

        try {
            redisService.set(DICT_SUFFIX+dictCode,objectMapper.writeValueAsString(dictVOS), RandomUtils.nextLong(300,600) * 1000);
        }catch (Exception e){
            log.error("写入redis异常",e);
        }

        return  dictVOS;
    }




}
