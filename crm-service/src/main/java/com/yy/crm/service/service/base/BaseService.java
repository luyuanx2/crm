package com.yy.crm.service.service.base;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author 鲁源源 on 2018/1/10.
 */
@Service
public abstract class BaseService<T> {

    @Autowired
    private Mapper<T> mapper;
    /**
     * 根据id查询数据
     *
     * @param id
     * @return
     */
    public T queryById(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    /**
     * 查询所有数据
     *
     * @return
     */
    public List<T> queryAll() {
        return mapper.select(null);
    }

    /**
     * 根据条件查询一条数据，如果有多条数据会抛出异常
     *
     * @param entity
     * @return
     */
    public T queryOne(T entity) {
        return mapper.selectOne(entity);
    }

    /**
     * 根据条件查询数据列表
     *
     * @param entity
     * @return
     */
    public List<T> queryListByWhere(T entity) {
        return mapper.select(entity);
    }

    /**
     * 分页查询
     *
     * @param pageNo
     * @param pageSize
     * @param entity
     * @return
     */
    public PageInfo<T> queryPageListByWhere(int pageNo, int pageSize, T entity) {
        // 设置分页条件
        PageHelper.startPage(pageNo, pageSize);
        List<T> list = this.queryListByWhere(entity);
        return new PageInfo<>(list);
    }

    /**
     * 新增数据，返回成功的条数
     *
     * @param entity
     * @return
     */
    public Integer save(T entity) {
        return mapper.insert(entity);
    }

    /**
     * 新增数据，使用不为null的字段，返回成功的条数
     *
     * @param entity
     * @return
     */
    public Integer saveSelective(T entity) {
        return mapper.insertSelective(entity);
    }

    /**
     * 修改数据，返回成功的条数
     *
     * @param entity
     * @return
     */
    public Integer update(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    /**
     * 修改数据，使用不为null的字段，返回成功的条数
     *
     * @param entity
     * @return
     */
    public Integer updateSelective(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    /**
     * 根据id删除数据
     *
     * @param id
     * @return
     */
    public Integer deleteById(Integer id) {
        return mapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除
     * @param clazz
     * @param property
     * @param values
     * @return
     */
    public Integer deleteByIds(Class<T> clazz, String property, List<Object> values) {
        Example example = new Example(clazz);
        example.createCriteria().andIn(property, values);
        return mapper.deleteByExample(example);
    }

    /**
     * 根据条件做删除
     *
     * @param entity
     * @return
     */
    public Integer deleteByWhere(T entity) {
        return mapper.delete(entity);
    }
}
