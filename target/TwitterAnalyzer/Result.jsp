<%@page import="com.mycompany.twitterfeed.Response"%>
<%@page import="java.util.TimeZone"%>
<%@page import="com.mycompany.twitterfeed.Entity"%>
<%@page import="com.mycompany.twitterfeed.Analyzer"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tweetify</title>
	<script src="https://code.highcharts.com/highcharts.js"></script>
	<script src="https://code.highcharts.com/modules/exporting.js"></script>
</head>
<body>

<div id="container" style="width:100%; height:400px;"></div>

<script>
	var timezone = jstz.determine().name();
}

</script>

<% 
	String twitterId = request.getParameter("twitterId");
	String aggregationBy = request.getParameter("aggregationBy");
	String aggregationType = request.getParameter("aggregationType");
	String tweetsToA = request.getParameter("tweetsToA");
	
	if (twitterId == null || aggregationBy == null ||
			aggregationType == null || tweetsToA == null) {
	    response.sendRedirect("Tweetify.jsp");
	    return;
	}
	
	Response tweetifyResponse = Analyzer.INSTANCE.analyze(twitterId, 1, Entity.valueOf(aggregationBy), TimeZone.getDefault(), 
			aggregationType, Integer.valueOf(tweetsToA));
%>

<script type="text/javascript">
	
	
	
	Highcharts.chart('container', {
		  chart: {
		    type: 'column'
		  },
		  title: {
		    text: 'Tweetify'
		  },
		  xAxis: {
		    categories: <%=tweetifyResponse.getLegendsStr()%>
		  },
		  yAxis: {
		    min: 0,
		    title: {
		      text: <%= "\"" + aggregationBy + " analysis\"" %>
		    },
		    stackLabels: {
		      enabled: true,
		      style: {
		        fontWeight: 'bold',
		        color: (Highcharts.theme && Highcharts.theme.textColor) || 'gray'
		      }
		    }
		  },
		  legend: {
		    align: 'right',
		    x: -30,
		    verticalAlign: 'top',
		    y: 25,
		    floating: true,
		    backgroundColor: (Highcharts.theme && Highcharts.theme.background2) || 'white',
		    borderColor: '#CCC',
		    borderWidth: 1,
		    shadow: false
		  },
		  tooltip: {
		    headerFormat: '<b>{point.x}</b><br/>',
		    pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
		  },
		  plotOptions: {
		    column: {
		      stacking: 'normal',
		      dataLabels: {
		        enabled: true,
		        color: (Highcharts.theme && Highcharts.theme.dataLabelsColor) || 'white'
		      }
		    }
		  },
		  series: [{
		    name: <%="\'" + twitterId + "\'"%>,
		    data: <%=tweetifyResponse.getDataStr()%>
		  }]
		});
	
	</script>

</body>
</html>