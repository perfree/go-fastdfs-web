getStat();

//获取文件统计信息
function getStat() {
    $.post('/main/getStat', function (data) {
        if (data.state == 200) {
            $("#totalFileCount").text(data.data.totalFileCount);
            $("#totalFileSize").text(data.data.totalFileSize);
            $("#dayFileSize").text(data.data.dayFileSize);
            $("#dayFileCount").text(data.data.dayFileCount);

            var myChart = echarts.init(document.getElementById('main'));
            myChart.setOption(
                option = {
                    title: {
                        text: '文件统计(30天)',
                        textStyle: {
                            fontSize: '18'
                        }
                    },
                    color: ['#445e75', '#45a7ca', '#98d5ef'],
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {
                            type: 'shadow'
                        },
                        formatter: '{a}:{c}MB<br>{a1}:{c1}'
                    },
                    legend: {
                        data: ['文件大小', '文件数量'],
                        top: '20'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        top: '15%',
                        bottom: '11%',
                        containLabel: true
                    },
                    calculable: true,
                    xAxis: [{
                        type: 'category',
                        data: data.data.dayNumList
                    }],
                    yAxis: [{
                        type: 'value',
                        nameLocation: 'middle',
                        nameGap: 30,
                        nameTextStyle: {
                            fontWeight: 'bold',
                            fontSize: '14',
                        }
                    }],
                    dataZoom: [{
                        textStyle: {
                            color: '#8392A5'
                        },
                        handleSize: '80%',
                        dataBackground: {
                            areaStyle: {
                                color: '#8392A5'
                            },
                            lineStyle: {
                                opacity: 0.8,
                                color: '#8392A5'
                            }
                        },
                        handleStyle: {
                            color: '#fff',
                            shadowBlur: 3,
                            shadowColor: 'rgba(0, 0, 0, 0.6)',
                            shadowOffsetX: 2,
                            shadowOffsetY: 2
                        }
                    }, {
                        type: 'inside'
                    }],
                    series: [{
                        name: '文件大小',
                        type: 'bar',
                        data: data.data.dayFileSizeList,
                        markPoint: {
                            data: [{
                                type: 'max',
                                name: '最大值'
                            }, {
                                type: 'min',
                                name: '最小值'
                            }]
                        },
                    }, {
                        name: '文件数量',
                        type: 'bar',
                        data: data.data.dayFileCountList,
                        markPoint: {
                            data: [{
                                type: 'max',
                                name: '最大值'
                            }, {
                                type: 'min',
                                name: '最小值'
                            }]
                        },
                    }]
                }
            );
        } else {
            layer.msg(data.msg);
        }
    })
}