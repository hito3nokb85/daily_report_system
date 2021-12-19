package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.FollowView;
import actions.views.ReactionView;
import actions.views.ReportConverter;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.JpaConst;
import models.Follow;
import models.Report;
import services.EmployeeService;
import services.FollowService;
import services.ReactionService;
import services.ReportService;

/**
 * フォローに関わる処理を行うActionクラス
 *
 */
public class FollowAction extends ActionBase {

    private EmployeeService empService;
    private FollowService folService;
    private ReportService repService;
    private ReactionService reaService;

    /**
     * メソッドを実行する
     */
    @Override
    public void process() throws ServletException, IOException {

        empService = new EmployeeService();
        folService = new FollowService();
        repService = new ReportService();
        reaService = new ReactionService();

        invoke();

        empService.close();
        folService.close();
        repService.close();
        reaService.close();
    }

    /**
     * フォロー管理画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void follow() throws ServletException, IOException{

        //指定されたページ数の画面に表示する従業員データを取得
        int page = getPage();
        List<EmployeeView> employees = empService.getPerPage(page);

        //セッションからログイン中の従業員情報を取得
        EmployeeView employee = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //ログイン従業員のフォロイー・フォロワーリストを取得
        List<FollowView> followees = folService.getFollowee(EmployeeConverter.toModel(employee));
        List<FollowView> followers = folService.getFollower(EmployeeConverter.toModel(employee));

        //ログイン従業員のフォロイー・フォロワー件数を取得
        long countFollowee = folService.countFollowee(EmployeeConverter.toModel(employee));
        long countFollower = folService.countFollower(EmployeeConverter.toModel(employee));

        putRequestScope(AttributeConst.EMPLOYEES, employees); //取得した従業員データ
        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.FOLLOWEES, followees); //取得したフォロイーデータ
        putRequestScope(AttributeConst.FOLLOWERS, followers); //取得したフォロワーデータ
        putRequestScope(AttributeConst.COUNT_FOLEE, countFollowee); //フォロイー件数
        putRequestScope(AttributeConst.COUNT_FOLER, countFollower); //フォロワー件数

        putRequestScope(AttributeConst.TOKEN, getTokenId());

      //フォロー管理画面を表示
        forward(ForwardConst.FW_FOL_FOL);
    }

    /**
     * フォローデータを登録・削除する
     * @throws ServletException
     * @throws IOException
     */
    public void create() throws ServletException, IOException{

        if(checkToken()) {

        //セッションからログイン中の従業員情報を取得
        EmployeeView follower = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //idを条件に、フォロー対象の従業員情報を取得
        EmployeeView followee = empService.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));

        //フォローデータを1件取得する
        Follow f = folService.findOne(EmployeeConverter.toModel(follower), EmployeeConverter.toModel(followee));

        if(f == null) {
            //フォローのインスタンスを作成する
            FollowView fv = new FollowView(
                    null,
                    follower,
                    followee);

            folService.create(fv);

            //フォロー済みの場合は削除する
        }else {
            folService.destroy(f);
        }
    }
        //フォロー管理画面を再表示
        follow();
    }

    /**
     * フォローしている従業員の日報一覧画面を表示する
     * @throws ServletException
     * @throws IOException
     */
    public void index() throws ServletException, IOException{

        //セッションからログイン中の従業員情報を取得
        EmployeeView follower = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        //指定されたページ数の一覧画面に表示する日報を取得する
        int page = getPage();
        List<Report> reports = repService.getFolloweePerPage(EmployeeConverter.toModel(follower), page);

        //日報データの件数を取得
        long count = repService.countFollowee(EmployeeConverter.toModel(follower));

        //ログイン中の従業員が既読をつけた日報を取得する
        List<ReactionView> readReports = reaService.getReadRep(EmployeeConverter.toModel(follower));

        putRequestScope(AttributeConst.PAGE, page); //ページ数
        putRequestScope(AttributeConst.REPORTS, ReportConverter.toViewList(reports)); //日報データ
        putRequestScope(AttributeConst.REP_COUNT, count); //日報データの件数
        putRequestScope(AttributeConst.MAX_ROW, JpaConst.ROW_PER_PAGE); //1ページに表示するレコードの数
        putRequestScope(AttributeConst.READ_REPORTS, readReports); //既読がついた日報データ


        //一覧画面を表示
        forward(ForwardConst.FW_FOL_INDEX);


    }


}
