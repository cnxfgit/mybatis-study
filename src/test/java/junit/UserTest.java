package junit;

import com.cnxf.dao.UserMapper;
import com.cnxf.entity.MDUser;
import com.cnxf.entity.Role;
import com.cnxf.entity.RolesUser;
import com.cnxf.entity.User;
import com.cnxf.utils.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {
    @Test
    public void test1(){
        // 通过工具类获取sqlSession
        SqlSession sqlSession = MybatisUtil.init();
        // 获取mapper接口
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        // 执行查询语句
        List<User> list = userMapper.selectAllUser();
        // 打印结果
        for (User user : list) {
            System.out.println(user.getId()+user.getUsername()+user.getPassword());
        }
        // 关闭sqlSession
        sqlSession.close();
    }

    @Test
    public void test2(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1L);
            System.out.println(user.getId()+user.getUsername()+user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test3(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer result = userMapper.insertUser(new User(4L,"阿蒜","123"));
            if(result>0){
                System.out.println("插入成功！");
            }else {
                System.out.println("插入失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void test4(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.addUser(5L,"小明","1234");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();//开启return sqlSessionFactory.openSession(true);则可以注释掉
            sqlSession.close();
        }
    }

    @Test
    public void test5(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer res = userMapper.updateUser(new User(5L,"小明","abc"));
            if(res>0){
                System.out.println("更新成功！");
            }else {
                System.out.println("更新失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void test6(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Integer res = userMapper.deleteUserById(5L);
            if(res>0){
                System.out.println("删除成功！");
            }else {
                System.out.println("删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    // 参数案例
    @Test
    public void test7(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> list = userMapper.selectByName("阿蒜");
            for (User user : list) {
                System.out.println(user.getId()+user.getUsername()+user.getPassword());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test8(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",5L);
            map.put("username","map");
            map.put("password","map");
            userMapper.insertByMap(map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void test9(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.insertByAnno(new User(6L,"insertAnno","insertAnno"));// 注解插入
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("id",6L);
            map.put("username","updateAnno");
            map.put("password","updateAnno");
            userMapper.updateByAnno(map);// 注解更新
            userMapper.deleteByAnno(6L);// 注解删除
            User user = userMapper.selectByAnno("阿蒜");// 注解查询
            System.out.println(user.getId()+user.getUsername()+user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void test10(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectByColumn("username","'阿蒜'");
            System.out.println(user.getId()+user.getUsername()+user.getPassword());
            user = userMapper.selectByColumn("id","1");
            System.out.println(user.getId()+user.getUsername()+user.getPassword());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    // 结果集案例
    @Test
    public void test11(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            Map<String,Object> map = userMapper.selectInMap(1L);
            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test12(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            MDUser mdUser = userMapper.selectInMDUser(1L);
            System.out.println(mdUser.getUsername()+mdUser.getMdPassword());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test13(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            MDUser mdUser = userMapper.selectInResultMap(1L);
            System.out.println(mdUser.getId()+mdUser.getUsername()+mdUser.getMdPassword());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test14(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            RolesUser rolesUser = userMapper.selectRolesUser(1L);
            System.out.println(rolesUser.getId()+rolesUser.getUsername()+rolesUser.getPassword()+rolesUser.getUserStore().getName());
            for (Role role : rolesUser.getRoles()) {
                System.out.println(role.getId()+role.getRoleName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test15(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            RolesUser rolesUser = userMapper.selectRolesUser2(1L);
            System.out.println(rolesUser.getId()+rolesUser.getUsername()+rolesUser.getPassword()+rolesUser.getUserStore().getName());
            for (Role role : rolesUser.getRoles()) {
                System.out.println(role.getId()+role.getRoleName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    // 缓存测试
    @Test
    public void test16(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1L);
            User user2 = userMapper.selectById(1L);
            System.out.println(user==user2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test17(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1L);
            userMapper.insertUser(new User(66L,"te","te"));
            User user2 = userMapper.selectById(1L);
            System.out.println(user==user2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.commit();
            sqlSession.close();
        }
    }

    @Test
    public void test18(){
        SqlSession sqlSession = MybatisUtil.init();
        SqlSession sqlSession2 = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1L);
            sqlSession.close();

            UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
            User user2 = userMapper2.selectById(1L);
            System.out.println(user==user2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession2.close();
        }
    }

    // 动态sql案例
    @Test
    public void test19(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectOneBySql(1L);
            List<User> users = userMapper.selectAllBySql();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test20(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users = userMapper.selectByLimit(1,3);
            List<User> users1 = userMapper.selectByLimit(1,null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test21(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<User> users1 = userMapper.selectByChoose(1,3);
            List<User> users2 = userMapper.selectByChoose(null,2);
            List<User> users3 = userMapper.selectByChoose(null,null);
            List<User> users4 = userMapper.selectByChoose(1,null);
            System.out.println(users3==users4);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test22(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
//            List<User> users1 = userMapper.selectNotByWhere("菜",1L);
//            List<User> users2 = userMapper.selectNotByWhere("菜",null);
//            List<User> users3 = userMapper.selectNotByWhere(null,null);
            List<User> users4 = userMapper.selectByWhere("菜",1L);
            List<User> users5 = userMapper.selectByWhere("菜",null);
            List<User> users6 = userMapper.selectByWhere(null,null);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test23(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateBySet("菜鸟先飞","cnxf",3L);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test24(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.selectByTrim("菜",null);
            userMapper.updateByTrim("菜鸟","cn",1L);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test25(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            List<Long> ids = new ArrayList<Long>();
            ids.add(1L);ids.add(2L);ids.add(3L);
            userMapper.selectByForeach(ids);
            List<User> users = new ArrayList<User>();
            users.add(new User(44L,"44","44"));
            users.add(new User(45L,"45","45"));
            users.add(new User(46L,"46","46"));
            userMapper.insertByForeach(users);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test26(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.selectByAnnoScript(1L);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }

    @Test
    public void test27(){
        SqlSession sqlSession = MybatisUtil.init();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.selectByBind();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            sqlSession.close();
        }
    }
}
