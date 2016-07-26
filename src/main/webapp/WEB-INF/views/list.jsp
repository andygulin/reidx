<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container-fluid" style="margin-top:20px;">
	<div class="row">
		<div class="col-md-12">
			<h1><a href="${ctx }">舆情浏览</a></p></h1>
			<form class="form-inline">
				<div class="form-group">
					<p class="input-group input-group-sm">
						<input type="text" name="reidxId" class="form-control" value="${param.reidxId }" placeholder="reidxId" />
					</p>
				</div>
				<div class="form-group">
					<p class="input-group input-group-sm">
						<input type="text" name="title" class="form-control" value="${param.title }" placeholder="输入标题" />
					</p>
				</div>
				<div class="form-group">
					<p class="input-group input-group-sm">
						<select name="sourceType" class="form-control">
						<option value="-1" <c:if test="${empty param.sourceType or param.sourceType == -1}">selected="selected"</c:if>>全部</option>
						<c:forEach var="st" items="${sourceTypes }">
							<option value="${st.key }" <c:if test="${st.key == param.sourceType }">selected="selected"</c:if>>${st.value }</option>
						</c:forEach>
						</select>
					</p>
				</div>
				<div class="form-group">
					<p class="input-group input-group-sm">
						<input type="text" name="rdStart" class="form-control" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'});" value="${param.rdStart }" placeholder="开始日期" />
					</p>~
					<p class="input-group input-group-sm">
						<input type="text" name="rdEnd" class="form-control" onclick="WdatePicker({dateFmt:'yyyy/MM/dd HH:mm:ss'});" value="${param.rdEnd }" placeholder="结束日期" />
					</p>
				</div>
				<div class="form-group m-btn-group-fix">
					<p class="input-group input-group-sm">
						<span class="input-group-btn">
							<button type="submit" class="btn btn-primary">搜索</button>
						</span>
					</p>
				</div>
				<div class="form-group m-btn-group-fix">
					<p class="input-group input-group-sm">
						<span class="input-group-btn">
							<button type="button" class="btn btn-info" onclick="goChart();">数据分布</button>
						</span>
					</p>
				</div>
			</form>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<h4>共找到&nbsp;<b>${count }</b>&nbsp;条数据</h4>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table id="contentTable"
				class="table table-striped table-hover table-bordered table_font">
				<thead>
					<td>reidxId</td>
					<td>标题</td>
					<td>媒体</td>
					<td>类别</td>
					<td>发布日期</td>
				</thead>
				<tbody>
					<c:forEach var="resource" items="${resources }">
					<tr id="tr_message_${resource.id }">
						<td>${resource.reidxId }</td>
						<td><a href="${ctx }/view/${resource.id}" target="_blank" title="${resource.title }">
						<c:if test="${fn:length(resource.title)>30 }">${fn:substring(resource.title,0,30) }...</c:if>
						<c:if test="${fn:length(resource.title)<30 }">${resource.title }</c:if>
						</a></td>
						<td>${resource.mediaName }</td>
						<td>${sourceTypes[resource.sourceType] }</td>
						<td><fmt:formatDate value="${resource.releaseDate }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	${pageList }
</div>

<script type="text/javascript">
function goChart(){
	window.location.href = '${ctx }/chart';
}
</script>

<%@ include file="/WEB-INF/views/common/footer.jsp"%>