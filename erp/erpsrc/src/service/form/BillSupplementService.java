package service.form;

import java.sql.SQLException;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.form.CheckDetailBean;
import logic.form.StorageInOutBean;
import pojo.form.CheckDetail;
import pojo.form.StorageInOut;

import com.tanry.framework.acl.NoPrivilegeException;

public class BillSupplementService {
	public boolean isMeet(boolean isSupplement, StorageInOut condition) {
		if (isSupplement && condition != null) {
			return true;
		}
		return false;
	}

	/**
	 * 如果是补单并且有盘点单的话，一是要修改进出入库存表，还要修改一个盘点明细表
	 * 
	 * @param isSupplement
	 * @param condition
	 * @param storageInOutBean
	 * @param checkDetailBean
	 * @param resultCount
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void alter(boolean isSupplement, StorageInOut condition,
			StorageInOutBean storageInOutBean, CheckDetailBean checkDetailBean,
			Double resultCount) throws NoPrivilegeException, SQLException,
			NoConnection {
		condition.setOrgiCount(resultCount);
		// 盘点的结存数不会改变
		Double diff = condition.getResultCount() - condition.getOrgiCount();
		condition.setItemCountIn(diff > 0 ? diff : 0);
		condition.setItemCountOut(diff < 0 ? -diff : 0);
		storageInOutBean.updateEntity(condition);
		CheckDetail checkDetail = new CheckDetail();
		checkDetail.setFormId(condition.getFormId());
		checkDetail.setItemId(condition.getItemId());
		checkDetail.setTheoryCount(resultCount);
		checkDetailBean.updateSupplement(checkDetail);

	}

}
