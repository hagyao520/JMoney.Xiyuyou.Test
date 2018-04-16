package com.jmoney.xiyuyou.base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.jmoney.xiyuyou.handler.CheckActionHandler;
import com.jmoney.xiyuyou.handler.ClickActionHandler;
import com.jmoney.xiyuyou.handler.DBActionHandler;
import com.jmoney.xiyuyou.handler.HttpRequestHandler;
import com.jmoney.xiyuyou.handler.WaitActionHandler;
import com.jmoney.xiyuyou.util.SeleniumUtil;

public enum StepAction {
	
    GET("get", "访问地址"){
		@Override
		public void run(TestStep step) {
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			step.getDriver().get(step.getValue());
		}
	},

    SET("set", "设置值到全局"){
		@Override
		public void run(TestStep step) throws Exception {
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			step.getDriver().manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
			if(StringUtils.isBlank(step.getDetails().get("key")))
				throw new Exception("set操作必须设置保存结果的键值，供后续操作使用，例子为details='key:credit'！");
			String value = SeleniumUtil.getElement(step).getText();
			SeleniumUtil.localmap.put(step.getDetails().get("key").toUpperCase(), value);
//			System.out.println("GlobalVariable: " + SeleniumUtil.localmap.toString());
		}
	},
    
    INPUT("input", "输入"){
		@Override
		public void run(TestStep step) throws Exception {
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			WebElement e = SeleniumUtil.getElement(step);
			e.clear();
			e.sendKeys(SeleniumUtil.parseStringHasEls(step.getValue()));	
		}
	},
    
    SELECT("select", "选择"){
		@Override
		public void run(TestStep step) throws Exception {
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			WebDriver driver = step.getDriver();
			WebElement selectElem = SeleniumUtil.getElement(step);
            Actions actions = new Actions(driver);
            actions.moveToElement(selectElem).click().perform();
            driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
            WebElement optionElem = driver.findElement(By.xpath(step.getValue()));
            optionElem.click();
            //TODO 时间配置待完善
            driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
	},

    Clear("clear", "清除"){
		@Override
		public void run(TestStep step) throws Exception{   
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			WebElement e = SeleniumUtil.getElement(step);
			e.clear();
			step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }
	},
    
    ExecuteExE(".exe", "执行.exe程序"){
		@Override
		public void run(TestStep step){   
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			final Runtime runtime = Runtime.getRuntime();  
	    	   try {
	    	    	runtime.exec(step.getValue());   	
	    	   } 
	    	   catch (final Exception e) {
	    	        System.out.println("Error exec!");  
	    	   }
	    	   step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }
	},
    
    SwitchToCurrentNewestWindow("stcnw", "切换到当前最新窗口"){
		@Override
		public void run(TestStep step){   
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			String WindowHandle  = step.getDriver().getWindowHandle();
			   step.getDriver().switchTo().window(WindowHandle);
	    	   step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
        }
	},
    
    KeyboardGeneralButton("keybg", "模拟键盘普通按键，例如：Home键"){
		@Override
        public void run(TestStep step){
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
 	        Actions action = new Actions(step.getDriver()); 
 	        action.sendKeys(Keys.valueOf(step.getKey())).perform();
 	        step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
    },
	
    KeyboardCombinationCommonKey("keybc", "模拟键盘组合按键，例如：Ctrl+W"){
		@Override
        public void run(TestStep step){
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
			Actions action = new Actions(step.getDriver());
			action.keyDown(Keys.valueOf(step.getKey())).sendKeys(step.getValue()).perform();   
 	        step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
    },
    
    SpecialKeyForKeyboardCombination("skeybc", "模拟键盘特殊组合按键，例如：Ctrl+Tab"){
		@Override
        public void run(TestStep step){
			System.out.println("『正常测试』开始执行: " + "<" +step.getDesc() + ">");
 	        Actions action = new Actions(step.getDriver()); 
    	    action.keyDown(Keys.valueOf(step.getKey())).sendKeys(Keys.valueOf(step.getKeys())).keyUp(Keys.valueOf(step.getKey())).sendKeys(Keys.valueOf(step.getKeys())).perform(); 
 	        step.getDriver().manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
		}
    },
    
    CLICK("click", "点击", ClickActionHandler.class),
    CLICK_OK("click-ok", "点击弹出框_确定", ClickActionHandler.class),
    CLICK_CANCEL("click-cancel", "点击弹出框_取消", ClickActionHandler.class),
    
    CHECK("check", "检查界面元素值", CheckActionHandler.class),
    CHECK_LIST("check-list", "检查本地缓存的list中的元素", CheckActionHandler.class),
    
    WAIT_FORCED("wait-forced", "强制等待", WaitActionHandler.class),
    WAIT_IMPLICIT("wait-implicit", "隐性等待", WaitActionHandler.class),

    GET_COOKIE("get-cookie", "获取cookie值", HttpRequestHandler.class),
    SEND_POST("send-post", "发送Post接口请求", HttpRequestHandler.class),
    
	DB_QUERY("db-query", "数据库查询", DBActionHandler.class),
    DB_UPDATE("db-update", "数据库更新", DBActionHandler.class),
    DB_DELETE("db-delete", "数据库删除", DBActionHandler.class),
    DB_INSERT("db-insert", "数据库插入", DBActionHandler.class),
	DB_PROCEDURE("db-procedure", "数据库存储过程", DBActionHandler.class),
	
	DB_QUERYM("db-querym", "数据库查询", DBActionHandler.class),
    DB_UPDATEM("db-updatem", "数据库更新", DBActionHandler.class),
    DB_DELETEM("db-deletem", "数据库删除", DBActionHandler.class),
    DB_INSERTM("db-insertm", "数据库插入", DBActionHandler.class),
	DB_PROCEDUREM("db-procedurem", "数据库存储过程", DBActionHandler.class),
	
	DB_QUERYMSSH("db-querymssh", "数据库查询", DBActionHandler.class),
    DB_UPDATEMSSH("db-updatemssh", "数据库更新", DBActionHandler.class),
    DB_DELETEMSSH("db-deletemssh", "数据库删除", DBActionHandler.class),
    DB_INSERTMSSH("db-insertmssh", "数据库插入", DBActionHandler.class),
	DB_PROCEDUREMSSH("db-proceduremssh", "数据库存储过程", DBActionHandler.class);
	
    private String key;

    private String desc;

    private Class<?> handler;

    private static Map<String,StepAction> map;

    static{
        map = new HashMap<String,StepAction>();
        for(StepAction action : StepAction.values()){
            map.put(action.key(), action);
        }
    }

    private StepAction(String key, String desc, Class<?> handler) {
        this(key, desc);
        this.handler = handler;
    }

    private StepAction(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static StepAction action(String name){
        return map.get(name);
    }

    public String key(){
        return this.key;
    }

    public String desc(){
        return this.desc;
    }

    public Class<?> handler(){
        return this.handler;
    }
    
    public void run(TestStep step) throws Exception{	
    }
}
