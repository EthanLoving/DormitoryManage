package per.crystal.dormmanage.service.impl;

import org.apache.commons.lang.StringUtils;

import per.crystal.dormmanage.controller.view.Page;
import per.crystal.dormmanage.entity.BaseEntity;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.service.BaseService;
import per.crystal.dormmanage.util.IDGenerator;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Crystal
 * 2018/02/10
 */
public abstract class BaseServiceImpl<Entity extends BaseEntity> implements BaseService<Entity> {

    public abstract BaseMapper<Entity> getBaseMapper();

    /**
     * 公共的新增方法
     */
    @Override
    public int save(Entity entity) throws ServiceException {
        String id = IDGenerator.getPrimaryKey();
        int count;
        if (StringUtils.isBlank(entity.getId()) || "0".equalsIgnoreCase(entity.getId())) {
            entity.setId(id);
        }
        entity.setCreateDate(new Date());
        try {
            count = this.getBaseMapper().save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * 公共的根据ID删除方法
     */
    @Override
    public int deleteById(String id) throws ServiceException {
        int count;
        try {
            count = this.getBaseMapper().deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }
    
    /**
     * 公共的批量删除方法
     */
    @Override
    public int batchDelete(List<String> idList) throws ServiceException {
        int count;
        try {
            count = this.getBaseMapper().batchDelete(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * 公共的根据ID逻辑删除方法
     */
    @Override
    public int logicDeleteById(String id) throws ServiceException {
        int count;
        try {
            count = this.getBaseMapper().logicDeleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * 公共的批量逻辑删除方法
     */
    @Override
    public int logicBatchDelete(List<String> idList) throws ServiceException {
        int count;
        try {
            count = this.getBaseMapper().logicBatchDelete(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }
    
    @Override
    public int recoverById(String id) throws ServiceException {
    	int count;
    	try {
			count = this.getBaseMapper().recoverById(id);
		} catch (Exception e) {
			e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
		}
    	return count;
    }
    
    public int batchRecover(List<String> idList) throws ServiceException {
    	int count;
    	try {
			count = this.getBaseMapper().batchRecover(idList);
		} catch (Exception e) {
			e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
		}
    	return count;
    }

    /**
     * 公共的修改方法
     */
    @Override
    public int update(Entity entity) throws ServiceException {
        int count;
        try {
            count = this.getBaseMapper().update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * 公共的根据ID查询方法
     */
    @Override
    public Entity getById(String id) throws ServiceException {
        Entity entity;
        try {
            entity = this.getBaseMapper().getById(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return entity;
    }

    /**
     * 公共的查询是否存在方法
     */
    @Override
    public int findExists(String param) throws ServiceException {
        int count;
        try {
            count = this.getBaseMapper().findExists(param);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

    /**
     * 公共的查询所有方法
     */
    @Override
    public List<Entity> listAll() throws ServiceException {
        List<Entity> list;
        try {
            list = this.getBaseMapper().listAll();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return list;
    }

    /**
     * 公共的分页方法
     */
    @Override
    public Page listByPage(Page page) throws ServiceException {
        //单页查询不允许超过500条，防止恶意调用page size过大导致内存溢出
        if (page.getPageSize() > Page.MAX_PAGE_SIZE) {
            throw new ServiceException("超过允许查询的单页记录最大值");
        }
        Map<String, Object> queryParams = new HashMap<String, Object>();
        try {
            Map<String, Object> conditions = page.getConditions();
            if (null != conditions) {
                queryParams.putAll(conditions);
            }
            page.setTotal(this.getBaseMapper().getCount(queryParams));
            queryParams.put("startNum", page.getStartNum());
            queryParams.put("pageSize", page.getPageSize());
            page.setRows(this.getBaseMapper().listByPage(queryParams));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return page;
    }
}
