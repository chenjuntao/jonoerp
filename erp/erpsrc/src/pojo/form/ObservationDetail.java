package pojo.form;

public class ObservationDetail {
	/**
	 * 出品编号
	 */
	private String itemId;

	/**
	 * 部分编码
	 */
	private String partId;

	/**
	 * 加工编码
	 */
	private String processId;

	/**
	 * 加工名称
	 */
	private String processedName;

	/**
	 * 操作步骤
	 */
	private int step;

	/**
	 * 操作图片
	 */
	private byte[] stepPicBlob;

	/**
	 * 操作说明
	 */
	private String stepRemark;

	private String flag;

	public ObservationDetail(String itemId, String partId, String processId, String processedName, int step,
			byte[] stepPicBlob, String stepRemark, String flag) {
		super();
		this.itemId = itemId;
		this.partId = partId;
		this.processId = processId;
		this.processedName = processedName;
		this.step = step;
		this.stepPicBlob = stepPicBlob;
		this.stepRemark = stepRemark;
		this.flag = flag;
	}

	public ObservationDetail() {
		super();
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPartId() {
		return partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessedName() {
		return processedName;
	}

	public void setProcessedName(String processedName) {
		this.processedName = processedName;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public byte[] getStepPicBlob() {
		return stepPicBlob;
	}

	public void setStepPicBlob(byte[] stepPicBlob) {
		this.stepPicBlob = stepPicBlob;
	}

	public String getStepRemark() {
		return stepRemark;
	}

	public void setStepRemark(String stepRemark) {
		this.stepRemark = stepRemark;
	}

}
