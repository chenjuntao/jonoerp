/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 1, 2014 by liyzh
 * Last edited on Nov 1, 2014 by liyzh
 * 
 * 说明： TODO
 */
package pojo.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Branch {
	private String branchId; // 部门编号
	private String branchName; // 部门名称
	private String queryCode; // 助记码
	private String branchType; // 部门类型（餐厅/中央工厂/物流中心）
	private String branchAddress; // 部门地址
	private String email; // 电子邮箱
	private String telephone;// 联系电话
	private String fax; // 传真
	private String remark; // 备注
	private String park; // 停车位信息
	private String tag;// 标签
	private double lon;// 经度
	private double lat;// 纬度
	private String priceType;//价格类型，用于获取原料价格，餐厅对应标准价、加盟店对就加盟价，外部订货方对应批发价或零售价
	private String contactMan;//联系人
	private Integer enabled;
	private String regionId;
	private String regionName;
	private Date businessDate;

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPark() {
		return park;
	}

	public void setPark(String park) {
		this.park = park;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public double getLon() {
		return lon;
	}

	public String getContactMan() {
		return contactMan;
	}

	public void setContactMan(String contactMan) {
		this.contactMan = contactMan;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

	public Date getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public Branch(String branchId, String branchName, String queryCode, String branchType, String branchAddress,
			String email, String telephone, String fax, String remark, String contactMan, Integer enabled,
			String regionId, String regionName, String park, String tag, double lon, double lat, Date businessDate) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.queryCode = queryCode;
		this.branchType = branchType;
		this.branchAddress = branchAddress;
		this.email = email;
		this.telephone = telephone;
		this.fax = fax;
		this.remark = remark;
		this.contactMan = contactMan;
		this.enabled = enabled;
		this.regionId = regionId;
		this.regionName = regionName;
		this.park = park;
		this.tag = tag;
		this.lon = lon;
		this.lat = lat;
		this.businessDate = businessDate;
	}

	public Branch() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchId == null) ? 0 : branchId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Branch other = (Branch) obj;
		if (branchId == null) {
			if (other.branchId != null)
				return false;
		} else if (!branchId.equals(other.branchId))
			return false;
		return true;
	}

	public static void main(String[] args) {
		List<Branch> list1 = new ArrayList<Branch>();
		List<Branch> list2 = new ArrayList<Branch>();

		list1.add(new Branch("01", "dsd", null, null, null, null, null, null, null, null, 0, null, null, null, null,
				0.0, 0.0, null));
		list1.add(new Branch("02", null, "ew", null, null, null, null, null, null, null, 0, null, null, null, null,
				0.0, 0.0, null));

		list2.add(new Branch("02", null, "fff", null, null, null, null, null, null, null, 0, "cccc", null, null, null,
				0.0, 0.0, null));

		list1.removeAll(list2);
		System.out.println(list1.get(0).getBranchId());

	}

	public void setLon(double lon) {
		this.lon = lon;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}
}
