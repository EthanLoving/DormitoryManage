package per.crystal.dormmanage.service;

import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;

/**
 * 2018/02/11
 * @author Crystal
 *
 */
public interface UserService extends BaseService<User> {
	
	/**
	 * 用户登录
	 * @param username
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	User userLogin(String username, String password) throws ServiceException;
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	int updatePwd(User user) throws ServiceException;
	
}
