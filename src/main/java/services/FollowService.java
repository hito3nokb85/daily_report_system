package services;

import java.util.List;

import javax.persistence.NoResultException;

import actions.views.FollowConverter;
import actions.views.FollowView;
import constants.JpaConst;
import models.Employee;
import models.Follow;

/**
 * フォローテーブルの操作に関わる処理を行うクラス
 *
 */

public class FollowService extends ServiceBase {

    /**
     * フォローデータを1件登録する
     * @param fv
     */
    public void create(FollowView fv) {

        em.getTransaction().begin();
        em.persist(FollowConverter.toModel(fv));
        em.getTransaction().commit();
    }

    /**
     * フォローデータを1件取得する
     * @param follower
     * @param followee
     * @return 取得データのインスタンス 取得できない場合null
     */
    public Follow findOne(Employee follower, Employee followee) {
        Follow f = null;
        try {
            f = em.createNamedQuery(JpaConst.Q_FOL_GET, Follow.class)
                    .setParameter(JpaConst.JPQL_PARM_FOLLOWER, follower)
                    .setParameter(JpaConst.JPQL_PARM_FOLLOWEE, followee)
                    .getSingleResult();
        }catch(NoResultException ex) {
        }

        return f;

    }

    /**
     * フォローデータを削除する
     */
    public void destroy(Follow f) {
        em.getTransaction().begin();
        em.remove(f);
        em.getTransaction().commit();
    }

    /**
     * 指定した従業員がフォローしている従業員を取得し、FollowViewのリストで返却する
     * @param employee
     * @return フォロー中の従業員のリスト
     */
    public List<FollowView> getFollowee(Employee employee){
        List<Follow> followees = em.createNamedQuery(JpaConst.Q_FOL_GET_FOLEE, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, employee)
                .getResultList();

        return FollowConverter.toViewList(followees);
    }

    /**
     * 指定した従業員をフォローしている従業員を取得し、FollowViewのリストで返却する
     * @param employee
     * @return フォロワーのリスト
     */
    public List<FollowView> getFollower(Employee employee){
        List<Follow> followers = em.createNamedQuery(JpaConst.Q_FOL_GET_FOLER, Follow.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, employee)
                .getResultList();

        return FollowConverter.toViewList(followers);
    }



    /**
     * 指定した従業員がフォローしている従業員の件数を取得し、返却する
     * @param employee
     * @return フォロー中の件数
     */
    public long countFollowee(Employee employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT_FOLEE, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, employee)
                .getSingleResult();

        return count;
    }

    /**
     * 指定した従業員をフォローしている従業員の件数を取得し、返却する
     * @param employee
     * @return フォロワーの件数
     */
    public long countFollower(Employee employee) {

        long count = (long) em.createNamedQuery(JpaConst.Q_FOL_COUNT_FOLER, Long.class)
                .setParameter(JpaConst.JPQL_PARM_EMPLOYEE, employee)
                .getSingleResult();

        return count;
    }



}
