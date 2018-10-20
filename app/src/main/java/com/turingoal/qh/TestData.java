package com.turingoal.qh;

import android.util.Log;

import com.turingoal.qh.bean.AttributeBean;
import com.turingoal.qh.bean.AuditBean;
import com.turingoal.qh.bean.BillboardBean;
import com.turingoal.qh.bean.DailyBean;
import com.turingoal.qh.bean.DynamicBean;
import com.turingoal.qh.bean.HallAllBean;
import com.turingoal.qh.bean.HallAssignedBean;
import com.turingoal.qh.bean.HallConventionalBean;
import com.turingoal.qh.bean.HallRewardBean;
import com.turingoal.qh.bean.HonorBean;
import com.turingoal.qh.bean.LeaveBean;
import com.turingoal.qh.bean.LevelBean;
import com.turingoal.qh.bean.MallBackBean;
import com.turingoal.qh.bean.MallLifeBean;
import com.turingoal.qh.bean.MallStaminaBean;
import com.turingoal.qh.bean.MemoBean;
import com.turingoal.qh.bean.OvertimeBean;
import com.turingoal.qh.bean.RankBean;
import com.turingoal.qh.bean.TaskBean;
import com.turingoal.qh.bean.TeamBean;
import com.turingoal.qh.bean.WebBean;
import com.turingoal.qh.bean.WeeklyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 提供虚假数据
 */

public class TestData {

    public static List<LevelBean> getLevelBeans() {
        List<LevelBean> levelBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            LevelBean levelBean = new LevelBean();
            levelBean.setId("id" + i);
            levelBean.setStr("等级规则" + i);
            levelBeans.add(levelBean);
        }
        return levelBeans;
    }

    public static List<HonorBean> getHonorBeans() {
        List<HonorBean> honorBeans = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            HonorBean honorBean = new HonorBean();
            honorBean.setId("id" + i);
            honorBean.setStr("头衔规则" + i);
            honorBeans.add(honorBean);
        }
        return honorBeans;
    }

    public static List<RankBean> getRankBeans() {
        List<RankBean> rankBeans = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            RankBean rankBean = new RankBean();
            rankBean.setId("idtestdata" + i);
            rankBean.setAvatar(getAvatar());
            rankBean.setName(getName());
            rankBean.setValue(new Random().nextInt(9999));
            rankBeans.add(rankBean);
        }
        return rankBeans;
    }

    public static List<MallBackBean> getMallBackBeans() { // 回城道具
        List<MallBackBean> mallBackBeans = new ArrayList<>();
        MallBackBean mallBackBean1 = new MallBackBean();
        mallBackBean1.setId("hcf1");
        mallBackBean1.setPicRes(R.drawable.ic_mall_back);
        mallBackBean1.setName("回城符");
        mallBackBean1.setDescribe("可以抵扣假期一天，使用没有限制。");
        mallBackBean1.setPrice(100);
        mallBackBeans.add(mallBackBean1);
        return mallBackBeans;
    }

    public static List<MallStaminaBean> getMallStaminaBeans() { // 体力药水
        List<MallStaminaBean> mallStaminaBeans = new ArrayList<>();

        MallStaminaBean mallStaminaBean1 = new MallStaminaBean();
        mallStaminaBean1.setId("tlys1");
        mallStaminaBean1.setPicRes(R.drawable.ic_mall_stamina_big);
        mallStaminaBean1.setName("体力药水（大）");
        mallStaminaBean1.setDescribe("大瓶体力药水，补充大量体力值。");
        mallStaminaBean1.setRestore(100);
        mallStaminaBean1.setPrice(100);
        mallStaminaBeans.add(mallStaminaBean1);

        MallStaminaBean mallStaminaBean2 = new MallStaminaBean();
        mallStaminaBean2.setId("tlys2");
        mallStaminaBean2.setPicRes(R.drawable.ic_mall_stamina_medium);
        mallStaminaBean2.setName("体力药水（中）");
        mallStaminaBean2.setDescribe("中瓶体力药水，补充一定量体力值。");
        mallStaminaBean2.setRestore(50);
        mallStaminaBean2.setPrice(50);
        mallStaminaBeans.add(mallStaminaBean2);

        MallStaminaBean mallStaminaBean3 = new MallStaminaBean();
        mallStaminaBean3.setId("tlys3");
        mallStaminaBean3.setPicRes(R.drawable.ic_mall_stamina_small);
        mallStaminaBean3.setName("体力药水（小）");
        mallStaminaBean3.setDescribe("小瓶体力药水，补充少量体力值。");
        mallStaminaBean3.setRestore(10);
        mallStaminaBean3.setPrice(10);
        mallStaminaBeans.add(mallStaminaBean3);

        return mallStaminaBeans;
    }

    public static List<MallLifeBean> getMallLifeBeans() { // 生命药水
        List<MallLifeBean> mallLifeBeans = new ArrayList<>();

        MallLifeBean mallLifeBean1 = new MallLifeBean();
        mallLifeBean1.setId("smys1");
        mallLifeBean1.setPicRes(R.drawable.ic_mall_life_big);
        mallLifeBean1.setName("生命药水（大）");
        mallLifeBean1.setDescribe("大瓶生命药水，补充大量生命值。");
        mallLifeBean1.setRestore(100);
        mallLifeBean1.setPrice(100);
        mallLifeBeans.add(mallLifeBean1);

        MallLifeBean mallLifeBean2 = new MallLifeBean();
        mallLifeBean2.setId("smys2");
        mallLifeBean2.setPicRes(R.drawable.ic_mall_life_medium);
        mallLifeBean2.setName("生命药水（中）");
        mallLifeBean2.setDescribe("中瓶生命药水，补充一定量生命值。");
        mallLifeBean2.setRestore(50);
        mallLifeBean2.setPrice(50);
        mallLifeBeans.add(mallLifeBean2);

        MallLifeBean mallLifeBean3 = new MallLifeBean();
        mallLifeBean3.setId("smys3");
        mallLifeBean3.setPicRes(R.drawable.ic_mall_life_small);
        mallLifeBean3.setName("生命药水（小）");
        mallLifeBean3.setDescribe("小瓶生命药水，补充少量生命值。");
        mallLifeBean3.setRestore(10);
        mallLifeBean3.setPrice(10);
        mallLifeBeans.add(mallLifeBean3);

        return mallLifeBeans;
    }

    private static String getName() {
        String[] name = {"鸿祜", "欣家", "康震", "裕彬", "运文", "芃铭", "运韦", "禧运", "哲凯", "骞仕", "轩嘉", "翰谷", "星骞", "振强", "休驰", "运锦", "寅晨", "骏鸿"};
        return name[new Random().nextInt(name.length)];
    }

    public static String getAvatar(int... i) {
        List<String> avatar = new ArrayList<>();
        avatar.add("http://p2.so.qhimgs1.com/bdr/_240_/t011f596b90b752ccb2.jpg");
        avatar.add("http://p2.so.qhmsg.com/bdr/_240_/t01ba1bbe8aeac775e7.jpg");
        avatar.add("http://p2.so.qhmsg.com/bdr/_240_/t013a57b560b4383747.jpg");
        avatar.add("http://p4.so.qhmsg.com/bdr/_240_/t01b44e6c69e4545ca9.jpg");
        avatar.add("http://p0.so.qhimgs1.com/bdr/_240_/t012aaa7acf7eff735c.jpg");
        avatar.add("http://p2.so.qhimgs1.com/bdr/_240_/t01a1a81fb0811b8982.png");
        avatar.add("http://p1.so.qhmsg.com/bdr/_240_/t011a9155bb14545416.jpg");
        avatar.add("http://p0.so.qhimgs1.com/bdr/_240_/t01005d8e7329142d5c.jpg");
        avatar.add("http://p3.so.qhimgs1.com/bdr/_240_/t016530d2da8c62a863.jpg");
        avatar.add("http://p2.so.qhimgs1.com/bdr/_240_/t012ca69bd18aa6b37e.jpg");
        avatar.add("http://p0.so.qhmsg.com/bdr/_240_/t01f6c6128219504435.jpg");
        avatar.add("http://p2.so.qhmsg.com/bdr/_240_/t01e0fdf810a74adb97.jpg");
        avatar.add("http://p4.so.qhmsg.com/bdr/_240_/t014641023bd218f839.jpg");
        avatar.add("http://p4.so.qhmsg.com/bdr/_240_/t01ac7483436fcbb979.jpg");
        avatar.add("http://p5.so.qhimgs1.com/bdr/_240_/t010a174085bd1d2ebb.jpg");
        avatar.add("http://p5.so.qhimgs1.com/bdr/_240_/t01d7d131b2d26c45eb.jpg");
        avatar.add("http://p0.so.qhmsg.com/bdr/_240_/t01901fb8393690746f.jpg");
        avatar.add("http://p1.so.qhimgs1.com/bdr/_240_/t0113fc6eddd476538d.jpg");
        avatar.add("http://p2.so.qhimgs1.com/bdr/_240_/t0129f730eec684ae3e.jpg");
        avatar.add("http://p3.so.qhimgs1.com/bdr/_240_/t01c28f1f6d28a226f3.jpg");
        avatar.add("http://p0.so.qhimgs1.com/bdr/_240_/t013c12d6599f5abfbc.jpg");
        avatar.add("http://p2.so.qhimgs1.com/bdr/_240_/t01fe40bfba5e57439e.jpg");
        avatar.add("http://p1.so.qhimgs1.com/bdr/_240_/t01b9ecdd6f7c26cbdd.jpg");
        avatar.add("http://p1.so.qhimgs1.com/bdr/_240_/t01ed4a7ecd1788c66d.jpg");
        if (i.length == 0) { // 如果转入了非0的值，则把第一张返回回去
            return avatar.get(new Random().nextInt(avatar.size()));
        } else {
            return avatar.get(1);
        }
    }

    private static String getString() {
        String base = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        int length = new Random().nextInt(500);
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
