package com.lpmas.framework.db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lpmas.framework.page.PageBean;
import com.lpmas.framework.util.BeanKit;

public class SQLiteDBExecutor extends DBExecutor {
	public <E> List<E> getRecordListResult(String sql, List<String> paramList, Class<E> clazz, PageBean pageBean,
			DBObject db) throws SQLException, Exception {
		List<E> list = new ArrayList<E>();
		String sqlSearch = sql + " limit ?, ?";
		log.info("SqliteDBExecutor SQL = {}", sqlSearch);
		PreparedStatement ps = db.getPreparedStatement(sqlSearch);

		int index = 0;
		for (index = 0; index < paramList.size(); index++) {
			ps.setObject(index + 1, paramList.get(index));
		}
		ps.setInt(index + 1, pageBean.getStartRecordNumber());
		ps.setInt(index + 2, pageBean.getEndRecordNumber() - pageBean.getStartRecordNumber());

		ResultSet rs = db.executePstmtQuery();
		while (rs.next()) {
			E bean = clazz.newInstance();
			bean = BeanKit.resultSet2Bean(rs, clazz);
			list.add(bean);
		}
		rs.close();
		return list;
	}

	public String parsePageSql(String sql, int startNumber, int endNumber) {
		return sql + " limit " + startNumber + "," + (endNumber - startNumber);
	}
}
