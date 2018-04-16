package com.jmoney.xiyuyou.handler;

import org.openqa.selenium.WebElement;

import com.jmoney.xiyuyou.base.TestStep;
import com.jmoney.xiyuyou.util.SeleniumUtil;

/**
 * 点击动作处理类
 */
public class ClickActionHandler {
	
	/**
	 * 点击
	 * @param map
	 * @param step
	 * @throws Exception 
	 */
	public void click(TestStep step) throws Exception{
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		WebElement e = SeleniumUtil.getElement(step);
		e.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
	}
	
	public void clickOk(TestStep step){
		step.getDriver().switchTo().alert().accept();
	}
	
	public void clickCancel(TestStep step){
		step.getDriver().switchTo().alert().dismiss();
	}
	
	public void clickText(TestStep step) {
		step.getDriver().switchTo().alert().sendKeys(step.getValue());
    }
}
