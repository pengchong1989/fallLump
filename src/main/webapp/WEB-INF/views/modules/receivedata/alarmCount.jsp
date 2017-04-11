<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script src="${ctxStatic}/js/echarts.min.js"></script>
<script type="text/javascript">
	console.log('aaaa');
</script>
<script type="text/javascript">
$(function(){
	myChart = echarts.init(document.getElementById('main1'));
	myChart2 = echarts.init(document.getElementById('main2'));
	postValue();
});

function postValue(){
	 $.ajax({
		 url: '${ctx}/receivedata/receivedata/alarmCount',
         type : 'post',
 		  contentType : "application/json",
 		  cache : false,
 		  dataType : 'json',
        success: function(data) {
       	 setValue(myChart,data.list1,"节点在线状态统计");
       	 setValue(myChart2,data.list2,"节点告警统计");
        },
        error: function() {
            alert("接口连接异常");
        }
    });
}

function setValue(myChart,data,str){
	option = {
		    title : {
		        text: str,
		        x:'center'
		    },
		    tooltip : {
		        trigger: 'item',
		        formatter: "{a} <br/>{b} : {c} ({d}%)"
		    },
		    legend: {
		        orient: 'vertical',
		        left: 'left',
		        data: ['在线','离线','上限位警报','下限位警报','正常']
		    },
		    series : [
		        {
		            name: '访问来源',
		            type: 'pie',
		            radius : '55%',
		            center: ['50%', '60%'],
		            data:data,
		            itemStyle: {
		                emphasis: {
		                    shadowBlur: 10,
		                    shadowOffsetX: 0,
		                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		                }
		            }
		        }
		    ]
		};

		myChart.setOption(option);
}
</script>
	<title>节点状态统计</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/cms/stats/article">节点状态统计</a></li>
	</ul>
	 <div  style="clear:both;">
                <div style="margin-top:100px;float:left;margin-left:80px;border:1px solid #3954a9;">
                	<div class="title" style="border-bottom:1px solid rgb(76, 104, 191);line-height:40px;clear:both;height:40px;padding:0 20px;background:rgb(76, 104, 191);color:#fff;margin-bottom:20px;"><span style="float:left;">节点在线统计</span><a style="float:right;color:#fff;cursor:pointer;"></a></div>
                <div class="eacharts-wrap" style="padding:0 20px;"><div id="main1" style="width: 700px;height:450px;"></div></div>
                    
                </div>
               <div style="margin-top:100px;float:left;margin-left:80px;border:1px solid #3954a9;">
                	<div class="title" style="border-bottom:1px solid rgb(76, 104, 191);line-height:40px;clear:both;height:40px;padding:0 20px;background:rgb(76, 104, 191);color:#fff;margin-bottom:20px;"><span style="float:left;">节点告警统计</span><a style="float:right;color:#fff;cursor:pointer;"></a></div>
                <div class="eacharts-wrap" style="padding:0 20px;"><div id="main2" style="width: 700px;height:450px;"></div></div>
                    
                </div>
     </div>
</body>

</html>