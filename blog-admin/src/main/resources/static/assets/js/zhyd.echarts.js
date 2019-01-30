/**
 * @author yadong.zhang (yadong.zhang0415(a)gmail.com)
 * @version 1.0
 * @date 2018/5/21 11:05
 * @since 1.0
 */
var zhyd = window.zhyd || {};
zhyd.chartConfig = {
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
    };
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