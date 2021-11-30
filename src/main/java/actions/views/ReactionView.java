package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.ReactionType;

/**
 * リアクションについて画面の入力値・出力値を扱うViewモデル
 *
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ReactionView {

    /**
     * id
     */
    private Integer id;

    /**
     * リアクションした従業員
     */
    private EmployeeView employee;

    /**
     * リアクションされた日報
     */
    private ReportView report;

    /**
     * リアクションの種類
     */
    private ReactionType reactionType;

}
