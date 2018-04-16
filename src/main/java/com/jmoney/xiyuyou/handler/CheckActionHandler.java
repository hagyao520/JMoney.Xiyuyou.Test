package com.jmoney.xiyuyou.handler;

import java.util.List;
import java.util.Map;

import org.testng.Assert;

import com.jmoney.xiyuyou.base.TestStep;
import com.jmoney.xiyuyou.service.TestXmlParseService;
import com.jmoney.xiyuyou.util.Constants;
import com.jmoney.xiyuyou.util.SeleniumUtil;

/**
 * <br>检查操作<br/>
 *
 * @author    King
 * @date        2018年4月12日 16:40:44
 * @version 1.0
 * @since   1.0
 */
public class CheckActionHandler {
	
	/**
	 * <br>检查界面上的元素是否与预期值相等</br>
	 *
	 * @author    King
	 * @date        2018年4月12日 16:40:44
	 * @param step
	 * @throws Exception 
	 */
	public void check(TestStep step) throws Exception{
		String Actual = SeleniumUtil.getElement(step).getText();
		String Expected = SeleniumUtil.parseStringHasEls(step.getExpect());	
		String FailHint = step.getMessage();
		String CaseID = step.getCaseid();
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		checkEquals(Actual,Expected,FailHint,CaseID);
	}
	
	public void checkList(TestStep step) throws NumberFormatException, Exception{
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		String conditon = step.getDetails().get("condition");
		if(Constants.SIZE_NOT_ZERO.equals(conditon))
			listSizeNotZero(step);
		else if(Constants.FIELD.equals(conditon)) 
			fieldEquals(step);
	}
		
	@SuppressWarnings("unchecked")
	public void listSizeNotZero(TestStep step) throws Exception{
		List<Map<String,Object>>  list = (List<Map<String,Object>>)
		SeleniumUtil.parseEL(step.getDetails().get("subject"));
		
		if(list.size() == 0)
			throw new Exception(step.getMessage());
	}
	
	/**
	 * <br>检查列表中的字段值</br>
	 * 此时localmap中的值是一个List<Map<>>，所以需要提供检查的索引
	 *
	 * @author    King
	 * @date        2018年4月12日 16:40:44
	 * @param step
	 * @throws NumberFormatException
	 * @throws Exception
	 */
	public void fieldEquals(TestStep step) throws NumberFormatException, Exception{
//		System.out.println("CHECK_FILED---"+step.getDetails().get("subject"));	
		String Actual = (String)SeleniumUtil.parseStringHasEls(step.getDetails().get("subject"));
		String Expected = SeleniumUtil.parseStringHasEls(step.getExpect());
		System.out.println(Expected);	
		String FailHint = step.getMessage();
		try {
			Assert.assertEquals(Actual,Expected,FailHint);
			Thread.sleep(500);
        }
 	    catch (Error | InterruptedException e)  {
 	    	Assert.fail(FailHint +"  "+"Actual 【"+ Actual +"】"+"  "+"but found 【"+ Expected +"】");
 	    }
	}	 

	/**
	 * <br>检查预期与实际是否相等，不等则提示错误信息，并截图</br>
	 *
	 * @author    King
	 * @date        2018年4月12日 16:40:44
	 * @param Actual 
	 * @param Expected
	 * @param FailHint
	 * @param CaseID
	 */
	public void checkEquals(String Actual,String Expected,String FailHint,String CaseID){
		try {
			Assert.assertEquals(Actual,Expected,FailHint);
			Thread.sleep(500);
        }
 	    catch (Error | InterruptedException e)  {
 	    	TestXmlParseService.screenShot(CaseID);
 	    	Assert.fail(FailHint +"  "+"Actual 【"+ Actual +"】"+"  "+"but found 【"+ Expected +"】");
 	    }
	}
}
