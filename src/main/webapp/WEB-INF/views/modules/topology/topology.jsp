<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<script type="text/javascript" src="${ctxStatic}/js/twaver.js"></script>
<script type="text/javascript">
	$(function(){
		parm = {};
		init();
		window.setInterval(function(){ 
			initTopo(parm); 
			},1000*60*2); 
	});
	
function init(){
	//注册图片方法 
	function registerNormalImage(url, name) {
	 var image = new Image();
	 image.src = url;
	 image.onload = function() {
	 twaver.Util.registerImage(name, image, image.width, image.height);
	 image.onload = null;
	 network.invalidateElementUIs();
	 };
	 }
	//注册图片
	registerNormalImage('${ctxStatic}/images/red.png','red');
	registerNormalImage('${ctxStatic}/images/green.png','green');
	network = new twaver.network.Network();
	box = network.getElementBox();
	
	
	initTopo(parm);
	var networkDom = network.getView();
	networkDom.style.width = "100%";
	networkDom.style.height = "100%";
	document.body.appendChild(networkDom);
}

function initTopo(parm){
	 $.ajax({
        url: '${ctx}/receivedata/receivedata/nodeTopology',
        type : 'post',
		  contentType : "application/json",
		  cache : false,
		  data : JSON.stringify(parm),
		  dataType : 'json',
        success: function(datas) {
        	console.log("aa");
        	var y = 0;
        	var nodes = [];
        	var x = 0;
        	var data = datas.list;
        	box.clear();
        	for(i=0;i<data.length;i++){
        		var node = new twaver.Node();
    			node.setName(data[i].segment_id_msb+"_"+data[i].msb_id);
    			node.setToolTip("dsadas");
    			if(data[i].isline =='1'){
    				node.setImage("green")
    			}
    			box.add(node);
    			nodes.push(node);
    			if(i>0 && data[i].segment_id_msb != data[i-1].segment_id_msb){
    				y+=1;
    				x=0;
    			}else{
    				x+=1;
    			}
    			if(i>0 && data[i].segment_id_msb == data[i-1].segment_id_msb){
    				var link = new twaver.Link(nodes[i-1], nodes[i]);
        			link.setName("段"+data[i].segment_id_msb);
        			link.setToolTip("<b>Hello TWaver</b>");
        			box.add(link);
    			}
    			node.setLocation(100+x*70, 100+y*70);
        	}
        	
        },
        error: function() {
            alert("接口连接异常");
        }
    });
}
</script>
</head>
<meta name="decorator" content="default"/>
<body>
	<h1 id="ceshi"></h1>
<body style="margin: 0;"></body>
</body>
</html>
