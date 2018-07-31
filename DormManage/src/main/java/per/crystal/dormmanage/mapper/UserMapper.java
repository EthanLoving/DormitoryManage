package per.crystal.dormmanage.mapper;

import org.apache.ibatis.annotations.Param;

import per.crystal.dormmanage.entity.User;

/**
 * @author Crystal
 * 2018/02/10
 */
public interface UserMapper extends BaseMapper<User> {

	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 */
	User userLogin(@Param("username") String username, @Param("password") String password);
	
}
