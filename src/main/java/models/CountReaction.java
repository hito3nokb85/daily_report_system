package models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter //全てのクラスフィールドについてgetterを自動生成する(Lombok)
@Setter //全てのクラスフィールとについてsetterを自動生成する(Lombok)
@NoArgsConstructor //引数なしコンストラクタを自動生成する(Lombok)
@AllArgsConstructor //全てのクラスフィールドを引数に持つ引数ありコンストラクタを自動生成する(Lombok)


public class CountReaction {

    /**
     * リアクションされた日報
     */
    private Report report;

    /**
     * つけられたリアクションのタイプ
     */
    private ReactionType reactionType;

    /**
     * リアクションされた件数
     */
    private long count;

}
