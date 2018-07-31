package per.crystal.dormmanage.service;

import java.util.List;

import per.crystal.dormmanage.entity.DormBuild;
import per.crystal.dormmanage.exception.ServiceException;

/**
 * 2018/02/11
 * @author Crystal
 *
 */
public interface DormBuildService extends BaseService<DormBuild> {

	/**
	 * 停用楼层的时候批量停用宿舍
	 * @param idList
	 * @return
	 */
	int batchDeleteByBuildId(List<String> idList) throws ServiceException;
	
	/**
	 * 停用楼层的时候停用宿舍
	 * @param id
	 * @return
	 */
	int deleteByBuildId(String id) throws ServiceException;
	
	/**
	 * 查看该楼层下是否还有宿舍住有学生
	 * @param id
	 * @return
	 */
	int getCountByBuildId(String id) throws ServiceException;
	
	/**
	 * 查看批量停用的楼层宿舍中是否还有学生
	 * @param idList
	 * @return
	 */
	int getCountByBuildIdList(List<String> idList) throws ServiceException;
	
	/**
	 * 启用楼层的时候启用该楼层的所有宿舍
	 * @param id
	 * @return
	 */
	int recoverByBuildId(String id) throws ServiceException;
	
	/**
	 * 批量启用楼层的时候启用所有宿舍
	 * @param idList
	 * @return
	 */
	int recoverByBuildIdList(List<String> idList) throws ServiceException;
	
}
