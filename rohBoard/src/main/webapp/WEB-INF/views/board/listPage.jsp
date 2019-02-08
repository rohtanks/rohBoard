<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script type="text/javascript">
	var result = '${result}';
	
	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}
</script>

<%@ include file="../include/header.jsp"%>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">LIST ALL PAGE</h3>
				</div>
				
				<!-- insert point!! -->
				<table class="table table-bordered">
					<tr>
						<th style="width: 10px">BNO</th>
						<th>TITLE</th>
						<th>WRITER</th>
						<th>REGDATE</th>
						<th style="width: 70px">조회수</th>
						<th style="width: 70px">추천수</th>
					</tr>
				
				<c:forEach items="${list }" var="boardVO">
				
					<tr>
						<td>${boardVO.bno }</td>
						<td>
							<a href="readPage${pageMaker.makeQuery(pageMaker.cri.page) }&bno=${boardVO.bno }">
							${boardVO.title }</a>
						</td>
						<td><a href="">${boardVO.writer }</a></td>
						<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }" /></td>
						<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
						<td><span class="badge bg-red">${boardVO.likecnt }</span></td>
					</tr>
					
				</c:forEach>	
					
				</table>
				
			</div>

			<div class="text-center">
				<ul class="pagination">
					
					<c:if test="${pageMaker.prev }">
						<li><a href="listPage${pageMaker.makeQuery(pageMaker.startPage - 1) }">&laquo;</a></li>
					</c:if>
					
					<c:forEach begin="${pageMaker.startPage }" 
						end="${pageMaker.endPage }" var="idx">
						<li <c:out value="${pageMaker.cri.page == idx?'class =active':'' }" />>
						<a href="listPage${pageMaker.makeQuery(idx) }">${idx }</a></li>
					</c:forEach>
					
					<c:if test="${pageMaker.next && pageMaker.endPage > 0 }">
						<li><a href="listPage${pageMaker.makeQuery(pageMaker.endPage + 1) }">&raquo;</a></li>
					</c:if>
				</ul>
			</div>
		</div>
		<!-- /.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->



<%@ include file="../include/footer.jsp"%>	