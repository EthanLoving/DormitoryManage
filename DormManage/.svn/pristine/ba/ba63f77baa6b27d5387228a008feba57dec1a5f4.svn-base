package per.crystal.dormmanage.service;

import per.crystal.dormmanage.entity.Student;
import per.crystal.dormmanage.exception.ServiceException;

/**
 * 
 * @author Crystal
 * 2018/02/10
 */
public interface StudentService extends BaseService<Student> {

	/**
	 * 学生登录
	 * @param stuNo
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	Student studentLogin(String stuNo, String password) throws ServiceException;
	
	/**
	 * 删除学生，所住宿舍人数减一
	 * @param stuId
	 * @param dormId
	 * @return
	 * @throws ServiceException
	 */
	int deleteById(String stuId, String dormId) throws ServiceException;
	
	/**
	 * 修改密码
	 * @param student
	 * @return
	 * @throws ServiceException 
	 */
	int updatePwd(Student student) throws ServiceException;
	
}
