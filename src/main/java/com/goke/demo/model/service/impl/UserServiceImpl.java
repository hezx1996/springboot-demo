package com.goke.demo.model.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goke.demo.Utlis.ExcelUtil;
import com.goke.demo.model.dao.UserDao;
import com.goke.demo.model.entity.User;
import com.goke.demo.model.entity.UserEntity;
import com.goke.demo.model.entity.tite;
import com.goke.demo.model.service.B;
import com.goke.demo.model.service.UserService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * (User)表服务实现类
 *
 * @author 隔壁子
 * @since 2020-07-20 11:50:38
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    @Resource
    private B b;

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public User queryById(Integer uid) {
        List<tite> liat = userDao.queryAlls();
        return this.userDao.queryById(uid);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<User> queryAllByLimit(int offset, int limit) {
        return this.userDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User insert(User user) {
        this.userDao.insert(user);
        return user;
    }

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    @Override
    public User update(User user) {
        this.userDao.update(user);
        return this.queryById(user.getUid());
    }

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer uid) {
        return this.userDao.deleteById(uid) > 0;
    }




    @Override
    public void out(HttpServletResponse response) {
        OutputStream out = null;
        Workbook workbook = null;
        try {
            List<User> wrinCusList = new ArrayList<>();
            User user7 = new User();
            user7.setPwd("斤");
            user7.setTitle("蔬菜:");
            user7.setTitles("大白菜");
            user7.setUname("1");
            wrinCusList.add(user7);

            User user = new User();
            user.setPwd("11111111");
            user.setTitle("haaiaao");
            user.setTitles("静静地看大口大口");
            user.setUname("小何");
            wrinCusList.add(user);
            wrinCusList.add(user);
            wrinCusList.add(user);
            wrinCusList.add(user);
            User user1 = new User();
            user1.setPwd("11111111");
            user1.setTitle("肉类:");
            user1.setTitles("静静地看大口大口");
            user1.setUname("1");
            wrinCusList.add(user1);
            User user2 = new User();
            user2.setPwd("11111111");
            user2.setTitle("haaiaao");
            user2.setTitles("静静地看大口大口");
            user2.setUname("鸡肉");
            wrinCusList.add(user2);

            User user3 = new User();
            user3.setPwd("11111111");
            user3.setTitle("蔬菜:");
            user3.setTitles("静静地看大口大口");
            user3.setUname("1");
            wrinCusList.add(user3);

            User user5 = new User();
            user5.setPwd("11111111");
            user5.setTitle("haaiaao");
            user5.setTitles("静静地看大口大口");
            user5.setUname("芹菜");
            wrinCusList.add(user5);

            User user4 = new User();
            user4.setPwd("11111111");
            user4.setTitle("水果类:");
            user4.setTitles("静静地看大口大口");
            user4.setUname("1");
            wrinCusList.add(user4);

            User user6 = new User();
            user6.setPwd("11111111");
            user6.setTitle("haaiaao");
            user6.setTitles("静静地看大口大口");
            user6.setUname("苹果");
            wrinCusList.add(user6);

            workbook = ExcelUtil.expiredExcle(wrinCusList);
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-disposition",
                    "attachment;filename=" + URLEncoder.encode("测试.xlsx", "UTF-8"));
            out = response.getOutputStream();
            workbook.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(out);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void A(HttpServletResponse response) throws Exception {
        User user = new User();
        user.setUname("A");
        this.userDao.insert(user);
        try {
            int i = 0;
            int j = 100;
            int o = j/i;
        } catch (Exception e) {
            e.printStackTrace();
          throw  new Exception("66666");
        }
         b.B(response);
    }

    /**
     * myBatis Plus 测试
     * @param response
     * @return
     */
    @Override
    public List<UserEntity> myBatisPlus(HttpServletResponse response) {
        //分装查询条件
        QueryWrapper<UserEntity> queryWrapper = new QueryWrapper<>();

        List<UserEntity> users = userDao.selectList(null);
        return users;
    }
}