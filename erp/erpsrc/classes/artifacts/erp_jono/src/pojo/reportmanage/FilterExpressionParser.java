package pojo.reportmanage;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class FilterExpressionParser implements
		JsonDeserializer<FilterExpression> {

	public FilterExpressionParser() {
	}

	public FilterExpression deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		FilterExpression expr = null;
		if (json == null) {
			expr = null;
		} else {
			JsonElement data = json.getAsJsonObject().get("data");
			if (data.isJsonArray()) {
				FilterExpressionList exprList = new FilterExpressionList();
				Type type = new TypeToken<List<FilterExpression>>() {
				}.getType();
				exprList.data = context.deserialize(data, type);
				exprList.op = json.getAsJsonObject().get("op").getAsString();
				expr = exprList;
			} else if (data.isJsonPrimitive()) {
				FilterExpressionColOrValue exprDetail = new FilterExpressionColOrValue();
				exprDetail.data = json.getAsJsonObject().get("data")
						.getAsString().trim();
				exprDetail.op = json.getAsJsonObject().get("op").getAsString();
				if (json.getAsJsonObject().get("isCol") != null) {
					exprDetail.isCol = json.getAsJsonObject().get("isCol")
							.getAsBoolean();
				}
				expr = exprDetail;
			} else {
				expr = null;
			}
		}

		return expr;
	}
}