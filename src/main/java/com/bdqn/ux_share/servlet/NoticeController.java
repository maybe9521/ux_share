package com.bdqn.ux_share.servlet;


import com.bdqn.ux_share.pojo.ArticleTag;
import com.bdqn.ux_share.pojo.Notice;
import com.bdqn.ux_share.service.NoticeService;
import com.bdqn.ux_share.service.impl.NoticeServiceImpl;
import com.bdqn.ux_share.util.Common;
import com.bdqn.ux_share.util.FilePath;
import com.bdqn.ux_share.util.Page;
import com.bdqn.ux_share.util.PrintResult;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@WebServlet("/Notice")
public class NoticeController extends HttpServlet {
    private NoticeService noticeService = new NoticeServiceImpl();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1.设置请求编码
        req.setCharacterEncoding("UTF-8");
        // 2.设置响应格式
        resp.setContentType("text/html;charset=UTF-8");
        // 3.获取请求参数
        try {
            String opr = req.getParameter("opr");
            switch (opr) {
                case "getAll": {
                    getAll(req, resp);
                    break;
                }
                case "getByPage": {
                    getByPage(req, resp);
                    break;
                }
                case "getPageByNoticeId": {
                    getPageByNoticeId(req, resp);
                    break;
                }
                case "delete": {
                    delete(req, resp);
                    break;
                }
                case "add": {
                    add(req, resp);
                    break;
                }
                case "update": {
                    update(req, resp);
                    break;
                }
                case "getTopNotice":{
                    getTopNotice(req,resp);
                    break;
                }
                default: {
                    resp.sendRedirect("/404.jsp");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/500.jsp");
        }
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        ServletContext application = req.getServletContext();
        List<Notice> notices = null;
        if(application.getAttribute("articleTagsList") != null){
            notices = (List<Notice>)application.getAttribute("articleTagsList");
        }else{
            notices = noticeService.getAll();
        }
        PrintResult.print(resp, Common.success(notices));
    }
    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("page");
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        // 调用service
        Page<Notice> pageData = noticeService.getNcByPage(new Page<Notice>(page,size));
        PrintResult.print(resp, Common.success(pageData));
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String noticeId = req.getParameter("noticeId");
        System.out.println(noticeId);
        System.out.println("我被调用了");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Integer id = Integer.valueOf(noticeId);
        if(noticeService.delete(id)){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();
    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String tco = req.getParameter("tco");
        String tname = req.getParameter("tname");
        System.out.println(tco);
        System.out.println(tname);
        Notice notice = new Notice();
        notice.setNoticeContent(tco);
        notice.setNoticeTitle(tname);
        try{
            if(noticeService.add(notice)){
                PrintResult.print(resp, Common.success());
            }else{
                PrintResult.print(resp,Common.failed());
            }
        } catch (Exception e){
            e.printStackTrace();
            PrintResult.print(resp,Common.failed(e.getMessage()));
        }
    }
    public void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            System.out.println("update");
            //1、创建一个DiskFileItemFactory工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            //2、创建一个文件上传解析器
            ServletFileUpload upload = new ServletFileUpload(factory);
            //解决上传文件名的中文乱码
            upload.setHeaderEncoding("UTF-8");
            //3、判断提交上来的数据是否是上传表单的数据
            if(!ServletFileUpload.isMultipartContent(req)){
                //按照传统方式获取数据
                System.out.println("请求中没有文件！");
                return;
            }
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(req);
            if(list != null){
                Notice notice = new Notice();
                //5.循环取数据
                for(FileItem file:list){
                    //判断表单数据
                    if(file.isFormField()){//普通表单数据
                        //普通表单数据的name
                        switch (file.getFieldName()){
                            case "noticeId":{
                                notice.setNoticeId(Integer.parseInt(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "noticeTitle":{
                                notice.setNoticeTitle(file.getString(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "noticeContent":{
                                notice.setNoticeContent(file.getString("UTF-8"));
                                break;
                            }

                            case "noticeDate":{
                                notice.setNoticeDate(new Date());
                                break;
                            }
                        }
                    }else{//文件数据
                        String filename = FilePath.makeFileName(file.getName());// uuid_文件名
                        notice.setNoticeContent(filename);
                        String path = FilePath.makePath(filename,req.getSession().getServletContext().getRealPath(FilePath.UPLOAD));// 生成离散目录
                        file.write(new File(path+"\\"+filename));//保存文件到指定位置
                    }
                }
                if(noticeService.add(notice)){
                    PrintResult.print(resp, Common.success("修改成功"));
                }else{
                    PrintResult.print(resp, Common.failed());
                }
            }
        }catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
    }
    public void getTopNotice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            // 获取参数
            Integer informId = Integer.parseInt(req.getParameter("ntid"));
            int limit = Integer.parseInt(req.getParameter("limit"));
            // 查询新闻详情
            PrintResult.print(resp, Common.success(noticeService.getNcByPage(informId,limit)));
        } catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
    }
    public void getPageByNoticeId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        System.out.println(page);
        System.out.println(size);
        // 调用service
        Page<Notice> pageData = noticeService.getNcByPage(new Page<Notice>(page,size));
        PrintResult.print(resp,Common.success(pageData));
    }
}
