package per.crystal.dormmanage.mapper;

import java.util.List;

import per.crystal.dormmanage.entity.Dorm;

/**
 * @author Crystal
 * 2018/02/10
 */
public interface DormMapper extends BaseMapper<Dorm> {

	/**
	 * 停用楼层的时候批量停用宿舍
	 * @param idList
	 * @return
	 */
	int batchDeleteByBuildId(List<String> idList);
	
	/**
	 * 停用楼层的时候停用宿舍
	 * @param id
	 * @return
	 */
	int deleteByBuildId(String id);
	
	/**
	 * 查看该楼层下是否还有宿舍住有学生
	 * @param id
	 * @return
	 */
	int getCountByBuildId(String id);
	
	/**
	 * 查看批量停用的楼层宿舍中是否还有学生
	 * @param idList
	 * @return
	 */
	int getCountByBuildIdList(List<String> idList);
	
	/**
	 * 启用楼层的时候启用该楼层的所有宿舍
	 * @param id
	 * @return
	 */
	int recoverByBuildId(String id);
	
	/**
	 * 批量启用楼层的时候启用所有宿舍
	 * @param idList
	 * @return
	 */
	int recoverByBuildIdList(List<String> idList);

}
