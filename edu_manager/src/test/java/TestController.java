import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import edu.hstc.roast.web.controller.MyPageController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestController {
    private MockMvc mockMvc;

    @Before
    public void setUp() {
        MyPageController apiController = new MyPageController();
        mockMvc = MockMvcBuilders.standaloneSetup(apiController).build();
    }

    @Test
    public void testGetAnPost() {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/s.action"))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);
            Assert.assertTrue(status==200);
            JSONObject resultObj = JSON.parseObject(result);
            // 判断接口返回json中对应字段是否为true
            Assert.assertTrue(resultObj.getString("name").equals("Java is best?"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testGetSequence() {
        try {
            MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/main/g.action"))
                    .andExpect(MockMvcResultMatchers.status().is(200))
                    .andDo(MockMvcResultHandlers.print())
                    .andReturn();
            int status = mvcResult.getResponse().getStatus();
            System.out.println("请求状态码：" + status);
            String result = mvcResult.getResponse().getContentAsString();
            System.out.println("接口返回结果：" + result);
            Assert.assertTrue(status==200);
            JSONArray resultArray=JSONArray.parseArray(result);
            String s=resultArray.getJSONObject(0).getString("name");
            Assert.assertTrue(s.equals("You Have Only One Life"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}