package per.crystal.dormmanage.controller;

import java.util.HashMap;
import java.util.List;
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
import per.crystal.dormmanage.entity.Student;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.UserService;
import per.crystal.dormmanage.util.AESUtil;
import per.crystal.dormmanage.util.Constants;


/**
 * 2018/02/17
 * @author Crystal
 *
 */
@Controller
@RequestMapping("user")
public class UserController extends JsonView {

	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String login(@RequestBody User user) {
		try {
			user.setPassword(AESUtil.getEncrypt(user.getPassword()));
			Integer flag = user.getFlag();
			user = userService.userLogin(user.getUsername(), user.getPassword());
			if (null != user) {
				if (flag.equals(user.getFlag())) {
					jsonView.successPack("success");
					request.getSession().setAttribute(Constants.SESSION_KEY_USER, user);
					request.getSession().setAttribute("flag", user.getFlag());
				} else {
					jsonView.failPack("请选择正确的角色");
				}
			} else {
				jsonView.failPack("用户名或密码错误");
				request.getSession().removeAttribute(Constants.SESSION_KEY_USER);
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("UserController login is error, {user:" + user + "}", e.getMessage(), e);
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
	 * 添加用户
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String save(@RequestBody User user) {
		try {
			User userSession = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			user.setCreater(userSession.getName());
			int exists = userService.findExists(user.getUsername());
			if (exists > 0) {
				jsonView.failPack("账号已存在");
				return JSON.toJSONString(jsonView);
			}
			user.setPassword(AESUtil.getEncrypt(user.getPassword()));
			int count = userService.save(user);
			if (count > 0) {
				jsonView.successPack("", "添加成功");
			} else {
				jsonView.failPack("添加失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("UserController save is error, {user:" + user + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			User user) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("username", user.getUsername());
			map.put("flag", user.getFlag());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = userService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("UserController listByPage is error,{pageNumber:" + pageNumber 
					+ ",pageSize:" + pageSize + "}");
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd");
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "deleteById/{id}", method = RequestMethod.DELETE,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String deleteById(@PathVariable String id) {
		try {
			int count = userService.deleteById(id);
			if (count > 0 ) {
				jsonView.successPack("", "删除成功");
			} else {
				jsonView.failPack("删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("UserController deleteById is error, {id:" + id + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "batchDelete", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String batchDelete(String ids) {
		List<String> idList = JSON.parseArray(ids, String.class);
		try {
			int count = userService.batchDelete(idList);
			if (count > 0) {
				jsonView.successPack("", "批量删除成功");
			} else {
				jsonView.failPack("批量删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("UserController batchDelete is error, {ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
	/**
	 * 修改密码
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "updatePwd", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String changePassword(@RequestBody User user) {
		try {
			user.setOldPassword(AESUtil.getEncrypt(user.getOldPassword()));
			User u = userService.userLogin(user.getUsername(), user.getOldPassword());
			if (null == u) {
				jsonView.failPack("原密码输入错误");
				return JSON.toJSONString(jsonView);
			}
			user.setPassword(AESUtil.getEncrypt(user.getPassword()));
			int count = userService.updatePwd(user);
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
	 * 修改用户信息
	 * @param user
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(@RequestBody User user) {
		try {
			if (null != user.getPassword()){
				user.setPassword(AESUtil.getEncrypt(user.getPassword()));
			}
			int count = userService.update(user);
			if (count > 0) {
				jsonView.successPack("", "修改成功");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("UserController update is error, {user:" + user + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
}
