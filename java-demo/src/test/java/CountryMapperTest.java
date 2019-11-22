import com.fisher.mybatis.demo.MyMapperProxy;
import com.fisher.mybatis.demo.mapper.SysRoleMapper;
import com.fisher.mybatis.demo.mapper.SysUserMapper;
import com.fisher.mybatis.demo.model.Country;
import com.fisher.mybatis.demo.model.SysRole;
import com.fisher.mybatis.demo.model.SysUser;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class CountryMapperTest {

    private static SqlSessionFactory sqlSessionFactory;

    @BeforeClass
    public static void init() {
        try {
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testSelectSysUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUser sysUser = sqlSession.selectOne("selectById", 1);
        System.out.println(sysUser.toString());

        SysUserMapper userMapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser user = userMapper.selectById(1L);
        Assert.assertNotNull(user);
        Assert.assertEquals("test12", user.getUsername());
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testSelectAllUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<SysUser> sysUsers = sqlSession.selectList("selectAll");
        System.out.println(sysUsers);
        Assert.assertTrue(sysUsers.isEmpty());
        sqlSession.close();
    }


    @Test
    public void testSelectUserRoleById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> sysRoles = mapper.selectUserRoleById(1L);
        System.out.println(sysRoles);
        sqlSession.close();
    }

    @Test
    public void testSelectUserRoleById1() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        List<SysRole> sysRoles = mapper.selectUserRoleById1(1L);
        System.out.println(sysRoles);
        sqlSession.close();
    }


    @Test
    public void testInsertSysUser()  {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = new SysUser();
        sysUser.setUsername("shefenfei");
        sysUser.setPassword("123456");
        sysUser.setEmail("she1990111@sina.com");
        sysUser.setHeadImg("123".getBytes());
        sysUser.setCreateTime(new Date());
        int i = mapper.insertSysUser(sysUser);
        System.out.println(i);
        sqlSession.commit();
        sqlSession.close();
        Assert.assertTrue(i > 0);
    }


    @Test
    public void testDeleteSysUser() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        int i = mapper.deleteSysUser(8L);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testSelectAll() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        try {
            List<Country> countries = sqlSession.selectList("findCountries");
            countries.forEach(country -> {
                System.out.println(country.getCountryName() + "..." + country.getCountryCode());
            });
        } finally {
            sqlSession.close();
        }
    }


    @Test
    public void testInsertCountry() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Country country = new Country();
        country.setCountryName("马来西亚");
        country.setCountryCode("mtl");
        sqlSession.insert("insertCountry", country);
        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testDynamicInvokeMapper() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        MyMapperProxy mapperProxy = new MyMapperProxy(SysUserMapper.class, sqlSession);
        SysUserMapper o = (SysUserMapper) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{SysUserMapper.class}, mapperProxy);
        o.selectAll().forEach(sysUser -> {
            System.out.println(sysUser);
        });
    }


    @Test
    public void testSelectSysRole() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysRoleMapper mapper = sqlSession.getMapper(SysRoleMapper.class);
        SysRole sysRole = mapper.selectSysRole(1L);
        System.out.println(sysRole);
        sqlSession.close();
    }


    @Test
    public void testDynamicSql() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectUserByCondition1("1", "she1990111@sina.com");
        Assert.assertTrue(sysUsers.isEmpty());
        sqlSession.close();
    }


    @Test
    public void testDynamicUpdateSql() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);

        SysUser sysUser = new SysUser();
        sysUser.setId(1L);
        sysUser.setCreateTime(new Date());
        sysUser.setEmail("aaron1990111@gmail.com");
        mapper.updateSysUserInfo(sysUser);

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void testChooseWhen() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = mapper.selectSysUserByIdOrName(null, "");
        Assert.assertNull(sysUser);
        sqlSession.close();
    }


    @Test
    public void testForeach() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        List<SysUser> sysUsers = mapper.selectUserInIds(Arrays.asList(1L, 2L, 3L));
        Assert.assertNull(sysUsers);
        sqlSession.close();
    }

    @Test
    public void testSelectById() {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        SysUserMapper mapper = sqlSession.getMapper(SysUserMapper.class);
        SysUser sysUser = mapper.selectById(1L);
        System.out.println(sysUser);
        sqlSession.close();
    }
}
