package com.test.servlet;

import com.test.dao.*;
import com.test.pojo.Disease;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        String method = req.getParameter("method");
        if("search".equals(method)) {
            search(req, resp);
        } else if ("insert".equals(method)) {
            insert(req,resp);
        } else if ("delete".equals(method)) {
            delete(req,resp);
        } else if ("query".equals(method)) {
            query(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void search(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SearchDao searchDao = new SearchDao();
        // TODO Auto-generated method stub
        req.setCharacterEncoding("utf-8");
        String Date = req.getParameter("Date");
        List<Object> datas = searchDao.SearchInsect(Date);
        if(datas.size()==0) {
            req.setAttribute("message", "该土壤暂无病虫害");
        }else {
            req.setAttribute("datas", datas);
        }
        req.getRequestDispatcher("jsp/warn.jsp").forward(req,resp);
    }

    private void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        InsertDao insertDao = new InsertDao();
        String[] Date = req.getParameterValues("Date");
        String number = req.getParameter("number");
        String result = null;
        result =insertDao.InsertDisease(Date,number);
        if(result.equals("null")){
            // 将添加成功消息写入request属性中
            req.setAttribute("message", "添加失败，输入为空！");
        }else if(result.equals("false")){
            // 将添加失败消息写入request属性中
            req.setAttribute("message", "添加失败，数据有重复");
        } else if (result.equals("other")) {
            req.setAttribute("message", "添加失败，该土壤太多病虫害，请先解决！");
        }else if(result.equals("success")){
            req.setAttribute("message", "添加成功");
        }
        req.getRequestDispatcher("jsp/insertAfter.jsp").forward(req,resp);
    }


    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DeleteDao deleteDao = new DeleteDao();
        String[] Date = req.getParameterValues("isDelete");
        String number = req.getParameter("number");
        int result = 0;
        result = deleteDao.DeleteDisease(Date, number);
        if (result != 0) {
            // 将添加成功消息写入request属性中
            req.setAttribute("message", "删除成功");
        } else {
            // 将添加失败消息写入request属性中
            req.setAttribute("message", "删除失败");
        }
        req.getRequestDispatcher("jsp/deleteAfter.jsp").forward(req,resp);
    }



    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        QueryDao queryDao = new QueryDao();
        String number = req.getParameter("number");
        List<Disease> diseases = queryDao.QueryNumber(number);
        req.setAttribute("diseases",diseases);
        req.getRequestDispatcher("jsp/queryAfter.jsp").forward(req,resp);
    }
}
