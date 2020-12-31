package com.cnxf.dao;

import com.cnxf.entity.MDUser;
import com.cnxf.entity.RolesUser;
import com.cnxf.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface UserMapper {
    List<User> selectAllUser();// 查找所有用户
    User selectById(Long id);// 根据id查找用户
    Integer insertUser(User user);// 向数据库插入user，参数为user对象
    void addUser(@Param("id") Long id,@Param("username") String username,@Param("password") String password);// 向数据库插入user，参数为user所有字段
    Integer updateUser(User user);// 更新user
    Integer deleteUserById(Long id);// 根据id删除用户
    // 参数案例
    List<User> selectByName(String username);// 根据名称查询
    void insertByMap(Map<String,Object> map );// 用map当做参数
    // sql注解
    @Insert("INSERT INTO user VALUES (#{id},#{username},#{password})")
    void insertByAnno(User user);
    @Update("UPDATE user SET id=#{id},username=#{username},password=#{password} where id=#{id}")
    void updateByAnno(Map<String,Object> map);
    @Delete("DELETE FROM user WHERE id=#{id}")
    void deleteByAnno(@Param("id") Long id);
    @Select("SELECT * FROM user WHERE username=#{as}")
    User selectByAnno(String username);
    // 字符串替换
    @Select("SELECT * FROM user WHERE ${column}=${param}")
    User selectByColumn(@Param("column") String column,@Param("param") String param);
    // 结果集案例
    Map<String,Object> selectInMap(Long id);// 映射到map
    MDUser selectInMDUser(Long id);// 数据库别名映射到MDUser
    MDUser selectInResultMap(Long id);// 使用ResultMap
    RolesUser selectRolesUser(Long id);// 返回RolesUser结果集
    RolesUser selectRolesUser2(Long id);// 嵌套select方法
    // 动态sql案例
    User selectOneBySql(Long id);
    List<User> selectAllBySql();
    List<User> selectByLimit(@Param("start") Integer start,@Param("size") Integer size);
    List<User> selectByChoose(@Param("start") Integer start,@Param("size") Integer size);
    List<User> selectNotByWhere(@Param("username") String username,@Param("id") Long id);
    List<User> selectByWhere(@Param("username") String username,@Param("id") Long id);
    void updateBySet(@Param("username") String username,@Param("password") String password,@Param("id") Long id);
    List<User> selectByTrim(@Param("username") String username,@Param("id") Long id);
    void updateByTrim(@Param("username") String username,@Param("password") String password,@Param("id") Long id);
    List<User> selectByForeach(@Param("ids") List<Long> ids);
    void insertByForeach(@Param("users") List<User> users);
    @Select({"<script>",
            "SELECT * FROM user",
            "<if test='id!=null'>",
            "WHERE id=#{id}",
            "</if>",
            "</script>"})
    List<User> selectByAnnoScript(@Param("id") Long id);
    List<User> selectByBind();
}
