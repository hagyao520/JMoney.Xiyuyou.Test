package com.jmoney.xiyuyou.handler;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;

import com.jmoney.xiyuyou.base.TestStep;
import com.jmoney.xiyuyou.util.DBHelper;
import com.jmoney.xiyuyou.util.SeleniumUtil;

public class DBActionHandler {
	
	public void dbInsert(TestStep step){
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.insert(step.getValue());
		if(n > 0){	
		}
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbInsertm(TestStep step){
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.insert1(step.getValue());
		if(n > 0){	
		}
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbDelete(TestStep step){
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.delete(step.getValue());
		if(n > 0){	
		}
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbDeletem(TestStep step){
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		int n = DBHelper.delete1(step.getValue());
		if(n > 0){	
		}
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbUpdate(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		String sql = step.getValue();
//		System.err.println("Query-update "+sql);
		int n = DBHelper.update(sql);
		if(n > 0){
//			System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
//			Reporter.log(base.getDesc());
		}	
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbUpdatem(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		String sql = step.getValue();
//		System.err.println("Query-update "+sql);
		int n = DBHelper.update1(sql);
		if(n > 0){
//			System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
//			Reporter.log(base.getDesc());
		}	
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbQuery(TestStep step) throws Exception{
		if(StringUtils.isBlank(step.getDetails().get("key")))
			throw new Exception("数据库查询务必设置保存结果的键值，供后续操作使用，例子为details='key:credit'！");
		String sql = step.getValue();
//		System.err.println("Query-sql "+sql);
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		List<Map<String, Object>> st = DBHelper.query(sql);
		SeleniumUtil.localmap.put(step.getDetails().get("key").toUpperCase(), st);
//		System.out.println("DBAction: "+AppiumUtil.localmap.toString());
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbQuerym(TestStep step) throws Exception{
		if(StringUtils.isBlank(step.getDetails().get("key")))
			throw new Exception("数据库查询务必设置保存结果的键值，供后续操作使用，例子为details='key:credit'！");
		String sql = step.getValue();
//		System.err.println("Query-sql "+sql);
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		List<Map<String, Object>> st = DBHelper.query1(sql);
		SeleniumUtil.localmap.put(step.getDetails().get("key").toUpperCase(), st);
//		System.out.println("DBAction: "+SeleniumUtil.localmap.toString());
		System.out.println("『正常测试』开始执行: <成功记录到本地List列表，" +SeleniumUtil.localmap.toString() + ">");
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbQuerymssh(TestStep step) throws Exception{
		if(StringUtils.isBlank(step.getDetails().get("key")))
			throw new Exception("数据库查询务必设置保存结果的键值，供后续操作使用，例子为details='key:credit'！");
		String sql = step.getValue();
//		System.err.println("Query-sql "+sql);
		System.out.println("『正常测试』开始执行: "+ step.getValue() + " <" +step.getDesc() + ">");
		List<Map<String, Object>> st = DBHelper.query2(sql);
		SeleniumUtil.localmap.put(step.getDetails().get("key").toUpperCase(), st);
//		System.out.println("DBAction: "+SeleniumUtil.localmap.toString());
		System.out.println("『正常测试』开始执行: <成功记录到本地List列表，" +SeleniumUtil.localmap.toString() + ">");
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbProcedure(TestStep step) throws Exception{
		String params = step.getValue();
		System.out.println("『正常测试』开始执行: "+ step.getDetails().get("prc_name") + params + " <" +step.getDesc() + ">");
		int n =DBHelper.procedure(step.getDetails().get("prc_name"),params,null);
		if(n > 0){	
		}
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
	
	public void dbProcedurem(TestStep step) throws Exception{
		String params = step.getValue();
		System.out.println("『正常测试』开始执行: "+ step.getDetails().get("prc_name") + params + " <" +step.getDesc() + ">");
		int n =DBHelper.procedure1(step.getDetails().get("prc_name"),params,null);
		if(n > 0){	
		}
		step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
	}
}
