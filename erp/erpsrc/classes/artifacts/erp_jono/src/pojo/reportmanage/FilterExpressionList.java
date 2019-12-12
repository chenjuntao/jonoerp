package pojo.reportmanage;

import java.util.List;

public class FilterExpressionList implements FilterExpression {
	String op;
	List<FilterExpression> data;

	public String getOp() {
		return op;
	}

	public void setOp(String op) {
		this.op = op;
	}

	public List<FilterExpression> getData() {
		return data;
	}

	public void setData(List<FilterExpression> data) {
		this.data = data;
	}

}
