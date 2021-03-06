package constants;

/**
 * DB関連の項目値を定義するインターフェース
 * ※インターフェイスに定義した変数はpublic static final 修飾子がついているとみなされる
 */

public interface JpaConst {

    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "daily_report_system";

    //データ取得件数の最大値
    int ROW_PER_PAGE = 15; //1ページに表示するレコードの数

    //従業員テーブル
    String TABLE_EMP = "employees"; //テーブル名
    //従業員テーブルカラム
    String EMP_COL_ID = "id"; //id
    String EMP_COL_CODE = "code"; //社員番号
    String EMP_COL_NAME = "name"; //氏名
    String EMP_COL_PASS = "password"; //パスワード
    String EMP_COL_ADMIN_FLAG = "admin_flag"; //管理者権限
    String EMP_COL_CREATED_AT = "created_at"; //登録日時
    String EMP_COL_UPDATED_AT = "updated_at"; //更新日時
    String EMP_COL_DELETE_FLAG = "delete_flag"; //削除フラグ

    int ROLE_ADMIN = 1; //管理者権限ON(管理者)
    int ROLE_GENERAL = 0; //管理者権限OFF(一般)
    int EMP_DEL_TRUE = 1; //削除フラグON(削除済み)
    int EMP_DEL_FALSE = 0; //削除フラグOFF(現役)

    //日報テーブル
    String TABLE_REP = "reports"; //テーブル名
    //日報テーブルカラム
    String REP_COL_ID = "id"; //id
    String REP_COL_EMP = "employee_id"; //日報を作成した従業員のid
    String REP_COL_REP_DATE = "report_date"; //いつの日報かを示す日付
    String REP_COL_TITLE = "title"; //日報のタイトル
    String REP_COL_CONTENT = "content"; //日報の内容
    String REP_COL_CREATED_AT = "created_at"; //登録日時
    String REP_COL_UPDATED_AT = "updated_at"; //更新日時

    //リアクションタイプテーブル
    String TABLE_REA_TYP = "reaction_types"; //テーブル名
    //リアクションテーブルカラム
    String REA_TYP_COL_ID = "id"; //id
    String REA_TYP_COL_NAME = "reaction_name"; //リアクション名

    //リアクションテーブル
    String TABLE_REA = "reactions"; //テーブル名
    //リアクションテーブルカラム
    String REA_COL_ID = "id"; //id
    String REA_COL_EMP = "employee_id"; //リアクションした従業員のid
    String REA_COL_REP = "report_id"; //リアクションされた日報のid
    String REA_COL_REA_TYP = "reaction_type_id"; //リアクションの種類のid

    int REA_TYP_READ = 1; //既読
    int REA_TYP_LIKE = 2; //いいね！

    //フォローテーブル
    String TABLE_FOL = "follows"; //テーブル名
    //フォローテーブルカラム
    String FOL_COL_ID = "id"; //id
    String FOL_COL_FOLER = "follower_id"; //フォロワーid
    String FOL_COL_FOLEE = "followee_id"; //フォロイーid

    //Entity名
    String ENTITY_EMP = "employee"; //従業員
    String ENTITY_REP = "report"; //日報
    String ENTITY_REA = "reaction"; //リアクション
    String ENTITY_FOL = "follow"; //フォロー

    //JSQL内パラメータ
    String JPQL_PARM_CODE = "code"; //社員番号
    String JPQL_PARM_PASSWORD = "password"; //パスワード
    String JPQL_PARM_EMPLOYEE = "employee"; //従業員
    String JPQL_PARM_REPORT = "report"; //日報
    String JPQL_PARM_EMP = "EmployeeView"; //従業員
    String JPQL_PARM_REA_TYP = "ReactionType"; //リアクションタイプ
    String JPQL_PARM_REP = "ReportView"; //日報
    String JPQL_PARM_FOLLOWER = "follower"; //フォロワー
    String JPQL_PARM_FOLLOWEE = "followee"; //フォロイー
    String JPQL_PARM_FOLLOWEES = "followees";

    //NamedQueryのnameとquery
    //全ての従業員をidの降順に取得する
    String Q_EMP_GET_ALL = ENTITY_EMP + ".getAll"; //name
    String Q_EMP_GET_ALL_DEF = "SELECT e FROM Employee AS e ORDER BY e.id DESC"; //query
    //全ての従業員の件数を取得する
    String Q_EMP_COUNT = ENTITY_EMP + ".count";
    String Q_EMP_COUNT_DEF = "SELECT COUNT(e) FROM Employee AS e";
    //社員番号とハッシュ化済パスワードを条件に未削除の従業員を取得する
    String Q_EMP_GET_BY_CODE_AND_PASS = ENTITY_EMP + ".getByCodeAndPass";
    String Q_EMP_GET_BY_CODE_AND_PASS_DEF = "SELECT e FROM Employee AS e WHERE e.deleteFlag = 0 AND e.code = :" + JPQL_PARM_CODE + " AND e.password = :" + JPQL_PARM_PASSWORD;
    //指定した社員番号を保持する従業員の件数を取得する
    String Q_EMP_COUNT_RESISTERED_BY_CODE = ENTITY_EMP + ".countRegisteredByCode";
    String Q_EMP_COUNT_RESISTERED_BY_CODE_DEF = "SELECT COUNT(e) FROM Employee AS e WHERE e.code = :" + JPQL_PARM_CODE;
    //全ての日報をidの降順に取得する
    String Q_REP_GET_ALL = ENTITY_REP + ".getAll";
    String Q_REP_GET_ALL_DEF = "SELECT r FROM Report AS r ORDER BY r.id DESC";
    //全ての日報の件数を取得する
    String Q_REP_COUNT = ENTITY_REP + ".count";
    String Q_REP_COUNT_DEF = "SELECT COUNT(r) FROM Report AS r";
    //指定した従業員が作成した日報を全件idの降順で取得する
    String Q_REP_GET_ALL_MINE  = ENTITY_REP + ".getAllMine";
    String Q_REP_GET_ALL_MINE_DEF = "SELECT r FROM Report AS r  WHERE r.employee = :" + JPQL_PARM_EMPLOYEE + " ORDER BY r.id DESC";
    //指定した従業員が作成した日報の件数を取得する
    String Q_REP_COUNT_ALL_MINE = ENTITY_REP + ".countAllMine";
    String Q_REP_COUNT_ALL_MINE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee = :" + JPQL_PARM_EMPLOYEE;

    //リアクションデータを1件取得する
    String Q_REA_GET = ENTITY_REA + ".get";
    String Q_REA_GET_DEF = "SELECT rea FROM Reaction AS rea WHERE rea.employee = :" + JPQL_PARM_EMP + " AND rea.reactionType = :" + JPQL_PARM_REA_TYP + " AND rea.report = :" + JPQL_PARM_REP;
    //指定した日報につけられたリアクション件数を取得する
    String Q_REA_COUNT_ALL_MINE = ENTITY_REA + ".countAllMine";
    String Q_REA_COUTN_ALL_MINE_DEF = "SELECT COUNT(rea) FROM Reaction AS rea WHERE rea.reactionType = :" + JPQL_PARM_REA_TYP + " AND rea.report = :" + JPQL_PARM_REP;
    //指定した日報にリアクションをつけた社員を取得する
    String Q_REA_GET_READ_EMP = ENTITY_REA + ".getReadEmployee";
    String Q_REA_GET_READ_EMP_DEF = "SELECT rea FROM Reaction AS rea WHERE rea.reactionType = :" + JPQL_PARM_REA_TYP + " AND rea.report = :" + JPQL_PARM_REP;
    //各日報につけられたリアクション件数を、一覧画面に表示する分取得する
    String Q_REA_COUNT_DEF = "SELECT NEW models.CountReaction(rea.report, rea.reactionType, count(rea)) FROM Reaction As rea GROUP BY rea.report, rea.reactionType HAVING rea.reactionType =:" + JPQL_PARM_REA_TYP;
    //指定した従業員がリアクションをつけた日報を取得する
    String Q_REA_GET_READ_REP = ENTITY_REA + ".getReadReport";
    String Q_REA_GET_READ_REP_DEF = "SELECT rea FROM Reaction AS rea WHERE rea.reactionType = 1 AND rea.employee = :" + JPQL_PARM_EMPLOYEE;

    //フォローデータを1件取得する
    String Q_FOL_GET = ENTITY_FOL + ".get";
    String Q_FOL_GET_DEF = "SELECT f FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_FOLLOWER + " AND f.followee = :" + JPQL_PARM_FOLLOWEE;
    //指定した従業員がフォローしている従業員を取得する
    String Q_FOL_GET_FOLEE = ENTITY_FOL + ".getFollowee";
    String Q_FOL_GET_FOLEE_DEF = "SELECT f FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_EMPLOYEE;
    //指定した従業員をフォローしている従業員を取得する
    String Q_FOL_GET_FOLER = ENTITY_FOL + ".getFollower";
    String Q_FOL_GET_FOLER_DEF = "SELECT f FROM Follow AS f WHERE f.followee = :" + JPQL_PARM_EMPLOYEE;
    //指定した従業員がフォローしている従業員の件数を取得する
    String Q_FOL_COUNT_FOLEE = ENTITY_FOL + ".countFolowee";
    String Q_FOL_COUNT_FOLEE_DEF = "SELECT COUNT(f) FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_EMPLOYEE;
    //指定した従業員をフォローしている従業員の件数を取得する
    String Q_FOL_COUNT_FOLER = ENTITY_FOL + "countFollower";
    String Q_FOL_COUNT_FOLER_DEF = "SELECT COUNT(f) FROM Follow AS f WHERE f.followee = :" + JPQL_PARM_EMPLOYEE;

    //指定した従業員がフォローしている従業員の日報を取得する
    String Q_REP_GET_FOLEE = ENTITY_REP + ".getFollowee";
    String Q_REP_GET_FOLEE_DEF = "SELECT r FROM Report AS r WHERE r.employee IN (SELECT f.followee FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_EMPLOYEE + ")" + " ORDER BY r.id DESC";
    //指定した従業員がフォローしている従業員の日報の件数を取得する
    String Q_REP_COUNT_FOLEE = ENTITY_REP + "countFollowee";
    String Q_REP_COUNT_FOLEE_DEF = "SELECT COUNT(r) FROM Report AS r WHERE r.employee IN (SELECT f.followee FROM Follow AS f WHERE f.follower = :" + JPQL_PARM_EMPLOYEE + ")" ;

}
