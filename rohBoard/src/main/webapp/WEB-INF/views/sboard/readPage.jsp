<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="../include/header.jsp"%>

<script type="text/javascript">
	var result = '${result}';
	if (result == 'success') {
		alert("처리가 완료되었습니다.");
	}

	$(document).ready(function(){
		var formObj = $("form[role='form']");
		
		console.log(formObj);
		
		$(".btn-warning").on("click", function(){
			formObj.attr("action", "modifyPage");
			formObj.attr("method", "get");
			formObj.submit();
		});
		
		$(".btn-danger").on("click", function(){
			formObj.attr("action", "/sboard/removePage");
			formObj.submit();
		});
		
		$(".btn-primary").on("click", function(){
			// self.location = "/board/listPage";
			
			formObj.attr("action", "/sboard/list");
			formObj.attr("method", "get");
			// 목록보기 시 글 번호 필요없음
			$("input[name='bno']").attr("disabled",true);
			formObj.submit();
		});
		
		// 추천하기 기능
		$(".btn-default").on("click", function(){
			formObj.attr("action", "like");
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
					<h3 class="box-title">READ PAGE</h3>
				</div>
				
				<!-- insert point!! -->
				<form role="form" method="post">
				
					<input type="hidden" name="page" value="${cri.page }">
					<input type="hidden" name="perPageNum" value="${cri.perPageNum }">
					<input type="hidden" name="searchType" value="${cri.searchType }">
					<input type="hidden" name="keyword" value="${cri.keyword }">
					<input type="hidden" name="bno" value="${boardVO.bno }">
					
				</form>
				<div class="box-body">
					<div class="form-group">
						<label for="exampleInputEmail1">Title</label>
						<input type="text" name="title" class="form-control" value="${boardVO.title }"
							readonly="readonly">
					</div>
					<div class="form-group">
						<label for="exampleInputEmail1">Writer</label>
						<input type="text" name="writer" class="form-control" value="${boardVO.writer }"
							readonly="readonly">
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
						<textarea class="form-control" name="content" rows="3"
							readonly="readonly">${boardVO.content }</textarea>
					</div>
				</div>
				<!-- /.box-body -->

				<div class="box-footer">
					<button type="submit" class="btn btn-warning">Modify</button>
					<button type="submit" class="btn btn-danger">Remove</button>
					<button type="submit" class="btn btn-primary">List All</button>
					<button type="button" class="btn btn-default" style="float: right">추천하기</button>
				</div>
			</div>

		</div>
		<!-- /.col (left) -->
	</div>
	<!-- /.row -->
</section>
<!-- /.content -->



<%@ include file="../include/footer.jsp"%>	