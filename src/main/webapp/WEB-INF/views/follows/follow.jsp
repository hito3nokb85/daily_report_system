<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="constants.AttributeConst" %>
<%@ page import="constants.ForwardConst" %>

<c:set var="actFol" value="${ForwardConst.ACT_FOL.getValue()}" />
<c:set var="commCrt" value="${ForwardConst.CMD_CREATE.getValue()}" />
<c:set var="commIdx" value="${ForwardConst.CMD_INDEX.getValue()}" />


<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>フォロー管理</h2>
        <table id="follow_list">
            <tbody>
            <tr>
                <th class="follow_name">氏名</th>
                <th class="follow">フォロー/解除</th>
                <th class="follower">相手からのフォロー状況</th>
            </tr>
                <c:forEach var="employee" items="${employees}">
                    <tr>
                        <c:if test="${sessionScope.login_employee.id != employee.id
                            && employee.deleteFlag == AttributeConst.DEL_FLAG_FALSE.getIntegerValue()}">
                            <td class="follow_name"><c:out value="${employee.name}" /></td>
                            <td class="follow">
                                <c:set var="follow" value="フォローする" />
                                    <c:forEach var="followee" items="${followees}">
                                        <c:if test="${followee.followee.id == employee.id}">
                                            <c:set var="follow" value="<b>フォロー中</b>" />
                                        </c:if>
                                    </c:forEach>
                                        <form method="POST" action="<c:url value='?action=${actFol}&command=${commCrt}' />">
                                            <input type="hidden" name="${AttributeConst.EMP_ID.getValue()}" value="${employee.id}" />
                                            <input type="hidden" name="${AttributeConst.TOKEN.getValue()}" value="${_token}" />
                                            <button type="submit" class="button_style">${follow}</button>
                                        </form>
                            </td>
                            <td class="follower">
                                <c:forEach var="follower" items="${followers}">
                                    <c:if test="${follower.follower.id == employee.id}">
                                        <c:out value="フォローされています" />
                                    </c:if>
                                </c:forEach>
                            </td>
                        </c:if>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        ${countFollowee} フォロー &emsp; ${countFollower} フォロワー

        <p><a href="<c:url value='?action=${actFol}&command=${commIdx}' />">フォローしている人の日報一覧</a></p>
    </c:param>
</c:import>
