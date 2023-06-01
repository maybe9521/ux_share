package com.bdqn.ux_share.servlet;

import com.bdqn.ux_share.pojo.ArticleCarousel;
import com.bdqn.ux_share.service.CarouselService;
import com.bdqn.ux_share.service.impl.CarouselSericeImpl;
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

@WebServlet("/ArticleCarousel")
public class CarouselController extends HttpServlet {
    private CarouselService carouselService = new CarouselSericeImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
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
                case "getArticleCarouselbyid":{
                    getArticleCarouselbyid(req,resp);
                    break;
                }
                case "getTopCarousel":{
                    getTopCarousel(req,resp);
                    break;
                }
                case "getCarousel":{
                    getCarousel(req,resp);
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

    public void getTopCarousel(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            // 获取参数
            Integer carouselId= Integer.parseInt(req.getParameter("carouselId"));
            int limit = Integer.parseInt(req.getParameter("limit"));
            // 查询新闻详情
            PrintResult.print(resp, Common.success(carouselService.getTopAC(carouselId,limit)));
        } catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
    }
    public void delete(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException,SQLException{
        String carouselId = req.getParameter("carouselId");
        System.out.println(carouselId);
        System.out.println("我被调用了");
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Integer id = Integer.valueOf(carouselId);
        if(carouselService.delete(id)){
            out.print(true);
        }else{
            out.print(false);
        }
        out.flush();
        out.close();
    }

    public void getAll(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException,SQLException{
        ServletContext application = req.getServletContext();
        List<ArticleCarousel> articleCarousels = null;
        if(application.getAttribute("articleTagsList") != null){
            articleCarousels = (List<ArticleCarousel>)application.getAttribute("articleTagsList");
        }else{
            articleCarousels = carouselService.getAll();
        }
        PrintResult.print(resp, Common.success(articleCarousels));
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
                ArticleCarousel ac = new ArticleCarousel();
                //5.循环取数据
                for(FileItem file:list){
                    //判断表单数据
                    if(file.isFormField()){//普通表单数据
                        //普通表单数据的name
                        switch (file.getFieldName()){
                            case "carouselId":{
                                ac.setCarouselId(Integer.parseInt(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "carouselDesc":{
                                ac.setCarouselDesc(file.getString("UTF-8"));//普通表单数据的value
                                break;
                            }
                            case "carouselImg":{
                                ac.setCarouselImg(file.getString("UTF-8"));
                                break;
                            }
                            case "carouseSrc":{
                                ac.setCarouselSrc(file.getString("UTF-8"));
                                break;
                            }
                            case "carouselState":{
                                ac.setCarouselState(Integer.parseInt(file.getString("UTF-8")));
                                break;
                            }
                        }
                    }else{//文件数据
                        String filename = FilePath.makeFileName(file.getName());// uuid_文件名
                        ac.setCarouselSrc(filename);
                        String path = FilePath.makePath(filename,req.getSession().getServletContext().getRealPath(FilePath.UPLOAD));// 生成离散目录
                        file.write(new File(path+"\\"+filename));//保存文件到指定位置
                    }
                }
                if(carouselService.add(ac)){
                    PrintResult.print(resp, Common.success("修改轮播成功"));
                }else{
                    PrintResult.print(resp, Common.failed());
                }
            }
        }catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
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
            //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
            List<FileItem> list = upload.parseRequest(req);
            if(list != null){
                ArticleCarousel ac = new ArticleCarousel();
                //5.循环取数据
                for(FileItem file:list){
                    //判断表单数据
                    if(file.isFormField()){//普通表单数据
                        //普通表单数据的name
                        switch (file.getFieldName()){
                            case "carouselId":{
                                ac.setCarouselId(Integer.parseInt(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "carouselDesc":{
                                ac.setCarouselDesc(file.getString("UTF-8"));//普通表单数据的value
                                break;
                            }
                            case "carouselImg":{
                                ac.setCarouselImg(file.getString("UTF-8"));
                                break;
                            }
                            case "carouseSrc":{
                                ac.setCarouselSrc(file.getString("UTF-8"));
                                break;
                            }
                            case "carouselState":{
                                ac.setCarouselState(Integer.parseInt(file.getString("UTF-8")));
                                break;
                            }
                        }
                    }else{//文件数据
                        String filename = FilePath.makeFileName(file.getName());// uuid_文件名
                        ac.setCarouselSrc(filename);
                        String path = FilePath.makePath(filename,req.getSession().getServletContext().getRealPath(FilePath.UPLOAD));// 生成离散目录
                        file.write(new File(path+"\\"+filename));//保存文件到指定位置
                    }
                }
                // 新增
                System.out.println(ac.getCarouselDesc());
                if(carouselService.add(ac)){
                    PrintResult.print(resp, Common.success());
                }else{
                    PrintResult.print(resp, Common.failed());
                }
            }
        }catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }
    }

    public void getByPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        System.out.println(page);
        System.out.println(size);
        // 调用service
        Page<ArticleCarousel> pageData = carouselService.getByPage(new Page<ArticleCarousel>(page,size));
        PrintResult.print(resp,Common.success(pageData));
    }


    public void getArticleCarouselbyid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {
        try{
            // 获取参数
            String carouselId = req.getParameter("carouselId");
            //
            ArticleCarousel ac = carouselService.getArticleCarouselbyid(Integer.parseInt(carouselId));
            String filename = ac.getCarouselSrc();
            String path = "/"+FilePath.findFileSavePathByFileName(filename,FilePath.UPLOAD) + "/" + filename;
            ac.setCarouselSrc(path);
            System.out.println(ac.getCarouselSrc());
            PrintResult.print(resp, Common.success(ac));
        } catch (Exception e){
            PrintResult.print(resp, Common.failed(e.getMessage()));
        }

    }

    /**
     * 取得轮播图数据
     * @param req
     * @param resp
     */
    public void getCarousel(HttpServletRequest req, HttpServletResponse resp){
        PrintResult.print(resp, Common.success(carouselService.getAllCarousel()));
    }
}
