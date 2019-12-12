package pojo.reportmanage;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tanry.framework.util.DateTimeUtil;

public class ReportQuery {

	public static String getQueryString(List<FilterExpression> data, Map prefixMap) {
		String queryString = "";

		for (FilterExpression item1 : data) {
			FilterExpressionList fExpressionList1 = (FilterExpressionList) item1;
			String op1 = fExpressionList1.getOp();

			// 日期区间
			if ("and".equals(op1)) {
				for (FilterExpression item2 : fExpressionList1.getData()) {
					FilterExpressionList fExpressionList2 = (FilterExpressionList) item2;
					String op2 = fExpressionList2.getOp();

					for (FilterExpression item3 : fExpressionList2.getData()) {

						FilterExpressionColOrValue fColOrValue = (FilterExpressionColOrValue) item3;
						String key, value, op3 = fColOrValue.getOp();

						if (fColOrValue.isCol()) {
							key = fColOrValue.getData();

							if ("greaterEqual".equals(op2)) {
								if ("date".equals(op3)) {
									queryString += " AND to_char(" + prefixMap.get(key).toString() + "." + key
											+ ",'YYYY-MM-DD') >= ";
								}
							}

							if ("lessEqual".equals(op2)) {
								if ("date".equals(op3)) {
									queryString += " AND to_char(" + prefixMap.get(key).toString() + "." + key
											+ ",'YYYY-MM-DD') <= ";
								}
							}

						} else {
							value = fColOrValue.getData();
							if ("date".equals(op3)) {
								Date date = new Date(Long.parseLong(value));
								queryString += " '" + DateTimeUtil.getDateStr(date) + "' ";
							}
						}
					}
				}

			} else {

				// 除日期区间以外所有情况
				for (FilterExpression item2 : fExpressionList1.getData()) {
					FilterExpressionColOrValue fColOrValue = (FilterExpressionColOrValue) item2;
					String key, value, op2 = fColOrValue.getOp();

					if (fColOrValue.isCol()) {
						key = fColOrValue.getData();

						if ("equal".equals(op1)) {
							if ("date".equals(op2)) {
								queryString += " AND to_char(" + prefixMap.get(key).toString() + "." + key
										+ ",'YYYY-MM-DD') = ";
							} else {
								queryString += " and upper(" + prefixMap.get(key).toString() + "." + key + ") = ";
							}
						}

						if ("contain".equals(op1)) {
							queryString += " and upper(" + prefixMap.get(key).toString() + "." + key;
						}

						if ("greater".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += " AND " + prefixMap.get(key).toString() + "." + key + " > ";
							}
						}

						if ("less".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += " AND " + prefixMap.get(key).toString() + "." + key + " < ";
							}
						}

						if ("greaterEqual".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += " AND " + prefixMap.get(key).toString() + "." + key + " >= ";
							}
						}

						if ("lessEqual".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += " AND " + prefixMap.get(key).toString() + "." + key + " <= ";
							}
						}

					} else {
						value = fColOrValue.getData();
						if ("equal".equals(op1)) {
							if ("date".equals(op2)) {
								Date date = new Date(Long.parseLong(value));
								queryString += " '" + DateTimeUtil.getDateStr(date) + "' ";
							} else {
								queryString += "'" + value.toUpperCase() + "'";
							}
						}

						if ("contain".equals(op1)) {
							queryString += ") like '%" + value.toUpperCase() + "%' ";
						}

						if ("greater".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += value;
							}
						}

						if ("less".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += value;
							}
						}

						if ("greaterEqual".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += value;
							}
						}

						if ("lessEqual".equals(op1)) {
							if ("number".equals(op2)) {
								queryString += value;
							}
						}

					}
				}
			}

		}

		return queryString;
	}
}
