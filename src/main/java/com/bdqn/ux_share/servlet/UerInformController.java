package com.bdqn.ux_share.servlet;


import com.bdqn.ux_share.pojo.UserInform;
import com.bdqn.ux_share.service.UserInformService;
import com.bdqn.ux_share.service.impl.UserInformServiceImpl;
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
import java.util.List;

@WebServlet("/userInform")
public class UerInformController extends HttpServlet {
    private UserInformService us=new UserInformServiceImpl();
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        this.doGet(req, resp);
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
                case "getPageByinformId": {
                    getPageByinformId(req, resp);
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
                case "getTopUserInform":{
                    getTopUserInform(req,resp);
                    break;
                }case "reBack": {
                    reBack(req, resp);
                    break;
                } case "getUserInform":{
                    getUserInform(req,resp);
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
        System.out.println("test");
        ServletContext application = req.getServletContext();
        List<UserInform> userInforms = null;
        if(application.getAttribute("userInform") != null){
            userInforms = (List<UserInform>)application.getAttribute("userInform");
        }else{
            userInforms = us.getAll();
        }
        PrintResult.print(resp, Common.success(userInforms));
    }

    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        // 调用service
        Page<UserInform> pageData = us.getByPage(new Page<UserInform>(page,size));
        PrintResult.print(resp, Common.success(pageData));
    }
    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        String informId = req.getParameter("informId");
        System.out.println(informId);
        System.out.println("我被调用了");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        if(us.delete(Integer.valueOf(informId))){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();

    }
    public void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            System.out.println("add");
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
            List<FileItem> list = upload.parseRequest(req);
            if(list != null){
                UserInform ui = new UserInform();
                //5.循环取数据
                for(FileItem file:list){
                    //判断表单数据
                    if(file.isFormField()){//普通表单数据
                        //普通表单数据的name
                        switch (file.getFieldName()){
                            case "informId":{
                                ui.setInformId(Integer.parseInt(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "informTitle":{
                                ui.setInformTitle(file.getString("UTF-8"));
                                break;
                            }
                            case "informContent":{
                                ui.setInformContent(file.getString("UTF-8"));
                                break;
                            }

                            case "informState":{
                                ui.setInformState(Integer.parseInt("UTF-8"));
                                break;
                            }
                        }
                    }else{//文件数据
                        String filename = FilePath.makeFileName(file.getName());// uuid_文件名
                        ui.setInformContent(filename);
                        String path = FilePath.makePath(filename,req.getSession().getServletContext().getRealPath(FilePath.UPLOAD));// 生成离散目录
                        file.write(new File(path+"\\"+filename));//保存文件到指定位置
                    }
                }
                // 新增
                System.out.println(ui.getInformId());
                if(us.add(ui)){
                    PrintResult.print(resp, Common.success());
                }else{
                    PrintResult.print(resp, Common.failed());
                }
            }
        }catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
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
                UserInform userInform = new UserInform();
                //5.循环取数据
                for(FileItem file:list){
                    //判断表单数据
                    if(file.isFormField()){//普通表单数据
                        //普通表单数据的name
                        switch (file.getFieldName()){
                            case "informId":{
                                userInform.setInformId(Integer.parseInt(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "informTitle":{
                                userInform.setInformTitle(file.getString(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "informContent":{
                                userInform.setInformContent(file.getString("UTF-8"));
                                break;
                            }

                            case "userId":{
                                userInform.setUserId(Integer.parseInt("UTF-8"));
                                break;
                            }
                            case "informState":{
                                userInform.setInformState(Integer.parseInt("UTF-8"));
                                break;
                            }
                        }
                    }else{//文件数据
                        String filename = FilePath.makeFileName(file.getName());// uuid_文件名
                        userInform.setInformContent(filename);
                        String path = FilePath.makePath(filename,req.getSession().getServletContext().getRealPath(FilePath.UPLOAD));// 生成离散目录
                        file.write(new File(path+"\\"+filename));//保存文件到指定位置
                    }
                }
                if(us.add(userInform)){
                    PrintResult.print(resp, Common.success("修改成功"));
                }else{
                    PrintResult.print(resp, Common.failed());
                }
            }
        }catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
    }

    public void getTopUserInform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            // 获取参数
            Integer informId = Integer.parseInt(req.getParameter("ntid"));
            int limit = Integer.parseInt(req.getParameter("limit"));
            // 查询新闻详情
            PrintResult.print(resp, Common.success(us.getTopAC(informId,limit)));
        } catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }

    }

    /**
     * 分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void getPageByinformId(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        System.out.println("调用分页");
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        System.out.println(page);
        System.out.println(size);
        // 调用service
        Page<UserInform> pageData = us.getByPage(new Page<UserInform>(page,size));
        PrintResult.print(resp,Common.success(pageData));
    }

    /**
     * 反馈处理后新增通知
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     */
    public void reBack(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        UserInform userInform = new UserInform();
        int uid = Integer.valueOf(req.getParameter("uid"));
        String reTitle = req.getParameter("reTitle");
        String reMsg = req.getParameter("reMsg");
        userInform.setUserId(uid);
        userInform.setInformTitle(reTitle);
        userInform.setInformContent(reMsg);
        PrintResult.print(resp, us.add(userInform));
    }
    public void getUserInform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        int uid = Integer.valueOf(req.getParameter("uid"));
        PrintResult.print(resp, us.getUserInform(uid));
    }
}
