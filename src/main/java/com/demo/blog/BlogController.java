package com.demo.blog;

import com.demo.blog.validator.BlogValidator;
import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.demo.common.model.Blog;
import com.jfinal.kit.Kv;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.SqlPara;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: http://jfinal.com/club
 * 
 * BlogController
 * 所有 sql 与业务逻辑写在 Model 或 Service 中，不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Before(BlogInterceptor.class)
public class BlogController extends Controller {
	
	@Inject
	BlogService service;
	
	public void index() {
		setAttr("blogPage", service.paginate(getParaToInt(0, 1), 10));
		setAttr("username", "username");
		render("blog.html");
	}
	
	public void add() {
	}
	
	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中，
	 * 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(BlogValidator.class)
	public void save() {
		getBean(Blog.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		setAttr("blog", service.findById(getParaToInt()));
	}
	
	/**
	 * save 与 update 的业务逻辑在实际应用中也应该放在 serivce 之中，
	 * 并要对数据进正确性进行验证，在此仅为了偷懒
	 */
	@Before(BlogValidator.class)
	public void update() {
		getBean(Blog.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		service.deleteById(getParaToInt());
		redirect("/blog");
	}

	public void getUser() {
		//获取预定义的sql，这里使用Db.getSql方法
		String sql = Db.getSql("findUserList");
		//执行查询方法，查找id=3的数据，最后输出到页面
		renderJson(Db.find(sql,3));
	}

	public void getUserList() {
		//根据标识将参数封装进sql模板并返回完整的sql
		SqlPara sqlpara=Db.getSqlPara("findUserList1",3,345678);
		//执行查询
		Db.find(sqlpara);
		//此处用作演示返回查询结果
		renderJson(Db.find(sqlpara));
	}

	public void getUserList2() {
		//设置查询参数
		Kv cond= Kv.by("id", 3).set("pwd",345678);
		//根据标识将参数封装进sql模板并返回完整的sql
		SqlPara sqlpara=Db.getSqlPara("findUserList2",cond);
		//执行查询
		Db.find(sqlpara);
		//此处用作演示返回查询结果
		renderJson(Db.find(sqlpara));
	}

	public void getUserList3() {
		//设置查询参数
		Kv cond= Kv.by("id=",3).set("pwd=",345678);
		//封装查询参数并返回sql
		SqlPara sqlpara =Db.getSqlPara("sugar.findUserList",Kv.by("cond",cond));
		//执行查询
		Db.find(sqlpara);
		//输出查询结果
		renderJson(Db.find(sqlpara));
	}
}


