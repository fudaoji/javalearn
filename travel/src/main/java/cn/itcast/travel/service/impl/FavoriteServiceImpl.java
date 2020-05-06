package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

public class FavoriteServiceImpl implements FavoriteService {
    FavoriteDao favoriteDao = new FavoriteDaoImpl();
    /**
     * 是否收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite isFavorite(int rid, int uid) {
        return (Favorite) favoriteDao.getByUidCid(rid, uid);
    }

    /**
     * 收藏取消收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public Favorite doFavorite(int rid, int uid) {
        Favorite favorite = (Favorite) favoriteDao.getByUidCid(rid, uid);
        if(favorite == null){
            //新增
            favoriteDao.add(rid, uid);
            favorite = new Favorite();
            favorite.setStatus(1);
            favorite.setRid(rid);
            favorite.setUid(uid);
        }else{
            //编辑
            favorite.setStatus(Math.abs(favorite.getStatus() - 1));
            System.out.println(favorite); return  favorite;
            //favoriteDao.set(favorite);
        }
        return favorite;
    }
}
