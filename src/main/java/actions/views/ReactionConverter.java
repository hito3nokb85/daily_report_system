package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Reaction;

/**
 * リアクションデータのDTOモデル⇔Viewモデルの変換を行うクラス
 */

public class ReactionConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param rav ReactionViewのインスタンス
     * @return Reactionのインスタンス
     */
    public static Reaction toModel(ReactionView rav) {
        return new Reaction(
                rav.getId(),
                EmployeeConverter.toModel(rav.getEmployee()),
                ReportConverter.toModel(rav.getReport()),
                rav.getReactionType());
    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param ra Reactionのインスタンス
     * @return ReactionViewのインスタンス
     */
    public static ReactionView toView(Reaction ra) {

        if(ra == null) {
            return null;
        }

        return new ReactionView(
                ra.getId(),
                EmployeeConverter.toView(ra.getEmployee()),
                ReportConverter.toView(ra.getReport()),
                ra.getReactionType());
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param List DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<ReactionView> toViewList(List<Reaction> list){
        List<ReactionView>  evs = new ArrayList<>();

        for(Reaction ra : list) {
            evs.add(toView(ra));
        }

        return evs;
    }

}
