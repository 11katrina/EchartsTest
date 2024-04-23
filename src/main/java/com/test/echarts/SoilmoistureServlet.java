package com.test.echarts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.dao.EchartsDao;
import com.test.pojo.Soilmoisture;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SoilmoistureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EchartsDao echartsDao = new EchartsDao();
        ObjectMapper mapper = new ObjectMapper();
        List<Soilmoisture> dataList = new ArrayList<>();
        dataList = echartsDao.soilmoistureEcharts();
        // 将要返回的数据转化为 json 字符串
        String resultJson = mapper.writeValueAsString(dataList);
        // 设置响应内容类型和字符集
        resp.setContentType("text/html;charset=UTF-8");
        // 输出 json 字符串到客户端
        resp.getWriter().write(resultJson);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
