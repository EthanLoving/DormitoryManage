package per.crystal.dormmanage.service;

import per.crystal.dormmanage.controller.view.Page;
import per.crystal.dormmanage.exception.ServiceException;

import java.util.List;

/**
 * @author Crystal
 * 2018/02/10
 */
public interface BaseService<Entity> {

    /**
     * 公共的新增方法
     * @param entity
     * @return
     */
    int save(Entity entity) throws ServiceException;

    /**
     * 公共的根据id删除方法
     * @param id
     * @return
     */
    int deleteById(String id) throws ServiceException;

    /**
     * 公共的批量删除方法
     * @param idList
     * @return
     */
    int batchDelete(List<String> idList) throws ServiceException;

    /**
     * 公共的逻辑删除方法
     * @param id
     * @return
     */
    int logicDeleteById(String id) throws ServiceException;

    /**
     * 公共的批量逻辑删除方法
     * @param idList
     * @return
     */
    int logicBatchDelete(List<String> idList) throws ServiceException;
    
    /**
     * 根据id启用
     * @param id
     * @return
     */
    int recoverById(String id) throws ServiceException;
    
    /**
     * 批量启用
     * @param idList
     * @return
     */
    int batchRecover(List<String> idList) throws ServiceException;

    /**
     * 公共的修改方法
     * @param entity
     * @return
     */
    int update(Entity entity) throws ServiceException;

    /**
     * 公共的根据id查询方法
     * @param id
     * @return
     */
    Entity getById(String id) throws ServiceException;

    /**
     * 公共的查询是否存在方法
     * @param param
     * @return
     */
    int findExists(String param) throws ServiceException;

    /**
     * 公共的查询所有方法
     * @return
     */
    List<Entity> listAll() throws ServiceException;

    /**
     * 公共的分页方法
     * @param page
     * @return
     */
    Page listByPage(Page page) throws ServiceException;

}
