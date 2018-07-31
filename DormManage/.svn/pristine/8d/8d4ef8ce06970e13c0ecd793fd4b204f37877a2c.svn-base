package per.crystal.dormmanage.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import per.crystal.dormmanage.controller.view.JsonView;
import per.crystal.dormmanage.controller.view.Page;
import per.crystal.dormmanage.entity.Dorm;
import per.crystal.dormmanage.entity.Student;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.DormService;
import per.crystal.dormmanage.service.StudentService;
import per.crystal.dormmanage.util.AESUtil;
import per.crystal.dormmanage.util.Constants;

/**
 * 2018/02/18
 * @author Crystal
 *
 */
@Controller
@RequestMapping("student")
public class StudentController extends JsonView {
	
	private static final Logger log = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private DormService dormService;
	
	/**
	 * 学生登录
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String login(@RequestBody Student student) {
		try {
			student.setPassword(AESUtil.getEncrypt(student.getPassword()));
			student = studentService.studentLogin(student.getStuNo(), student.getPassword());
			if (null != student) {
				jsonView.successPack("success");
				request.getSession().setAttribute(Constants.SESSION_KEY_USER, student);
				request.getSession().setAttribute("flag", 2);
			} else {
				jsonView.failPack("用户名或密码错误");
				request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("StudentController login is error, {student:" + student + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd");
	}
	
	/**
	 * 退出系统
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "loginOut", method = RequestMethod.GET)
	public String loginOut() {
		try {
			request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
			request.getSession().removeAttribute("flag");
			jsonView.successPack("success");
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 添加学生
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String save(@RequestBody Student student) {
		try {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			student.setCreater(user.getName());
			int exists = studentService.findExists(student.getStuNo());
			if (exists > 0) {
				jsonView.failPack("学号已存在");
				return JSON.toJSONString(jsonView);
			}
			Dorm dorm = dormService.getById(student.getDormId());
			if (dorm.getDormNum() < dorm.getDormCapacity()) {
				int count = studentService.save(student);
				if (count > 0) {
					jsonView.successPack("", "添加成功");
				} else {
					jsonView.failPack("添加失败");
				}
			} else {
				jsonView.failPack("该宿舍已经住满");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("StudentController save is error, {student:" + student + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			Student student) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("stuNo", student.getStuNo());
			map.put("stuName", student.getStuName());
			map.put("className", student.getClassName());
			map.put("dormNo", student.getDormNo());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = studentService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("StudentController listByPage is error,{pageNumber:" + pageNumber 
					+ ",pageSize:" + pageSize + "}");
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd");
	}
	
	/**
	 * 根据id删除
	 * @param stuId
	 * @param dormId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteById/{stuId}/{dormId}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String deleteById(@PathVariable String stuId, @PathVariable String dormId) {
		try {
			int count = studentService.deleteById(stuId, dormId);
			if (count > 0 ) {
				jsonView.successPack("", "删除成功");
			} else {
				jsonView.failPack("删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("StudentController deleteById is error, {stuId:" + stuId + ",dormId:" 
			+ dormId + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 修改密码
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updatePwd", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String changePassword(@RequestBody Student student) {
		try {
			student.setOldPassword(AESUtil.getEncrypt(student.getOldPassword()));
			Student s = studentService.studentLogin(student.getStuNo(), student.getOldPassword());
			if (null == s) {
				jsonView.failPack("原密码输入错误");
				return JSON.toJSONString(jsonView);
			}
			student.setPassword(AESUtil.getEncrypt(student.getPassword()));
			int count = studentService.updatePwd(student);
			if (count > 0) {
				request.getSession().removeAttribute("flag");
				request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
				jsonView.successPack("", "修改成功,请重新登录");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 修改学生信息
	 * @param student
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(@RequestBody Student student) {
		try {
			if (!student.getOldDormId().equals(student.getDormId())) {
				Dorm dorm = dormService.getById(student.getDormId());
				if (dorm.getDormNum() >= dorm.getDormCapacity()) {
					jsonView.failPack("调换的宿舍已住满");
					return JSON.toJSONString(jsonView);
				} 
			}
			int count = studentService.update(student);
			if (count > 0) {
				jsonView.successPack("", "修改成功");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("StudentController update is error, {student:" + student + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}

}
