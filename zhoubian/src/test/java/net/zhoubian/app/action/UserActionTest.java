package net.zhoubian.app.action;


public class UserActionTest extends ITBaseTest {
    public void testSubmitRegister() {
        beginAt("/user_register.do");
        setTextField("user.loginName", "somename1");
        setTextField("user.password", "111111");
        setTextField("user.email", "somename1@gmail.com");
        this.selectOptionByValue("user.sex", "1");
        submit();
    	
//    	getTestContext().setUserAgent("Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; .NET CLR 2.0.50727)");
//        beginAt("/index.html");
//        System.out.println(this.getPageSource());
    }
}
