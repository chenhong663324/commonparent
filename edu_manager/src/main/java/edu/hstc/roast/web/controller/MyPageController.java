package edu.hstc.roast.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import edu.hstc.roast.module.Posting;
import edu.hstc.roast.module.Reply;
import edu.hstc.roast.module.Theme;
import edu.hstc.roast.module.User;
import edu.hstc.roast.service.PostingService;
import edu.hstc.roast.service.ReplyService;
import edu.hstc.roast.service.ThemeService;
import edu.hstc.roast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/main")
@Controller
public class MyPageController {
    StringBuffer stringBuffer=new StringBuffer();
    Reply replyCache =new Reply();
    @Autowired
    ReplyService replyService;
    @Autowired
    ThemeService themeService;
    @Autowired
    PostingService postingService;
    @Autowired
    UserService userService;

//    @InitBinder
//    protected void initBinder(HttpServletRequest request,
//                              ServletRequestDataBinder binder) throws Exception {
//        // DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        CustomDateEditor dateEditor = new CustomDateEditor(format, true);
//        binder.registerCustomEditor(Date.class, dateEditor);
//        //super.initBinder(request, binder);
//    }
    @RequestMapping("/goToHandlebarsTest.action")
    public String goToHandlebasTest( RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("posting_id", "p");
        String path="redirect:/HandlebarsTest.html";
        System.out.println(path);
        return path;
    }
    @RequestMapping("/goToViewPosting.action")
    public String goToViewPosting( RedirectAttributes redirectAttributes,String posting_id) {
        redirectAttributes.addAttribute("posting_id", posting_id);
        String path="redirect:/viewPosting.html";
        System.out.println(path);
        return path;
    }
//    @RequestMapping("/goToMain.action")
//    public String goToMain( RedirectAttributes redirectAttributes,String posting_id) {
//        redirectAttributes.addAttribute("posting_id", posting_id);
//        String path="redirect:/viewPosting.html";
//        System.out.println(path);
//        return path;
//    }
    @RequestMapping("/s.action")
    @ResponseBody
    public Posting sendMess1() {
        Posting posting=new Posting();
        posting.setName("Java is best?");
        posting.setIntroduce("I think java in org.springframework.web.bind.annotation.ResponseBody and org.springframework.web.bind.annotation.ResponseBody");
        System.out.println("ssss");
        return posting;
    }

    @RequestMapping("/queryPostingsByName.action")
    @ResponseBody
    public List<Posting> queryPostingsByName(String name) {
        Posting posting=new Posting();
        posting.setName(name);

        return postingService.queryPostingsByNameOrContext(posting);
    }


    @RequestMapping("/g.action")
    @ResponseBody
    public List<Posting> sendMess2(@RequestParam(value="pn",required=false, defaultValue="1")Integer pn) {
        //List<Posting> postings=new ArrayList<Posting>();
        PageHelper.startPage(pn, 2);
        List<Posting> postings=postingService.queryAll();
        PageInfo<Posting> pageInfo=new PageInfo<>(postings);
        return postings;
    }

    @RequestMapping("/addReply.action")
    @ResponseBody
    public Reply addReply(@RequestBody Reply reply) {
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        reply.setId(uuid);
        java.util.Date utilDate=new Date();
        java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
        reply.setTime(sqlDate);
        int i=replyService.add(reply);
        if(i==1){
            return reply;
        }
        return null;
    }


    @RequestMapping("/queryAllAndReturnPageInfo.action")
    @ResponseBody
    public PageInfo<Posting> sendMess3(@RequestParam(value="pn",required=false, defaultValue="1")Integer pn) {
        //List<Posting> postings=new ArrayList<Posting>();
        PageHelper.startPage(pn, 2);
        List<Posting> postings=postingService.queryAll();
        PageInfo<Posting> pageInfo=new PageInfo<>(postings);
        return pageInfo;
    }

    @RequestMapping("/queryPostingByID.action")
    @ResponseBody
    public Posting queryPostingByID(String posting_id) {
        return postingService.queryPostingByID(posting_id);
    }

    @RequestMapping("/uploadImage.action")
    @ResponseBody
    public Map<String,String> uploadImage(@RequestPart("upload") MultipartFile gpic, HttpServletRequest request, HttpServletResponse response) {
        String path =request.getServletContext().getRealPath("/upload");
        String pic_name = gpic.getOriginalFilename();
        String newname = UUID.randomUUID().toString() + pic_name.substring(pic_name.lastIndexOf("."));
        File picFile = new File(path, newname);
        if (!picFile.exists()) {
            picFile.mkdirs();
        }
        try {
            gpic.transferTo(picFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String,String > result=new HashMap<>();
        result.put("uploaded","true");
        result.put("url","upload/"+newname);
        return result;
    }

    @RequestMapping("/loadReply.action")
    @ResponseBody
    public List<Reply> loadReply(HttpServletRequest request, HttpServletResponse response) {
        List<Reply> replyList=replyService.queryAll();
        return replyList;
    }

    @RequestMapping("/loadReplyByPostingID.action")
    @ResponseBody
    public List<Reply> loadReplyByPostingID(HttpServletRequest request, HttpServletResponse response,String posting_id) {
        replyCache.setPosting_id(posting_id);
        replyCache.setTarget_reply("r1");
        List<Reply> replyList=replyService.queryRepliesForPostingByPostingID(posting_id);
        return replyList;
    }


    @RequestMapping( value ="/createPosting.action",method = {RequestMethod.POST})
    @ResponseBody
    public  int createPosting(@RequestParam("name")String name,@RequestParam("introduce")String introduce,@RequestParam("context")String context,@RequestParam("owner_id")String owner_id,HttpSession httpSession ) throws ParseException {
        //List<Reply> replyList=replyService.queryAll();
        User user= (User) httpSession.getAttribute("userLogin");

        Posting posting=new Posting();

        String postid=getUUID32();
        String introduce1=contextToIntroduce(introduce);

        java.util.Date date = new java.util.Date();//获得系统时间.
        SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);
        java.util.Date time = sdf.parse( nowTime );
        java.sql.Date sqltime = new java.sql.Date(time.getTime());

        posting.setId(postid);
        posting.setName(name);
        posting.setTheme_id(1);
        posting.setIntroduce(introduce1);
        posting.setOwner_id(user.getId());
        posting.setContext(context);
        posting.setTime(sqltime);


        postingService.add(posting);

        return 1;
    }


    @RequestMapping("/myposting.action")
    @ResponseBody
    public List<Posting> GetMyPosting(String userid,HttpServletRequest request, HttpServletResponse response,HttpSession httpSession) {//将当前用户发的帖子信息返回
        User user= (User) httpSession.getAttribute("userLogin");
        List<Posting> postingList=postingService.queryPostingByUserID(user.getId());
        return postingList;
    }


    @RequestMapping("/deletePosting.action")
    @ResponseBody
    public int DeleteMyPosting(String posting,HttpServletRequest request, HttpServletResponse response) {//删除用户自己的贴子
        int  getDeleteResult=postingService.deletePostingByID(posting);
        return getDeleteResult;
    }


    public static String getUUID32(){//UUID生成方法
        String uuid = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        return uuid;//因为一般数据库主键为String类型，所以接收类型为String，生成的uuid数据包含-，所以要去掉-
    }
    public  String  contextToIntroduce(String context){//提取正文前100个词作为介绍
        String introduce=new String();
        if(context.length()<=100){
            introduce=context;
        }else{
            introduce=context.substring(0,100);
        }
        return  introduce;
    }
}


//
//    Posting posting=new Posting();
//        posting.setName("You Have Only One Life");
//                posting.setIntroduce("May you have enough happiness to make you sweet,enough trials to make you strong,enough sorrow to keep you human,enough hope to make you happy? Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too.");
//                postings.add(posting);
//                Posting posting2=new Posting();
//                posting2.setName("You Have Only One Life");
//                posting2.setIntroduce("May you have enough happiness to make you sweet,enough trials to make you strong,enough sorrow to keep you human,enough hope to make you happy? Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +" Always put yourself in others’shoes.If you feel that it hurts you,it probably hurts the other person, too."
//                +"I think java in org.springframework.web.bind.annotation.ResponseBody and org.springframework.web.bind.annotation.ResponseBody");
//                postings.add(posting2);
//                System.out.println("ssss2");
//
//
//                Reply reply=new Reply();
//                reply.setId("r1");
////        System.out.println(replyService.queryReplyByID(reply).toString());
//                reply.setPosting_id("p");
////        for (Reply r:replyService.queryPostingReply(reply)){
////            System.out.println(r);
////        }
////        replyService.queryAll();
////        for (Reply r:replyService.queryAll()){
////            System.out.println(r);
////        }
//
////        for(User user:userService.queryAllUsers()){
////            System.out.println(user);
////        }
//
////        for(Theme theme:themeService.queryThemeParentByID(5)){
////            System.out.println(theme);
////        }
//
//
//                for(Posting p:postingService.queryPostingByUserID("1")){
//                System.out.println(p);
//                }


    /**
     * 对Page<E>结果进行包装
     * <p/>
     * 新增分页的多项属性，主要参考:http://bbs.csdn.net/topics/360010907
     *
     * @author liuzh/abel533/isea533
     * @version 3.3.0
     * @since 3.2.2
     * 项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
     */
//当前页
//    private int pageNum;
//    //每页的数量
//    private int pageSize;
//    //当前页的数量
//    private int size;
//    //排序
//    private String orderBy;
//
////由于startRow和endRow不常用，这里说个具体的用法
////可以在页面中"显示startRow到endRow 共size条数据"
//
//    //当前页面第一个元素在数据库中的行号
//    private int startRow;
//    //当前页面最后一个元素在数据库中的行号
//    private int endRow;
//    //总记录数
//    private long total;
//    //总页数
//    private int pages;
//    //结果集
//    private List<T> list;
//
//    //第一页
//    private int firstPage;
//    //前一页
//    private int prePage;
//    //下一页
//    private int nextPage;
//    //最后一页
//    private int lastPage;
//
//    //是否为第一页
//    private boolean isFirstPage = false;
//    //是否为最后一页
//    private boolean isLastPage = false;
//    //是否有前一页
//    private boolean hasPreviousPage = false;
//    //是否有下一页
//    private boolean hasNextPage = false;
//    //导航页码数
//    private int navigatePages;
//    //所有导航页号
//    private int[] navigatepageNums;