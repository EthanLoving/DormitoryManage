package per.crystal.dormmanage.mapper;

import org.apache.ibatis.annotations.Param;

import per.crystal.dormmanage.entity.Student;

/**
 * @author Crystal
 * 2018/02/10
 */
public interface StudentMapper extends BaseMapper<Student> {

	/**
	 * 学生登录
	 * @param stuNo
	 * @param password
	 * @return
	 */
	Student studentLogin(@Param("stuNo") String stuNo, @Param("password") String password);
	
}
