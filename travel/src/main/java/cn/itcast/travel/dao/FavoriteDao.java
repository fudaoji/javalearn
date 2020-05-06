package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteDao {
    /**
     * 根据rid和uid获取数据
     * @param rid
     * @param uid
     * @return
     */
    public Favorite getByUidCid(int rid, int uid);

    /**
     * 新增
     * @param rid
     * @param uid
     */
    void add(int rid, int uid);

    /**
     * 编辑
     * @param favorite
     */
    void set(Favorite favorite);
}
