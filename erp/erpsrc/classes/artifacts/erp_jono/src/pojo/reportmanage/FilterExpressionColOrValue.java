package pojo.reportmanage;

public class FilterExpressionColOrValue implements FilterExpression {
	public String op;
	public String data;
	public boolean isCol;

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isCol() {
		return isCol;
	}

	public void setCol(boolean isCol) {
		this.isCol = isCol;
	}

}
