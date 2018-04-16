package TestCases;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jmoney.xiyuyou.base.TestUnit;
import com.jmoney.xiyuyou.service.RunUnitService;
import com.jmoney.xiyuyou.service.TestXmlParseService;

public class Tourists_Purchase_Tickets {
	
	private static RunUnitService runService;
	
	@BeforeTest
	private void stup() throws Exception{
		TestUnit testunit = TestXmlParseService.parse("src/test/java/TestCaseXml/Tourists_Purchase_Tickets.xml");
		runService = new RunUnitService(testunit);
		System.out.println("-----------------------------------【西域游-PC游客端自动购买门票流程】-----------------------------------");
	}
	
//	@Test
//	public void Precondition() throws Exception{
//		runService.runCase("前置条件");
//		runService.TestReportRemarks("验证在数据库中,执行对应SQL语句,可以正常执行成功");
//	}
	
	@Test
	public void case1() throws Exception{
		runService.runCase("case1");
		runService.TestReportRemarks("验证在火狐浏览器中，输入新疆旅游系统域名后，可以正常访问");
	}
	
	@Test
	public void case2() throws Exception{
		runService.runCase("case2");
		runService.TestReportRemarks("验证在登录界面，输入正确的账号和密码，点击登录按钮后，可以正常跳转至首页主界面");
	}
	
	@Test
	public void case3() throws Exception{
		runService.runCase("case3");
		runService.TestReportRemarks("验证在首页界面搜索栏中，输入对应景点名称，选择对应景点后，可以正常搜索且进入景点详情界面");
	}

	@Test
	public void case4() throws Exception{
		runService.runCase("case4");
		runService.TestReportRemarks("验证在景点详情界面，选择对应景点票务组合后，可以正常进入门票购买界面");
	}
	
	@Test
	public void case5() throws Exception{
		runService.runCase("case5");
		runService.TestReportRemarks("验证在门票购买界面，选择对应门票类型，适用人群，出行日期等信息，点击立即预订按钮后，可以正常进入提交订单界面");
	}
	
	@Test
	public void case6() throws Exception{
		runService.runCase("case6");
		runService.TestReportRemarks("验证在提交订单界面，选择输入对应出行人信息和预定人信息，点击支付按钮后，可以正常进入快捷支付界面");
	}
	
	@Test
	public void case7() throws Exception{
		runService.runCase("case7");
		runService.TestReportRemarks("验证在快捷支付界面，选择对应支付方式，点击快捷支付按钮后，可以正常进入二维码扫描支付界面");
	}
	
	@Test
	public void case8() throws Exception{
		runService.runCase("case8");
		runService.TestReportRemarks("验证在二维码扫描支付界面，调用后台支付接口后，可以自动支付成功并跳转至支付成功界面");
	}
	
	@Test
	public void case9() throws Exception{
		runService.runCase("case9");
		runService.TestReportRemarks("验证在支付成功界面，点击查看详情按钮后，可以正常跳转至个人中心-全部订单-订单详情界面");
	}
	
	@Test
	public void case_10() throws Exception{
		runService.runCase("case_10");
		runService.TestReportRemarks("验证在订单详情界面，对应订单信息显示正确，包含(订单名称，订单编号，总价，订单状态，取票码，商品类型，游玩时间，商品数量，商品价格，商品状态，出现人信息，联系人信息)");
	}
		
	@AfterTest
	public void TearDown(){
		runService.close();
	}
}
