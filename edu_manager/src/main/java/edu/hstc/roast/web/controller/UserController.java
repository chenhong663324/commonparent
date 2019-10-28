package edu.hstc.roast.web.controller;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.hstc.roast.module.Posting;
import edu.hstc.roast.module.User;
import edu.hstc.roast.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@RequestMapping("/user")
@Controller
public class UserController {
	@Autowired
	private UserService userService;

	
//	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
//	public String login(String username,String password, Model model,
//                                                          HttpSession session) {
//		User u=new User();
//		u.setUsername(username);
//		u.setPassword(password);
//		User user = userService.checkLoginAndPwd(u);
//		if(user != null){
//			// 将用户对象添加到Session
//			session.setAttribute("userLogin", user);
//			return "index";
//		}
//		model.addAttribute("msg", "账号或密码错误，请重新输入！");
//         // 返回到登录页面
//		return "redirect:/login.jsp";
//	}

	@RequestMapping(value = "/login.action", method = RequestMethod.POST)
	public String login(String username,String password,  RedirectAttributes redirectAttributes,HttpSession session) {
		User u=new User();
		u.setUsername(username);
		u.setPassword(password);
		User user = userService.checkLoginAndPwd(u);
		if(user != null){
			// 将用户对象添加到Session
			System.out.println(user);
			session.setAttribute("userLogin", user);
			redirectAttributes.addAttribute("id", user.getId());
			return "redirect:/mypage.html";
		}
		session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
		redirectAttributes.addFlashAttribute("msg", "账号或密码错误，请重新输入！");
		// 返回到登录页面
		return "redirect:/login.jsp";
	}

	@RequestMapping("/loadPersonalInfo.action")
	@ResponseBody
	public User loadPersonalInfo(int id) {
		return userService.queryUserByUserID(id);
	}
	//注销
	@RequestMapping("/logOut")
	public String LogOut(HttpSession session) {
		session.invalidate();
		return "redirect:/login.jsp";
	}

	// 添加用户
//	@RequestMapping(value = "/addUser.action")
//	@ResponseBody
//	public String addUser(@RequestBody User user) {
//		int i=userService.addUser(user);
//		if(i>0){
//			return "true";
//		}
//		return "flase";
//	}
	@RequestMapping("/addUser.action")
	public String addItems(User user,MultipartFile pic,HttpServletRequest request,  RedirectAttributes redirectAttributes,HttpSession session) throws IllegalStateException, IOException{

		User user1=new User();
		String h;
		//设置图片上传的路径
		String path =request.getServletContext().getRealPath("/upload");
		System.out.println("上传路径是：" + path);

		// 获取图片文件名
		String pic_name = pic.getOriginalFilename();
		System.out.println("原文件名是：" + pic_name);

		// 为了防止上传同名图片导致覆盖文件，引入随机数UUID解决。
		String newname = UUID.randomUUID().toString() + pic_name.substring(pic_name.lastIndexOf("."));
		System.out.println("新文件名是:" + newname);

		// 创建文件流对象picfile
		File picFile = new File(path, newname);
		System.out.println("文件流为：" + picFile);

		// 如果不存在则创建
		if (!picFile.exists()) {
			picFile.mkdirs();
		}
		pic.transferTo(picFile);
		user.setProfile_pic(newname);
		h=user.getUsername();
		user1=userService.queryUserByUsername(h);
		if (user1!=null){
			session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
			redirectAttributes.addFlashAttribute("msg", "用户已存在！");
			return "redirect:/login.jsp";
		}
		userService.addUser(user);
		session.removeAttribute("org.springframework.web.servlet.support.SessionFlashMapManager.FLASH_MAPS");
		redirectAttributes.addFlashAttribute("msg", "注册成功！");
		return "redirect:/login.jsp";
	}

//	@RequestMapping("/transfor.action")
//	public String login1( Model model, HttpSession session) {
//		model.addAttribute("msg", "用户名已存在！");
//		return "redirect:/login.jsp";
//	}



	  @RequestMapping("/queryUsers.action")
	    public String queryItems(@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
	        //1.引入分页插件,pn是第几页，5是每页显示多少行
	        PageHelper.startPage(pn,6);
	        //2.紧跟的查询就是一个分页查询
	        List<User> userslist =userService.queryAllUsers();
	        //3.使用PageInfo包装查询后的结果,5是连续显示的条数
	        PageInfo<User> pageInfo =new PageInfo<User>(userslist,5);
	        //4.使用model设置到前端
	        model.addAttribute("pageInfo",pageInfo);
	        //5.最后设置返回的jsp
	        return "main2";
	    }
	  
	  @RequestMapping("/delete.action")
		@ResponseBody
		public String customerDelete( int id) {
		    int rows = userService.deleteUserByID(id);
		    if(rows > 0){			
		        return "OK";
		    }else{
		        return "FAIL";			
		    }
		}
	  
	  @RequestMapping("/queryUserByID.action")
		@ResponseBody
		public User queryUserByID(User user,Model model) {
			user=userService.queryUserByUserID(user.getId());
			return user;
		}
	  
	  @RequestMapping("/updateUser.action")
		@ResponseBody
		public String updateUser(@RequestBody User user) {
			System.out.println(user);
			int i=userService.updateUserByID(user);
			if(i>0){
				return "true";
			}
			return "flase";
		}
	  
	//查询用户信息
		 @RequestMapping("/chaxun.action")
		    public String chaxun(User user,@RequestParam(value="pn",defaultValue="1")Integer pn,Model model){
		        PageHelper.startPage(pn,5);
		        List<User> userlist =userService.queryUsersByName(user);
		        PageInfo<User> pageInfo =new PageInfo<User>(userlist,5);
		        model.addAttribute("pageInfo",pageInfo);
		        return "main2";
		    }
}
