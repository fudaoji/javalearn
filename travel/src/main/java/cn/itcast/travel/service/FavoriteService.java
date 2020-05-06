package cn.itcast.travel.service;

import cn.itcast.travel.domain.Favorite;

public interface FavoriteService {
    /**
     * 是否收藏
     * @param rid
     * @param uid
     * @return
     */
    public Favorite isFavorite(int rid, int uid);

    /**
     * 收藏取消收藏
     * @param rid
     * @param uid
     * @return
     */
    Favorite doFavorite(int rid, int uid);
}
