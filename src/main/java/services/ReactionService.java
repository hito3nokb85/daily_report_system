package services;

import java.util.List;

import javax.persistence.NoResultException;

import actions.views.ReactionConverter;
import actions.views.ReactionView;
import constants.JpaConst;
import models.CountReaction;
import models.Employee;
import models.Reaction;
import models.ReactionType;
import models.Report;

/**
 * リアクションテーブルの操作に関わる処理を行うクラス
 */

public class ReactionService extends ServiceBase{



    /**
     * idを条件にリアクションタイプのデータを１件取得する
     * @param reaTypId
     * @return 取得データのインスタンス
     */
    public ReactionType findOne(int reaTypId) {
        return em.find(ReactionType.class, reaTypId);
    }


    /**
     * リアクションデータを１件登録する
     * @param rav リアクションデータ
     */
    public void create(ReactionView rav) {

        em.getTransaction().begin();
        em.persist(ReactionConverter.toModel(rav));
        em.getTransaction().commit();
    }


    /**
     * リアクションデータを１件取得する
     * @param employee
     * @param reactionType
     * @param report
     * @return 取得データのインスタンス 取得できない場合null
     */
    public Reaction findOne(Employee employee, ReactionType reactionType, Report report) {
        Reaction ra = null;
        try {
            ra = em.createNamedQuery(JpaConst.Q_REA_GET, Reaction.class)
                    .setParameter(JpaConst.JPQL_PARM_EMP, employee)
                    .setParameter(JpaConst.JPQL_PARM_REA_TYP, reactionType)
                    .setParameter(JpaConst.JPQL_PARM_REP, report)
                    .getSingleResult();

        }catch(NoResultException ex) {
        }

        return ra;

    }

    /**
     * リアクションデータを削除する
     */
    public void destroy(Reaction ra) {
        em.getTransaction().begin();
        em.remove(ra);
        em.getTransaction().commit();
        em.close();
    }

    /**
     * 指定した日報につけられたリアクションの件数を取得する
     *
     */
    public long countAllMine(ReactionType reactionType, Report report) {
        long count = (long) em.createNamedQuery(JpaConst.Q_REA_COUNT_ALL_MINE,Long.class)
                .setParameter(JpaConst.JPQL_PARM_REA_TYP, reactionType)
                .setParameter(JpaConst.JPQL_PARM_REP, report)
                .getSingleResult();

        return count;
    }

    /**
     * 指定した日報にリアクショした従業員を取得し、ReactionViewのリストで返却する
     * @param reactionType
     * @param report
     * @return リアクションした従業員のリスト
     */
    public List<ReactionView> getReadEmp(ReactionType reactionType, Report report){
        List<Reaction> reactions = em.createNamedQuery(JpaConst.Q_REA_GET_READ_EMP, Reaction.class)
                .setParameter(JpaConst.JPQL_PARM_REA_TYP, reactionType)
                .setParameter(JpaConst.JPQL_PARM_REP, report)
                .getResultList();

        return ReactionConverter.toViewList(reactions);
    }

    /**
     * 指定した日報につけられた既読件数を取得し、CountReactionのリストで返却する
     * @param reactionType
     * @return リアクション件数のリスト
     */
    public List<CountReaction> getCountRead(ReactionType reactionType){
        List<CountReaction> countReaction = em.createQuery(JpaConst.Q_REA_COUNT_DEF, CountReaction.class)
                .setParameter(JpaConst.JPQL_PARM_REA_TYP, reactionType)
                .getResultList();
        for(CountReaction c : countReaction) {
            System.out.println(c.getReport().getId());
            System.out.println(c.getReactionType().getReactionName());
            System.out.println(c.getCount());
        }
        return countReaction;
    }






}
