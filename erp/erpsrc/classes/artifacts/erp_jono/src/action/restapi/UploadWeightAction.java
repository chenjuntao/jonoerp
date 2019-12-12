/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月22日 by cjt
 * Last edited on 2016年3月22日 by cjt
 */
package action.restapi;

import action.common.BaseAction;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.tanry.framework.acl.NoPrivilegeException;
import logic.NoConnection;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import service.restapi.UploadWeightService;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 说明：上传重量以及图片
 */

public class UploadWeightAction extends BaseAction
{

	public static Logger log = Logger.getLogger("UpLoadWeightAction");

  private UploadWeightService uploadWeightService;

	public void setUploadWeightService(UploadWeightService uploadWeightService) {
		this.uploadWeightService = uploadWeightService;


	}

	private String myid;
	public void setMyid(String myid) {
		this.myid = myid;
	}

	private String num;
	public void setNum(String num) {
		this.num = num;
	}

	private String pic;
	public void setPic(String pic) {
		this.pic = pic;
	}

	public void uploadWeight() throws NoPrivilegeException, NoConnection, SQLException, IOException {
//		String strImg = getImageStr(pic);

		// 获取Body中的json字符串进行解析--------------------------------------------------------------
		// 获取Request对象
		HttpServletRequest request = ServletActionContext.getRequest();
		StringBuffer sb = new StringBuffer();
		try {
			// json格式字符串
			String jsonStr = "";
			// 获取application/json格式数据，返回字符流
			BufferedReader reader = request.getReader();
			// 对字符流进行解析
			while ((jsonStr = reader.readLine()) != null) {
				sb.append(jsonStr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 将json字符串（jsonStr）-->json对象（JSONObject）
		JSONObject jo = JSONObject.fromObject(sb.toString()).getJSONObject("data");
		String logis_no = jo.getString("logis_no"); //单号
		String weight = jo.getString("weight");   //重量
		String file = jo.getString("file");         //图片base64


		log.debug("---------------------get new weight:" + logis_no + "," + weight);
		//----------------------------------------------------------------------------


		String realPath= ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(realPath);
		File filepath = new File(realPath);
		if (!filepath.exists())
			filepath.mkdirs();
		// 文件保存路径
//                String savePath = PICTUREURL + filename1;
		if(file.indexOf("jpeg") != -1) {
			//base64字串中有jpeg字串，这是一个4个字的，而我这里是把base64字串的指定位置的字串来作为上传
			//文件类型的判断依据，所以在大部分都是三个字的文件类型下就得把jpeg改成jpg了
			file = file.replaceFirst("jpeg", "jpg");
		}
//      String upNames = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + buf.toString()+"."+pic.substring(11, 14);
		String upName =System.currentTimeMillis()+"."+file.substring(11, 14);
		String iconBase64 = file.substring(22);
		FileOutputStream out;
		String s="";
		try {
			byte[] buffer = new BASE64Decoder().decodeBuffer(iconBase64);
			s=realPath+"/"+upName;
			System.out.println(s);
			out = new FileOutputStream(realPath+"/"+upName);
			out.write(buffer);
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		Random r = new Random();
//		StringBuffer buf = new StringBuffer();
//		for (int x = 0; x < 3; x++) {           //循环取得三个不大于10的随机整数
//			buf.append(r.nextInt(10));
//		}
//		String pics = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + buf.toString() + pic.substring(pic.lastIndexOf("."));
//		System.out.println(pics);
//		copyFile(pic,realPath+"/"+pics);

		int n = uploadWeightService.saveWeight(logis_no, weight, s);

		JSONObject meta = new JSONObject();
		if(n>0) {
			meta.put("code", "0");
			meta.put("msg", "上传数据成功！");
			meta.put("error", "无");
		}else{
			meta.put("code", "-1");
			meta.put("msg", "上传数据失败！");
			meta.put("error", String.valueOf(n));
		}
		String actionName = ActionContext.getContext().getName();
		meta.put("request_url", actionName);
		JSONObject response = new JSONObject();
		response.put("logistic_no", logis_no);
		JSONObject result = new JSONObject();
		result.put("meta", meta);
		result.put("response", response);
		this.outJS(result.toString());
	}

	public static void copyFile(String pic,String path) {
		try {
			InputStream input = new FileInputStream(pic);
			OutputStream output = new FileOutputStream(path);
			byte[] buf = new byte[1024];
			int bytesRead;
			while ((bytesRead = input.read(buf)) > 0) {
				output.write(buf, 0, bytesRead);
			}
			input.close();
			output.close();
		}catch (IOException e){
			e.printStackTrace();
		}
	}


	/**
	 * 查询
	 *
	 * @param request
	 * @return
	 */
//	public String tests(HttpServletRequest request) {
//		List<Test> list = uploadWeightService.selectTest();
//		String path = "D:\\leafsd.jpg";
//		for (int i = 0; i < list.size(); i++) {
//			String pic = list.get(i).getPic();
//			generateImage(pic, path);
//			System.out.println(pic);
//		}
//		String s = JSON.toJSONString(list);
//		JSONObject obj = new com.alibaba.fastjson.JSONObject();
//		obj.put("list", list);
//		obj.put("path", path);
//		return obj.toJSONString();
//	}

	/**
	 * 解密
	 *
	 * @param imgStr
	 * @param path
	 * @return
	 */
	public static boolean generateImage(String imgStr, String path) {
		if (imgStr == null) { // 图像数据为空
			return false;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(imgStr);
			System.out.println(b);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(path);
			out.write(b);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * 加密
	 *
	 * @param imgFile
	 * @return
	 */
	public static String getImageStr(String imgFile) {
		InputStream inputStream = null;
		byte[] data = null;
		try {
			inputStream = new FileInputStream(imgFile);
			data = new byte[inputStream.available()];
			inputStream.read(data);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 加密
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}
}
