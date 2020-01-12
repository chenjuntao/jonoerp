package action.common;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logic.NoConnection;
import logic.module.setting.SysUserBean;
import logic.module.setting.UserToBranchBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.reportmanage.ReportManageService;

import com.tanry.business.module.setting.user.pojo.SysUser;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

import dao.restapi.CompanyDao;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SysUserBean sysUserBean;
	private UserToBranchBean userToBranchBean;
	private BranchStorageBean branchStorageBean;
	private BranchBean branchBean;

	private ReportManageService reportManageService;
	private String username;
	private String password;
	private String branchId;
	private String companyId;

	@Resource()
	private CompanyDao companyDao;

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void doCheck() throws Exception {
		JSONObject result = new JSONObject();

		String msg = "ok";
		if (checkCompanyId()) {
			SysUser sysUser = sysUserBean.queryById(username);
			if (sysUser != null) {
				if (TextUtil.MD5(password).equals(sysUser.getPassword())) {
					boolean isMulti = userToBranchBean.isMulti(username);
					List<Branch> branchLst = userToBranchBean.queryBranch(username);
					// 如果不属于多个部门
					if (!isMulti) {
						Branch branch = branchLst.get(0);
						branchId = branch.getBranchId();
						String branchType = branch.getBranchType();
						sysUser.setBranchId(branchId);
						Date businessDate = reportManageService.getBusinessByBranchId(branchId);
						if ("HEADQUATER".equals(branchType) || "SUPPLIER".equals(branchType)) {
							businessDate = DateTimeUtil.getNow();
						}

						BranchStorage branchStorage = branchStorageBean.queryMainStore(branchId);
						// 成功登录
						getSessionMap().put("LOGIN_TYPE", 1);
						getSessionMap().put("loginUser", sysUser);
						getSessionMap().put("businessDate", DateTimeUtil.getDateStr(businessDate));
						getSessionMap().put("branchStorage", branchStorage);
						getSessionMap().put("branchType", branch.getBranchType());
						getSessionMap().put("branchName", branch.getBranchName());

					} else {
						// 需要在前台选择部门后再登录，临时存储用户信息
						getSessionMap().put("tempUser", sysUser);
						result.put("branchLst", branchLst);
						result.put("isMulti", true);
					}
				} else {
					msg = "密码不正确!";
				}
			} else {
				msg = "用户名不存在!";
			}
		} else {
			msg = "企业编号不正确!";
		}
		result.put("msg", msg);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 前台选择部门后再登录
	 */
	public void selectDept() throws Exception {
		SysUser sysUser = (SysUser) getSessionMap().get("tempUser");
		sysUser.setBranchId(branchId);
		Date businessDate = reportManageService.getBusinessByBranchId(branchId);
		BranchStorage branchStorage = branchStorageBean.queryMainStore(branchId);
		Branch branch = branchBean.GetBranchById(branchId);
		getSessionMap().put("LOGIN_TYPE", 1);
		getSessionMap().put("loginUser", sysUser);
		getSessionMap().put("businessDate", DateTimeUtil.getDateStr(businessDate));
		getSessionMap().put("branchStorage", branchStorage);
		getSessionMap().put("branchType", branch.getBranchType());
		getSessionMap().put("branchName", "[" + branchId + "]" + branch.getBranchName());
		OperateLogUtil.record("登录");

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String logout() throws Exception {
		OperateLogUtil.record("登出");

		getSessionMap().remove("LOGIN_TYPE");
		getSessionMap().remove("loginUser");
		getSessionMap().remove("businessDate");
		getSessionMap().remove("branchStorage");
		return SUCCESS;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setSysUserBean(SysUserBean sysUserBean) throws NoPrivilegeException {
		this.sysUserBean = sysUserBean;
	}

	public void setUserToBranchBean(UserToBranchBean userToBranchBean) throws NoPrivilegeException {
		this.userToBranchBean = userToBranchBean;
	}

	public void setReportManageService(ReportManageService reportManageService) {
		this.reportManageService = reportManageService;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) throws NoPrivilegeException {
		this.branchStorageBean = branchStorageBean;
	}

	public void setBranchBean(BranchBean branchBean) throws NoPrivilegeException {
		this.branchBean = branchBean;
	}

	public void setCompanyId(String companyId) {
		// 如果没有填，则企业编号默认为"test"
		if (companyId.equals("")) {
			companyId = "jono";
			// System.out.println("CompanyId没有填写，设置为默认值jono-------------------------");
		}
		this.companyId = companyId;
	}

	// 检验企业ID是否存在 (by cjt,2016.4.15)
//	public boolean checkCompanyId() {
//		try {
//			List<Map> companyLst = companyDao.listCompany();
//			for (int i = 0; i < companyLst.size(); i++) {
//				if (companyLst.get(i).get("companyId").equals(companyId)) {
//					// 这里的companyId应设置成DB_NAME
//					getSessionMap().put("companyId", companyLst.get(i).get("dbName"));
//					getSessionMap().put("companyName", companyLst.get(i).get("companyName"));
//					// System.out.println("正在设置CompanyId-------------------------------------"
//					// + companyId);
//
//					return true;
//				}
//			}
//		} catch (NoPrivilegeException | SQLException | NoConnection e) {
//			e.printStackTrace();
//		}
//		return false;
//	}

	// 检验企业ID是否存在 (by cjt,2016.4.15)
	public boolean checkCompanyId() {
					// 这里的companyId应设置成DB_NAME
					getSessionMap().put("companyId", "jono");
					getSessionMap().put("companyName", "jono");
					// System.out.println("正在设置CompanyId-------------------------------------"
					// + companyId);

					return true;
	}
}