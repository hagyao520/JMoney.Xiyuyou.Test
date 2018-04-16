package com.jmoney.xiyuyou.handler;

import java.util.concurrent.TimeUnit;
import com.jmoney.xiyuyou.base.TestStep;

/**
 * 等待动作处理类
 */
public class WaitActionHandler {
	
	/**
	 * 强制等待
	 * @param map
	 * @param step
	 */
	public void waitForced(TestStep step){
		try {
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			String waitTime = step.getValue();
			Thread.sleep(Long.valueOf(waitTime));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 隐式等待
	 * @param map
	 * @param step
	 * TODO 时间单位自定义
	 */
	public void waitImplicit(TestStep step){
		System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
		long waitTime = Long.valueOf(step.getValue());
		step.getDriver().manage().timeouts().implicitlyWait(waitTime, TimeUnit.MILLISECONDS);
	}

}
