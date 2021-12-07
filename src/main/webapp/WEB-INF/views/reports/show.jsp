<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="constants.ForwardConst" %>
<%@ page import="constants.AttributeConst" %>

<c:set var="actRep" value="${ForwardConst.ACT_REP.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />
<c:set var="commEdt" value="${ForwardConst.CMD_EDIT.getValue()}" />
<c:set var="commRea" value="${ForwardConst.CMD_CREATE_REA.getValue()}" />

<c:import url="/WEB-INF/views/layout/app.jsp">
    <c:param name="content">

        <h2>日報 詳細ページ</h2>

        <table>
            <tbody>
                <tr>
                    <th>氏名</th>
                    <td><c:out value="${report.employee.name}" /></td>
                </tr>
                <tr>
                    <th>日付</th>
                    <fmt:parseDate value="${report.reportDate}" pattern="yyyy-MM-dd" var="reportDay" type="date" />
                    <td><fmt:formatDate value='${reportDay}' pattern='yyyy-MM-dd' /></td>
                </tr>
                <tr>
                    <th>内容</th>
                    <td><pre><c:out value="${report.content}" /></pre></td>
                </tr>
                <tr>
                    <th>登録日時</th>
                    <fmt:parseDate value="${report.createdAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="createDay" type="date" />
                    <td><fmt:formatDate value="${createDay}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                </tr>
                <tr>
                    <th>更新日時</th>
                    <fmt:parseDate value="${report.updatedAt}" pattern="yyyy-MM-dd'T'HH:mm:ss" var="updateDay" type="date" />
                    <td><fmt:formatDate value="${updateDay}" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                </tr>
            </tbody>
        </table>

        <c:if test="${sessionScope.login_employee.id == report.employee.id}">
            <div class ="reactions">
                <div class ="reaction">「既読」${reads_count}件 </div>

                <div class ="reaction">「いいね！」${likes_count}件 </div>
            </div>


            <c:if test="${fn:length(reactions) != 0}">
                <div class ="reaction_emp">
                <div class ="reaction_title">
                    <span>既読者 一覧</span>
                </div>
                    <c:forEach var="reaction" items="${reactions}">
                        <span class ="emp"><c:out value="${reaction.employee.name} "/></span>
                    </c:forEach>
                </div>

            </c:if>



            <p>
                <a href="<c:url value='?action=${actRep}&command=${commEdt}&id=${report.id}' />">この日報を編集する</a>
            </p>
        </c:if>

        <p>
            <c:if test="${sessionScope.login_employee.id != report.employee.id}">
            <div class="reactions">
            <div class ="reaction">
                <form method="POST" action="<c:url value='?action=${actRep}&command=${commRea}' />">

                       <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
                       <input type="hidden" name="${AttributeConst.REA_TYP_ID.getValue()}" value="1" />
                       <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                       <c:choose>
                            <c:when test="${read == null}">
                                <input type="submit" value="既読"><br/>
                                 (既読 ${reads_count}件)
                            </c:when>
                       <c:otherwise>
                            <input type="submit" value="既読を取り消す"><br/>
                             (既読 ${reads_count}件)
                       </c:otherwise>
                       </c:choose>
                </form>
            </div>

            <div class ="reaction">
                <form method="POST" action="<c:url value='?action=${actRep}&command=${commRea}' />">

                       <input type="hidden" name="${AttributeConst.REP_ID.getValue()}" value="${report.id}" />
                       <input type="hidden" name="${AttributeConst.REA_TYP_ID.getValue()}" value="2" />
                       <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                       <c:choose>
                            <c:when test="${like == null}">
                                <input type="submit" value="いいね！"><br/>
                               (いいね ${likes_count}件)
                            </c:when>
                       <c:otherwise>
                            <input type="submit" value="いいね！を取り消す"><br/>
                             (いいね ${likes_count}件)
                       </c:otherwise>
                       </c:choose>
                </form>
            </div>
            </div>
            </c:if>
        </p>
        <p>
            <a href="<c:url value='?action=${actRep}&command=${commIdx}' />">一覧に戻る</a>
        </p>
    </c:param>
</c:import>
