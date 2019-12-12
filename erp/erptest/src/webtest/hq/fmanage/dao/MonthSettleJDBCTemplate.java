package webtest.hq.fmanage.dao;

import java.util.Date;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class MonthSettleJDBCTemplate {
	@SuppressWarnings("unused")
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void updateAllBranchDate(Date businessDate) {
		String sql = "update D_T2_BRANCH set BUSINESS_DATE = ? ";
		jdbcTemplateObject.update(sql, businessDate);

		return;
	}

	public void deleteAllMonthSettleRecord() {
		String sql = " delete from D_T3_MONTHLY_SETTLE_RECORD ";
		jdbcTemplateObject.execute(sql);
		return;
	}

}
