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
 * フォローデータのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_FOL)
@NamedQueries({
@NamedQuery(
        name = JpaConst.Q_FOL_GET,
        query = JpaConst.Q_FOL_GET_DEF),
@NamedQuery(
        name = JpaConst.Q_FOL_GET_FOLEE,
        query = JpaConst.Q_FOL_GET_FOLEE_DEF),
@NamedQuery(
        name = JpaConst.Q_FOL_GET_FOLER,
        query = JpaConst.Q_FOL_GET_FOLER_DEF),
@NamedQuery(
        name = JpaConst.Q_FOL_COUNT_FOLEE,
        query = JpaConst.Q_FOL_COUNT_FOLEE_DEF),
@NamedQuery(
        name = JpaConst.Q_FOL_COUNT_FOLER,
        query = JpaConst.Q_FOL_COUNT_FOLER_DEF)
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Follow {

    /**
     * id
     */
    @Id
    @Column(name = JpaConst.FOL_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * フォローした従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_FOLER, nullable = false)
    private Employee follower;

    /**
     * フォローされた従業員
     */
    @ManyToOne
    @JoinColumn(name = JpaConst.FOL_COL_FOLEE, nullable = false)
    private Employee followee;

}
