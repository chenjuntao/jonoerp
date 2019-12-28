package pojo.reportmanage;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class QueryGenUtil {
	public static String getQueryStr(String inputJsonString, Map prefixMap) {
		GsonBuilder gb = new GsonBuilder();
		gb.registerTypeAdapter(FilterExpression.class,
				new FilterExpressionParser());

		Gson gson = gb.create();
		FilterExpressionList filter = (FilterExpressionList) gson.fromJson(
				inputJsonString, FilterExpression.class);

		return ReportQuery.getQueryString(filter.getData(), prefixMap);
	}

	public static String getSortStr(String str, Map prefixMap, Map columnMap,
			String collectFlag) {
		StringBuilder sb = new StringBuilder();

		Gson gson = new Gson();
		List<SortInfo> sortInfoList = gson.fromJson(str,
				new TypeToken<List<SortInfo>>() {
				}.getType());

		for (int i = 0, length = sortInfoList.size(); i < length; i++) {
			SortInfo sortInfo = sortInfoList.get(i);
			if (i == 0) {
				sb.append(" order by ");
			}

			if (columnMap.get(sortInfo.getColId()).toString().equals("String")) {
				sb.append(" NVL("
						+ prefixMap.get(sortInfo.getColId()).toString() + "."
						+ sortInfo.getColId() + ", ' ') ");
			} else if (columnMap.get(sortInfo.getColId()).toString()
					.equals("Number")) {
				if ("head".equals(collectFlag)) {
					sb.append(" NVL(sum("
							+ prefixMap.get(sortInfo.getColId()).toString()
							+ "." + sortInfo.getColId() + "), 0) ");
				} else {
					sb.append(" NVL("
							+ prefixMap.get(sortInfo.getColId()).toString()
							+ "." + sortInfo.getColId() + ", 0) ");
				}
			} else if (columnMap.get(sortInfo.getColId()).toString()
					.equals("Date")) {
				sb.append(" NVL("
						+ prefixMap.get(sortInfo.getColId()).toString() + "."
						+ sortInfo.getColId()
						+ ", TO_DATE('1900-01-01','YYYY-MM-DD')) ");
			}

			if ("true".equals(sortInfo.getDescending())) {
				sb.append(" desc ");
			} else {
				sb.append(" asc ");
			}

			if (i != length - 1) {
				sb.append(" , ");
			}
		}

		return sb.toString();
	}
}
