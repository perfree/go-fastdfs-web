//条形图
var dom = document.getElementById("pageiew");
var myChart = echarts.init(dom);
var app = {};
option = null;
option = {
	title: {
		text: ''
	},
	tooltip: {
		trigger: 'axis'
	},
	legend: {
		data: ['邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎']
	},
	grid: {
		left: '3%',
		right: '4%',
		bottom: '3%',
		containLabel: true
	},
	toolbox: {
		feature: {
			saveAsImage: {}
		}
	},
	xAxis: {
		type: 'category',
		boundaryGap: false,
		data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
	},
	yAxis: {
		type: 'value'
	},
	series: [{
			name: '邮件营销',
			type: 'line',
			stack: '总量',
			data: [120, 132, 101, 134, 90, 230, 210]
		},
		{
			name: '联盟广告',
			type: 'line',
			stack: '总量',
			data: [220, 182, 191, 234, 290, 330, 310]
		},
		{
			name: '视频广告',
			type: 'line',
			stack: '总量',
			data: [150, 232, 201, 154, 190, 330, 410]
		},
		{
			name: '直接访问',
			type: 'line',
			stack: '总量',
			data: [320, 332, 301, 334, 390, 330, 320]
		},
		{
			name: '搜索引擎',
			type: 'line',
			stack: '总量',
			data: [820, 932, 901, 934, 1290, 1330, 1320]
		}
	]
}
if (option && typeof option === "object") {
	myChart.setOption(option, true);
}
//堆叠条形图
var dom = document.getElementById("category");
var myChart = echarts.init(dom);
var app = {};
option = null;
app.title = '堆叠条形图';

option = {
	tooltip: {
		trigger: 'axis',
		axisPointer: { // 坐标轴指示器，坐标轴触发有效
			type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
		}
	},
	legend: {
		data: ['直接访问', '邮件营销', '联盟广告', '视频广告', '搜索引擎']
	},
	grid: {
		left: '3%',
		right: '4%',
		bottom: '3%',
		containLabel: true
	},
	xAxis: {
		type: 'value'
	},
	yAxis: {
		type: 'category',
		data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
	},
	series: [{
			name: '直接访问',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: [320, 302, 301, 334, 390, 330, 320]
		},
		{
			name: '邮件营销',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: [120, 132, 101, 134, 90, 230, 210]
		},
		{
			name: '联盟广告',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: [220, 182, 191, 234, 290, 330, 310]
		},
		{
			name: '视频广告',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: [150, 212, 201, 154, 190, 330, 410]
		},
		{
			name: '搜索引擎',
			type: 'bar',
			stack: '总量',
			label: {
				normal: {
					show: true,
					position: 'insideRight'
				}
			},
			data: [820, 832, 901, 934, 1290, 1330, 1320]
		}
	]
};;
if (option && typeof option === "object") {
	myChart.setOption(option, true);
}
