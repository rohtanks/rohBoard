<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click", function(){
			/* self.location = "board/listAll"; */
			/* history.back(); */
			self.location = "list?page=${cri.page}&perPageNum=${cri.perPageNum}"
					+ "&searchType=${cri.searchType}&keyword=${cri.keyword}";
		});
		
		$(".btn-primary").on("click", function(){
			/* action 을 지정해주지 않으면 GET 방식으로 된 URL에 있는 파라미터가 남아서 값이 중복된다 */
			/* formObj.attr("action", "modifyPage"); */
			formObj.submit();
		});
		
	});
</script>

<!-- Main content -->
<section class="content">
	<div class="row">
		<!-- left column -->
		<div class="col-md-12">
			<!-- general form elements -->
			<div class="box">
				<div class="box-header with-border">
					<h3 class="box-title">MODIFY PAGE</h3>
				</div>
				
				<!-- insert point!! -->
				<form role="form" action="modifyPage" method="post">
				
					<input type="hidden" name="page" value="${cri.page }">
					<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
					<input type="hidden" name="searchType" value="${cri.searchType }">
					<input type="hidden" name="keyword" value="${cri.keyword }">
					
					<div class="box-body">
						<div class="form-group">
							<input type="hidden" name="bno" value="${boardVO.bno }">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Title</label>
							<input type="text" name="title" class="form-control" value="${boardVO.title }">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">Writer</label>
							<input type="text" name="writer" class="form-control" value="${boardVO.writer }">
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">등록일</label>
							<p><fmt:formatDate pattern="yyyy-MM-dd HH:mm" value="${boardVO.regdate }"/></p>
						</div>
						<div class="form-group">
							<label for="exampleInputEmail1">조회수 : ${boardVO.viewcnt } / 추천수 : ${boardVO.likecnt }</label>
						</div>
						<div class="form-group">
							<label for="exampleInputPassword1">Content</label>
							<textarea class="form-control" name="content" rows="3">${boardVO.content }</textarea>
						</div>
					</div>
					<!-- /.box-body -->
				</form>

				<div class="box-footer">
					<button type="submit" class="btn btn-primary">Save</button>
					<button type="submit" class="btn btn-warning">Cancel</button>
				</div>
			</div>

		</div>
		<!-- /.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->



<%@ include file="../include/footer.jsp"%>	