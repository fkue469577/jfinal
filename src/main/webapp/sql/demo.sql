#sql("findUserList")
    select * from t_user where id = ?
#end

#sql("findUserList1")
    select * from t_user where id = #para(0) and pwd=#para(1)
#end

#sql("findUserList2")
    select * from t_user where id = #para(id) and pwd=#para(pwd)
#end

#namespace("sugar")
    #sql("findUserList")
      select * from t_user where id=#para(id) and pwd=#para(pwd)
    #end
#end
