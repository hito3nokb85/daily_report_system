package actions.views;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * フォローについて画面の入力値・出力値を扱うViewモデル
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FollowView {

    /**
     * id
     */
    private Integer id;

    /**
     * フォローした従業員
     */
    private EmployeeView follower;

    /**
     * フォローされた従業員
     */
    private EmployeeView followee;

}
