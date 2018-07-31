package per.crystal.dormmanage.service.impl;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import per.crystal.dormmanage.entity.Dorm;
import per.crystal.dormmanage.entity.Student;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.mapper.BaseMapper;
import per.crystal.dormmanage.mapper.DormMapper;
import per.crystal.dormmanage.mapper.StudentMapper;
import per.crystal.dormmanage.service.StudentService;
import per.crystal.dormmanage.util.AESUtil;
import per.crystal.dormmanage.util.IDGenerator;

/**
 * 2018/02/10
 * @author Crystal
 *
 */
@Service
public class StudentServiceImpl extends BaseServiceImpl<Student> implements StudentService {

	@Autowired
	private StudentMapper studentMapper;
	
	@Autowired
	private DormMapper dormMapper;
	
	@Override
	public BaseMapper<Student> getBaseMapper() {
		return studentMapper;
	}
	
	/**
	 * 新增学生的同时宿舍所住人数加1
	 * @param student
	 * @return
	 * @throws ServiceException
	 */
	@Override
    public int save(Student student) throws ServiceException {
        int count;
        String id = IDGenerator.getPrimaryKey();
        if (StringUtils.isBlank(student.getId()) || "0".equalsIgnoreCase(student.getId())) {
        	student.setId(id);
        }
        student.setCreateDate(new Date());
        // 密码加密
        student.setPassword(AESUtil.getEncrypt(student.getPassword()));
        try {
        	Dorm dorm = dormMapper.getById(student.getDormId());
        	dorm.setDormNum(dorm.getDormNum() + 1);
        	dormMapper.update(dorm);
            count = this.getBaseMapper().save(student);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }
	
	/**
	 * 删除学生，对应宿舍人数减一
	 * @param stuId
	 * @param dormId
	 * @return
	 * @throws ServiceException 
	 */
	@Override
	public int deleteById(String stuId, String dormId) throws ServiceException {
		int count;
		try {
			count = studentMapper.deleteById(stuId);
			Dorm dorm = dormMapper.getById(dormId);
			dorm.setDormNum(dorm.getDormNum() - 1);
			dormMapper.update(dorm);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}
	
	/**
	 * 修改学生同时修改对应的宿舍信息
	 * @param student
	 * @return
	 * @throws ServiceException
	 */
	@Override
    public int update(Student student) throws ServiceException {
        int count;
        try {
        	if (null != student.getPassword()) {
        		student.setPassword(AESUtil.getEncrypt(student.getPassword()));
        	}
        	if (!student.getOldDormId().equals(student.getDormId())) {
        		Dorm oldDorm = dormMapper.getById(student.getOldDormId());
        		Dorm newDorm = dormMapper.getById(student.getDormId());
        		oldDorm.setDormNum(oldDorm.getDormNum() - 1);
        		newDorm.setDormNum(newDorm.getDormNum() + 1);
        		dormMapper.update(oldDorm);
        		dormMapper.update(newDorm);
        	}
        	count = this.getBaseMapper().update(student);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(e.getMessage(), e);
        }
        return count;
    }

	/**
	 * 登录
	 * @param stuNo
	 * @param password
	 * @return
	 * @throws ServiceException
	 */
	@Override
	public Student studentLogin(String stuNo, String password)
			throws ServiceException {
		Student student;
		try {
			student = studentMapper.studentLogin(stuNo, password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return student;
	}

	@Override
	public int updatePwd(Student student) throws ServiceException {
		int count;
		try {
			count = studentMapper.update(student);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
		return count;
	}

}
