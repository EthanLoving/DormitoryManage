package per.crystal.dormmanage.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import per.crystal.dormmanage.entity.PhotoBean;

/**
 * 2018/02/18
 * @author Crystal
 *
 */
public class UploadFileUtil{
	
	public static void uploadPhoto(MultipartFile photo,PhotoBean photoBean) throws IllegalStateException, IOException{
		if(null == photo){
			return;
		}
		String name = IDGenerator.getPrimaryKey();
		String extension = FilenameUtils.getExtension(photo.getOriginalFilename());
		// 存放的路径
		String filePath = PropertiesUtil.getValue("photoPath");
		// UUID生成的文件名
		String fileName = name + "." + extension;
		File file = new File(filePath, fileName);
		if(!file.exists()){
			file.mkdirs();
		}
		photoBean.setPhoto(fileName);
		photo.transferTo(file);
	}
	
}
