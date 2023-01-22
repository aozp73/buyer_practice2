<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ include file="../layout/header.jsp" %>
        <h1>구매 목록</h1>
        <table border="1">
            <tr>
                <th>번호</th>
                <th>구매자</th>
                <th>구매품</th>
                <th>구매갯수</th>
            </tr>
            <c:forEach items="${purchaseList}" var="purchase">
                <tr>
                    <td>${purchase.id}</td>
                    <td>${purchase.username}</td>
                    <td>${purchase.name}</td>
                    <td>${purchase.count}</td>

                    <form action="/product/${purchase.id}/delete" method="post">
                        <td><button type="submit">삭제</button></td>
                    </form>
                </tr>
            </c:forEach>


        </table>


        <%@ include file="../layout/footer.jsp" %>