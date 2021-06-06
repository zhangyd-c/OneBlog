/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/5/21 11:05
 * @since 1.0
 */
var zhyd = window.zhyd || {};

//扩展echart图表样式
zhyd.chartConfig = {

    "color": [
        "#5470c6",
        "#91cc75",
        "#fac858",
        "#ee6666",
        "#73c0de",
        "#3ba272",
        "#fc8452",
        "#9a60b4",
        "#ea7ccc",
        "#60c0dd",
        "#d7504b",
        "#c6e579",
        "#f4e001",
        "#f0805a",
        "#26c0c0"
    ],
    // "backgroundColor": "rgba(254,248,239,1)",
    "textStyle": {},
    "title": {
        "textStyle": {
            "color": "#27727b"
        },
        "subtextStyle": {
            "color": "#aaaaaa"
        }
    },
    "line": {
        "itemStyle": {
            "borderWidth": 1
        },
        "lineStyle": {
            "width": "3"
        },
        "symbolSize": "5",
        "symbol": "emptyCircle",
        "smooth": false
    },
    "radar": {
        "itemStyle": {
            "borderWidth": 1
        },
        "lineStyle": {
            "width": "3"
        },
        "symbolSize": "5",
        "symbol": "emptyCircle",
        "smooth": false
    },
    "bar": {
        "itemStyle": {
            "barBorderWidth": 0,
            "barBorderColor": "#ccc"
        }
    },
    "pie": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "scatter": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "boxplot": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "parallel": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "sankey": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "funnel": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "gauge": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        }
    },
    "candlestick": {
        "itemStyle": {
            "color": "#c1232b",
            "color0": "#b5c334",
            "borderColor": "#c1232b",
            "borderColor0": "#b5c334",
            "borderWidth": 1
        }
    },
    "graph": {
        "itemStyle": {
            "borderWidth": 0,
            "borderColor": "#ccc"
        },
        "lineStyle": {
            "width": 1,
            "color": "#aaaaaa"
        },
        "symbolSize": "5",
        "symbol": "emptyCircle",
        "smooth": false,
        "color": [
            "#c1232b",
            "#27727b",
            "#fcce10",
            "#e87c25",
            "#b5c334",
            "#fe8463",
            "#9bca63",
            "#fad860",
            "#f3a43b",
            "#60c0dd",
            "#d7504b",
            "#c6e579",
            "#f4e001",
            "#f0805a",
            "#26c0c0"
        ],
        "label": {
            "color": "#eeeeee"
        }
    },
    "map": {
        "itemStyle": {
            "normal": {
                "areaColor": "#dddddd",
                "borderColor": "#eeeeee",
                "borderWidth": 0.5
            },
            "emphasis": {
                "areaColor": "#fe994e",
                "borderColor": "#444",
                "borderWidth": 1
            }
        },
        "label": {
            "normal": {
                "textStyle": {
                    "color": "#c1232b"
                }
            },
            "emphasis": {
                "textStyle": {
                    "color": "rgb(100,0,0)"
                }
            }
        }
    },
    "geo": {
        "itemStyle": {
            "normal": {
                "areaColor": "#dddddd",
                "borderColor": "#eeeeee",
                "borderWidth": 0.5
            },
            "emphasis": {
                "areaColor": "#fe994e",
                "borderColor": "#444",
                "borderWidth": 1
            }
        },
        "label": {
            "normal": {
                "textStyle": {
                    "color": "#c1232b"
                }
            },
            "emphasis": {
                "textStyle": {
                    "color": "rgb(100,0,0)"
                }
            }
        }
    },
    "categoryAxis": {
        "axisLine": {
            "show": true,
            "lineStyle": {
                "color": "#27727b"
            }
        },
        "axisTick": {
            "show": true,
            "lineStyle": {
                "color": "#27727b"
            }
        },
        "axisLabel": {
            "show": true,
            "textStyle": {
                "color": "#333"
            }
        },
        "splitLine": {
            "show": false,
            "lineStyle": {
                "color": [
                    "#ccc"
                ]
            }
        },
        "splitArea": {
            "show": false,
            "areaStyle": {
                "color": [
                    "rgba(250,250,250,0.3)",
                    "rgba(200,200,200,0.3)"
                ]
            }
        }
    },
    "valueAxis": {
        "axisLine": {
            "show": false,
            "lineStyle": {
                "color": "#333"
            }
        },
        "axisTick": {
            "show": false,
            "lineStyle": {
                "color": "#333"
            }
        },
        "axisLabel": {
            "show": true,
            "textStyle": {
                "color": "#333"
            }
        },
        "splitLine": {
            "show": true,
            "lineStyle": {
                "color": [
                    "#ccc"
                ]
            }
        },
        "splitArea": {
            "show": false,
            "areaStyle": {
                "color": [
                    "rgba(250,250,250,0.3)",
                    "rgba(200,200,200,0.3)"
                ]
            }
        }
    },
    "logAxis": {
        "axisLine": {
            "show": true,
            "lineStyle": {
                "color": "#27727b"
            }
        },
        "axisTick": {
            "show": true,
            "lineStyle": {
                "color": "#333"
            }
        },
        "axisLabel": {
            "show": true,
            "textStyle": {
                "color": "#333"
            }
        },
        "splitLine": {
            "show": true,
            "lineStyle": {
                "color": [
                    "#ccc"
                ]
            }
        },
        "splitArea": {
            "show": false,
            "areaStyle": {
                "color": [
                    "rgba(250,250,250,0.3)",
                    "rgba(200,200,200,0.3)"
                ]
            }
        }
    },
    "timeAxis": {
        "axisLine": {
            "show": true,
            "lineStyle": {
                "color": "#27727b"
            }
        },
        "axisTick": {
            "show": true,
            "lineStyle": {
                "color": "#333"
            }
        },
        "axisLabel": {
            "show": true,
            "textStyle": {
                "color": "#333"
            }
        },
        "splitLine": {
            "show": true,
            "lineStyle": {
                "color": [
                    "#ccc"
                ]
            }
        },
        "splitArea": {
            "show": false,
            "areaStyle": {
                "color": [
                    "rgba(250,250,250,0.3)",
                    "rgba(200,200,200,0.3)"
                ]
            }
        }
    },
    "toolbox": {
        "iconStyle": {
            "normal": {
                "borderColor": "#c1232b"
            },
            "emphasis": {
                "borderColor": "#e87c25"
            }
        }
    },
    "legend": {
        "textStyle": {
            "color": "#333333"
        }
    },
    "tooltip": {
        "axisPointer": {
            "lineStyle": {
                "color": "#27727b",
                "width": 1
            },
            "crossStyle": {
                "color": "#27727b",
                "width": 1
            }
        }
    },
    "timeline": {
        "lineStyle": {
            "color": "#293c55",
            "width": 1
        },
        "itemStyle": {
            "normal": {
                "color": "#27727b",
                "borderWidth": 1
            },
            "emphasis": {
                "color": "#72d4e0"
            }
        },
        "controlStyle": {
            "normal": {
                "color": "#27727b",
                "borderColor": "#27727b",
                "borderWidth": 0.5
            },
            "emphasis": {
                "color": "#27727b",
                "borderColor": "#27727b",
                "borderWidth": 0.5
            }
        },
        "checkpointStyle": {
            "color": "#c1232b",
            "borderColor": "#c23531"
        },
        "label": {
            "normal": {
                "textStyle": {
                    "color": "#293c55"
                }
            },
            "emphasis": {
                "textStyle": {
                    "color": "#293c55"
                }
            }
        }
    },
    "visualMap": {
        "color": [
            "#c1232b",
            "#fcce10"
        ]
    },
    "dataZoom": {
        "backgroundColor": "rgba(0,0,0,0)",
        "dataBackgroundColor": "rgba(181,195,52,0.3)",
        "fillerColor": "rgba(181,195,52,0.2)",
        "handleColor": "#27727b",
        "handleSize": "100%",
        "textStyle": {
            "color": "#999999"
        }
    },
    "markPoint": {
        "label": {
            "color": "#eeeeee"
        },
        "emphasis": {
            "label": {
                "color": "#eeeeee"
            }
        }
    }
}


/*初始化数据,这是原来的*/
/*zhyd.chartConfig = {
        color: ["#26B99A", "#34495E", "#BDC3C7", "#3498DB", "#9B59B6", "#8abb6f", "#759c6a", "#bfd3b7"],
        title: {
            itemGap: 8,
            textStyle: {
                fontWeight: "normal",
                color: "#408829"
            }
        },
        dataRange: {
            color: ["#1f610a", "#97b58d"]
        },
        toolbox: {
            color: ["#408829", "#408829", "#408829", "#408829"]
        },
        tooltip: {
            backgroundColor: "rgba(0,0,0,0.5)",
            axisPointer: {
                type: "line",
                lineStyle: {
                    color: "#408829",
                    type: "dashed"
                },
                crossStyle: {
                    color: "#408829"
                },
                shadowStyle: {
                    color: "rgba(200,200,200,0.3)"
                }
            }
        },
        dataZoom: {
            dataBackgroundColor: "#eee",
            fillerColor: "rgba(64,136,41,0.2)",
            handleColor: "#408829"
        },
        grid: {
            borderWidth: 0
        },
        categoryAxis: {
            axisLine: {
                lineStyle: {
                    color: "#408829"
                }
            },
            splitLine: {
                lineStyle: {
                    color: ["#eee"]
                }
            }
        },
        valueAxis: {
            axisLine: {
                lineStyle: {
                    color: "#408829"
                }
            },
            splitArea: {
                show: !0,
                areaStyle: {
                    color: ["rgba(250,250,250,0.1)", "rgba(200,200,200,0.1)"]
                }
            },
            splitLine: {
                lineStyle: {
                    color: ["#eee"]
                }
            }
        },
        timeline: {
            lineStyle: {
                color: "#408829"
            },
            controlStyle: {
                normal: {
                    color: "#408829"
                },
                emphasis: {
                    color: "#408829"
                }
            }
        },
        k: {
            itemStyle: {
                normal: {
                    color: "#68a54a",
                    color0: "#a9cba2",
                    lineStyle: {
                        width: 1,
                        color: "#408829",
                        color0: "#86b379"
                    }
                }
            }
        },
        map: {
            itemStyle: {
                normal: {
                    areaStyle: {
                        color: "#ddd"
                    },
                    label: {
                        textStyle: {
                            color: "#c12e34"
                        }
                    }
                },
                emphasis: {
                    areaStyle: {
                        color: "#99d2dd"
                    },
                    label: {
                        textStyle: {
                            color: "#c12e34"
                        }
                    }
                }
            }
        },
        force: {
            itemStyle: {
                normal: {
                    linkStyle: {
                        strokeColor: "#408829"
                    }
                }
            }
        },
        chord: {
            padding: 4,
            itemStyle: {
                normal: {
                    lineStyle: {
                        width: 1,
                        color: "rgba(128, 128, 128, 0.5)"
                    },
                    chordStyle: {
                        lineStyle: {
                            width: 1,
                            color: "rgba(128, 128, 128, 0.5)"
                        }
                    }
                },
                emphasis: {
                    lineStyle: {
                        width: 1,
                        color: "rgba(128, 128, 128, 0.5)"
                    },
                    chordStyle: {
                        lineStyle: {
                            width: 1,
                            color: "rgba(128, 128, 128, 0.5)"
                        }
                    }
                }
            }
        },
        gauge: {
            startAngle: 225,
            endAngle: -45,
            axisLine: {
                show: !0,
                lineStyle: {
                    color: [[.2, "#86b379"], [.8, "#68a54a"], [1, "#408829"]],
                    width: 8
                }
            },
            axisTick: {
                splitNumber: 10,
                length: 12,
                lineStyle: {
                    color: "auto"
                }
            },
            axisLabel: {
                textStyle: {
                    color: "auto"
                }
            },
            splitLine: {
                length: 18,
                lineStyle: {
                    color: "auto"
                }
            },
            pointer: {
                length: "90%",
                color: "auto"
            },
            title: {
                textStyle: {
                    color: "#333"
                }
            },
            detail: {
                textStyle: {
                    color: "auto"
                }
            }
        },
        textStyle: {
            fontFamily: "Arial, Verdana, sans-serif"
        }
    };*/
zhyd.createChart = function (options) {
    var op = $.extend({
        id: '',
        theme: zhyd.chartConfig,
        title: null,
        subtext: '',
        legendData: [],
        series: {
            name: '数值',
            type: 'line',
            seriesData: []
        }
    }, options);

    var myChart = echarts.init(document.getElementById(op.id), op.theme);
    var option = {
        tooltip: {
            trigger: "item",
            formatter: "{a} <br/>{b} : {c} ({d}%)"
        },
        legend: {
            x: "center",
            y: "bottom",
            data: op.legendData
        },
        calculable: !0,
        series: [{
            name: op.series.name,
            type: op.series.type,
            radius: "55%",
            center: ["50%", "35%"],
            label: {
                show: false
            },
            data: op.series.seriesData
        }]
    };
    // 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
};

function init_echarts() {
    if ("undefined" != typeof echarts) {
        if ($("#echart_line").length) {
            var f = echarts.init(document.getElementById("echart_line"), zhyd.chartConfig);
            f.setOption({
                tooltip: {
                    trigger: "axis"
                },
                legend: {
                    x: 220,
                    y: 40,
                    data: ["Intent", "Pre-order", "Deal"]
                },
                calculable: !0,
                xAxis: [{
                    type: "category",
                    boundaryGap: !1,
                    data: ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"]
                }],
                yAxis: [{
                    type: "value"
                }],
                series: [{
                    name: "Deal",
                    type: "line",
                    smooth: !0,
                    itemStyle: {
                        normal: {
                            areaStyle: {
                                type: "default"
                            }
                        }
                    },
                    data: [10, 12, 21, 54, 260, 830, 710]
                }, {
                    name: "Pre-order",
                    type: "line",
                    smooth: !0,
                    itemStyle: {
                        normal: {
                            areaStyle: {
                                type: "default"
                            }
                        }
                    },
                    data: [30, 182, 434, 791, 390, 30, 10]
                }, {
                    name: "Intent",
                    type: "line",
                    smooth: !0,
                    itemStyle: {
                        normal: {
                            areaStyle: {
                                type: "default"
                            }
                        }
                    },
                    data: [1320, 1132, 601, 234, 120, 90, 20]
                }]
            })
        }
    }
}
