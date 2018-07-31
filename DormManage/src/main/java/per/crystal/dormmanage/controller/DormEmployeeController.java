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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import per.crystal.dormmanage.controller.view.JsonView;
import per.crystal.dormmanage.controller.view.Page;
import per.crystal.dormmanage.entity.DormEmployee;
import per.crystal.dormmanage.entity.PhotoBean;
import per.crystal.dormmanage.entity.User;
import per.crystal.dormmanage.exception.ServiceException;
import per.crystal.dormmanage.service.DormEmployeeService;
import per.crystal.dormmanage.util.Constants;
import per.crystal.dormmanage.util.UploadFileUtil;

/**
 * 2018/02/18
 * @author Crystal
 *
 */
@Controller
@RequestMapping("employee")
public class DormEmployeeController extends JsonView {
	
	private static final Logger log = LoggerFactory.getLogger(DormEmployeeController.class);
	
	@Autowired
	private DormEmployeeService dormEmployeeService;
	
	/**
	 * 分页
	 * @param pageNumber
	 * @param pageSize
	 * @param dormEmployee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "listByPage", method = RequestMethod.POST,
			produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String listByPage(@RequestParam("page") Integer pageNumber, @RequestParam("rows") Integer pageSize,
			DormEmployee dormEmployee) {
		Page page = new Page();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("empName", dormEmployee.getEmpName());
			map.put("buildName", dormEmployee.getBuildName());
			page.setConditions(map);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page = dormEmployeeService.listByPage(page);
			jsonView.successPack(page);
		} catch (ServiceException e) {
			log.error("DormEmployeeController listByPage is error,{pageNumber:" + pageNumber 
					+ ",pageSize:" + pageSize + "}");
			jsonView.failPack("服务器错误，请联系系统管理员");
			e.printStackTrace();
		}
		return JSON.toJSONStringWithDateFormat(jsonView, "yyyy-MM-dd");
	}
	
	/**
	 * 添加员工,含文件上传
	 * @param dormEmployee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "save", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String save(DormEmployee dormEmployee, MultipartFile picture) {
		try {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_KEY_USER);
			dormEmployee.setCreater(user.getName());
			int exists = dormEmployeeService.findExists(dormEmployee.getEmpNo());
			if (exists > 0) {
				jsonView.failPack("员工号已存在");
				return JSON.toJSONString(jsonView);
			}
			PhotoBean photoBean = new PhotoBean();
			UploadFileUtil.uploadPhoto(picture, photoBean);
			dormEmployee.setPhoto(photoBean.getPhoto());
			int count = dormEmployeeService.save(dormEmployee);
			if (count > 0) {
				jsonView.successPack("", "添加成功");
			} else {
				jsonView.failPack("添加失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormEmployeeController save is error, {dormEmployee:" + dormEmployee + "}",
					e.getMessage(), e);
			e.printStackTrace();
		} 
		return JSON.toJSONString(jsonView);
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
			int count = dormEmployeeService.deleteById(id);
			if (count > 0 ) {
				jsonView.successPack("", "删除成功");
			} else {
				jsonView.failPack("删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormEmployeeController deleteById is error, {id:" + id + "}", e.getMessage(), e);
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
			int count = dormEmployeeService.batchDelete(idList);
			if (count > 0) {
				jsonView.successPack("", "批量删除成功");
			} else {
				jsonView.failPack("批量删除失败");
			}
		} catch (ServiceException e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormEmployeeController batchDelete is error, {ids:" + ids + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}

	/**
	 * 修改员工信息
	 * @param dormEmployee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "update", method = RequestMethod.POST,
		produces = MediaType.APPLICATION_JSON + ";charset=utf-8")
	public String update(DormEmployee dormEmployee, MultipartFile picture) {
		PhotoBean photoBean = new PhotoBean();
		try {
			UploadFileUtil.uploadPhoto(picture, photoBean);
			dormEmployee.setPhoto(photoBean.getPhoto());
			int count = dormEmployeeService.update(dormEmployee);
			if (count > 0) {
				jsonView.successPack("", "修改成功");
			} else {
				jsonView.failPack("修改失败");
			}
		} catch (Exception e) {
			jsonView.failPack("服务器错误，请联系系统管理员");
			log.error("DormEmployeeController update is error, {dormEmployee:" + dormEmployee + "}", e.getMessage(), e);
			e.printStackTrace();
		}
		return JSON.toJSONString(jsonView);
	}
	
}
