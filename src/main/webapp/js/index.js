let noname = "田地";
//读取当前时间
(function () {
    let t = setTimeout(time, 1000)
    function time() {
        let dt = new Date()
        const y = dt.getFullYear();
        const mt = dt.getMonth() + 1;
        const day = dt.getDate();
        const h = dt.getHours();
        const m = dt.getMinutes();
        const s = dt.getSeconds();
        document.querySelector(".showTime").innerHTML =
            "" + y + "年" + mt + "月" + day + "-" + h + "时" + m + "分" + s + "秒";
        t = setTimeout(time, 1000); //设定定时器，循环运行
    }
})();
//实时天气
(function(){
    $.getJSON("https://devapi.qweather.com/v7/warning/list?range=cn&key=1fbd90fafbd045ed8d7d2ab4ff8697d4",function(data){
        let location=data["warningLocList"];
        let now_location=101020200;//预报城市编码 101010200 海淀区  101280101广州
        let flag=0;
        let url;
        for (const i in location) {
            //随机地点
            if (location[i]["locationId"] == now_location) {
                flag=0;
                url = "https://devapi.qweather.com/v7/warning/now?location=" + now_location + "&lang=cn&key=1fbd90fafbd045ed8d7d2ab4ff8697d4";
                $.getJSON(url, function (data) {
                    for (let i in data["warning"]) {
                        i = +i;
                        let warning = document.querySelector(`.warning:nth-child(${i + 1})`);
                        var beforewarning = getComputedStyle(warning, ":before").getPropertyValue('color');
                        ;
                        warning.className = `warning qi-${data["warning"][i]["type"]}`;
                        beforewarning = `${data["warning"][i]["level"]}`;
                        warning.innerHTML = data["warning"][i]["pubTime"] + "   " + data["warning"][i]["text"];
                    }
                    flag++;
                });
            }
            if (flag == 0) {
                document.querySelector(`.warning:nth-child(1)`).innerHTML = "暂无预警";
            }
        }
    })
})();
//气候
(function () {
    let myChart = echarts.init(document.querySelector(".line .chart"));
    let option = {
        //线的颜色
        color: ['#2f89cf'],
        //提示组件
        tooltip: {
            trigger: "axis",
            axisPointer: {
                // 坐标轴指示器，坐标轴触发有效
                type: "shadow" // 默认为直线，可选为：'line' | 'shadow'//影子
            }
        },
        // 图例组件
        legend: {
            // 如果series 对象有name 值，则 legend可以不用写data
            textStyle: {
                color: '#4c9bfd' // 图例文字颜色
            },
            right: "10%"
            // 距离右边10%
            // 这个10% 必须加双引号
        },
        grid: {
            left: "0%",
            top: "6px",
            right: "0%",
            bottom: "4%",
            containLabel: true
        },
        xAxis: {
            type: 'category',
            data: [],
            axisTick: {
                show: false
            },
            axisLabel: {
                color: "rgba(255,255,255,.6)",
                fontSize: "8",
                setInterval: 0,//dat a全部显示
            },
            axisLine: {
                show: false // 去除轴线
            },
            // boundaryGap: true,  // 去除轴内间距
            max: 11,
        },
        yAxis: {
            type: 'value',
            // axisTick: {
            //     show: ture
            // },小横线
            axisLabel: {
                color: "rgba(255,255,255,.6)",
                fontSize: "12"
            },
            splitLine: {
                lineStyle: {
                    color: "rgba(255,255,255,.1)"
                }
            }
        },
        series: [
            {
                name: '',
                type: 'bar',
                // 折线修饰为圆滑
                smooth: true,
                itemStyle: {
                    // 修改柱子圆角
                    barBorderRadius: 2
                },
                barWidth: "80%",
                stack: 'Total',
                data: [],
            }
        ]
    };
    let get_rainfall_data = function () {
        $.getJSON("Weather", function (data) {
            let datax = [];
            let seriesdata = [];
            let key;
            let flag = "line";
            for (let i in data) {
                for (let k in data[i]) {
                    datax.push(data[i]["month"] + "月");
                    flag = "bar";
                    seriesdata.push(data[i]["precipitation"]);
                }
            }
            myChart.setOption({
                xAxis: { data: datax },
                series: { name:"降水量",type: flag, data: seriesdata, }
            })
        });
    }
    let get_temperature_data = function () {
        $.getJSON("Weather", function (data) {
            let datax = [];
            let seriesdata = [];
            let key;
            // let flag = "line";
            let flag = "line";
            for (let i in data) {
                for (let k in data[i]) {
                    console.log(k);
                    console.log(data[i]);
                    datax.push(data[i]["month"] + "月");
                    seriesdata.push(data[i]["temperature"]);
                }
            }
            myChart.setOption({
                xAxis: { data: datax },
                series: { type: flag,name:"气温", data: seriesdata, }
            })
        });
    }
    get_rainfall_data();
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
    // 点击切换数据
    // $("id")得到id
    $(".line h2").on("click", "a", function () {
        if(this.id=="rainfall"){
            get_rainfall_data();
        }else if(this.id=="temperature"){
            get_temperature_data();
        }
    });
})();

//存在病害
//饼图
(function () {
    let myChart = echarts.init(document.querySelector(".pie .chart"));
    let option = {
        color: ['#006cff', '#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff'],
        //提示框组件
        tooltip: {
            trigger: 'item',
            formatter: '{a}<br/>{b}:{c}({d}%)'
        },
        //图例
        legend: {
            //放到底部
            bottom: "0%",
            // 修改小图标的大小
            itemWidth: 10,
            itemHeight: 10,
            left: 'center',
            // 修改图例组件的文字为 12px
            textStyle: {
                color: "rgba(255,255,255,.5)",
                fontSize: "10"
            }
        },
        series: [
            {
                name: '病害存在情况',
                type: 'pie',
                //饼的大小 radius: ['内园半径', '外圆'],
                radius: ['10%', '70%'],
                //圆的半径
                center: ["50%", "50%"],
                avoidLabelOverlap: false,
                //标签文字是否显示
                label: {
                    show: true,
                    // position: 'center'
                },
                //在中间显示属性名
                // emphasis: {
                //   label: {
                //     show: true,
                //     fontSize: 40,
                //     fontWeight: 'bold'
                //   }
                // },
                // 链接文字和图形的线是否显
                labelLine: {
                    // 第一条线
                    length: 6,
                    //第二条线
                    length2: 8,
                },
                data: []
            }
        ]
    };
    myChart.setOption(option);
    $.getJSON("Disease", function (data) {
        let diseases = [];//值
        let values = [];
        let disease = -1;
        let seriesdata = [];
        for (let i = 0; i < data.length; i++) {
            if(data[i].disease===null){
            }
            else if ((disease = diseases.indexOf(data[i].disease)) !== -1) {
                values[disease] += 1;
            }
            else {
                values.push(1);
                diseases.push(data[i].disease);
            }
        };
        for (let i = 0; i < values.length; i++) {
            var sd = {};
            sd["value"] = values[i];
            sd["name"] = diseases[i];
            seriesdata.push(sd);
        }
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                data: seriesdata
            },
            ]
        });
    });
    // 监听浏览器缩放，图表对象调用缩放resize函数
    window.addEventListener("resize", function () {
        myChart.resize();
    });
})();

//土壤健康
//进度条：一个框状包含另一个条状
(function () {
    let myChart = echarts.init(document.querySelector(".soilhealth .chart"));
    let option = {
        grid: {
            top: "10%",
            left: "22%",
            bottom: "10%",
            // containLabel: true
            //如果图表太小
            //if把标签算进图表大小
        },
        xAxis: {
            show: false//不显示x轴
        },
        yAxis: [
            {
                type: 'category',
                inverse: true,//数据显示是否翻转
                data: [],
                //不显示y轴线条
                axisLine: {
                    show: false
                },
                // 不显示刻度
                //axis：轴
                //tick:记号
                axisTick: {
                    show: false
                },
                //把刻度标签的颜色显示为白色
                axisLabel: {
                    color: "#fff"
                },
            }, {
                //设置y轴的第二组对象
                inverse: true,//数据显示是否翻转
                show: true,
                data: [2, 2, 2, 2, 2, 2],
                //不显示y轴线条
                axisLine: {
                    show: false
                },
                // 不显示刻度
                axisTick: {
                    show: false
                },
                //把刻度标签的颜色显示为白色
                axisLabel: {
                    show: false
                },
            }],
        series: [
            {
                name: '条',
                type: 'bar',
                data: [],
                //叠加效果
                yAxisIndex: 0,
                itemStyle: {
                    normal: {
                        //圆角
                        barBorderRadius: 20,
                        //给每个柱子不同的颜色
                        color: function (params) {
                            // params 传进来的是柱子对象
                            // console.log(params);
                            // dataIndex 是当前柱子的索引号
                            return myColor[params.dataIndex];
                        }
                    }
                },
                // 柱子之间的距离
                barCategoryGap: 50,
                //柱子的宽度
                barWidth: 10,
                //(显示柱子上的文字)图形上的文本标签
                //不显示：拼错了
                label: {
                    show: true,
                    //在图形内显示
                    position: "inside",
                    //自动获取y轴的数据
                    formatter: '{c}'
                    //不显示百分比：formatter拼错了！！！！！！！可恶！
                    //a：系列名；b：数据名；c：数据值

                }
            },
            {
                name: '框',
                type: 'bar',
                barCategoryGap: 50,
                barWidth: 15,
                data: [],
                yAxisIndex: 1,
                itemStyle: {
                    color: "none",//变成框→颜色置空
                    borderColor: "#00c1de",
                    borderWidth: 3,
                    barBorderRadius: 15
                },
            }
        ]
    };
    $.getJSON("Contents", function (data) {
        let ydata = [];//y轴坐标标签名
        let seriesdata1 = [];//土壤污染情况
        let myColor=[]
        for (let i = 0; i < data.length; i++) {
            ydata.push(noname + data[i].soilNumber);
            piAs = data[i].asC / 40;
            piCd = data[i].cdC / 0.3;
            piHg = data[i].hgC / 1.3;
            piZn = data[i].znC / 200;
            piCu = data[i].cuC / 50;
            //用公式计算土壤污染状况
            let Pave = (piAs + piCd +piHg+ piCu + piZn ) / 5;
            let Pmax=Math.max(piAs , piCd ,piHg, piCu , piZn );
            let P=((Pave**2+Pmax**2)/2)**0.5;
            P = +(P.toFixed(2));
            seriesdata1.push(P);
            if(P<0.6){
                myColor.push("#56D0E3");
            }else if(P<1){
                myColor.push("#F8B448");
            }else{
                myColor.push("#F57474");
            }
        }
        myChart.setOption({
            xAxis: {},
            yAxis: [{ data: ydata }, {}],
            series: [{
                data: seriesdata1,
                itemStyle: {
                    normal: {
                        color: function (params) {
                            // params 传进来的是柱子对象
                            // console.log(params);
                            // dataIndex 是当前柱子的索引号
                            return myColor[params.dataIndex];
                        }
                    }
                }, }, { data: [1,1,1,1,1,1] }]
            // 100, 100, 100, 100, 100, 100
        })
    });
    myChart.setOption(option);
    window.addEventListener("resize", function () {
        myChart.resize();
    });
})();


//土壤水分
//柱状图
(function () {
    let myChart = echarts.init(document.querySelector(".line2 .chart"));
    //空格！！
    let option = {
        color: ["#00f2f1"],
        tooltip: {
            trigger: "axis",
            axisPointer: {
                // 坐标轴指示器，坐标轴触发有效
                type: "shadow" // 默认为直线，可选为：'line' | 'shadow'//影子
            }
        },
        grid: {
            left: "0%",
            top: "6px",
            right: "0%",
            bottom: "4%",
            containLabel: true,
        },
        xAxis: [
            {
                type: "category",
                data: [],
                axisTick: {
                    show:false
                },
                axisLabel: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: "8",
                    setInterval: 0,//dat a全部显示
                },
                axisLine: {
                    show: false
                }

            }
        ],
        yAxis: [
            {
                type: "value",
                axisLabel: {
                    color: "rgba(255,255,255,.6)",
                    fontSize: "12"
                },
                splitLine: {
                    lineStyle: {
                        color: "rgba(255,255,255,.1)"
                    }
                }
            }
        ],
        series: [
            {
                name: "土壤水分",
                type: "bar",
                barWidth: "35%",
                data: [],
                itemStyle: {
                    // 修改柱子圆角
                    barBorderRadius: 5
                },
                label: {
                    color:"rgb(255,255,255)",
                    show: true,
                    //在图形内显示
                    position: "inside",
                    //自动获取y轴的数据
                    formattter: "{c}%"
                    //a：系列名；b：数据名；c：数据值

                }
            }
        ]
    };
    myChart.setOption(option);
    $.getJSON("Soilmoisture", function (data) {
        let xdata = [];//y轴坐标标签名
        let seriesdata1 = [];//土壤健康值
        for (let i = 0; i < data.length; i++) {
            xdata.push(noname + data[i].soilNumber);
            seriesdata1.push(data[i].soilMoisture)
        };
        myChart.setOption({
            xAxis: [{ data: xdata }, {}],
            series: [{ data: seriesdata1 }]
        })
    });
    //   让图表跟随屏幕自适应
    window.addEventListener("resize", function () {
        myChart.resize();
    });
})();

//虫害
//饼图
(function () {
    let myChart = echarts.init(document.querySelector(".pie2 .chart"));
    var option = {
        color: ['#006cff', '#60cda0', '#ed8884', '#ff9f7f', '#0096ff', '#9fe6b8', '#32c5e9', '#1d9dff'],
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            bottom: "0%",
            itemWidth: 10,
            itemHeight: 10,
            textStyle: {
                color: "rgba(225,225,225,.6)",
                fontSize: "8"
            }
        },
        series: [
            {
                name: '虫害存在情况',
                type: 'pie',
                radius: ['10%', '70%'],
                center: ['50%', '40%'],
                // 把饼形图的显示模式改为半径模式
                roseType: 'radius',
                itemStyle: {
                    borderRadius: 5
                },
                data: [],
                //显示标签文字
                label: {
                    fontSize: 10,
                    show: true,
                },
                //标签链接线
                labelLine: {
                    //第一条线
                    length: 6,
                    //第二条线
                    length2: 8,
                },
                emphasis: {
                    label: {
                        show: true
                    }
                },
            }
        ]
    };
    myChart.setOption(option);
    $.getJSON("Disease", function (data) {
        let diseases = [];//值
        let values = [];
        let disease = -1;
        let seriesdata = [];
        for (let i = 0; i < data.length; i++) {
            if(data[i].pest===null){
            }
            else if ((disease = diseases.indexOf(data[i].pest)) !== -1) {
                values[disease] += 1;
            } else {
                values.push(1);
                diseases.push(data[i].pest);
            }
        };
        for (let i = 0; i < values.length; i++) {
            let sd = {};
            sd["value"] = values[i];
            sd["name"] = diseases[i];
            seriesdata.push(sd);
        }
        myChart.setOption({
            series: [{
                // 根据名字对应到相应的系列
                data: seriesdata
            },
            ]
        });
    });
    window.addEventListener("resize", function () {
        myChart.resize();
    });
})();

