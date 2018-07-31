package per.crystal.dormmanage.service;

import java.util.List;

import per.crystal.dormmanage.entity.Dorm;
import per.crystal.dormmanage.exception.ServiceException;

/**
 * 2018/02/15
 * @author Crystal
 *
 */
public interface DormService extends BaseService<Dorm> {

	/**
     * 停用宿舍
     * @param id
     * @return
     */
    int logicDeleteById(String id) throws ServiceException;

    /**
     * 批量停用宿舍
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
}
