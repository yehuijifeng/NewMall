package com.alsfox.mall.db.index;

import com.alsfox.mall.bean.index.IndexBean;
import com.alsfox.mall.bean.index.IndexDaohangBean;
import com.alsfox.mall.bean.index.IndexLunfanBean;
import com.alsfox.mall.bean.index.IndexMokuaiBean;
import com.alsfox.mall.bean.index.IndexMokuaiContentBean;
import com.alsfox.mall.db.base.BaseDBDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by 浩 on 2016/10/24.
 * 首页数据库操作类
 */

public class IndexDao {
    private BaseDBDao<IndexBean, Integer> indexDao;
    private BaseDBDao<IndexLunfanBean, Integer> indexLunfanDao;
    private BaseDBDao<IndexDaohangBean, Integer> indexDaohangDao;
    private BaseDBDao<IndexMokuaiBean, Integer> indexMokuaigDao;
    private BaseDBDao<IndexMokuaiContentBean, Integer> indexMokuaiContentDao;

    public IndexDao() {
        indexDao = new BaseDBDao<>(IndexBean.class);
        indexLunfanDao = new BaseDBDao<>(IndexLunfanBean.class);
        indexDaohangDao = new BaseDBDao<>(IndexDaohangBean.class);
        indexMokuaigDao = new BaseDBDao<>(IndexMokuaiBean.class);
        indexMokuaiContentDao = new BaseDBDao<>(IndexMokuaiContentBean.class);
    }

    public int insert(IndexBean indexBean) {
        int i;
        Collection<IndexLunfanBean> indexLunfanBeans = indexBean.getIndexLunfanContentList();
        Collection<IndexDaohangBean> indexDaohangBeens = indexBean.getIndexNavList();
        Collection<IndexMokuaiBean> indexMokuaiBeans = indexBean.getIndexMoudleList();


        indexLunfanDao.resetTable();
        for (IndexLunfanBean indexLunfanBean : indexLunfanBeans) {
            indexLunfanDao.insertOrUpdate(indexLunfanBean);
        }

        indexDaohangDao.resetTable();
        for (IndexDaohangBean daohangBean : indexDaohangBeens) {
            indexDaohangDao.insertOrUpdate(daohangBean);
        }
        indexDaohangDao.resetTable();
        for (IndexDaohangBean daohangBean : indexDaohangBeens) {
            indexDaohangDao.insertOrUpdate(daohangBean);
        }

        indexMokuaigDao.resetTable();
        indexMokuaiContentDao.resetTable();
        for (IndexMokuaiBean mokuaiBean : indexMokuaiBeans) {
            Collection<IndexMokuaiContentBean> indexMokuaiContentBeans = mokuaiBean.getIndexMoudleContentList();
            for (IndexMokuaiContentBean indexMokuaiContentBean : indexMokuaiContentBeans) {
                indexMokuaiContentDao.insertOrUpdate(indexMokuaiContentBean);
            }
            indexMokuaigDao.insertData(mokuaiBean);
        }

        indexDao.resetTable();
        i = indexDao.insertData(indexBean);
        return i;
    }

    public IndexBean select() {
        List<IndexBean> indexBeans = indexDao.queryByUserId("indexDbId", 1);
        List<IndexLunfanBean> indexLunfanBeens = indexLunfanDao.queryAll();
        List<IndexDaohangBean> indexDaohangBeans = indexDaohangDao.queryAll();
        List<IndexMokuaiBean> indexMokuaiBeans = indexMokuaigDao.queryAll();
        List<IndexMokuaiContentBean> indexMokuaiContentBeens = indexMokuaiContentDao.queryAll();

        for (IndexMokuaiBean indexMokuaiBean : indexMokuaiBeans) {
            List<IndexMokuaiContentBean> indexMokuaiContentBeenList = new ArrayList<>();
            for (IndexMokuaiContentBean indexMokuaiContentBean : indexMokuaiContentBeens) {
                if (indexMokuaiContentBean.getMoudleId() == indexMokuaiBean.getMoudleId()) {
                    indexMokuaiContentBeenList.add(indexMokuaiContentBean);
                }
            }
            indexMokuaiBean.setIndexMoudleContentList(indexMokuaiContentBeenList);
        }
        if (indexBeans == null || indexBeans.isEmpty()) return null;
        IndexBean indexBean = indexBeans.get(0);
        indexBean.setIndexLunfanContentList(indexLunfanBeens);
        indexBean.setIndexNavList(indexDaohangBeans);
        indexBean.setIndexMoudleList(indexMokuaiBeans);
        return indexBean;
    }
}
