<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxStatic}/js/echarts.min.js"></script>
<script type="text/javascript">
$(function(){
	myChart = echarts.init(document.getElementById('main1'));
	myChart2 = echarts.init(document.getElementById('main2'));
	parm = {"segmentId":1,"msbId":4,"beginDate":'',"endDate":''};
	postValue(parm);
});
function postValue(parm){
	 $.ajax({
         url: '${ctx}/receivedata/receivedata/articleCount',
         type : 'post',
         data : JSON.stringify(parm),
 		  contentType : "application/json",
 		  cache : false,
 		  dataType : 'json',
         success: function(data) {
        	 setValue(myChart,data.rtc_time,data.ranging_msb,"信号强度");
        	 setValue(myChart2,data.rtc_time,data.temperature_msb,"传感器温度");
         },
         error: function() {
             alert("接口连接异常");
         }
     });
}
function queryData(){
	parm = {"segmentId":$("#segmentId").val(),"msbId":$("#msbId").val(),"date1":$("#beginDate").val(),"date2":$("#endDate").val()};
	console.log($("#beginDate").val());
	myChart.clear();
	myChart2.clear();
	postValue(parm);
}

function setValue(myChart,date,data,str){
	option = {
		    tooltip: {
		        trigger: 'axis',
		        position: function (pt) {
		            return [pt[0], '10%'];
		        }
		    },
		    title: {
		        left: 'center',
		        text: str+'曲线图',
		    },
		    toolbox: {
		        feature: {
		            dataZoom: {
		                yAxisIndex: 'none'
		            },
		            restore: {},
		            saveAsImage: {}
		        }
		    },
		    xAxis: {
		        type: 'category',
		        boundaryGap: false,
		        data: date
		    },
		    yAxis: {
		        type: 'value',
		        boundaryGap: [0, '100%']
		    },
		    dataZoom: [{
		        type: 'inside',
		        start: 0,
		        end: data.length
		    }, {
		        start: 0,
		        end: 10,
		        handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
		        handleSize: '80%',
		        handleStyle: {
		            color: '#fff',
		            shadowBlur: 3,
		            shadowColor: 'rgba(0, 0, 0, 0.6)',
		            shadowOffsetX: 2,
		            shadowOffsetY: 2
		        }
		    }],
		    series: [
		        {
		            name:str,
		            type:'line',
		            smooth:true,
		            symbol: 'none',
		            sampling: 'average',
		            itemStyle: {
		                normal: {
		                    color: 'rgb(255, 70, 131)'
		                }
		            },
		            areaStyle: {
		                normal: {
		                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [{
		                        offset: 0,
		                        color: 'rgb(255, 158, 68)'
		                    }, {
		                        offset: 1,
		                        color: 'rgb(255, 70, 131)'
		                    }])
		                }
		            },
		            data: data
		        }
		    ]
		};


		myChart.setOption(option);
}
</script>
	<title>信息量统计</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/stats/article">信息量统计</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="paramDto" action="${ctx}/cms/stats/article" method="post" class="breadcrumb form-search">
		<div>
			<label>段号ID：</label><form:input id="segmentId" path="segmentId" htmlEscape="false" maxlength="50" class="input-medium"/>
			<label>ID：</label><form:input id="msbId" path="msbId" htmlEscape="false" maxlength="50" class="input-medium"/>
			<label>开始日期：</label><input id="beginDate" path="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			<label>结束日期：</label><input id="endDate" path="endDate" name="endDate" type="text" readonly="readonly" maxlength="20" class="input-small Wdate"
				 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
			<input id="btnSubmit" class="btn btn-primary" type="button" value="查询" onclick="queryData()"/>
		</div>
	</form:form>
	 <div  style="clear:both;">
                <div style="margin-top:100px;float:left;margin-left:80px;border:1px solid #69b1da;">
                	<div class="title" style="border-bottom:1px solid rgb(76, 104, 191);line-height:40px;clear:both;height:40px;padding:0 20px;background: rgb(76, 104, 191);color:#fff;margin-bottom:20px;"><span style="float:left;">状态曲线(信号强度)</span><a style="float:right;color:#fff;cursor:pointer;"></a></div>
                <div class="eacharts-wrap" style="padding:0 20px;"><div id="main1" style="width: 700px;height:450px;"></div></div>
                    
                </div>
               <div style="margin-top:100px;float:left;margin-left:80px;border:1px solid #69b1da;">
                	<div class="title" style="border-bottom:1px solid rgb(76, 104, 191);line-height:40px;clear:both;height:40px;padding:0 20px;background:rgb(76, 104, 191);color:#fff;margin-bottom:20px;"><span style="float:left;">状态曲线(传感器温度)</span><a style="float:right;color:#fff;cursor:pointer;"></a></div>
                <div class="eacharts-wrap" style="padding:0 20px;"><div id="main2" style="width: 700px;height:450px;"></div></div>
                    
                </div>
     </div>
</body>

</html>