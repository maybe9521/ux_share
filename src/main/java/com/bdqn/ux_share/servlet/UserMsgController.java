package com.bdqn.ux_share.servlet;

import com.alibaba.fastjson.JSON;
import com.bdqn.ux_share.dao.ArticleDao;
import com.bdqn.ux_share.dao.UserMsgDao;
import com.bdqn.ux_share.dao.impl.ArticleDaoImpl;
import com.bdqn.ux_share.dao.impl.UserMsgDaoImpl;
import com.bdqn.ux_share.pojo.*;
import com.bdqn.ux_share.service.ArticleService;
import com.bdqn.ux_share.service.NoticeService;
import com.bdqn.ux_share.service.UserMsgService;
import com.bdqn.ux_share.service.impl.ArticleServiceImpl;
import com.bdqn.ux_share.service.impl.NoticeServiceImpl;
import com.bdqn.ux_share.service.impl.UserMsgServiceImpl;
import com.bdqn.ux_share.util.*;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@WebServlet("/usermsg")
public class UserMsgController extends HttpServlet {
    UserMsgService userMsgService = new UserMsgServiceImpl();

    NoticeService noticeService = new NoticeServiceImpl();
    ArticleService articleService = new ArticleServiceImpl();

    UserMsgDao userMsgDao = new UserMsgDaoImpl();
    ArticleDao articleDao = new ArticleDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        try {
            String opr = req.getParameter("opr");
            switch (opr) {
                case "getUser": {
                    getByNameAndPwd(req, resp);
                    break;
                }
                case "errorImg": {
                    errorImg(req, resp);
                }
                case "login": {
                    login(req, resp);
                    break;
                }
                case "rigister": {
                    rigister(req, resp);
                    break;
                }
                case "updateState": {
                    updateState(req, resp);
                    break;
                }
                case "getAll": {
                    getAll(req, resp);
                    break;
                }
                case "getcount": {
                    getcount(req, resp);
                    break;
                }
                case "getUserMsgInfo": {
                    getUserMsgInfo(req, resp);
                    break;
                }
                case "loadUserName": {
                    loadUserName(req, resp);
                    break;
                }
                case "loadUserMsgInfo": {
                    loadUserMsgInfo(req, resp);
                    break;
                }
                case "addArticle": {
                    addArticle(req, resp);
                    break;
                }
                case "getByPage": {
                    getByPage(req, resp);
                    break;
                }
                case "totalInfo": {
                    totalInfo(req, resp);
                    break;
                }
                case "searchNickname": {
                    searchNickname(req, resp);
                    break;
                }
                default: {
                    resp.sendRedirect("/404.html");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.sendRedirect("/500.html");
        }
    }

    /**
     * 加载统计数据
     *
     * @param req
     * @param resp
     */
    private void totalInfo(HttpServletRequest req, HttpServletResponse resp) {
        // 从上下文对象加载数据
        ServletContext application = req.getServletContext();
        List<Notice> noticeList = new ArrayList<Notice>();
        Long[] nums = new Long[5];
        // 公告数量
        int noticeNum = 0;
        if (application.getAttribute("noticeList") != null) {
            noticeList = (List<Notice>) application.getAttribute("noticeList");
            System.out.println(noticeList);
        } else {
            try {
                noticeList = noticeService.getAll();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        noticeNum= noticeList.size();
        nums[0] = (long) noticeNum;
        // 用户数量
        int userNum = 0;
        try {
            userNum = userMsgService.getCount();
            nums[1]= (long) userNum;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 文章总数
        int articleNum = 0;
        try {
            articleNum = articleDao.getAllInts();
            nums[2]= (long) articleNum;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 所有文章访问量
        int visitTotalNum = 0;
        try {
            visitTotalNum = articleDao.visitTotal();
            nums[3]= (long) visitTotalNum;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        // 用户登录时间
        nums[4]= (Long) req.getSession().getAttribute("loginTime");

        PrintResult.print(resp, Common.success(nums));
    }
    private void searchNickname(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        resp.setContentType("application/json;charset=UTF-8");
        System.out.println("进入模糊分页查询");
        String like = req.getParameter("like");
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        Page<UserMsg> pageData = userMsgService.getLikeUser(like,(new Page<UserMsg>(page, size)));
        PrintResult.print(resp, Common.success(pageData));
    }
    private void getByPage(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        // 请求分页参数
        String page = req.getParameter("page");
        String size = req.getParameter("size");
        System.out.println(page + "\t" + size);
        // 调用service
        Page<UserMsg> pageData = userMsgService.getBypage(new Page<UserMsg>(page, size));
        PrintResult.print(resp, Common.success(pageData));
    }

    private void addArticle(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("add");
        //1、创建一个DiskFileItemFactory工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //2、创建一个文件上传解析器
        ServletFileUpload upload = new ServletFileUpload(factory);
        //解决上传文件名的中文乱码
        upload.setHeaderEncoding("UTF-8");
        ArticleDto articleDto = new ArticleDto();
        //3、判断提交上来的数据是否是上传表单的数据
        if (!ServletFileUpload.isMultipartContent(req)) {
            //按照传统方式获取数据
            System.out.println("请求中没有文件！");
            return;
        }
        //4、使用ServletFileUpload解析器解析上传数据，解析结果返回的是一个List<FileItem>集合，每一个FileItem对应一个Form表单的输入项
        try {
            List<FileItem> list = upload.parseRequest(req);
            if (list != null) {

                //5.循环取数据
                for (FileItem file : list) {
                    //判断表单数据
                    if (file.isFormField()) {//普通表单数据
                        //普通表单数据的name
                        switch (file.getFieldName()) {
                            case "userId": {
                                articleDto.setUserId((int) Long.parseLong(file.getString("UTF-8")));//普通表单数据的value
                                break;
                            }
                            case "articleTitle": {
                                articleDto.setArticleTitle(file.getString("UTF-8"));
                                break;
                            }
                            case "classId": {
                                articleDto.setClassId(Integer.valueOf(file.getString("UTF-8")));
                                break;
                            }
                            case "tagIds": {
                                String temp = "{" + file.getString("UTF-8") + "}";
                                articleDto.setTagIds(ByteArrayConvert.convertIntToArray(temp));
                                break;
                            }
                            case "articleContent": {
                                articleDto.setArticleContent(file.getString("UTF-8"));
                                break;
                            }
                            case "articleSummary": {
                                articleDto.setArticleSummary(file.getString("UTF-8"));
                                break;
                            }
                        }
                    } else {//文件数据
                        ///upload/cover/40/fd5bfb6e- 2fd7-4386- 9563-9cb6c3585632 file\.image/png
                        String fileName = "cover" + FilePath.makeFileName(file.getFieldName());
                        articleDto.setArticleCover(fileName);
                        String path = FilePath.makePath(fileName, FilePath.UPLOAD);// 生成离散目录
                        file.write(new File(path + "\\" + fileName));//保存文件到指定位置
                    }
                }
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + list);
                // 新增
                //System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + SimpleForamtCommon.stringFormat(articleDto.getArticleCover()));
                if (articleService.addArticle(articleDto)) {
                    PrintResult.print(resp, Common.success(articleDto));
                } else {
                    PrintResult.print(resp, Common.failed(String.valueOf(articleDto)));
                }
            }
            System.out.println(ConsoleColors.BLACK_BACKGROUND_BRIGHT + "进来添加文章了");
//            String uname = req.getParameter("uname");
//            String pwd = req.getParameter("pwd");
            if (articleDto == null) {
                PrintResult.print(resp, articleDto);
            } else {
                PrintResult.print(resp, articleDto);
                System.out.println(ConsoleColors.GREEN + articleDto);
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (FileUploadException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加载登录用户信息
     *
     * @param req
     * @param resp
     */
    private void loadUserMsgInfo(HttpServletRequest req, HttpServletResponse resp) {
        UserMsg loginUser = (UserMsg) req.getSession().getAttribute("loginUser");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + loginUser);
        if (loginUser == null) {
            PrintResult.printInfo(resp, "您还未登录");
        } else {
            UserMsg userMsg = new UserMsg();
            try {
                UserMsg userMsgInfo = userMsgDao.getLoginUserInfo(loginUser.getUserId());
                userMsg.setUserId(userMsgInfo.getUserId());// 登录者id
                userMsg.setUserNickname(userMsgInfo.getUserNickname());// 登录者昵称
                userMsg.setUserUname(userMsgInfo.getUserUname());// 登录者账户
                userMsg.setUserRole(loginUser.getUserRole());// 登录者角色
                userMsg.setUserIcon(userMsgInfo.getUserIcon());// 角色头像
                userMsg.setUserPoint(userMsgInfo.getUserPoint());// 角色积分
                userMsg.setSumArticle(articleDao.getArticleSum(userMsg.getUserId()));// 返回文章总数
                userMsg.setSumAtt(userMsgDao.getCareCount(userMsg.getUserId()));// 返回用户关注数量
                userMsg.setCollectAtt(userMsgDao.getUserCollectSum(loginUser.getUserId()));// 返回用户收藏数量
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            PrintResult.print(resp, userMsg);
        }
    }

    /**
     * 加载登录用户信息
     *
     * @param req
     * @param resp
     */
    private void loadUserName(HttpServletRequest req, HttpServletResponse resp) {
        UserMsg loginUser = (UserMsg) req.getSession().getAttribute("loginUser");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + loginUser);
        if (loginUser == null) {
            PrintResult.printInfo(resp, "您还未登录");
        } else if (loginUser.getUserRootId() < 4) {
            PrintResult.printInfo(resp, "您的用户等级未到4级,请努力升级！");
        } else {
            UserMsg userMsg = new UserMsg(loginUser.getUserId(), loginUser.getUserNickname());
            PrintResult.print(resp, userMsg);
        }

    }

    /**
     * 返回用户详细信息
     *
     * @param req  用户账户和用户密码 或者用户id
     * @param resp 用户详细信息
     */
    private void getUserMsgInfo(HttpServletRequest req, HttpServletResponse resp) {
        UserMsg loginUser = (UserMsg) req.getSession().getAttribute("loginUser");
        System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + loginUser);
        if (loginUser == null) {
            PrintResult.printInfo(resp, "您还未登录");
        } else {
            UserMsg userMsg = new UserMsg();
            try {
                UserMsg userMsgInfo = userMsgDao.getLoginUserInfo(loginUser.getUserId());
                userMsg.setUserId(userMsgInfo.getUserId());// 登录者id
                userMsg.setUserNickname(userMsgInfo.getUserNickname());// 登录者昵称
                userMsg.setUserUname(userMsgInfo.getUserUname());// 登录者账户
                userMsg.setUserRole(userMsgInfo.getUserRole());// 登录者角色
                userMsg.setUserIcon(userMsgInfo.getUserIcon());// 角色头像
                userMsg.setUserPoint(userMsgInfo.getUserPoint());// 角色积分
                userMsg.setSumArticle(articleDao.getArticleSum(userMsg.getUserId()));// 返回文章总数
                userMsg.setSumAtt(userMsgDao.getCareCount(userMsg.getUserId()));// 返回用户关注数量
                userMsg.setCollectAtt(userMsgDao.getUserCollectSum(loginUser.getUserId()));// 返回用户收藏数量
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            PrintResult.print(resp, userMsg);
        }
    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException, SQLException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(JSON.toJSONString(userMsgService.getAll()));
        out.flush();
        out.close();
    }

    public void getcount(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException, SQLException {
        resp.setContentType("application/json;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(JSON.toJSONString(userMsgService.getCount()));
        out.flush();
        out.close();
    }

    private void rigister(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        String nickname = req.getParameter("nickname");
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        Integer role = Integer.valueOf(req.getParameter("role"));
        Integer state = Integer.valueOf(req.getParameter("state"));
        Date date = new Date();
        String icon = req.getParameter("icon");
        String phone = req.getParameter("phone");
        Integer point = Integer.valueOf(req.getParameter("point"));
        Integer level = Integer.valueOf(req.getParameter("level"));
        Integer rootid = Integer.valueOf(req.getParameter("rootid"));
        Integer ex = Integer.valueOf(req.getParameter("ex"));
        UserMsg userMsg = new UserMsg(nickname,uname,pwd,role,state,date,icon,phone,point,level,rootid,ex);
        if(userMsgService.UserRegister(userMsg)){
            PrintResult.print(resp,userMsg);
            System.out.println("注册成功");
        }else {
            PrintResult.print(resp,null);
        }

    }

    /**
     * 根据用户账户和密码进行登录验证
     *
     * @param req
     * @param resp
     */
    private void getByNameAndPwd(HttpServletRequest req, HttpServletResponse resp) {
        String uname = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        UserMsg userMsg = userMsgService.getByNameAndPwd(uname, pwd);
        if (userMsg == null) {
            PrintResult.print(resp, userMsg);
        } else {
            PrintResult.print(resp, userMsg);
            System.out.println(ConsoleColors.GREEN + userMsg);
            if(userMsg == null){// 登录失败
                req.setAttribute("login_error","账号或密码不匹配！");
                try {
                    req.getRequestDispatcher("/front/loginpage.html").forward(req,resp);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }else{// 登录成功
                // Session存储用户信息
                HttpSession session = req.getSession();
                session.setAttribute("loginUser",userMsg);
                session.setMaxInactiveInterval(3600);
                session.setAttribute("loginTime",System.currentTimeMillis());// 用户登录时间
                System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + userMsg);
                // 记住密码
                if (req.getParameter("save") != null && req.getParameter("save").equals("1")) {
                    // 客户端存储cookie
                    Cookie cookieName = new Cookie("username", userMsg.getUserUname());
                    Cookie cookiePwd = new Cookie("password", pwd);
                    // 设置cookie的访问路径
                    cookieName.setPath("/");
                    cookiePwd.setPath("/");
                    // 设置cookie的存活时间
                    cookieName.setMaxAge(60 * 60 * 24 * 7);
                    cookiePwd.setMaxAge(60 * 60 * 24 * 7);
                    // 要求客户端添加cookie文件
                    resp.addCookie(cookieName);
                    resp.addCookie(cookiePwd);
                }
                // Session存储用户信息
                try {
                    userMsg = userMsgService.getUserMsgInfo(new UserMsgDto(userMsg.getUserUname(), userMsg.getUserPwd()));
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                session.setAttribute("loginUser", userMsg);
                PrintResult.print(resp,userMsg);
            }
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException, SQLException {
//        // 1.获取登录参数
        String name = req.getParameter("uname");
        String pwd = req.getParameter("pwd");
        System.out.println("name:" + name + "\npwd:" + pwd);
        // 2.判断登录情况
        UserMsg userMsg = userMsgService.login(new UserMsg(name, pwd));
        if (userMsg == null) {// 登录失败
            req.setAttribute("login_error", "账号或密码不匹配！");
            req.getRequestDispatcher("/front/loginpage.html").forward(req, resp);
        } else {// 登录成功
            // Session存储用户信息
            HttpSession session = req.getSession();
            userMsg = userMsgDao.getLoginUserInfo(userMsg.getUserId());
            session.setAttribute("loginUser", userMsg);
            session.setAttribute("loginTime", System.currentTimeMillis());// 用户登录时间
            session.setMaxInactiveInterval(3600);
            System.out.println(ConsoleColors.GREEN_BOLD_BRIGHT + userMsg);
            // 记住密码
            if (req.getParameter("save") != null && req.getParameter("save").equals("1")) {
                // 客户端存储cookie
                Cookie cookieName = new Cookie("username", userMsg.getUserUname());
                Cookie cookiePwd = new Cookie("password", pwd);
                // 设置cookie的访问路径
                cookieName.setPath("/");
                cookiePwd.setPath("/");
                // 设置cookie的存活时间
                cookieName.setMaxAge(60 * 60 * 24 * 7);
                cookiePwd.setMaxAge(60 * 60 * 24 * 7);
                // 要求客户端添加cookie文件
                resp.addCookie(cookieName);
                resp.addCookie(cookiePwd);
            }
            PrintResult.print(resp, userMsg);
        }
    }

    /**
     * 破图加载
     *
     * @param req
     * @param resp
     */
    private void errorImg(HttpServletRequest req, HttpServletResponse resp) {
        String articleId = req.getParameter("articleId");
        String fileName = req.getParameter("cover");
        String path = FilePath.findFileSavePathByFileName(fileName, FilePath.UPLOAD) + "\\" + fileName;
        PrintResult.print(resp, Common.success(path));
    }


    public void updateState(HttpServletRequest req, HttpServletResponse resp) throws
            ServletException, IOException, SQLException {
        String state = req.getParameter("state");
        String id = req.getParameter("userid");
        String userid = id.trim();
        System.out.println(state + "\t" + userid);
        PrintResult.print(resp, Common.success(userMsgService.updateState(Integer.valueOf(userid), Integer.valueOf(state))));
    }
}