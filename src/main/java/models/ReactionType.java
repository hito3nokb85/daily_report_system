package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import constants.JpaConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * リアクションタイプのDTOモデル
 *
 */
@Table(name = JpaConst.TABLE_REA_TYP)

@Getter 
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class ReactionType {
    
    /**
     * id
     */
    @Id
    @Column(name = JpaConst.REA_TYP_COL_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    /**
     * リアクション名
     */
    @Column(name = JpaConst.REA_TYP_COL_NAME,nullable = false)
    private String reactionName;

}
