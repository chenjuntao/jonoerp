/*@lineinfo:filename=TagHeaderBean*//*@lineinfo:user-code*//*@lineinfo:1^1*//**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Jun 01 10:51:52 CST 2016 by pw
 * Last edited on Wed Jun 01 10:51:52 CST 2016 by pw
 * 
 * comment: 标签头表
 */
package logic.store;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.log4j.Logger;

import pojo.store.TagHeader;
import sqlj.runtime.ref.DefaultContext;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.acl.NoPrivilegeException;

public class TagHeaderBean extends ConnectionPool {

	public static Logger log = Logger.getLogger(TagHeaderBean.class);
	
	/*@lineinfo:generated-code*//*@lineinfo:35^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class HeaderIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public HeaderIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    rownumberNdx = findColumn("rownumber");
    tag_idNdx = findColumn("tag_id");
    tag_nameNdx = findColumn("tag_name");
    type_codeNdx = findColumn("type_code");
    tag_noteNdx = findColumn("tag_note");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
  public String tag_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_idNdx);
  }
  private int tag_idNdx;
  public String tag_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_nameNdx);
  }
  private int tag_nameNdx;
  public String type_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(type_codeNdx);
  }
  private int type_codeNdx;
  public String tag_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_noteNdx);
  }
  private int tag_noteNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:41^3*/
	
	
	public int saveEntity(TagHeader header)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		String tagId = header.getTagId();
		String tagName = header.getTagName();
		String typeCode = header.getTypeCode();
		String tagNote = header.getTagNote();

		/*@lineinfo:generated-code*//*@lineinfo:53^3*/

//  ************************************************************
//  #sql [myCtx] { INSERT INTO :Com_("D_T2_TAG_HEADER")   
//  				(TAG_ID, TAG_NAME, TAG_NOTE,TYPE_CODE) 
//  			VALUES (:tagId, :tagName,:tagNote, :typeCode)
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_HEADER");
  String __sJT_2 = tagId;
  String __sJT_3 = tagName;
  String __sJT_4 = tagNote;
  String __sJT_5 = typeCode;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagHeaderBean_SJProfileKeys.getKey(0), 0);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      __sJT_stmt.setString(4, __sJT_4);
      __sJT_stmt.setString(5, __sJT_5);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:57^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	public List<TagHeader> query(String type) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		HeaderIter headerIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:68^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				ROWNUM rownumber,
//  			    h.*
//  			    FROM
//  			    	:Com_("D_T2_TAG_HEADER")  h
//  			    ORDER BY
//  			        1 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_HEADER");
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagHeaderBean_SJProfileKeys.getKey(0), 1);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:75^13*/
		
		List<TagHeader> headerLst = processIter(headerIter);
		headerIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	/*@lineinfo:generated-code*//*@lineinfo:83^2*/

//  ************************************************************
//  SQLJ iterator declaration:
//  ************************************************************

private class CheckedIter 
extends sqlj.runtime.ref.ResultSetIterImpl
implements sqlj.runtime.NamedIterator
{
  public CheckedIter(sqlj.runtime.profile.RTResultSet resultSet) 
    throws java.sql.SQLException 
  {
    super(resultSet);
    rownumberNdx = findColumn("rownumber");
    tag_idNdx = findColumn("tag_id");
    tag_nameNdx = findColumn("tag_name");
    type_codeNdx = findColumn("type_code");
    tag_noteNdx = findColumn("tag_note");
    checkedNdx = findColumn("checked");
  }
  public String rownumber() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(rownumberNdx);
  }
  private int rownumberNdx;
  public String tag_id() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_idNdx);
  }
  private int tag_idNdx;
  public String tag_name() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_nameNdx);
  }
  private int tag_nameNdx;
  public String type_code() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(type_codeNdx);
  }
  private int type_codeNdx;
  public String tag_note() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(tag_noteNdx);
  }
  private int tag_noteNdx;
  public String checked() 
    throws java.sql.SQLException 
  {
    return resultSet.getString(checkedNdx);
  }
  private int checkedNdx;
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:90^3*/
	
	public List<TagHeader> queryCheckedTag(String itemId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		
		CheckedIter checkedIter = null;
		/*@lineinfo:generated-code*//*@lineinfo:97^3*/

//  ************************************************************
//  #sql [myCtx] checkedIter = { SELECT
//  				ROWNUM rownumber,
//  			    h.*,
//  			    DECODE(d.CODE,NULL,'N','Y') checked
//  			FROM
//  				:Com_("D_T2_TAG_HEADER") h 
//  			LEFT JOIN
//  				:Com_("D_T2_TAG_DETAIL") d 
//  			ON
//  			    h.TAG_ID = d.TAG_ID
//  			AND d.CODE = :itemId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_HEADER");
  String __sJT_2 = Com_("D_T2_TAG_DETAIL");
  String __sJT_3 = itemId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagHeaderBean_SJProfileKeys.getKey(0), 2);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_stmt.setString(3, __sJT_3);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      checkedIter = new CheckedIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:108^24*/
		
		List<TagHeader> headerLst = new ArrayList<TagHeader>();
		while(checkedIter.next()) {
			TagHeader header = new TagHeader();
			header.setTagId(checkedIter.tag_id());
			header.setTagName(checkedIter.tag_name());
			header.setTypeCode(checkedIter.type_code());
			header.setTagNote(checkedIter.tag_note());
			header.setRownumber(checkedIter.rownumber());
			header.setChecked(checkedIter.checked());
			headerLst.add(header);
		}
		checkedIter.close();
		closeDefaultContext(myCtx);
		return headerLst;
	}
	
	public TagHeader queryByTagId(String tagId) 
			throws NoPrivilegeException,SQLException,NoConnection {
		DefaultContext myCtx = getDefaultContext();
		TagHeader header = new TagHeader();
		HeaderIter headerIter = null;
		
		/*@lineinfo:generated-code*//*@lineinfo:132^3*/

//  ************************************************************
//  #sql [myCtx] headerIter = { SELECT
//  				ROWNUM rownumber,
//  			    h.*
//  			    FROM
//  			    	:Com_("D_T2_TAG_HEADER")  h
//  			    WHERE
//  			        h.TAG_ID = :tagId };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_HEADER");
  String __sJT_2 = tagId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagHeaderBean_SJProfileKeys.getKey(0), 3);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      sqlj.runtime.profile.RTResultSet __sJT_result = __sJT_execCtx.executeQuery();
      headerIter = new HeaderIter(__sJT_result);
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:139^29*/
		
		if(headerIter.next()) {
			header.setTagId(headerIter.tag_id());
			header.setTagName(headerIter.tag_name());
			header.setTypeCode(headerIter.type_code());
			header.setTagNote(headerIter.tag_note());
		}
		
		headerIter.close();
		closeDefaultContext(myCtx);
		return header;
	}
	
	public int deleteEntity(String tagId)
		throws NoPrivilegeException, SQLException, NoConnection {
		DefaultContext myCtx = getDefaultContext();

		/*@lineinfo:generated-code*//*@lineinfo:157^3*/

//  ************************************************************
//  #sql [myCtx] { DELETE
//  			FROM
//  				:Com_("D_T2_TAG_HEADER")  h
//  			WHERE
//  				h.TAG_ID = :tagId
//  		 };
//  ************************************************************

{
  sqlj.runtime.ConnectionContext __sJT_connCtx = myCtx;
  if (__sJT_connCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_CONN_CTX();
  sqlj.runtime.ExecutionContext __sJT_execCtx = __sJT_connCtx.getExecutionContext();
  if (__sJT_execCtx == null) sqlj.runtime.error.RuntimeRefErrors.raise_NULL_EXEC_CTX();
  String __sJT_1 = Com_("D_T2_TAG_HEADER");
  String __sJT_2 = tagId;
  synchronized (__sJT_execCtx) {
    sqlj.runtime.profile.RTStatement __sJT_stmt = __sJT_execCtx.registerStatement(__sJT_connCtx, TagHeaderBean_SJProfileKeys.getKey(0), 4);
    try 
    {
      __sJT_stmt.setString(1, __sJT_1);
      __sJT_stmt.setString(2, __sJT_2);
      __sJT_execCtx.executeUpdate();
    }
    finally 
    {
      __sJT_execCtx.releaseStatement();
    }
  }
}


//  ************************************************************

/*@lineinfo:user-code*//*@lineinfo:163^3*/
		closeDefaultContext(myCtx);
		
		return 1;
	}
	
	
	private List<TagHeader> processIter(HeaderIter headerIter) 
			throws SQLException {
		List<TagHeader> headerLst = new ArrayList<TagHeader>();
		while(headerIter.next()) {
			TagHeader header = new TagHeader();
			header.setTagId(headerIter.tag_id());
			header.setTagName(headerIter.tag_name());
			header.setTypeCode(headerIter.type_code());
			header.setTagNote(headerIter.tag_note());
			header.setRownumber(headerIter.rownumber());
			headerLst.add(header);
		}
		return headerLst;
	}
	
}/*@lineinfo:generated-code*/class TagHeaderBean_SJProfileKeys 
{
  private static TagHeaderBean_SJProfileKeys inst = null;
  public static java.lang.Object getKey(int keyNum) 
    throws java.sql.SQLException 
  {
    if (inst == null)
    {
      inst = new TagHeaderBean_SJProfileKeys();
    }
    return inst.keys[keyNum];
  }
  private final sqlj.runtime.profile.Loader loader = sqlj.runtime.RuntimeContext.getRuntime().getLoaderForClass(getClass());
  private java.lang.Object[] keys;
  private TagHeaderBean_SJProfileKeys() 
    throws java.sql.SQLException 
  {
    keys = new java.lang.Object[1];
    keys[0] = DefaultContext.getProfileKey(loader, "logic.store.TagHeaderBean_SJProfile0");
  }
}
