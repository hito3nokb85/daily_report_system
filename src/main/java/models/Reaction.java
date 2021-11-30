package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * リアクションデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_REA)
@NamedQueries({
    @NamedQuery(
            name = JpaConst.Q_REA_GET,
            query = JpaConst.Q_REA_GET_DEF),
    @NamedQuery(
            name = JpaConst.Q_REA_COUNT_ALL_MINE,
            query = JpaConst.Q_REA_COUTN_ALL_MINE_DEF),
    @NamedQuery(
            name = JpaConst.Q_REA_GET_READ_EMP,
            query = JpaConst.Q_REA_GET_READ_EMP_DEF)
})


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Reaction {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.REA_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * リアクションした従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REA_COL_EMP, nullable = false)
    private Employee employee;

    /**
     * リアクションされた日報
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REA_COL_REP, nullable = false)
    private Report report;

    /**
     * リアクションの種類
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.REA_COL_REA_TYP, nullable = false)
    private ReactionType reactionType;


}
