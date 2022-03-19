package com.epam.librarysalessystem;

import com.alibaba.fastjson.JSONObject;
import com.epam.librarysalessystem.entity.Member;
import com.epam.librarysalessystem.entity.MemberType;
import com.epam.librarysalessystem.entity.OrderInfo;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.math.BigDecimal;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = LibrarySalesSystemApplication.class)
@FixMethodOrder(MethodSorters.JVM)
class LibrarySalesSystemApplicationTests {

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @PostConstruct
    public void init() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateAndQueryMember() throws Exception {
        Member member = new Member();
        member.setIdentityCardId("321023199912158888");
        member.setPhone("15850033588");
        member.setPassWord("123456");
        member.setAge(20);
        member.setMemberType(MemberType.GOLD);
        member.setMemberName("test");
        MockHttpServletRequestBuilder mockHttpServletRequest = MockMvcRequestBuilders.post("/library/member", member).contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(member));
        MvcResult result = mockMvc.perform(mockHttpServletRequest).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        MockHttpServletRequestBuilder mockQueryHttpServletRequest = MockMvcRequestBuilders.get("/library/member");
        MvcResult queryResult = mockMvc.perform(mockQueryHttpServletRequest).andReturn();
        System.out.println(queryResult.getResponse().getContentAsString());
    }

    @Test
    public void testCreateAndQueryOrder() throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setMemberId(1L);
        orderInfo.setName("book1");
        orderInfo.setPrice(new BigDecimal(288.99));
        MockHttpServletRequestBuilder mockHttpServletRequest = MockMvcRequestBuilders.post("/library/order", orderInfo).contentType(MediaType.APPLICATION_JSON).content(JSONObject.toJSONString(orderInfo));
        MvcResult result = mockMvc.perform(mockHttpServletRequest).andReturn();
        System.out.println(result.getResponse().getContentAsString());

        MockHttpServletRequestBuilder mockQueryHttpServletRequest = MockMvcRequestBuilders.get("/library/order");
        MvcResult queryResult = mockMvc.perform(mockQueryHttpServletRequest).andReturn();
        System.out.println(queryResult.getResponse().getContentAsString());
    }
}
