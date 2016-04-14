<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container-fluid" style="margin-top:20px;">
	<div class="row">
		<div class="col-md-1"></div>
		<div class="col-md-11">
			<p class="lead">舆情数据分布</p>
			<div id="chart" style="width:800px;height:600px;"></div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function() {
    $.jqplot.config.enablePlugins = true;
    var data = ${data};
    $.jqplot('chart', data, {
        title: '舆情数据分布',
        seriesDefaults: {
            shadow: false,
            renderer: $.jqplot.PieRenderer,
            rendererOptions: {
            	startAngle: 180,
                sliceMargin: 4,
                padding: 5,
                showDataLabels: true
            }
        },
        legend: {
            show: true,
            location: 'e'
        }
    });
});
</script>
<%@ include file="/WEB-INF/views/common/footer.jsp"%>