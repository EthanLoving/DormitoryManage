package per.crystal.dormmanage.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.mapper.UserMapper;
import per.crystal.dormmanage.service.UserService;

/**
 * 2018/02/11
 * @author Crystal
 *
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService {

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public BaseMapper<User> getBaseMapper() {
		return userMapper;
	}

	@Override
	public User userLogin(String username, String password)
			throws ServiceException {
		User user;
		try {
			user = userMapper.userLogin(username, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return user;
	}

	@Override
	public int updatePwd(User user) throws ServiceException {
		int count;
		try {
			count = userMapper.update(user);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}
	
}
